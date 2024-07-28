package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ModLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger("imagicthud");

    /**
     * Prints a log message.
     *
     * @param msg log format
     * @param data other data
     */
    public static void log(final String msg, final Object... data) {
        LOGGER.info(msg, data);
    }

    /**
     * Prints a error message.
     *
     * @param msg log format
     * @param data other data
     */
    public static void error(final String msg, final Object... data) {
        LOGGER.error(msg, data);
    }

    /**
     * Prints a debug message.
     *
     * @param msg log format
     * @param args other data
     */
    public static void debug(final String msg, final Object... args) {
        if (ImagictHudClient.DEBUG) {
            LOGGER.warn(msg, args);
        }
    }
}