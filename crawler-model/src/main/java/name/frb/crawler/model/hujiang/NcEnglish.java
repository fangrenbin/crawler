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
    /**
     * id
     */
    private String id;

    /**
     * 第几册
     */
    private Integer bookNum;

    /**
     * 课程名称
     */
    private String title;

    /**
     * 课程
     */
    private String lessonUrl;

    /**
     * 学习笔记
     */
    private String notesUrl;

    /**
     * 听力美音
     */
    private String dictationAmericaUrl;

    /**
     * 听力英音
     */
    private String dictationBritishUrl;

    /**
     * 册数枚举
     * <p/>
     *
     * @author : renbin.fang
     * @date : Jan 17, 2014
     */
    public enum NCEBookNum {
        BOOK_ONE(1),
        BOOK_TWO(2),
        BOOK_THREE(3),
        BOOK_FOUR(4);

        public int value;

        /**
         * @param value
         */
        private NCEBookNum(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public NcEnglish() {
    }

    public NcEnglish(String id, Integer bookNum, String title, String lessonUrl, String notesUrl, String dictationAmericaUrl, String dictationBritishUrl) {
        this.id = id;
        this.bookNum = bookNum;
        this.title = title;
        this.lessonUrl = lessonUrl;
        this.notesUrl = notesUrl;
        this.dictationAmericaUrl = dictationAmericaUrl;
        this.dictationBritishUrl = dictationBritishUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLessonUrl() {
        return lessonUrl;
    }

    public void setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
    }

    public String getNotesUrl() {
        return notesUrl;
    }

    public void setNotesUrl(String notesUrl) {
        this.notesUrl = notesUrl;
    }

    public String getDictationAmericaUrl() {
        return dictationAmericaUrl;
    }

    public void setDictationAmericaUrl(String dictationAmericaUrl) {
        this.dictationAmericaUrl = dictationAmericaUrl;
    }

    public String getDictationBritishUrl() {
        return dictationBritishUrl;
    }

    public void setDictationBritishUrl(String dictationBritishUrl) {
        this.dictationBritishUrl = dictationBritishUrl;
    }
}