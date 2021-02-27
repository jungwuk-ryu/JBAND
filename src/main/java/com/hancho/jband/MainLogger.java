package com.hancho.jband;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainLogger {
    public static final Logger LOGGER = LogManager.getLogger(MainLogger.class);

    public MainLogger() {

    }

    public static void debug(Object content) {
        LOGGER.debug(content);
    }

    public static void info(Object content) {
        LOGGER.info(content);
    }

    public static void warn(Object content) {
        LOGGER.warn(content);
    }

    public static void error(Object content) {
        LOGGER.error(content);
    }

    public static void error(Object content, Throwable t) {
        LOGGER.error(content, t);
    }

}
