package com.softdevelop.biomedplus.util.logs;


import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.hibernate.annotations.Immutable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Immutable
public final class LoggerEvent {
  private final LoggerLevel level;
  private final boolean obfuscation = Boolean.parseBoolean(System.getProperty("logging.obfuscation"));
  private final Map<String, String> fields;
  private final Logger logger;

  private LoggerEvent(LoggerLevel level) {
    this.level = level;
    this.fields = Collections.emptyMap();
    this.logger = LoggerFactory.getLogger("ROOT");
  }

  private LoggerEvent(LoggerLevel level, Logger logger, Map<String, String> fields) {
    this.level = level;
    this.logger = logger;
    this.fields = fields;
  }

  public static LoggerEvent info() {
    return new LoggerEvent(LoggerLevel.INFO);
  }

  public static LoggerEvent warn() {
    return new LoggerEvent(LoggerLevel.WARN);
  }

  public static LoggerEvent error() {
    return new LoggerEvent(LoggerLevel.ERROR);
  }

  public static LoggerEvent debug() {
    return new LoggerEvent(LoggerLevel.DEBUG);
  }

  public static LoggerEvent trace() {
    return new LoggerEvent(LoggerLevel.TRACE);
  }

  public final LoggerEvent forClass(Class<?> clazz) {
    Objects.requireNonNull(clazz);
    return new LoggerEvent(this.level, LoggerFactory.getLogger(clazz), this.fields);
  }

  public final void log() {
    this.level.log(this.logger, LoggerUtil.buildLoggerMessageAsString(this.fields));
  }

  public final LoggerEvent withField(String name, Object value) {
    Objects.requireNonNull(name);
    String sanitizedValue = LoggerUtil.sanitizeLogElement(String.valueOf(value));
    Map<String, String> newFields = new TreeMap(this.fields);
    newFields.put(name, sanitizedValue);
    return new LoggerEvent(this.level, this.logger, Collections.unmodifiableMap(newFields));
  }

  public final LoggerEvent withField(String name, String value, MaskingMethod obfuscateMaskingMethod) {
    Objects.requireNonNull(name);
    if (this.obfuscation) {
      value = LoggerUtil.obfuscate(value, obfuscateMaskingMethod);
    }

    String sanitizedValue = LoggerUtil.sanitizeLogElement(value);
    Map<String, String> newFields = new TreeMap(this.fields);
    newFields.put(name, sanitizedValue);
    return new LoggerEvent(this.level, this.logger, Collections.unmodifiableMap(newFields));
  }

  final Map<String, String> getFields() {
    return this.fields;
  }
}
