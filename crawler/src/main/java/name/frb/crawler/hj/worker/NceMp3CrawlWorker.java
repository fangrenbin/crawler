/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 20, 2014
 * File Name      : NceMp3CrawlWorker.java
 */

package name.frb.crawler.hj.worker;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import name.frb.crawler.bean.Webpage;
import name.frb.crawler.container.Container;
import name.frb.crawler.container.url.SeedUrlContainer;
import name.frb.crawler.container.url.TodoUrlContainer;
import name.frb.crawler.container.url.VistedUrlContainer;
import name.frb.crawler.container.webpage.WebpageContainer;
import name.frb.crawler.model.hujiang.NcEnglish;
import name.frb.crawler.model.hujiang.NceMp3;
import name.frb.crawler.worker.base.AbstarctCrawlWorker;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.CollectionUtils;

/**
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 20, 2014
 */
public class NceMp3CrawlWorker extends AbstarctCrawlWorker {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final static String REGEX_MP3 = "http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?\\.mp3?";
    private final static String REGEX_TITLE = "(id=\"article_title\">)(.*)(</h1>)";

    private Container<String> todoUrlContainer = new TodoUrlContainer();
    private Container<String> seedUrlContainer = new SeedUrlContainer();
    private Container<String> visitedUrlContainer = new VistedUrlContainer();
    private Container<Webpage> webpageContainer = new WebpageContainer();

    /**
     * {@link MongoTemplate}
     */
    private MongoTemplate mongoTemplate;

    /**
     * @param mongoTemplate
     */
    public NceMp3CrawlWorker(MongoTemplate mongoTemplate) {
        super();
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean parseOperation(Webpage webpage) {
        String content = webpage.getContent();
        if (StringUtils.isEmpty(content)) {
            return false;
        }

        NceMp3 nceMp3 = new NceMp3();
        String title = findLessonTitle(content);
        String mp3url = findMp3Link(content);

        nceMp3.setTitle(title);
        nceMp3.setMp3Url(mp3url);

        mongoTemplate.insert(nceMp3);

        return true;
    }

    /**
     * find out the mp3 url
     * <p/>
     * Author renbin.fang, Jan 10, 2014
     *
     * @param htmlContent
     * @return mp3 url
     */
    private String findMp3Link(String htmlContent) {
        Pattern p = Pattern.compile(REGEX_MP3);
        Matcher m = p.matcher(htmlContent);
        if (!m.find()) {
            return null;
        }

        return m.group();
    }

    /**
     * find out the lesson title
     * <p/>
     * Author renbin.fang, Jan 14, 2014
     *
     * @param htmlContent
     * @return lesson title
     */
    private String findLessonTitle(String htmlContent) {
        Pattern p = Pattern.compile(REGEX_TITLE);
        Matcher m = p.matcher(htmlContent);

        if (m.find() && m.groupCount() == 3) {
            return m.group(2);
        } else {
            return null;
        }
    }

    @Override
    public Container<String> getSeedContainer() {
        return this.seedUrlContainer;
    }

    @Override
    public Container<String> getTodoUrlContainer() {
        return this.todoUrlContainer;
    }

    @Override
    public Container<String> getVisitedUrlContainer() {
        return this.visitedUrlContainer;
    }

    @Override
    public Container<Webpage> getWebpageContainer() {
        return this.webpageContainer;
    }

    @Override
    public boolean doObtainTodoUrl() {
        List<NcEnglish> nceList = mongoTemplate.findAll(NcEnglish.class);
        if (CollectionUtils.isEmpty(nceList)) {
            return false;
        }

        for (NcEnglish nce : nceList) {
            this.todoUrlContainer.add(nce.getLessonUrl());
        }

        return true;
    }


    @Override
    public Logger getLOGGER() {
        return LOGGER;
    }

    /**
     * setter method
     *
     * @param mongoTemplate the mongoTemplate to set
     * @see NceMp3CrawlWorker#mongoTemplate
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}