package org.vizun;

import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class Log {
    
    private final Vizun instance;

    private Logger logger;
    
    public Log(Vizun game) {
        instance = game;
        logger = LoggerFactory.getLogger("org.vizun");
    }
    
    public Logger getLogger() {
        return logger;
    }

    /**
     * Static Logger method*
     * @Deprecated should get the logger and use the logger object*
     * @param level level of log entry
     * @param log log message
     */
    @Deprecated
    public static void log(Level level, String log) {
        Logger logger = LoggerFactory.getLogger("org.vizun");
        if(level == Level.DEBUG) {
            logger.debug(log);
        } else if(level == Level.ERROR) {
            logger.error(log);
        } else if(level == Level.INFO) {
            logger.info(log);
        } else if(level == Level.TRACE) {
            logger.trace(log);
        } else if(level == Level.WARN) {
            logger.warn(log);
        }
    }
}
