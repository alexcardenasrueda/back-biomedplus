package com.softdevelop.biomedplus.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Logger {

  private String className;
  private String action;
  private String description;
  private Object object;

  @Override
  public String toString() {
    ReflectionToStringBuilder rtsb = new ReflectionToStringBuilder(this);
    rtsb.setExcludeNullValues(true);
    return rtsb.toString();
  }
}
