package com.mt.webcrawler.processor;

import org.junit.Assert;
import org.junit.Test;

public class WebCrawlerProcessorTest {
    private WebCrawlerProcessor webCrawlerProcessor;

    @Test
    public void return_empty_set_for_wrong_url() {
        webCrawlerProcessor = new WebCrawlerProcessor("abc", 1);
        webCrawlerProcessor.process();
        Assert.assertEquals(0, webCrawlerProcessor.getProcessedUrls().size());
    }

    @Test
    public void return_a_set_with_the_given_url_for_non_existing_url() {
        webCrawlerProcessor = new WebCrawlerProcessor("https://monzo.com/test/index.html", 1);
        webCrawlerProcessor.process();
        Assert.assertEquals(1, webCrawlerProcessor.getProcessedUrls().size());
    }

    @Test
    public void return_non_empty_set_ignoring_slash_at_end_for_proper_url() {
        webCrawlerProcessor = new WebCrawlerProcessor("https://monzo.com/", 0);
        webCrawlerProcessor.process();
        Assert.assertEquals(false, webCrawlerProcessor.getProcessedUrls().containsKey("https://monzo.com/"));
        Assert.assertEquals(true, webCrawlerProcessor.getProcessedUrls().containsKey("https://monzo.com"));
    }

    @Test
    public void return_non_empty_set_ignoring_hash_at_end_for_proper_url() {
        webCrawlerProcessor = new WebCrawlerProcessor("https://monzo.com/#", 0);
        webCrawlerProcessor.process();
        Assert.assertEquals(false, webCrawlerProcessor.getProcessedUrls().containsKey("https://monzo.com/#"));
        Assert.assertEquals(true, webCrawlerProcessor.getProcessedUrls().containsKey("https://monzo.com"));
    }

    @Test
    public void return_non_empty_set_for_proper_url() {
        webCrawlerProcessor = new WebCrawlerProcessor("https://monzo.com/business", 1);
        webCrawlerProcessor.process();
        Assert.assertEquals(1, webCrawlerProcessor.getMaxDepth());
        Assert.assertEquals("https://monzo.com/business", webCrawlerProcessor.getStartUrl());
        Assert.assertEquals("https://monzo.com", webCrawlerProcessor.getBaseUrl());

        Assert.assertEquals(true, webCrawlerProcessor.getProcessedUrls().size() > 1);
    }

}
