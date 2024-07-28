package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.ImagictHud;
import kr.shihyeon.imagicthud.client.ImagictHudClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ModLogger {

    public static final Logger LOGGER = LogManager.getLogger(ImagictHud.MODID);

    /**
     * Prints a log message.
     *
     * @param message log format
     * @param params other data
     */
    public static void log(final String message, final Object... params) {
        LOGGER.info(message, params);
    }

    /**
     * Prints a error message.
     *
     * @param message log format
     * @param params other data
     */
    public static void error(final String message, final Object... params) {
        LOGGER.error(message, params);
    }

    /**
     * Prints a debug message.
     *
     * @param message log format
     * @param params other data
     */
    public static void debug(final String message, final Object... params) {
        if (ImagictHudClient.DEBUG) {
            LOGGER.warn(message, params);
        }
    }
}