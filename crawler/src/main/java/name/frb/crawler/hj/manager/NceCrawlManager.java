/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 10, 2014
 * File Name      : HuJiangCrawlManager.java
 */

package name.frb.crawler.hj.manager;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.crawler.bean.CrawlStatus;
import name.frb.crawler.manager.base.AbstractCrawlManager;
import name.frb.crawler.model.hujiang.NcEnglish;
import name.frb.crawler.worker.CrawlWorker;
import name.frb.crawler.hj.worker.NceCrawlWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * NceCrawlManager
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 10, 2014
 */
public class NceCrawlManager extends AbstractCrawlManager {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private MongoTemplate mongoTemplate;
    private XmlConfiguration crawlConfig;
    private CrawlStatus crawlStatus;

    @Override
    public CrawlWorker getCrawlWorker() {
        return new NceCrawlWorker(mongoTemplate, crawlStatus);
    }

    @Override
    public XmlConfiguration getCrawlConfig() {
        return crawlConfig;
    }

    @Override
    public void dropHistoryData() {
        mongoTemplate.dropCollection(NcEnglish.class);
    }

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Override
    public CrawlStatus getCrawlStatus() {
        return crawlStatus;
    }

    @Override
    public void setCrawlStatus(CrawlStatus crawlStatus) {
        this.crawlStatus = crawlStatus;
    }

    @Override
    public Logger getLOGGER() {
        return LOGGER;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setCrawlConfig(XmlConfiguration crawlConfig) {
        this.crawlConfig = crawlConfig;
    }
}