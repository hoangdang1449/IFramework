package com.sss.testng;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.testng.IReporter;
import org.uncommons.reportng.ReportMetadata;
import org.uncommons.reportng.ReportNGException;
import org.uncommons.reportng.ReportNGUtils;

import java.io.*;
import java.util.ResourceBundle;

/** Created by vin on 11/6/16. */
public abstract class IFrameworkAbstractReporter implements IReporter {
  private static final String ENCODING = "UTF-8";
  protected static final String TEMPLATE_EXTENSION = ".vm";
  private static final String META_KEY = "meta";
  private static final ReportMetadata META = new ReportMetadata();
  private static final String UTILS_KEY = "utils";
  private static final ReportNGUtils UTILS = new ReportNGUtils();
  private static final String MESSAGES_KEY = "messages";
  private static final ResourceBundle MESSAGES;
  private final String classpathPrefix;

  protected IFrameworkAbstractReporter(String classpathPrefix) {
    this.classpathPrefix = classpathPrefix;
    Velocity.setProperty("resource.loader", "classpath");
    Velocity.setProperty(
        "classpath.resource.loader.class",
        "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    if (!META.shouldGenerateVelocityLog()) {
      Velocity.setProperty(
          "runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
    }

    try {
      Velocity.init();
    } catch (Exception var3) {
      throw new ReportNGException("Failed to initialise Velocity.", var3);
    }
  }

  protected VelocityContext createContext() {
    VelocityContext context = new VelocityContext();
    context.put("meta", META);
    context.put("utils", UTILS);
    context.put("messages", MESSAGES);
    return context;
  }

  protected void generateFile(File file, String templateName, VelocityContext context)
      throws Exception {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));

    try {
      Velocity.mergeTemplate(this.classpathPrefix + templateName, "UTF-8", context, writer);
      writer.flush();
    } finally {
      writer.close();
    }
  }

  protected void copyClasspathResource(
      File outputDirectory, String resourceName, String targetFileName) throws IOException {
    String resourcePath = this.classpathPrefix + resourceName;
    InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath);
    this.copyStream(outputDirectory, resourceStream, targetFileName);
  }

  protected void copyFile(File outputDirectory, File sourceFile, String targetFileName)
      throws IOException {
    FileInputStream fileStream = new FileInputStream(sourceFile);

    try {
      this.copyStream(outputDirectory, fileStream, targetFileName);
    } finally {
      fileStream.close();
    }
  }

  protected void copyStream(File outputDirectory, InputStream stream, String targetFileName)
      throws IOException {
    File resourceFile = new File(outputDirectory, targetFileName);
    BufferedReader reader = null;
    BufferedWriter writer = null;

    try {
      reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
      writer =
          new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resourceFile), "UTF-8"));

      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        writer.write(line);
        writer.write(10);
      }

      writer.flush();
    } finally {
      if (reader != null) {
        reader.close();
      }

      if (writer != null) {
        writer.close();
      }
    }
  }

  protected void removeEmptyDirectories(File outputDirectory) {
    if (outputDirectory.exists()) {
      File[] arr$ =
          outputDirectory.listFiles(new IFrameworkAbstractReporter.EmptyDirectoryFilter());
      int len$ = arr$.length;

      for (int i$ = 0; i$ < len$; ++i$) {
        File file = arr$[i$];
        file.delete();
      }
    }
  }

  static {
    MESSAGES =
        ResourceBundle.getBundle("org.uncommons.reportng.messages.reportng", META.getLocale());
  }

  private static final class EmptyDirectoryFilter implements FileFilter {
    private EmptyDirectoryFilter() {}

    public boolean accept(File file) {
      return file.isDirectory() && file.listFiles().length == 0;
    }
  }
}
