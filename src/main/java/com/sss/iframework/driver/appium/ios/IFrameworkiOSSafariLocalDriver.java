package com.sss.iframework.driver.appium.ios;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkLocalDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IFrameworkiOSSafariLocalDriver extends IFrameworkLocalDriver {
  private String _remoteAddress;

  public void setRemoteAddress(String remoteAddress) {
    this._remoteAddress = remoteAddress;
  }

  public IFrameworkiOSSafariLocalDriver() {}

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
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "Safari");
    this._webDriver = new IOSDriver(new URL(_remoteAddress), desiredCapabilities);
    _webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  @Override
  public String getApplicationType() {
    return IFrameworkConstants.APP_TYPE_SAFARI;
  }
} // end class
