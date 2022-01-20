package br.com.denys.poc.rabbit.producer.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ConsoleLogger {

    public ConsoleLogger() {
    }

    private static final Map<String, Logger> loggersMap = new HashMap<>();

    private static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    private static Logger getLogger(String clazz) {
        Logger logger = loggersMap.get(clazz);
        if(logger == null) {
            logger = LoggerFactory.getLogger(clazz);
            loggersMap.put(clazz, logger);
        }
        return logger;
    }

    public static void info(String message, Class<?> clazz) {
        getLogger(clazz).info(message);
    }

    public static void info(String message, Class<?> clazz, Throwable exception) {
        getLogger(clazz).info(message, exception);
    }

    public static void warn(String message, Class<?> clazz) {
        getLogger(clazz).warn(message);
    }

    public static void warn(String message, Class<?> clazz, Throwable exception) {
        getLogger(clazz).warn(message, exception);
    }

    public static void error(String message, Class<?> clazz) {
        getLogger(clazz).error(message);
    }

    public static void error(String message, Class<?> clazz, Throwable exception) {
        getLogger(clazz).error(message, exception);
    }

}
