package com.monzo.webcrawler.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Adil Muthukoya
 * Test cases for the Web Crawler
 */
public class WebCrawlerTest {

    @Test
    public void return_empty_set_for_non_existing_url() {
        WebCrawler webCrawler = new WebCrawler("https://monzo.com/test/index.html", 1);
        webCrawler.crawl();
        Assert.assertEquals(0, webCrawler.getContainedUrls().size());
    }

    @Test
    public void return_non_empty_set_for_proper_url() {
        WebCrawler webCrawler = new WebCrawler("https://monzo.com/help", 1);
        webCrawler.crawl();
        Assert.assertEquals(true, webCrawler.getContainedUrls().size() > 1);
    }
}
