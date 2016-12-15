package com.sss.iframework.driver.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.IOException;

/** Created by vin on 11/9/16. */
public class IFrameworkAppium {
  public static String appiumServiceUrl;

  public IFrameworkAppium() {}

  public static void startServer() {

    //appium.node.js.exec.path
    System.setProperty(AppiumServiceBuilder.NODE_PATH, "/usr/local/bin/node");

    System.setProperty(
        AppiumServiceBuilder.APPIUM_PATH, "/usr/local/lib/node_modules/appium/build/lib/main.js");
    //Start Appium local sevice
    AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildDefaultService();
    appiumService.start();

    appiumServiceUrl = appiumService.getUrl().toString();
  }

  public static void stopServer() {
    String[] command = {"/usr/bin/killall", "-KILL", "node"};
    try {
      Runtime.getRuntime().exec(command);
      System.out.println("Appium server stopped.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
