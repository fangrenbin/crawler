/*
* Create Author  : renbin.fang
* Create Date    : Jan 23, 2014
* File Name      : NceCrawlWorkerTest.java
*/

package name.frb.crawler.manager;

import name.frb.crawler.AbstractTestng;
import name.frb.crawler.bean.CrawlStatus;
import name.frb.crawler.hj.job.impl.CrawlJobImpl;
import name.frb.crawler.hj.manager.NceCrawlManager;
import name.frb.crawler.model.hujiang.NcEnglish;
import name.frb.crawler.model.hujiang.NceMp3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NceCrawlManagerTest
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 23, 2014
 */
public class NceCrawlManagerTest extends AbstractTestng {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    NceCrawlManager nceCrawlManager;

    @Test
    public void crawlTest() {
//        nceCrawlManager.crawl();
    }

    @AfterMethod
    @BeforeClass
    protected void destroy() {
//        mongoTemplate.dropCollection(NcEnglish.class);
//        mongoTemplate.dropCollection(CrawlStatus.class);
    }
}