package com.sss.iframework;

import org.openqa.selenium.WebDriver;

import com.sss.iframework.driver.IFrameworkDriverManager;

public class IFrameworkNavigator {

  /**
   * Open a web page or start native application on Max OS X
   *
   * @param url
   * @throws Exception
   */
  public static void open(String url) throws Exception {
    //TODO check the platform => throws warning
    //get exist WebDriver or create a new WebDriver
    WebDriver webDriver = IFrameworkDriverManager.getAndCheckWebDriver();
    webDriver.get(url);
    //use that driver to open new url
  }

  /**
   * Start native application on Android
   *
   * @param url
   * @throws Exception
   */
  public static void open() throws Exception {
    //TODO check the platform => throws warning
    //get exist WebDriver or create a new WebDriver
    IFrameworkDriverManager.getAndCheckWebDriver();
  }
}
