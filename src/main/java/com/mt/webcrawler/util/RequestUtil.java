package com.mt.webcrawler.util;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Adil Muthukoya
 * 1. Util functions for fetching and parsing HTML
 */
public class RequestUtil {
    private static final Logger logger = new Logger(RequestUtil.class);

    public static Optional<Document> fetch(String url) {
        Connection conn = Jsoup.connect(url);
        try {
            Document doc = conn.get();
            if (conn.response().statusCode() == 200) {
                return Optional.of(doc);
            }
        } catch (HttpStatusException e) {
            logger.error("Fetching url {} failed with status code: {} and message: {}",
                    url, e.getStatusCode(), e.getMessage());
        } catch (IOException e) {
            logger.error("Fetching url {} failed with message {}", url, e.getMessage());
        }
        return Optional.empty();
    }
}
