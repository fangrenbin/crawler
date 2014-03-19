/*
* Create Author  : renbin.fang
* Create Date    : Jan 23, 2014
* File Name      : NceMp3CrawlManagerTest.java
*/

package name.frb.crawler.manager;

import name.frb.crawler.AbstractTestng;
import name.frb.crawler.hj.manager.NceMp3CrawlManager;
import name.frb.crawler.model.hujiang.NceMp3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * NceMp3CrawlManagerTest
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 23, 2014
 */
public class NceMp3CrawlManagerTest extends AbstractTestng {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    NceMp3CrawlManager nceMp3CrawlManager;

    @Test
    public void crawlTest() {
//        nceMp3CrawlManager.crawl();
    }

    @AfterMethod
    @BeforeClass
    protected void destroy() {
//        mongoTemplate.dropCollection(NceMp3.class);
    }
}
