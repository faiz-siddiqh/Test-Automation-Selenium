package log4j_Practice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging_Console {

	private static final Logger log = LogManager.getLogger(Logging_Console.class.getName());

	public static void main(String... args) {

		log.debug("Debug Message Logged");
		log.error("Error Message Logged");
		log.fatal("Fatal Message Logged");

	}

}
