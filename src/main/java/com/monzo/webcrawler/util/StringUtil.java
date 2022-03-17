package com.monzo.webcrawler.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Adil Muthukoya
 * 1. Util functions for String manipulations
 */
public class StringUtil {
    private static final Logger logger = new Logger(StringUtil.class);

    public static String removeTrailingSlashAndHash(String str) {
        if (str.endsWith("/") || str.endsWith("#")) {
            str = str.substring(0, str.length() - 1);
        } else if (str.endsWith("/#")) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    public static String getBaseUrl(String path) {
        try {
            final URL url = new URL(path);
            return new StringBuilder().append(url.getProtocol())
                    .append("://").append(url.getHost()).toString();
        } catch (MalformedURLException e) {
            logger.error("Malformed url: {}", path);
            return null;
        }
    }
}
