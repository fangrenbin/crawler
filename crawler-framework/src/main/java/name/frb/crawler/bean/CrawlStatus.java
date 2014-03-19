package name.frb.crawler.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * CrawlStatus
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 24, 2014
 */
public class CrawlStatus {
    private String _id;
    private int crawlId;
    private int status;
    private String className;
    private int denpendOn;
    private int threadPoolSize;
    private int maxQueueSize;
    private Set<String> seedUrls = new HashSet<String>();

    /**
     * Constructor
     */
    public CrawlStatus() {
    }

    /**
     * Constructor
     *
     * @param crawlId
     * @param seedUrls
     * @param status
     * @param className
     * @param denpendOn
     * @param threadPoolSize
     * @param maxQueueSize
     */
    public CrawlStatus(String _id, int crawlId, Set<String> seedUrls, int status, String className, int denpendOn, int threadPoolSize, int maxQueueSize) {
        this._id = _id;
        this.crawlId = crawlId;
        this.seedUrls = seedUrls;
        this.status = status;
        this.className = className;
        this.denpendOn = denpendOn;
        this.threadPoolSize = threadPoolSize;
        this.maxQueueSize = maxQueueSize;
    }

    /**
     * Crawl job status enum
     */
    public enum STATUS {
        STARTED(1),

        CRAWLING(2),

        FINISHED(3),

        READY(4);

        private STATUS(int value) {
            this.value = value;
        }

        private int value;

        public int getValue() {
            return value;
        }
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getCrawlId() {
        return crawlId;
    }

    public void setCrawlId(int crawlId) {
        this.crawlId = crawlId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDenpendOn() {
        return denpendOn;
    }

    public void setDenpendOn(int denpendOn) {
        this.denpendOn = denpendOn;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public Set<String> getSeedUrls() {
        return seedUrls;
    }

    public void setSeedUrls(Set<String> seedUrls) {
        this.seedUrls = seedUrls;
    }

    @Override
    public String toString() {
        return "CrawlStatus{" +
                "_id='" + _id + '\'' +
                ", crawlId=" + crawlId +
                ", status=" + status +
                ", className='" + className + '\'' +
                ", denpendOn=" + denpendOn +
                ", threadPoolSize=" + threadPoolSize +
                ", maxQueueSize=" + maxQueueSize +
                ", seedUrls=" + seedUrls +
                '}';
    }
}
