/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 9, 2014
 * File Name      : WebPage.java
 */

package name.frb.crawler.bean;

/**
 * WebPage
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 9, 2014
 */
public class Webpage {
    private String url;
    private String content;

    /**
     * getter method
     * 
     * @see Webpage#url
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * setter method
     * 
     * @see Webpage#url
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * getter method
     * 
     * @see Webpage#content
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * setter method
     * 
     * @see Webpage#content
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WebPage [url=" + url + ", content=" + content + "]";
    }
}