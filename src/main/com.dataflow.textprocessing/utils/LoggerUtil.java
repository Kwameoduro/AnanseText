package com.dataflow.textprocessing.utils;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class LoggerUtil {

    private static Logger logger;

    public static Logger getLogger(String name) {
        if (logger == null) {
            logger = Logger.getLogger(name);
            try {
                FileHandler fileHandler = new FileHandler("app_logs.log", true);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                System.err.println("Failed to initialize logger: " + e.getMessage());
            }
        }
        return logger;
    }
}
