package com.monzo.webcrawler.service;

import com.monzo.webcrawler.util.Logger;
import com.monzo.webcrawler.util.RequestUtil;
import com.monzo.webcrawler.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * @author Adil Muthukoya
 * 1. Web crawler crawling a URL to get its related links
 *
 */
public class WebCrawler extends AbstractCrawler {

    private static final Logger logger = new Logger(WebCrawler.class);

    public WebCrawler(String url, int depth) {
        super(url, depth);
    }

    /**
     * 1. Crawls the given URL and obtain its immediate unique links in contained URL set
     * 2. Depth is the child level depth info of an ongoing crawl.
     *
     * @return
     */

    public void crawl() {
        logger.info(">> Crawling Level={}, URL={}", this.getDepth(), this.getUrl());
        Optional<Document> doc = RequestUtil.fetch(this.getUrl());
        if (doc.isPresent()) {
            for (Element element : doc.get().select(WebCrawler.A_HREF_TAG)) {
                String href = element.attr(HREF_TAG);
                if (StringUtil.isNullOrEmpty(href) || href.startsWith("#"))
                    continue;
                this.getContainedUrls().add(element.absUrl(HREF_TAG));
            }
        }
    }

    @Override
    public WebCrawler call() throws Exception {

        crawl();
        return this;
    }


}
