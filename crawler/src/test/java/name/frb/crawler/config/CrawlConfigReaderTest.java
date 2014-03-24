package name.frb.crawler.config;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.crawler.AbstractTestng;
import name.frb.crawler.bean.CrawlStatus;
import name.frb.crawler.hj.manager.NceCrawlManager;
import org.apache.commons.lang.StringUtils;
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
            if (StringUtils.contains(seed, "xingainian1")) {
                Assert.assertEquals(seed, "http://www.hjenglish.com/nce/xingainian1/");
            } else if (StringUtils.contains(seed, "xingainian2")) {
                Assert.assertEquals(seed, "http://www.hjenglish.com/nce/xingainian2/");
            } else if (StringUtils.contains(seed, "xingainian3")) {
                Assert.assertEquals(seed, "http://www.hjenglish.com/nce/xingainian3/");
            } else if (StringUtils.contains(seed, "xingainian4")) {
                Assert.assertEquals(seed, "http://www.hjenglish.com/nce/xingainian4/");
            }
        }
    }
}
