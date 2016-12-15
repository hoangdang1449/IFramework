package com.sss.testng;

import com.sss.iframework.driver.IFrameworkDriverFactory;
import com.sss.iframework.driver.IFrameworkDriverManager;
import com.sss.iframework.driver.IFrameworkWebDriver;
import com.sss.iframework.helper.xml.DeviceConfigItem;
import com.sss.iframework.helper.xml.XMLParser;
import com.sss.selenium.IFrameworkSetting;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlTest;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IFrameworkTestListener extends IFrameworkSetting implements ITestListener {
  private static final Log logger = LogFactory.getLog(IFrameworkTestListener.class);

  @Override
  public void onFinish(ITestContext arg0) {}

  @Override
  public void onStart(ITestContext testContext) {
    String log =
        String.format(
            "Test[%s] - thread[%d] ", testContext.getName(), Thread.currentThread().getId());
    System.out.println(log);
    logger.info(log);

    XmlTest xml = testContext.getCurrentXmlTest();
    String testName = xml.getName();
    DeviceConfigItem deviceConfigItem = null;
    String xmlPath = testContext.getSuite().getXmlSuite().getFileName();

    try {
      deviceConfigItem = XMLParser.parseXMLToList(xmlPath, testName);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

      IFrameworkWebDriver iFrameworkWebDriver;
    try {
      iFrameworkWebDriver = IFrameworkDriverFactory.createInstance(deviceConfigItem);

      IFrameworkDriverManager.setIFrameworkDriver(iFrameworkWebDriver);

      String strLog =
          String.format(
              "Created IFrameworkDriver object[%d] - on thread[%d] ",
              iFrameworkWebDriver.hashCode(), Thread.currentThread().getId());
      System.out.println(strLog);
    } catch (InstantiationException | IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onTestFailure(ITestResult arg0) {
    //Get current WebDriver instance
    WebDriver driver = null;
    try {
      driver = IFrameworkDriverManager.getAndCheckWebDriver();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //Capture screen with TakesScreenshot
    File scrFile = null;
    if (driver != null) {
      scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }
    DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
    String destDir = System.getProperty("user.dir") + "/screenshot";
    new File(destDir).mkdirs();
    String destFile = dateFormat.format(new Date()) + ".png";
    //Save it
    try {
      FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
    } catch (IOException e) {
      e.printStackTrace();
    }

    //Convert image to base64 for including html report
    ByteArrayOutputStream imgFileStream = new ByteArrayOutputStream();
    BufferedImage image = null;
    String imgString = null;
    try {
      image = ImageIO.read(scrFile);
      ImageIO.write(image, "png", imgFileStream);
      byte[] imgByte = imgFileStream.toByteArray();
      imgString = Base64.encodeBase64String(imgByte);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.setProperty("org.uncommons.reportng.escape-output", "false");
    Reporter.log("<a> <img src='data:image/png;base64," + imgString + "' /> </a>");
    //IFrameworkSendEmail sendEmail = new IFrameworkSendEmail();
    //sendEmail.SendEmail(StringUtils.join(Reporter.getOutput(arg0), "\n"));
  }

  @Override
  public void onTestSkipped(ITestResult arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onTestStart(ITestResult arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onTestSuccess(ITestResult arg0) {
    // TODO Auto-generated method stub

  }
}
