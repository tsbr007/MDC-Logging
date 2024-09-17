package com.logging.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Log4jMDCExample implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Log4jMDCExample.class);
    private final String userId;

    public Log4jMDCExample(String userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        try {
            // Put the user ID in the MDC
            MDC.put("userID", userId);

            // Log messages with user ID context
            logger.info("Starting processing for user.");
            System.out.println("MDC userID: " + MDC.get("userID"));
            performTask();
            logger.info("Completed processing for user.");
        } finally {
            // Clear MDC to avoid memory leaks
            MDC.clear();
        }
    }

    private void performTask() {
        try {
            // Simulate some processing time
            Thread.sleep(1000);
            logger.info("Processing in performTask method.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread was interrupted", e);
        }
    }
}
