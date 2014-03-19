package name.frb.crawler.hj.job.impl;

import name.frb.crawler.hj.job.CrawlJob;
import name.frb.crawler.manager.CrawlManager;

/**
 * CrawlJobImpl
 * <p/>
 *
 * @author : renbin.fan g
 * @date : Jan 24, 2014
 */
public class CrawlJobImpl implements CrawlJob {
    private CrawlManager nceCrawlManager;
    private CrawlManager nceMp3CrawlManager;


    @Override
    public void nceCrawlerJob() {
        nceCrawlManager.crawl();
    }

    @Override
    public void nceMp3CrawlManager() {
        nceMp3CrawlManager.crawl();
    }

    public void setNceCrawlManager(CrawlManager nceCrawlManager) {
        this.nceCrawlManager = nceCrawlManager;
    }

    public void setNceMp3CrawlManager(CrawlManager nceMp3CrawlManager) {
        this.nceMp3CrawlManager = nceMp3CrawlManager;
    }
}
