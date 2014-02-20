/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 14, 2014
 * File Name      : CrawlFinished.java
 */

package name.frb.crawler.bean;

/**
 * CrawlFinished
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 14, 2014
 */
public class CrawlFinished {
    private boolean finished;

    /**
     * Constructor
     */
    public CrawlFinished() {
        super();
    }

    /**
     * Constructor
     *
     * @param finished
     */
    public CrawlFinished(boolean finished) {
        super();
        this.finished = finished;
    }

    /**
     * getter method
     *
     * @return the finished
     * @see CrawlFinished#finished
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * setter method
     *
     * @param finished the finished to set
     * @see CrawlFinished#finished
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}