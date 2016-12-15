package com.sss.testng;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;
import org.uncommons.reportng.ReportNGException;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/** Created by vin on 11/5/16. */
public class IFrameworkCustomReportHTML extends HTMLReporter {
  @Override
  public void generateReport(
      List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectoryName) {
    boolean useFrames = System.getProperty("org.uncommons.reportng.frames", "true").equals("true");
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy - HH.mm.ss");
    Date date = new Date();
    File outputDirectory = new File(outputDirectoryName, "Report " + dateFormat.format(date));
    outputDirectory.mkdir();

    try {
      if (useFrames) {
        this.createFrameset(outputDirectory);
      }

      this.createOverview(suites, outputDirectory, !useFrames);
      this.createSuiteList(suites, outputDirectory);
      this.createGroups(suites, outputDirectory);
      this.createResults(suites, outputDirectory);
      this.createLog(outputDirectory);
      this.createEmailable(suites, outputDirectory);
      this.copyResources(outputDirectory);
    } catch (Exception var9) {
      throw new ReportNGException("Failed generating HTML report.", var9);
    }
  }

  private void createEmailable(List<ISuite> suites, File outputDirectory) throws Exception {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    ve.init();
    VelocityContext context = this.createContext();
    context.put("suites", suites);
    StringWriter w = new StringWriter();
    int index = 1;

    for (Iterator i$ = suites.iterator(); i$.hasNext(); ++index) {
      ISuite suite = (ISuite) i$.next();
      int index2 = 1;

      for (Iterator i$1 = suite.getResults().values().iterator(); i$1.hasNext(); ++index2) {
        ISuiteResult result = (ISuiteResult) i$1.next();
        context.put("result", result);
        context.put(
            "failedConfigurations",
            this.sortByTestClass(result.getTestContext().getFailedConfigurations()));
        context.put(
            "skippedConfigurations",
            this.sortByTestClass(result.getTestContext().getSkippedConfigurations()));
        context.put("failedTests", this.sortByTestClass(result.getTestContext().getFailedTests()));
        context.put(
            "skippedTests", this.sortByTestClass(result.getTestContext().getSkippedTests()));
        context.put("passedTests", this.sortByTestClass(result.getTestContext().getPassedTests()));
        Velocity.mergeTemplate("results.html.vm", "UTF-8", context, w);
      }
    }

    context.put("allResult", w);
    Template t = ve.getTemplate("emailable-report.html.vm");
    writeFile(
        ve,
        context,
        "emailable-report.html.vm",
        new File(outputDirectory, "emailable-report.html").getAbsolutePath());
  }

  private void writeFile(VelocityEngine ve, VelocityContext context, String vmPath, String fileName)
      throws ResourceNotFoundException, ParseErrorException, Exception {
    Template t = ve.getTemplate(vmPath);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    FileWriter fileWriter = new FileWriter(fileName, true);
    fileWriter.append(writer.toString());
    fileWriter.flush();
    fileWriter.close();
  }

  public Object sortByTestClass(IResultMap failedConfigurations) {
    return null;
  }

  public void createOverview(List<ISuite> suites, File outputDirectory, boolean b) {}

  public void createSuiteList(List<ISuite> suites, File outputDirectory) {}

  public void createGroups(List<ISuite> suites, File outputDirectory) {}

  public void createResults(List<ISuite> suites, File outputDirectory) {}

  public void createLog(File outputDirectory) {}

  public void copyResources(File outputDirectory) {}

  public void createFrameset(File outputDirectory) {}
}
