/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 14, 2014
 * File Name      : ConcurrentTaskExecutor.java
 */

package name.frb.crawler.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ConcurrentTaskExecutor
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 14, 2014
 */
public class ConcurrentTaskExecutor {
    /**
     * {@link ExecutorService}
     */
    private ExecutorService threadPool;

    /**
     * Constructor
     *
     * @param threadPoolSize
     * @param maxQueueSize
     */
    public ConcurrentTaskExecutor(int threadPoolSize, int maxQueueSize) {
        this.threadPool = new ThreadPoolExecutor(threadPoolSize, threadPoolSize, Long.MAX_VALUE, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(maxQueueSize, false),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * execute task
     * <p/>
     * Author renbin.fang, Jan 14, 2014
     *
     * @param task
     */
    public void execute(Runnable task) {
        threadPool.execute(task);
    }

    /**
     * stop all of the tasks in the thread pool
     * <p/>
     * Author renbin.fang, Jan 14, 2014
     */
    public void stop() {
        threadPool.shutdown();

        try {
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

            threadPool.shutdownNow();
        }
    }

}
