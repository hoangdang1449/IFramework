package com.sss.iframework.driver.selenium.mac;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkLocalDriver;
import com.sss.selenium.IFrameworkSetting;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class IFrameworkMacFirefoxLocalDriver extends IFrameworkLocalDriver {

  private String _geckoDriverExecutable;

  public void setDriverExe(String driverExe) {
    this._geckoDriverExecutable = driverExe;
  }

  @Override
  public String getDeviceName() {
    return IFrameworkConstants.DEVICE_MAC;
  }

  public IFrameworkMacFirefoxLocalDriver() {
    System.out.println("IFrameworkWindowsFirefoxLocalDriver - creator");
  }

  @Override
  public String getProvider() {
    return "selenium";
  }

  @Override
  public void createWebDriver() {
    File file = null;

    file = new File(_geckoDriverExecutable);
    String sFile = file.getAbsolutePath();
    System.setProperty("webdriver.gecko.driver", sFile);

    if (this._webDriver == null) {
      System.out.println("*** Gecko driver is NULL");
    } else {
      System.out.println("*** Gecko driver is NOT NULL");
    }
    try {
      this._webDriver = new FirefoxDriver();
    } catch (Exception ex) {
      System.err.println("FireFox - createWebDriver - exception: " + ex.getMessage());
    }
    _webDriver
        .manage()
        .timeouts()
        .pageLoadTimeout(IFrameworkSetting.getObjecWait(), TimeUnit.SECONDS);
  }

  @Override
  public String getApplicationType() {
    return IFrameworkConstants.APP_TYPE_FIREFOX;
  }
}
