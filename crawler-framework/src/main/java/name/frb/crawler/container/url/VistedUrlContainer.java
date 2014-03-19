/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 20, 2014
 * File Name      : VistedUrlContainer.java
 */

package name.frb.crawler.container.url;

import java.util.HashSet;
import java.util.Set;

import name.frb.crawler.container.base.AbstractContainer;

/**
 * Visted url container
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 20, 2014
 */
public class VistedUrlContainer extends AbstractContainer<String> {
    private Set<String> vistedUrlSet = new HashSet<String>();

    @Override
    public void destroy() {
    }

    @Override
    public synchronized boolean empty() {
        return vistedUrlSet.isEmpty();
    }

    @Override
    public synchronized boolean add(String url) {
        return vistedUrlSet.add(url);
    }

    @Override
    public String shift() {
        throw new UnsupportedOperationException(getClass().getName() + ":findAndRemove()");
    }

    @Override
    public synchronized boolean contains(String url) {
        return vistedUrlSet.contains(url);
    }

    @Override
    public int size() {
        return vistedUrlSet.size();
    }

    @Override
    protected void init() {
        readToduUrlFromDb();
    }

    private void readToduUrlFromDb() {

    }
}