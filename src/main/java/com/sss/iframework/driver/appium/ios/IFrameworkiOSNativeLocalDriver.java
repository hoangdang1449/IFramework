package com.sss.iframework.driver.appium.ios;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkLocalDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IFrameworkiOSNativeLocalDriver extends IFrameworkLocalDriver {
  protected String _remoteAddress;
  protected String _appPath;

  public void setremoteaddress(String remoteAddress) {
    this._remoteAddress = remoteAddress;
  }

  public void setapppath(String appPath) {
    this._appPath = appPath;
  }

  public IFrameworkiOSNativeLocalDriver() {}

  @Override
  public String getDeviceName() {
    return IFrameworkConstants.DEVICE_IOS;
  } // end method

  @Override
  public String getProvider() {
    return "appium";
  }

  @Override
  public void createWebDriver() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("app", this._appPath);
    this._webDriver = new IOSDriver(new URL(this._remoteAddress), capabilities);
    System.out.println(this._webDriver.toString());
  }

  @Override
  public String getApplicationType() {
    return IFrameworkConstants.APP_TYPE_NATIVE_APP;
  }
} // end class
