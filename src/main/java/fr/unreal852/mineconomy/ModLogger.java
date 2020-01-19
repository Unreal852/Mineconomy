package fr.unreal852.mineconomy;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ModLogger
{
    /**
     * Mod Logger
     */
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * Log new message
     *
     * @param logLevel Message Log Level
     * @param message  Message
     */
    public static void Log(Level logLevel, String message)
    {
        LOGGER.log(logLevel, "[" + ModConstants.MOD_LOGGER_ID + "] " + message);
    }

    /**
     * Log new message with level INFO
     *
     * @param message Message
     */
    public static void LogInfo(String message)
    {
        Log(Level.INFO, message);
    }

    /**
     * Log new message with level ERROR
     *
     * @param message Message
     */
    public static void LogError(String message)
    {
        Log(Level.ERROR, message);
    }
}
