package com.softdevelop.biomedplus.util;


import com.softdevelop.biomedplus.exception.GenericException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

  public void imageBuilder(MultipartFile image, String directory) {
    try{
      this.makeDirectoryIfNotExist(directory);
      Path fileNamePath = Paths.get(directory);
      String absoluteRoute = fileNamePath.toFile().getAbsolutePath();
      Path completeRoute = Paths.get(absoluteRoute.concat("\\")
          .concat(Objects.requireNonNull(image.getOriginalFilename())));
      Files.write(completeRoute, image.getBytes());
    } catch (IOException ex) {
      throw new GenericException("Failed creating image");
    }
  }

  public String readImageFromServer(String directory, String imageName) {
    try{
      String completeRoute = directory.concat("\\").concat(imageName);
      byte[] fileContent = FileUtils.readFileToByteArray(new File(completeRoute));
      return Base64.getEncoder().encodeToString(fileContent);
    } catch (IOException ex) {
      throw new GenericException("Failed returning image");
    }
  }
}
