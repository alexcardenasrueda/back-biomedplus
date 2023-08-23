package com.softdevelop.biomedplus.util.logs;
import org.slf4j.Logger;

public enum LoggerLevel implements LoggerLevelInterface{
  ERROR {
    public void log(Logger logger, String message) {
      logger.error(message);
    }
  },
  WARN {
    public void log(Logger logger, String message) {
      logger.warn(message);
    }
  },
  INFO {
    public void log(Logger logger, String message) {
      logger.info(message);
    }
  },
  DEBUG {
    public void log(Logger logger, String message) {
      logger.debug(message);
    }
  },
  TRACE {
    public void log(Logger logger, String message) {
      logger.trace(message);
    }
  };

  private LoggerLevel() {
  }
}