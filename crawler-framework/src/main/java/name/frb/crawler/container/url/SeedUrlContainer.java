/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 20, 2014
 * File Name      : SeedUrlContainer.java
 */

package name.frb.crawler.container.url;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import name.frb.crawler.container.base.AbstractContainer;

/**
 * SeedUrlContainer
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 20, 2014
 */
public class SeedUrlContainer extends AbstractContainer<String> {
    private Queue<String> seedUrlQueue = new LinkedBlockingQueue<String>();

    private Set<String> seedSet;

    @Override
    public void destroy() {
    }

    @Override
    public synchronized boolean empty() {
        return seedSet.isEmpty();
    }

    @Override
    public boolean add(String url) {
        return seedUrlQueue.offer(url);
    }

    @Override
    public synchronized String shift() {
        return null;
    }

    @Override
    public synchronized boolean contains(String t) {
        return false;
    }

    @Override
    public int size() {
        return seedSet.size();
    }

    @Override
    protected void init() {
        readDataFromConfig();
    }

    private void readDataFromConfig() {

    }
}