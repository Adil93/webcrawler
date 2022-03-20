package com.monzo.webcrawler.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Adil Muthukoya
 * Test cases for the Web Crawler
 */
public class WebCrawlerTest {

    @Test
    public void return_non_empty_set_for_proper_url() {
        WebCrawler webCrawler = new WebCrawler("https://monzo.com/help", 2);
        webCrawler.crawl();
        Assert.assertEquals(true, webCrawler.getContainedUrls().size() > 1);
    }

}
