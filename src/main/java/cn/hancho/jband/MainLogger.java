package cn.hancho.jband;

import org.apache.log4j.Logger;

public class MainLogger {
    public static final Logger LOGGER = Logger.getLogger(MainLogger.class);

    public MainLogger(){

    }

    public void debug(Object content){
        LOGGER.debug(content);
    }

    public void info(Object content){
        LOGGER.info(content);
    }

    public void warn(Object content){
        LOGGER.warn(content);
    }

    public void error(Object content){
        LOGGER.error(content);
    }

    public void error(Object content, Throwable t){
        LOGGER.error(content, t);
    }

}
