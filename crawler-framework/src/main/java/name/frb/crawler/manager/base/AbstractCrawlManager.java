package name.frb.crawler.manager.base;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.crawler.bean.CrawlFinished;
import name.frb.crawler.bean.CrawlStatus;
import name.frb.crawler.config.CrawlConfigReader;
import name.frb.crawler.manager.CrawlManager;
import name.frb.crawler.thread.CaptureTask;
import name.frb.crawler.thread.ConcurrentTaskExecutor;
import name.frb.crawler.thread.ParseTask;
import name.frb.crawler.worker.CrawlWorker;
import org.slf4j.Logger;
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
        XmlConfiguration crawlConfig = getCrawlConfig();
        CrawlConfigReader crawlConfigReader = new CrawlConfigReader(crawlConfig);
        CrawlStatus crawlStatus = crawlConfigReader.readCrawlConfig(getClass().getName());
        if (crawlStatus == null) {
            return;
        }
        setCrawlStatus(crawlStatus);


        //2. save the status of the job into database
        crawlStatus.setStatus(CrawlStatus.STATUS.READY.getValue());
        saveCrawlStatus(crawlStatus.getStatus());

        //3rd. Check depending whether it finished
        Integer dependOn = crawlStatus.getDenpendOn();
        if (dependOn > 0 && dependOn != null) {
            // get this task's dependent status
            Query query = new Query(Criteria.where("denpendOn").is(crawlStatus.getDenpendOn() - 1));
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

        //4th. obtain toodo url
        CrawlWorker worker = getCrawlWorker();
        boolean success = worker.obtainTodoUrl();
        if (!success) {
            getLOGGER().error("Failed to obtain todo url");

            return;
        }

        //4. drop current class from mongodb
        dropHistoryData();

        //5. put tasks(crawl and parse) into thread pool
        ConcurrentTaskExecutor executor = new ConcurrentTaskExecutor(crawlStatus.getThreadPoolSize(), crawlStatus.getMaxQueueSize());
        CrawlFinished crawlFinished = new CrawlFinished(false);
        for (int i = 1; i <= crawlStatus.getMaxQueueSize(); i++) {
            executor.execute(new CaptureTask(crawlFinished, worker));
        }

        for (int i = 1; i <= crawlStatus.getMaxQueueSize(); i++) {
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

    /**
     * Save crawl state
     *
     * @param status
     */
    private void saveCrawlStatus(int status) {
        CrawlStatus crawlStatus = getCrawlStatus();
        crawlStatus.setStatus(status);
        Query query = new Query(Criteria.where("crawlId").is(crawlStatus.getCrawlId()));
        CrawlStatus savedStatus = getMongoTemplate().findOne(query, CrawlStatus.class);
        if (savedStatus != null) {
            String _id = savedStatus.get_id();
            crawlStatus.set_id(_id);

            getMongoTemplate().save(crawlStatus);
        } else {
            getMongoTemplate().insert(crawlStatus);
        }

        getLOGGER().info("Saved crawl status. {}", crawlStatus.toString());

        return;
    }

    public abstract CrawlWorker getCrawlWorker();

    public abstract XmlConfiguration getCrawlConfig();

    public abstract void dropHistoryData();

    public abstract MongoTemplate getMongoTemplate();

    public abstract CrawlStatus getCrawlStatus();

    public abstract void setCrawlStatus(CrawlStatus crawlStatus);

    public abstract Logger getLOGGER();
}