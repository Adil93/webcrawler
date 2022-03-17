package com.monzo.webcrawler;

import com.monzo.webcrawler.service.WebCrawler;

/**
 * @author Adil Muthukoya
 * 1. Client Application calling the crawler
 */
public class WebCrawlerApplication {
    public static void main(String[] args) {
        new WebCrawler(3).crawl("http://monzo.com/business");
    }
}
