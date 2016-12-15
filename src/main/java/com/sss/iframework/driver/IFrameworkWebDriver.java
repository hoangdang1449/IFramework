package com.sss.iframework.driver;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class IFrameworkWebDriver {
  protected RemoteWebDriver _webDriver;

  public abstract String getDeviceName();

  public abstract String getProvider();

  public abstract String getApplicationType();

  public RemoteWebDriver getWebDriver() {
    return _webDriver;
  }

  public abstract void createWebDriver() throws Exception;
}
