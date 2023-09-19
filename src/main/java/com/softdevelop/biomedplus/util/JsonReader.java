package com.softdevelop.biomedplus.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mccue.guava.io.Resources;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonReader {

  /**
   * Search the json to read and sets the output.
   *
   * @param path uri to the json file.
   * @param clazz format of the transformation.
   * @param <T> class type for the transformation.
   * @return the object from the json file.
   * @throws IOException can throw some exceptions to format or file not found.
   */
  public static <T> T readObject(String path, Class<T> clazz) throws IOException {
    URL url = Resources.getResource(path);
    String text = Resources.toString(url, StandardCharsets.UTF_8);
    return new ObjectMapper().readValue(text, clazz);
  }
}