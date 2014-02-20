/*
* Create Author  : renbin.fang
* Create Date    : Jan 23, 2014
* File Name      : NceMp3CrawlManagerTest.java
*/

package name.frb.crawler.manager;

import name.frb.crawler.AbstractTestng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

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

    //    @Test
    public void crawl() {
    }

    @AfterMethod
    @BeforeClass
    protected void destroy() {
        // mongoTemplate.dropCollection(NewConceptEnglish.class);
    }
}
