/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 17, 2014
 * File Name      : NewConceptEnglish.java
 */

package name.frb.crawler.model.hujiang;

/**
 * NewConceptEnglish
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 17, 2014
 */
public class NcEnglish {
    /*  id */
    private String id;

    /*  lesson url */
    private String lessonUrl;


    /**
     * Constructor
     */
    public NcEnglish() {
    }

    /**
     * Constructor
     *
     * @param id
     * @param lessonUrl
     */
    public NcEnglish(String id, String lessonUrl) {
        this.id = id;
        this.lessonUrl = lessonUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLessonUrl() {
        return lessonUrl;
    }

    public void setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
    }

    @Override
    public String toString() {
        return "NcEnglish{" +
                "id='" + id + '\'' +
                ", lessonUrl='" + lessonUrl + '\'' +
                '}';
    }
}