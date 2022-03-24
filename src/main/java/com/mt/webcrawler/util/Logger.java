package com.mt.webcrawler.util;

import org.slf4j.LoggerFactory;

/**
 * @author Adil Muthukoya
 * 1. Custom Logger class
 * 2. Can change the pattern if needed
 */
public class Logger {

    private org.slf4j.Logger logger;

    public Logger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void info(String msg, Object... arguments) {
        this.logger.info(msg, arguments);
    }

    public void error(String msg, Object... arguments) {
        this.logger.error(msg, arguments);
    }

}

