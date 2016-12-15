package com.sss.iframework.driver.appium.android;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkLocalDriver;
import com.sss.iframework.driver.appium.IFrameworkAppium;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IFrameworkAndroidNativeLocalDriver extends IFrameworkLocalDriver {
  protected String _remoteAddress;
  protected String _appPackage;
  protected String _appActivity;
  protected String _version;
  protected String _deviceName;
  protected String _app;

  public void setRemoteAddress(String remoteAddress) {
    this._remoteAddress = remoteAddress;
  }

  public void setAppPackage(String appPackage) {
    this._appPackage = appPackage;
  }

  public void setAppActivity(String appActivity) {
    this._appActivity = appActivity;
  }

  public void setVersion(String version) {
    _version = version;
  }

  public void setDeviceName(String deviceName) {
    _deviceName = deviceName;
  }

  public void setApp(String app) {
    _app = app;
  }

  public IFrameworkAndroidNativeLocalDriver() {}

  @Override
  public String getDeviceName() {
    return IFrameworkConstants.DEVICE_ANDROID;
  }

  @Override
  public String getProvider() {
    return "appium";
  }

  @Override
  public void createWebDriver() throws MalformedURLException {
    IFrameworkAppium appium = new IFrameworkAppium();
    IFrameworkAppium.startServer();

    //Get appium local service after start
    String appiumServiceUrl = IFrameworkAppium.appiumServiceUrl;
    System.out.println("Appium service started at " + appiumServiceUrl);

    DesiredCapabilities capabilities = DesiredCapabilities.android();
    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
    capabilities.setCapability("appPackage", _appPackage);
    capabilities.setCapability("appActivity", _appActivity);
    capabilities.setCapability(CapabilityType.VERSION, _version);
    capabilities.setCapability("deviceName", _deviceName);
    capabilities.setCapability("app", _app);
    capabilities.setCapability("noReset", "true");
    capabilities.setCapability("fullReset", "false");

    //		this._webDriver = new AndroidDriver(new URL(_remoteAddress),
    //				capabilities);
    this._webDriver = new AndroidDriver(new URL(appiumServiceUrl), capabilities);
    if (_webDriver.getSessionId() == null) {
      System.out.println("IFrameworkAndroidNativeLocalDriver - closed!");
    } else {
      System.out.println("IFrameworkAndroidNativeLocalDriver - OKKK!");
    }
  }

  @Override
  public String getApplicationType() {
    return IFrameworkConstants.APP_TYPE_NATIVE_APP;
  }
} // end class
