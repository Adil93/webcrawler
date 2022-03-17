package com.monzo.webcrawler.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * @author Adil Muthukoya
 * Test cases for the Web Crawler
 */
public class WebCrawlerTest {

    private WebCrawler webCrawler;

    @Before
    public void setup() {
        webCrawler = new WebCrawler(2);
    }

    @Test
    public void return_empty_set_for_malformed_url() {
        Set<String> links = webCrawler.crawl("abc:abc");
        Assert.assertEquals(0, links.size());
    }

    @Test
    public void return_non_empty_set_for_proper_url() {
        Set<String> links = webCrawler.crawl("https://monzo.com");
        Assert.assertEquals(2, webCrawler.getMaxDepth());
        Assert.assertEquals(true, links.size() > 0);
    }

}
