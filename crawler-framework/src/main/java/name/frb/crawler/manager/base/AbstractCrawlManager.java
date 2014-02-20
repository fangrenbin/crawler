package name.frb.crawler.manager.base;

import name.frb.crawler.bean.CrawlConfig;
import name.frb.crawler.bean.CrawlFinished;
import name.frb.crawler.bean.CrawlStatus;
import name.frb.crawler.manager.CrawlManager;
import name.frb.crawler.thread.CaptureTask;
import name.frb.crawler.thread.ConcurrentTaskExecutor;
import name.frb.crawler.thread.ParseTask;
import name.frb.crawler.worker.CrawlWorker;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * AbstractCrawlManager
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 14, 2014
 */
public abstract class AbstractCrawlManager implements CrawlManager {
    @Override
    public void crawl() {
        //1. retrieve configuration
        CrawlConfig config = getCrawlConfig();


        //2. save the status of the job into database
        Integer dependOn = config.getDepend();
        CrawlStatus crawlStatus = new CrawlStatus(dependOn, CrawlStatus.STATUS.READY.getValue(), getClassName());
        getMongoTemplate().insert(crawlStatus);


        //3rd. Check depending whether it finished
        Query query = new Query(Criteria.where("denpendOn").is(dependOn - 1));
        if (dependOn > 0 && dependOn != 1) {
            while (true) {
                CrawlStatus status = getMongoTemplate().findOne(query, CrawlStatus.class);

                if (status.getStatus() == CrawlStatus.STATUS.FINISHED.getValue()) {
                    break;
                }

                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    continue;
                }
            }
        }

        //4th. obtain todo url
        CrawlWorker worker = getCrawlWorker();
        boolean success = worker.obtainTodoUrl();
        if (!success) {
            System.out.println("Failed to obtain todo url");

            return;
        } else {
            System.out.println("Success to obtain todo url");
        }

        //4. drop current class from mongodb
        dropHistoryData();

        //5. put tasks(crawl and parse) into thread pool
        ConcurrentTaskExecutor executor = new ConcurrentTaskExecutor(config.getThreadPoolSize(), config.getMaxQueueSize());
        CrawlFinished crawlFinished = new CrawlFinished(false);
        for (int i = 1; i <= config.getMaxQueueSize(); i++) {
            executor.execute(new CaptureTask(crawlFinished, worker));
        }

        for (int i = 1; i <= config.getMaxQueueSize(); i++) {
            executor.execute(new ParseTask(crawlFinished, worker));
        }

        //6. while(does finished)
        while (true) {
            boolean doesItDone = worker.doesCrawlFinish();
            if (!doesItDone) {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    continue;
                }

                continue;
            }

            //change crawl status
            Query statusQuery = new Query(Criteria.where("denpendOn").is(dependOn));
            getMongoTemplate().updateMulti(statusQuery, Update.update("status", CrawlStatus.STATUS.FINISHED.getValue()), CrawlStatus.class);

            break;
        }

    }

    public abstract CrawlWorker getCrawlWorker();

    public abstract CrawlConfig getCrawlConfig();

    public abstract String getClassName();

    public abstract void dropHistoryData();

    public abstract MongoTemplate getMongoTemplate();
}