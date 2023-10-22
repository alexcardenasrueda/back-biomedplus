package com.softdevelop.biomedplus.util;

import java.io.File;
import org.springframework.stereotype.Component;

@Component
public class GenericUtilities {

  public boolean makeDirectoryIfNotExist(String imageDirectory) {
    boolean mkdir = false;
    File directory = new File(imageDirectory);
    if (!directory.exists()) {
      mkdir = directory.mkdir();
    }
    return mkdir;
  }

  public String getRandomString(int i) {
    String theAlphaNumericS;
    StringBuilder builder;

    theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789";

    // create the StringBuffer
    builder = new StringBuilder(i);

    for (int m = 0; m < i; m++) {
      // generate numeric
      int myindex = (int) (theAlphaNumericS.length() * Math.random());

      // add the characters
      builder.append(theAlphaNumericS.charAt(myindex));
    }

    return builder.toString();
  }
}
