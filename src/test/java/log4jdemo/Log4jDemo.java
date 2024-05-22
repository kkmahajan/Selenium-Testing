package log4jdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jDemo {

	private static Logger logger = LogManager.getLogger();
	public static void main(String[] args) {
		
		System.out.println("\n Hello World    !!!\n");
		
		logger.info("This is an info message");
		logger.warn("This is warning message");
		logger.fatal("This is fatal message");
		logger.error("This is an error message");
		
		System.out.println("Log4j Test Completed");
	}

}
