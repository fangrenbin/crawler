/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 14, 2014
 * File Name      : ParseTask.java
 */

package name.frb.crawler.thread;

import name.frb.crawler.bean.CrawlFinished;
import name.frb.crawler.worker.CrawlWorker;

/**
 * ParseTask
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 14, 2014
 */
public class ParseTask implements Runnable {
    /** {@link CrawlFinished} */
    private CrawlFinished crawlFinished;

    /** crawl cycle period */
    private final long CYCLE_PERIOD = 1000L;

    /** {@link CrawlWorker} */
    private CrawlWorker worker;

    /**
     * @param crawlFinished
     * @param worker
     */
    public ParseTask(CrawlFinished crawlFinished, CrawlWorker worker) {
        super();
        this.crawlFinished = crawlFinished;
        this.worker = worker;
    }

    @Override
    public void run() {
        while (!crawlFinished.isFinished()) {
            try {
                Thread.sleep(CYCLE_PERIOD);
            }
            catch (InterruptedException e) {
                e.printStackTrace();

                continue;
            }

            worker.parse();
        }
    }
}
