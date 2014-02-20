package name.frb.crawler.worker.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import name.frb.crawler.bean.Webpage;
import name.frb.crawler.container.Container;
import name.frb.crawler.worker.CrawlWorker;

import org.apache.commons.lang3.StringUtils;

/**
 * AbstarctCrawlWorker
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 9, 2014
 */
public abstract class AbstarctCrawlWorker implements CrawlWorker {
    /**
     * GET
     */
    private static final String REQUEST_TYPE_GET = "GET";

    @Override
    public boolean capture() {
        String url = getTodoUrlContainer().shift();

        if (StringUtils.isEmpty(url)) {
            return false;
        }

        String htmlContent = captureWebPage(url);
        if (StringUtils.isEmpty(htmlContent)) {
            return false;
        }

        Webpage webpage = new Webpage();
        webpage.setUrl(url);
        webpage.setContent(htmlContent);

        getWebpageContainer().add(webpage);

        System.out.println("抓取网页：" + url);

        return true;
    }

    @Override
    public boolean parse() {
        Webpage webpage = getWebpageContainer().shift();

        if (webpage == null) {
            return false;
        }

        System.out.println("解析URL：" + webpage.getUrl());

        return parseOperation(webpage);
    }

    @Override
    public boolean obtainTodoUrl() {
        boolean success = doObtainTodoUrl();
        if (!success) {
            return false;
        }

        if (getTodoUrlContainer().size() == 0) {
            return false;
        }

        return true;
    }

    @Override
    public boolean doesCrawlFinish() {
        return getTodoUrlContainer().empty() && getWebpageContainer().empty();
    }

    /**
     * capture the content of web page
     * <p/>
     * Author renbin.fang, Jan 10, 2014
     *
     * @param url
     * @return the content of web page
     */

    /**
     *
     * @param url
     * @return
     */
    protected String captureWebPage(String url) {
        HttpURLConnection httpUrlConnection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            httpUrlConnection.setRequestMethod(REQUEST_TYPE_GET);
            httpUrlConnection.setUseCaches(true); // use caches
            httpUrlConnection.connect(); // connect
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        StringBuffer htmlContent = new StringBuffer();
        try {
            inputStream = httpUrlConnection.getInputStream(); // get input
            // stream
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String string;
            while ((string = bufferedReader.readLine()) != null) {
                htmlContent.append(string + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                inputStream.close();
                httpUrlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return htmlContent.toString();
    }

    public abstract boolean parseOperation(Webpage webpage);

    public abstract Container<String> getSeedContainer();

    public abstract Container<String> getTodoUrlContainer();

    public abstract Container<String> getVisitedUrlContainer();

    public abstract Container<Webpage> getWebpageContainer();

    public abstract boolean doObtainTodoUrl();
}
