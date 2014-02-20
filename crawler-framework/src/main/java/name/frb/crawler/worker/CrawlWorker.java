/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 8, 2014
 * File Name      : CrawlWorker.java
 */

package name.frb.crawler.worker;

/**
 * CrawlWorker
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 8, 2014
 */
public interface CrawlWorker {
    /**
     * capture the contents of web page
     * <p>
     * Author renbin.fang, Jan 8, 2014
     * 
     * @return
     */
    boolean capture();

    /**
     * Parse the html contents
     * <p>
     * Author renbin.fang, Jan 8, 2014
     * 
     * @return
     */
    boolean parse();

    /**
     * Obtain url which is need to do
     * <p>
     * Author renbin.fang, Jan 9, 2014
     * 
     * @return
     */
    boolean obtainTodoUrl();

    /**
     * Whether all of crawl jobs finished
     * <p>
     * Author renbin.fang, Jan 9, 2014
     * 
     * @return
     */
    boolean doesCrawlFinish();
}