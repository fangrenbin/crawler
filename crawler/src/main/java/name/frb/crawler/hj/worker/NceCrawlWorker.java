/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 10, 2014
 * File Name      : HuJiangCrawlWorker.java
 */

package name.frb.crawler.hj.worker;

import name.frb.crawler.bean.CrawlStatus;
import name.frb.crawler.bean.Webpage;
import name.frb.crawler.container.Container;
import name.frb.crawler.container.url.SeedUrlContainer;
import name.frb.crawler.container.url.TodoUrlContainer;
import name.frb.crawler.container.url.VistedUrlContainer;
import name.frb.crawler.container.webpage.WebpageContainer;
import name.frb.crawler.model.hujiang.NcEnglish;
import name.frb.crawler.worker.base.AbstarctCrawlWorker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HuJiangCrawlWorker
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 10, 2014
 */
public class NceCrawlWorker extends AbstarctCrawlWorker {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final static String UTF8 = "UTF-8";
    private final static String REGEX_MP3 = "http://app.\\w+.com/listen/\\d+/";

    private Container<String> todoUrlContainer = new TodoUrlContainer();
    private Container<String> vistedUrlContainer = new VistedUrlContainer();
    private Container<Webpage> webpageContainer = new WebpageContainer();
    private Container<String> seedUrlContainer = new SeedUrlContainer();

    private MongoTemplate mongoTemplate;
    private CrawlStatus crawlStatus;

    /**
     * Constructor
     */
    public NceCrawlWorker(MongoTemplate mongoTemplate, CrawlStatus crawlStatus) {
        super();
        this.mongoTemplate = mongoTemplate;
        this.crawlStatus = crawlStatus;
    }

    @Override
    public boolean parseOperation(Webpage webpage) {
        String content = webpage.getContent();

        if (StringUtils.isEmpty(content)) {
            return false;
        }

        List<NcEnglish> ncenglishList = parseParentPage(content);
        for (NcEnglish newConceptEnglish : ncenglishList) {
            mongoTemplate.insert(newConceptEnglish);
        }

        return false;
    }

    /**
     * parse parent page and get all url of lessons
     * <p/>
     * Author renbin.fang, Jan 10, 2014
     *
     * @param parentPageContent
     * @return url of lessons
     */
    private List<NcEnglish> parseParentPage(String parentPageContent) {
        List<NcEnglish> lessonList = new ArrayList<NcEnglish>();

        Pattern p = Pattern.compile(REGEX_MP3);
        Matcher m = p.matcher(parentPageContent);

        while (m.find()) {
            NcEnglish ncEnglish = new NcEnglish();
            ncEnglish.setLessonUrl(m.group());

            lessonList.add(ncEnglish);
        }

        return lessonList;
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
        return this.vistedUrlContainer;
    }

    @Override
    public Container<Webpage> getWebpageContainer() {
        return this.webpageContainer;
    }

    @Override
    public boolean doObtainTodoUrl() {
        Set<String> seedUrlSet = crawlStatus.getSeedUrls();

        if (CollectionUtils.isEmpty(seedUrlSet)) {
            return false;
        }

        for (String seedUrl : seedUrlSet) {
            todoUrlContainer.add(seedUrl);
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
     * @see NceCrawlWorker#mongoTemplate
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}