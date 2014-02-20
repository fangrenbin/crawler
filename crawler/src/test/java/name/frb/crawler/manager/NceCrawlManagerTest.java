/*
* Create Author  : renbin.fang
* Create Date    : Jan 23, 2014
* File Name      : NceCrawlWorkerTest.java
*/

package name.frb.crawler.manager;

import name.frb.crawler.AbstractTestng;
import name.frb.crawler.bean.CrawlStatus;
import name.frb.crawler.manager.hj.job.CrawlJob;
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
    CrawlJob hjCrawlJob;

//    @Test
    public void crawlManagerTest() {
        System.out.println("test done!");
    }

    @AfterMethod
    @BeforeClass
    protected void destroy() {
//        mongoTemplate.dropCollection(NceMp3.class);
//        mongoTemplate.dropCollection(CrawlStatus.class);
//        mongoTemplate.dropCollection(NcEnglish.class);
    }
}