package com.softdevelop.biomedplus.util.logs;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedMap;
import org.apache.commons.lang3.StringUtils;

public class LoggerUtil {
  private static final int INITIAL_LOG_BUFFER = 256;
  private static final char WRAP_CHAR = '"';
  private static final char WHITE_SPACE = ' ';

  private LoggerUtil() {
  }

  public static String getStackTrace(Exception exception) {
    return getStackTrace((Throwable)exception);
  }

  public static String getStackTrace(Throwable throwable) {
    StringWriter writer = new StringWriter();
    throwable.printStackTrace(new PrintWriter(writer));
    return writer.toString();
  }

  public static String buildLoggerMessageAsString(Map<String, String> fields) {
    StringBuilder sb = new StringBuilder(256);
    Iterator var2 = fields.entrySet().iterator();

    while(var2.hasNext()) {
      Map.Entry<String, String> entry = (Map.Entry)var2.next();
      String value = StringUtils.wrap((String)entry.getValue(), '"');
      sb.append((String)entry.getKey()).append('=').append(value).append(" ");
    }

    return sb.deleteCharAt(sb.length() - 1).toString();
  }

  public static String buildEntityAsString(String entityName, SortedMap<String, String> propertiesMap) {
    StringBuilder sb = new StringBuilder(entityName);
    Iterator var3 = propertiesMap.entrySet().iterator();

    while(var3.hasNext()) {
      Map.Entry<String, String> entry = (Map.Entry)var3.next();
      sb.append("|").append((String)entry.getKey()).append(":").append((String)entry.getValue());
    }

    return sanitizeLogElement(sb.toString());
  }

  public static String sanitizeLogElement(String element) {
    if (Objects.isNull(element)) {
      return null;
    } else {
      char[] chars = element.toCharArray();
      int invalidCharsCount = getInvalidCharsCount(chars);
      if (invalidCharsCount <= 0) {
        return element;
      } else {
        char[] validChars = new char[chars.length - invalidCharsCount];
        int pos = 0;

        for(int i = 0; i < chars.length; ++i) {
          if (chars[i] != 0) {
            validChars[pos] = chars[i];
            ++pos;
          }
        }

        return String.valueOf(validChars);
      }
    }
  }

  private static int getInvalidCharsCount(char[] chars) {
    int invalidCharsCount = 0;

    for(int i = 0; i < chars.length; ++i) {
      if (chars[i] < ' ') {
        ++invalidCharsCount;
        chars[i] = 0;
      }
    }

    return invalidCharsCount;
  }

  public static String obfuscate(String s, MaskingMethod maskingMethod) {
    return StringUtils.isBlank(s) ? s : ((MaskingMethod)Optional.ofNullable(maskingMethod).orElse(MaskingMethod.FULL)).maskingValue(s);
  }
}