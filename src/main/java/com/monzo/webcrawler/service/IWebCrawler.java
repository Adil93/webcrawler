package com.monzo.webcrawler.service;

import java.util.Set;

public interface IWebCrawler {
    Set<String> crawl(String url);
}
