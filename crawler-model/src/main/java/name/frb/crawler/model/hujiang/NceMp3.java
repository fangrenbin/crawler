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
    /* id */
    private String id;

    /* lesson title */
    private String title;

    /* mp3 url */
    private String mp3Url;

    /* book number: book one, two, three... */
    private int bookNumber;

    /* pronounce type: American or england*/
    private int pronounceType;

    /**
     * enum book number
     */
    public enum BOOK_NUM {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4);

        private int value;

        BOOK_NUM(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Pronounce type
     */
    public enum PRONOUNCE_TYPE {
        AMERICAN(1),
        ENGLIAND(2);

        private int value;

        PRONOUNCE_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

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

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public int getPronounceType() {
        return pronounceType;
    }

    public void setPronounceType(int pronounceType) {
        this.pronounceType = pronounceType;
    }

    @Override
    public String toString() {
        return "NceMp3{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", mp3Url='" + mp3Url + '\'' +
                ", bookNumber=" + bookNumber +
                ", pronounceType=" + pronounceType +
                '}';
    }
}