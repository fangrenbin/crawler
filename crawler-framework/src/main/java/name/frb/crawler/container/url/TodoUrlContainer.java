/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 20, 2014
 * File Name      : ToduUrlContainer.java
 */

package name.frb.crawler.container.url;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import name.frb.crawler.container.base.AbstractContainer;

/**
 * To do url container
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 20, 2014
 */
public class TodoUrlContainer extends AbstractContainer<String> {
    private Queue<String> todoUrlQueue = new LinkedBlockingDeque<String>();

    @Override
    public void destroy() {

    }

    @Override
    public synchronized boolean empty() {
        return todoUrlQueue.isEmpty();
    }

    @Override
    public synchronized boolean add(String url) {
        return todoUrlQueue.offer(url);
    }

    @Override
    public synchronized String shift() {
        return empty() ? null : todoUrlQueue.poll();
    }

    @Override
    public boolean contains(String t) {
        return todoUrlQueue.contains(t);
    }

    @Override
    public int size() {
        return todoUrlQueue.size();
    }

    @Override
    protected void init() {
        // TODO Auto-generated method stub
    }
}