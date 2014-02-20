package name.frb.crawler.manager.hj.job;

import name.frb.crawler.manager.CrawlManager;

/**
 * CrawlJob
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 24, 2014
 */
public class CrawlJob {
    private CrawlManager nceCrawlManager;

    private CrawlManager nceMp3CrawlManager;

    public void init() {
        nceCrawlManager.crawl();
        nceMp3CrawlManager.crawl();
    }

    public void setNceCrawlManager(CrawlManager nceCrawlManager) {
        this.nceCrawlManager = nceCrawlManager;
    }

    public void setNceMp3CrawlManager(CrawlManager nceMp3CrawlManager) {
        this.nceMp3CrawlManager = nceMp3CrawlManager;
    }
}
