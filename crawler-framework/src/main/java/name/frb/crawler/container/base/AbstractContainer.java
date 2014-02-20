/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 20, 2014
 * File Name      : AbstractContainer.java
 */

package name.frb.crawler.container.base;

import name.frb.crawler.container.Container;

/**
 * AbstractContainer
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 20, 2014
 */
public abstract class AbstractContainer<T> implements Container<T> {
    /**
     * Container initialization method.
     * <p>
     * Author renbin.fang, Jan 20, 2014
     */
    protected abstract void init();
}
