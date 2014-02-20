/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 10, 2014
 * File Name      : HuJiangCrawlManager.java
 */

package name.frb.crawler.manager.hj;

import name.frb.crawler.bean.CrawlConfig;
import name.frb.crawler.manager.base.AbstractCrawlManager;
import name.frb.crawler.model.hujiang.NcEnglish;
import name.frb.crawler.worker.CrawlWorker;
import name.frb.crawler.worker.hj.NceCrawlWorker;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * NceCrawlManager
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 10, 2014
 */
public class NceCrawlManager extends AbstractCrawlManager {
    /**
     * link  MongoTemplate
     */
    private MongoTemplate mongoTemplate;

    CrawlConfig config = new CrawlConfig();

    {
        List<String> seedList = new ArrayList<String>();
        seedList.add("http://www.hjenglish.com/nce/xingainian2/");
        config.setSeedList(seedList);
        config.setDepend(1);
        config.setThreadPoolSize(30);
        config.setMaxQueueSize(15);
    }

    @Override
    public CrawlWorker getCrawlWorker() {
        return new NceCrawlWorker(mongoTemplate, config);
    }

    @Override
    public CrawlConfig getCrawlConfig() {
        return config;
    }

    @Override
    public String getClassName() {
        return "NceCrawlManager";
    }

    @Override
    public void dropHistoryData() {
        mongoTemplate.dropCollection(NcEnglish.class);
    }

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * setter method
     *
     * @param mongoTemplate the mongoTemplate to set
     * @see NceCrawlManager#mongoTemplate
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}