/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 14, 2014
 * File Name      : CaptureTask.java
 */

package name.frb.crawler.thread;

import name.frb.crawler.bean.CrawlFinished;
import name.frb.crawler.worker.CrawlWorker;

/**
 * Capture task
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 14, 2014
 */
public class CaptureTask implements Runnable {
    private CrawlFinished crawlFinished;

    private final long CYCLE_PERIOD = 1000L;

    private CrawlWorker worker;

    /**
     * Constructor
     *
     * @param crawlFinished
     * @param worker
     */
    public CaptureTask(CrawlFinished crawlFinished, CrawlWorker worker) {
        super();
        this.crawlFinished = crawlFinished;
        this.worker = worker;
    }

    @Override
    public void run() {
        while (!crawlFinished.isFinished()) {
            try {
                Thread.sleep(CYCLE_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();

                continue;
            }

            worker.capture();
        }
    }
}