package com.monzo.webcrawler.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @author Adil Muthukoya
 * 1. Abstract crawler
 */
public abstract class AbstractCrawler implements Callable<WebCrawler> {

    protected static final String A_HREF_TAG = "a[href]";
    protected static final String HREF_TAG = "href";

    private String url;
    private int depth;
    private Set<String> containedUrls;

    public AbstractCrawler(String url, int depth) {
        this.url = url;
        this.depth = depth;
        this.containedUrls = new HashSet<>();
    }

    public abstract void crawl();

    public String getUrl() {
        return url;
    }

    public int getDepth() {
        return depth;
    }

    public Set<String> getContainedUrls() {
        return containedUrls;
    }
}
