package name.frb.crawler.config;

import name.frb.configuration.xmlconfiguration.XmlConfiguration;
import name.frb.crawler.bean.CrawlStatus;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * CrawlConfigReaderz
 */
public class CrawlConfigReader {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private XmlConfiguration crawlConfig;

    /**
     * Constructor
     *
     * @param crawlConfig
     */
    public CrawlConfigReader(XmlConfiguration crawlConfig) {
        this.crawlConfig = crawlConfig;
    }

    public CrawlStatus readCrawlConfig(String className) {
        List<?> configList = crawlConfig.getXmlConfig().configurationsAt("Crawler.Targets.Target");

        if (CollectionUtils.isEmpty(configList)) {
            LOGGER.error("None of targets can be found in {}", crawlConfig.getXmlConfigFilePath());

            return null;
        }

        String currentClazz = StringUtils.substringAfterLast(className, ".");
        for (int i = 0; i < configList.size(); i++) {
            boolean runable = crawlConfig.getBoolean("Crawler.Targets.Target(" + i + ").Runable");
            String clazz = crawlConfig.getString("Crawler.Targets.Target(" + i + ").ClassName");

            if (!runable || !StringUtils.equals(currentClazz, clazz)) {
                continue;
            }

            CrawlStatus crawlStatus = new CrawlStatus();
            crawlStatus.setCrawlId(crawlConfig.getIntger("Crawler.Targets.Target(" + i + ").Id"));
            crawlStatus.setClassName(crawlConfig.getString("Crawler.Targets.Target(" + i + ").ClassName"));
            crawlStatus.setDenpendOn(crawlConfig.getIntger("Crawler.Targets.Target(" + i + ").Depend"));
            crawlStatus.setMaxQueueSize(crawlConfig.getIntger("Crawler.Targets.Target(" + i + ").MaxQueueSize"));
            crawlStatus.setThreadPoolSize(crawlConfig.getIntger("Crawler.Targets.Target(" + i + ").ThreadPoolSize"));

            List<?> seedsList = crawlConfig.getXmlConfig().configurationsAt("Crawler.Targets.Target(" + i + ").SeedUrls.SeedUrl");
            if (CollectionUtils.isNotEmpty(seedsList)) {
                Set<String> seedsSet = crawlStatus.getSeedUrls();
                for (int j = 0; j < seedsList.size(); j++) {
                    seedsSet.add(crawlConfig.getString("Crawler.Targets.Target(" + i + ").SeedUrls.SeedUrl(" + j + ")"));
                }
            }

            return crawlStatus;
        }

        return null;
    }

    public void setCrawlConfig(XmlConfiguration crawlConfig) {
        this.crawlConfig = crawlConfig;
    }
}
