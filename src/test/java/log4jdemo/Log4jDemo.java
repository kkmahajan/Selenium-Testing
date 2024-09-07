package log4jdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class Log4jDemo {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("This is an info message added on {}", LocalDateTime.now());
        logger.warn("This is warning message added on {}", LocalDateTime.now());
        logger.fatal("This is fatal message added on {}", LocalDateTime.now());
        logger.error("This is an error message added on {}", LocalDateTime.now());

        System.out.println("Log4j Test Completed");
    }

}
