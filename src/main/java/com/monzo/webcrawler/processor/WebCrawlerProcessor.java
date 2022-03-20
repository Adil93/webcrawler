package com.monzo.webcrawler.processor;

import com.monzo.webcrawler.service.WebCrawler;
import com.monzo.webcrawler.util.Logger;
import com.monzo.webcrawler.util.StringUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Adil Muthukoya
 * 1. Crawls and store the URLs for the given start url for specified depth
 */
public class WebCrawlerProcessor implements IProcessor {

    private static final Logger logger = new Logger(WebCrawlerProcessor.class);
    private static final int THREAD_COUNT = 4;

    private int maxDepth;
    private Map<String, LocalDateTime> processedUrls;

    private String startUrl;
    private String baseUrl;

    private ExecutorService executorService;
    private List<Future<WebCrawler>> futures;

    public WebCrawlerProcessor(String startUrl, int maxDepth) {
        logger.info(">>> Initializing Web Crawler, startUrl={}, maxDepth={} <<<", startUrl, maxDepth);

        this.startUrl = startUrl;
        this.maxDepth = maxDepth;
        processedUrls = new ConcurrentHashMap<>();
        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        futures = new ArrayList<>();
        baseUrl = StringUtil.getBaseUrl(startUrl);


    }

    /**
     * 1. Start processing the crawler for the start url with specified maximum depth
     */
    @Override
    public void process() {
        if (baseUrl != null) {
            logger.info(">>> Crawling all URLs starting with base URL={} <<<", baseUrl);
            crawlUrl(StringUtil.removeTrailingSlashAndHash(startUrl), 0);
            while (!futures.isEmpty()) {
                processAllCrawlerFutures();
            }
        }
        logSummary();
        executorService.shutdown();
    }

    /**
     * 1. Loop till All future object tasks are done
     */
    private void processAllCrawlerFutures() {
        Iterator<Future<WebCrawler>> iterator = futures.iterator();
        Set<WebCrawler> crawlers = new HashSet<>();
        while (iterator.hasNext()) {
            Future<WebCrawler> crawlFuture = iterator.next();
            if (crawlFuture.isDone()) {
                iterator.remove();
                try {
                    crawlers.add(crawlFuture.get());
                } catch (InterruptedException e) {
                } catch (ExecutionException e) {
                }
            }
        }

        for (WebCrawler childCrawler : crawlers) {
            crawlRelatedUrls(childCrawler);
        }
    }
    /**
     * 1. Loop through the contained URLs for the given parent URL
     * 2. Crawl each child URLs with incremented depth of its parent level
     *
     * @param crawler
     */
    private void crawlRelatedUrls(WebCrawler crawler) {
        for (String link : crawler.getContainedUrls()) {
            crawlUrl(StringUtil.removeTrailingSlashAndHash(link), crawler.getDepth() + 1);
        }
    }

    /**
     * 1. If Crawling criteria is met, Add the URL to the Concurrent Hash Map, With current server time
     * 2. Creates a Webcrawler instance and submit it to the executor service, Which will start the processing asynchronously
     * 3. Add the returning future objects to the futures list
     *
     * @param url
     * @param depth
     */
    private void crawlUrl(String url, int depth) {
        if (canCrawl(url, depth)) {
            processedUrls.put(url, LocalDateTime.now());
            WebCrawler crawler = new WebCrawler(url, depth);
            Future<WebCrawler> future = executorService.submit(crawler);
            futures.add(future);
        }
    }

    /**
     * @param url
     * @param depth
     * @return boolean indicating criteria for crawler is met
     */
    private boolean canCrawl(String url, int depth) {
        return !processedUrls.containsKey(url)
                && url.startsWith(baseUrl)
                && !url.endsWith(".pdf")
                && depth <= maxDepth;
    }

    /**
     * Logs the Summary of the Web Crawling
     */
    private void logSummary() {
        logger.info("\n\n*** SUMMARY ***\n\nCrawling URL={} \nMaximum Depth={} \nTotal Crawled URLs={}",
                startUrl, maxDepth, processedUrls.size());
        if (baseUrl != null) {
            logger.info("Starting with Base URL = {}", baseUrl);
        }
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public Map<String, LocalDateTime> getProcessedUrls() {
        return processedUrls;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
