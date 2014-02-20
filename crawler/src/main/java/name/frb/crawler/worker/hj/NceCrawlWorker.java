/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 10, 2014
 * File Name      : HuJiangCrawlWorker.java
 */

package name.frb.crawler.worker.hj;

import name.frb.crawler.bean.CrawlConfig;
import name.frb.crawler.bean.Webpage;
import name.frb.crawler.container.Container;
import name.frb.crawler.container.url.SeedUrlContainer;
import name.frb.crawler.container.url.TodoUrlContainer;
import name.frb.crawler.container.url.VistedUrlContainer;
import name.frb.crawler.container.webpage.WebpageContainer;
import name.frb.crawler.model.hujiang.NcEnglish;
import name.frb.crawler.worker.base.AbstarctCrawlWorker;
import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * HuJiangCrawlWorker
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 10, 2014
 */
public class NceCrawlWorker extends AbstarctCrawlWorker {
    private final static String UTF8 = "UTF-8";

    private Container<String> todoUrlContainer = new TodoUrlContainer();

    private Container<String> vistedUrlContainer = new VistedUrlContainer();

    private Container<Webpage> webpageContainer = new WebpageContainer();

    private Container<String> seedUrlContainer = new SeedUrlContainer();

    private CrawlConfig crawlConfig;

    private MongoTemplate mongoTemplate;

    /**
     * Constructor
     */
    public NceCrawlWorker(MongoTemplate mongoTemplate, CrawlConfig crawlConfig) {
        super();
        this.crawlConfig = crawlConfig;
        this.mongoTemplate = mongoTemplate;
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

        Parser parser = null;
        try {
            parser = new Parser(parentPageContent);
            parser.setEncoding(UTF8);
        } catch (ParserException e) {
            e.printStackTrace();
        }

        NodeFilter frameFilter = new NodeFilter() {
            /** Serial version UID */
            private static final long serialVersionUID = 1L;

            public boolean accept(Node node) {
                if (node.getText().startsWith("frame =")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        OrFilter orFilter = new OrFilter(new NodeClassFilter(LinkTag.class), new NodeClassFilter(ImageTag.class));
        OrFilter linkFilter = new OrFilter(orFilter, frameFilter);

        try {
            NodeList list = parser.extractAllNodesThatMatch(linkFilter);
            for (int i = 0; i < list.size(); i++) {
                Node tag = list.elementAt(i);

                if (tag instanceof LinkTag) {
                    LinkTag link = (LinkTag) tag;
                    String linkUrl = link.getLink();
                    String stringText = link.getStringText();

                    if (stringText.startsWith("新概念英语第一册Lesson") || stringText.startsWith("新概念英语第二册lesson") || stringText.startsWith("新概念英语第二册Lesson")
                            || stringText.startsWith("新概念英语第二册 lesson") || stringText.startsWith("新概念英语第三册lesson")) {

                        NcEnglish ncenglish = new NcEnglish();
                        ncenglish.setLessonUrl(linkUrl);
                        ncenglish.setTitle(stringText);

                        lessonList.add(ncenglish);
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
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
        List<String> seedList = crawlConfig.getSeedList();

        if (CollectionUtils.isEmpty(seedList)) {
            return false;
        }

        for (String seedUrl : seedList) {
            todoUrlContainer.add(seedUrl);
        }

        return true;
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