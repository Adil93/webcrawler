package com.monzo.webcrawler.service;

import com.monzo.webcrawler.util.Logger;
import com.monzo.webcrawler.util.RequestUtil;
import com.monzo.webcrawler.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Adil Muthukoya
 * 1. A Simple Web Crawler with a max depth set.
 * 2. Crawl considers all the urls starting with the base url of the given one.
 */
public class WebCrawler implements IWebCrawler {

    private static final Logger logger = new Logger(WebCrawler.class);
    private static final String A_HREF_TAG = "a[href]";
    private static final String HREF_TAG = "href";

    private int maxDepth;

    public WebCrawler(int maxDepth) {
        this.maxDepth = maxDepth;
        logger.info("Initialized Web Crawler with a maximum depth set to {}", maxDepth);
    }

    /**
     * @param url starting point of crawl
     * @return Set of strings which are the unique crawled urls for the given url
     */
    public Set<String> crawl(String url) {
        Set<String> visited = new HashSet<String>();
        String baseUrl = StringUtil.getBaseUrl(url);

        if (baseUrl != null) {
            logger.info("!! .... Started crawling for URL: {} .... !!", url);
            logger.info("Crawling only URLs with base URL: {}", baseUrl);
            String urlWithoutSlash = StringUtil.removeTrailingSlashAndHash(url);
            visited.add(urlWithoutSlash);
            crawl(0, baseUrl, url, visited);
            logger.info("!! .... Crawling Finished .... !!");
            logger.info("SUMMARY - Total crawled URLs with maximum depth {} for the URL {} and starting with base URL {} - {}",
                    maxDepth, url, baseUrl, visited.size());
        }
        return visited;
    }

    /**
     * @param level   depth of the crawling
     * @param baseUrl base url of the given url
     * @param url     starting point of the crawl
     * @param visited populating set containing unique crawled urls
     *                1. Followed the Breadth First Search Algorithm to recursively
     *                call each links to get the related links.
     *                2. Call until level is greater than equal to maxDepth set
     */
    private void crawl(int level, String baseUrl, String url, Set<String> visited) {
        if (level >= maxDepth) return;

        Optional<Document> doc = RequestUtil.fetch(url);
        if (doc.isPresent()) {
            level++;
            for (Element link : doc.get().select(A_HREF_TAG)) {
                String path = StringUtil.removeTrailingSlashAndHash(link.absUrl(HREF_TAG));
                if (path.startsWith(baseUrl) && visited.add(StringUtil.removeTrailingSlashAndHash(path))) {
                    logger.info(">> Crawl Level: {} >> URL [ {} ]", level, path);
                    crawl(level, baseUrl, path, visited);
                }
            }
        }

    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

}
