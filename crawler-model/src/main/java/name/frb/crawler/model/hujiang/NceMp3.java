/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 23, 2014
 * File Name      : NceMp3.java
 */

package name.frb.crawler.model.hujiang;

/**
 * New concept english MP3
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 23, 2014
 */
public class NceMp3 {
    /* id*/
    private String id;

    /* lesson title*/
    private String title;

    /* mp3 url*/
    private String mp3Url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }
}