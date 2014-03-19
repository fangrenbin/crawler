/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 20, 2014
 * File Name      : Container.java
 */

package name.frb.crawler.container;

/**
 * Container interface
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 20, 2014
 */
public interface Container<T> {
    /**
     * <p>
     * Author renbin.fang, Jan 20, 2014
     */
    void destroy();

    /**
     * Does the container empty
     * <p>
     * Author renbin.fang, Jan 20, 2014
     * 
     * @return whether container empty
     */
    boolean empty();

    /**
     * Add a element into container
     * <p>
     * Author renbin.fang, Jan 20, 2014
     * 
     * @param t
     * @return whether operation succeed
     */
    boolean add(T t);

    /**
     * Retrieve a element from this queue and then remove it.
     * <p>
     * Author renbin.fang, Jan 20, 2014
     * 
     * @return at the top of the queue
     */
    T shift();

    /**
     * Does this container contain this param.
     * <p>
     * Author renbin.fang, Jan 20, 2014
     * 
     * @param t
     * @return whether this container contains
     */
    boolean contains(T t);

    /**
     * Retrieve the size of this container.
     * <p>
     * Author renbin.fang, Jan 20, 2014
     * 
     * @return container size.
     */
    int size();
}