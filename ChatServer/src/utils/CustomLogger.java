package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class CustomLogger {
    private static CustomLogger customLogger;

    private Logger logger;

    /**
     *  This class is designed for printing log in controller.
     */

    class LogFormatter extends Formatter {
        private static final String ANSI_RESET = "\u001B[0m";
        private static final String ANSI_RED = "\u001B[31m";
        private static final String ANSI_GREEN = "\u001B[32m";
        private static final String ANSI_YELLOW = "\u001B[33m";
        private static final String ANSI_WHITE = "\u001B[37m";

        @Override
        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder(ANSI_RESET);

            Level level = record.getLevel();
            if (Level.INFO.equals(level)) {
                builder.append(ANSI_GREEN);
            } else if (Level.WARNING.equals(level)) {
                builder.append(ANSI_YELLOW);
            } else if (Level.SEVERE.equals(level)) {
                builder.append(ANSI_RED);
            } else {
                builder.append(ANSI_WHITE);
            }

            builder.append("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]");
            builder.append(" [" + record.getLevel().getName() + "]");
            builder.append(" [" + Thread.currentThread().getName() + "]");
            builder.append(" - " + record.getMessage());
            builder.append(System.lineSeparator());

            return builder.toString();
        }
    }

    CustomLogger() {
        Logger logger = Logger.getLogger("ChatSever");
        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new LogFormatter());
        logger.addHandler(consoleHandler);
        this.logger = logger;
    }

    public void info(String message) {
        logger.info("\u001B[32m" + message);
    }

    public void warning(String message) {
        logger.warning("\u001B[33m" + message);
    }

    public void error(String message) {
        logger.severe("\u001B[31m" + message);
    }

    public void fatal(String message) {
        logger.severe(message);
        logger.severe("Quitting due to fatal error...");
        System.exit(1);
    }

    public static CustomLogger getSingleton() {
        if (customLogger == null) {
            customLogger = new CustomLogger();
            return customLogger;
        }
        return customLogger;
    }
}
