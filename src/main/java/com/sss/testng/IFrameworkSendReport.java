package com.sss.testng;

import com.sss.utility.IFrameworkFileAndDirectory;
import com.sss.utility.IFrameworkSendEmail;
import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/** Created by vin on 11/6/16. */
public class IFrameworkSendReport extends HTMLReporter {

  public void generateReport(
      List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectoryName) {

    //Clean up directory before create HTML
    try {
      File outDirectory = new File(System.getProperty("user.dir") + "/out/");
      if (!outDirectory.exists()) {
        outDirectory.mkdir();
      }

      FileUtils.cleanDirectory(outDirectory);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //Create HTML report
    HTMLReporter a = new HTMLReporter();
    a.generateReport(xmlSuites, suites, System.getProperty("user.dir") + "/out/");

    //Get HTML report and send email
    IFrameworkFileAndDirectory iFile = new IFrameworkFileAndDirectory();
    try {
      String htmlFile = iFile.getLatestDirectory(System.getProperty("user.dir") + "/out");
      String fileContain =
          iFile.readFile(htmlFile + "/emailable-report.html", StandardCharsets.UTF_8);
      IFrameworkSendEmail iEmail = new IFrameworkSendEmail();
      iEmail.SendEmail(fileContain);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
