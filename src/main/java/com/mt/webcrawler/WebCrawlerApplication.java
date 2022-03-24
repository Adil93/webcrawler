package com.mt.webcrawler;

import com.mt.webcrawler.processor.WebCrawlerProcessor;

/**
 * @author Adil Muthukoya
 * 1. Client Application calling the crawler
 */
public class WebCrawlerApplication {
    private static final String DEFAULT_URL = "https://monzo.com";
    private static int DEFAULT_MAX_DEPTH = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String url = args.length > 0 && args[0] != null ? args[0] : DEFAULT_URL;
        int maxDepth = args.length > 1 && args[1] != null ? Integer.parseInt(args[1]) : DEFAULT_MAX_DEPTH;
        new WebCrawlerProcessor(url, maxDepth).process();
    }
}
