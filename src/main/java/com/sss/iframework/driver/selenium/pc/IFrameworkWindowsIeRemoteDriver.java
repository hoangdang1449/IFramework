package com.sss.iframework.driver.selenium.pc;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkRemoteDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class IFrameworkWindowsIeRemoteDriver extends IFrameworkRemoteDriver {

  @Override
  public String getDeviceName() {
    return IFrameworkConstants.DEVICE_PC;
  }

  public IFrameworkWindowsIeRemoteDriver() {} // end method

  @Override
  public String getProvider() {
    return "selenium";
  }

  @Override
  public void createWebDriver() throws MalformedURLException {
    Capabilities desiredCapabilities = DesiredCapabilities.internetExplorer();

    this._webDriver = new RemoteWebDriver(new URL(_hubUrl), desiredCapabilities);
  }

  @Override
  public String getApplicationType() {
    return IFrameworkConstants.APP_TYPE_IE;
  }
} // end class
