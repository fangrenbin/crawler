/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 20, 2014
 * File Name      : WebpageContainer.java
 */

package name.frb.crawler.container.webpage;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import name.frb.crawler.bean.Webpage;
import name.frb.crawler.container.base.AbstractContainer;

/**
 * WebpageContainer
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 20, 2014
 */
public class WebpageContainer extends AbstractContainer<Webpage> {
    /** Serial version UID */
    private Queue<Webpage> webpageQueue = new LinkedBlockingQueue<Webpage>();

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public synchronized boolean empty() {
        return webpageQueue.isEmpty();
    }

    @Override
    public synchronized boolean add(Webpage url) {
        return webpageQueue.offer(url);
    }

    @Override
    public Webpage shift() {
        return empty() ? null : webpageQueue.poll();
    }

    @Override
    public boolean contains(Webpage t) {
        throw new UnsupportedOperationException(getClass().getName() + ":contains()");
    }

    @Override
    public int size() {
        return webpageQueue.size();
    }

    @Override
    protected void init() {
        // TODO Auto-generated method stub
    }
}
