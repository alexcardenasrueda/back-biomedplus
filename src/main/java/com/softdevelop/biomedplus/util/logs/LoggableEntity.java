package com.softdevelop.biomedplus.util.logs;

import java.util.SortedMap;
import java.util.TreeMap;
import lombok.Generated;

public class LoggableEntity {
  private String entityName;
  private SortedMap<String, String> propertiesMap = new TreeMap();

  private LoggableEntity() {
  }

  public static LoggableEntity init(Class<?> clazz) {
    return (new LoggableEntity()).withEntityName(clazz.getSimpleName());
  }

  public LoggableEntity add(String propertyName, String propertyValue) {
    this.propertiesMap.put(propertyName, propertyValue);
    return this;
  }

  public LoggableEntity withEntityName(String entityName) {
    this.entityName = entityName;
    return this;
  }

  public LoggableEntity withPropertiesMap(SortedMap<String, String> propertiesMap) {
    this.propertiesMap = propertiesMap;
    return this;
  }

  public String toString() {
    return LoggerUtil.buildEntityAsString(this.entityName, this.propertiesMap);
  }

  @Generated
  public String getEntityName() {
    return this.entityName;
  }

  @Generated
  public SortedMap<String, String> getPropertiesMap() {
    return this.propertiesMap;
  }
}
