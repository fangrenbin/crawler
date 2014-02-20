/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 9, 2014
 * File Name      : HuJiangConfig.java
 */

package name.frb.crawler.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * HuJiangConfig
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 9, 2014
 */
public class CrawlConfig {
    private Integer id;

    private Integer threadPoolSize;

    private Integer maxQueueSize;

    private List<String> seedList;

    private Integer depend;

    public CrawlConfig() {
    }

    public CrawlConfig(Integer threadPoolSize, Integer maxQueueSize, List<String> seedList, Integer depend) {
        this.threadPoolSize = threadPoolSize;
        this.maxQueueSize = maxQueueSize;
        this.seedList = seedList;
        this.depend = depend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public Integer getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(Integer maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public List<String> getSeedList() {
        return seedList;
    }

    public void setSeedList(List<String> seedList) {
        this.seedList = seedList;
    }

    public Integer getDepend() {
        return depend;
    }

    public void setDepend(Integer depend) {
        this.depend = depend;
    }

    @Override
    public String toString() {
        return "CrawlConfig{" +
                "id=" + id +
                ", threadPoolSize=" + threadPoolSize +
                ", maxQueueSize=" + maxQueueSize +
                ", seedList=" + seedList +
                ", depend=" + depend +
                '}';
    }
}