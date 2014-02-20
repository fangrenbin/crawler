/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 23, 2014
 * File Name      : NceMp3CrawlManager.java
 */
package name.frb.crawler.manager.hj;

import name.frb.crawler.bean.CrawlConfig;
import name.frb.crawler.manager.base.AbstractCrawlManager;
import name.frb.crawler.model.hujiang.NceMp3;
import name.frb.crawler.worker.CrawlWorker;
import name.frb.crawler.worker.hj.NceMp3CrawlWorker;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * NceMp3CrawlManager
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 23, 2014
 */
public class NceMp3CrawlManager extends AbstractCrawlManager {
    /**
     * link  MongoTemplate
     */
    private MongoTemplate mongoTemplate;

    CrawlConfig config = new CrawlConfig();

    {
        config.setDepend(2);
        config.setThreadPoolSize(30);
        config.setMaxQueueSize(15);
    }

    @Override
    public CrawlWorker getCrawlWorker() {
        return new NceMp3CrawlWorker(mongoTemplate);
    }

    @Override
    public CrawlConfig getCrawlConfig() {
        return config;
    }

    @Override
    public String getClassName() {
        return "NceMp3CrawlManager";
    }

    @Override
    public void dropHistoryData() {
        mongoTemplate.dropCollection(NceMp3.class);
    }

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}