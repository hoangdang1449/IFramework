package com.sss.iframework.driver.selenium.pc;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkLocalDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class IFrameworkWindowsChromeLocalDriver extends IFrameworkLocalDriver {
  //TODO: hard code driver executable
  private String _chromeDriverExecutable;

  public void setDriverExe(String driverExe) {
    this._chromeDriverExecutable = driverExe;
  }

  @Override
  public String getDeviceName() {
    return IFrameworkConstants.DEVICE_PC;
  }

  public IFrameworkWindowsChromeLocalDriver() {} //end method

  @Override
  public String getProvider() {
    return "selenium";
  }

  @Override
  public void createWebDriver() {
    File file = null;

    file = new File(_chromeDriverExecutable);
    String sFile = file.getAbsolutePath();
    System.setProperty("webdriver.chrome.driver", sFile);

    if (this._webDriver == null) {
      System.out.println("*** Chrome driver is NULL");
    } else {
      System.out.println("*** Chrome driver is NOT NULL");
    }
    try {
      this._webDriver = new ChromeDriver();
    } catch (Exception ex) {
      System.out.println("Chrome - createWebDriver - exception: " + ex.getMessage());
    }
    System.out.println("[info] Chrome driver is created.");
  }

  @Override
  public String getApplicationType() {
    return IFrameworkConstants.APP_TYPE_CHROME;
  }
} //end class
