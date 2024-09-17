package com.logging.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication implements CommandLineRunner {

	 public static void main(String[] args) {
        // Log a startup message
    	// Specify the location of the log4j2.xml configuration file
        System.setProperty("log4j.configurationFile", "classpath:log4j2.xml");

        // Log a startup message
        Logger startupLogger = LoggerFactory.getLogger("StartupLogger");
        startupLogger.info("Custom startup message: log4j2.xml configuration loaded.");

        // Enable inheritable MDC for threads
        System.setProperty("isThreadContextMapInheritable", "true");
        SpringApplication.run(LoggingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and start multiple threads with different user IDs
        Thread thread1 = new Thread(new Log4jMDCExample("User1"));
        Thread thread2 = new Thread(new Log4jMDCExample("User2"));
        Thread thread3 = new Thread(new Log4jMDCExample("User3"));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
