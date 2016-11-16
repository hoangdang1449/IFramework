package com.sss.iframework.driver.appium.android;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkLocalDriver;
import com.sss.iframework.driver.appium.IFrameworkAppium;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IFrameworkAndroidChromeLocalDriver extends IFrameworkLocalDriver {
	protected String _remoteAddress;
	protected String _deviceName;

	public void setRemoteAddress(String remoteAddress) {
		this._remoteAddress = remoteAddress;
	}

	public IFrameworkAndroidChromeLocalDriver() {
	}

	@Override
	public String getDeviceName() {
		return IFrameworkConstants.DEVICE_ANDROID;
	} // end method

	@Override
	public String getProvider() {
		return "appium";
	}

	@Override
	public void createWebDriver() throws MalformedURLException {
        IFrameworkAppium appium = new IFrameworkAppium();
		appium.startServer();

		//Get appium local service after start
		String appiumServiceUrl = IFrameworkAppium.appiumServiceUrl;
		System.out.println("Appium service started at " + appiumServiceUrl);

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		capabilities.setCapability(CapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability("deviceName", "SSS Mi");
		capabilities.setCapability("noReset", "true");
//		this._webDriver = new AndroidDriver(new URL(_remoteAddress),
//				capabilities);
		this._webDriver = new AndroidDriver(new URL(appiumServiceUrl),
				capabilities);

	}

	@Override
	public String getApplicationType() {
		return IFrameworkConstants.APP_TYPE_CHROME;
	}

} // end class
