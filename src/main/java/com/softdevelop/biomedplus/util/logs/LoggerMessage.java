package com.softdevelop.biomedplus.util.logs;

import java.util.Objects;

public interface LoggerMessage<T extends LoggerMessage<T>> {
  void log();

  default T withAction(String action) {
    return this.withField("action", action);
  }

  default T withDescription(String description) {
    return this.withField("description", description);
  }

  default T withStackTrace(Exception exception) {
    return this.withStackTrace((Throwable)exception);
  }

  default T withStackTrace(Throwable throwable) {
    String stackTrace = LoggerUtil.getStackTrace(throwable);
    return this.withField("stackTrace", stackTrace);
  }

  default T withException(Exception exception) {
    return this.withException((Throwable)exception);
  }

  default T withException(Throwable throwable) {
    Objects.requireNonNull(throwable);
    return this.withField("exception", throwable.getClass().getCanonicalName());
  }

  default T withSystemInError(String systemInError) {
    return this.withField("systemInError", systemInError);
  }

  default T withEntity(LoggableEntity loggableEntity) {
    String logString = Objects.nonNull(loggableEntity) ? loggableEntity.toString() : null;
    return this.withField("entity", logString);
  }

  T withField(String var1, Object var2);
}
