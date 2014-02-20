package name.frb.crawler.bean;

/**
 * CrawlStatus
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 24, 2014
 */
public class CrawlStatus {
    private Integer status;
    private String className;
    private Integer denpendOn;

    /**
     * Constructor
     */
    public CrawlStatus() {
    }

    /**
     * Constructor
     *
     * @param denpendOn depend on which job
     * @param status    crawl job status
     * @param className class name
     */
    public CrawlStatus(Integer denpendOn, Integer status, String className) {
        this.denpendOn = denpendOn;
        this.status = status;
        this.className = className;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getDenpendOn() {
        return denpendOn;
    }

    public void setDenpendOn(Integer denpendOn) {
        this.denpendOn = denpendOn;
    }

    @Override
    public String toString() {
        return "CrawlStatus{" +
                "status=" + status +
                ", className='" + className + '\'' +
                ", denpendOn=" + denpendOn +
                '}';
    }
}
