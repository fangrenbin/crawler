package name.frb.crawler.config;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.crawler.AbstractTestng;
import name.frb.crawler.bean.CrawlStatus;
import name.frb.crawler.hj.manager.NceCrawlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class CrawlConfigReaderTest extends AbstractTestng {
    @Autowired
    private XmlConfiguration crawlConfig;

    @Test
    public void readerTest() {
        CrawlConfigReader crawlConfigReader = new CrawlConfigReader(crawlConfig);
        CrawlStatus crawlStatus = crawlConfigReader.readCrawlConfig(NceCrawlManager.class.getName());

        Assert.assertEquals(crawlStatus.getCrawlId(), 1);
        Assert.assertEquals(crawlStatus.getDenpendOn(), 0);
        Assert.assertEquals(crawlStatus.getClassName(), "NceCrawlManager");
        Assert.assertEquals(crawlStatus.getThreadPoolSize(), 30);
        Assert.assertEquals(crawlStatus.getMaxQueueSize(), 15);

        Set<String> seedSet = crawlStatus.getSeedUrls();
        for (String seed : seedSet) {
            Assert.assertEquals(seed, "http://www.hjenglish.com/nce/xingainian2/");
        }
    }
}
