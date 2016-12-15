package com.sss.utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** Created by vin on 10/28/16. */
public class IFrameworkFileAndDirectory {
  public String getLatestDirectory(String outputDirectory) throws Exception {
    Path dir = Paths.get(outputDirectory);
    ArrayList<Path> files = new ArrayList<>();
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
      for (Path p : stream) {
        if (Files.isDirectory(p)) {
          files.add(p);
        }
      }
    }

    Collections.sort(
        files,
        new Comparator<Path>() {
          public int compare(Path o1, Path o2) {
            try {
              return Files.getLastModifiedTime(o1).compareTo(Files.getLastModifiedTime(o2));
            } catch (IOException e) {
              System.out.print("Cannot find any latest folder!!!");
            }
            return 0;
          }
        });

    return files.get(0).toString();
  }

  public String readFile(String path, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }
}
