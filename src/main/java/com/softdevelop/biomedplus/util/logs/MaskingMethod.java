package com.softdevelop.biomedplus.util.logs;

public enum MaskingMethod {
  FULL(".", "*"),
  PHONE("\\b(\\d{2})\\d+(\\d)", "$1*******$2"),
  EMAIL("(?<=.)[^@](?=[^@]*?@)|(?:(?<=@.)|(?!^)\\G(?=[^@]*$)).(?=.*\\.)", "*"),
  STRING("(?<=..).", "*");

  private String regex;
  private String replacement;

  private MaskingMethod(String regex, String replacement) {
    this.regex = regex;
    this.replacement = replacement;
  }

  public String maskingValue(String value) {
    return value.replaceAll(this.regex, this.replacement);
  }
}
