package com.sss.iframework.driver;

import com.sss.iframework.IFrameworkConstants;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.internal.Killable;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class IFrameworkDriverManager {
	private static ThreadLocal<IFrameworkWebDriver> _iframeworkWebDriver = new ThreadLocal<IFrameworkWebDriver>();

	protected static WebDriver getDriver() {
		return _iframeworkWebDriver.get().getWebDriver();
	}

	public IFrameworkDriverManager() {
	}

	public static IFrameworkWebDriver getIFrameworkDriver() {
		return _iframeworkWebDriver.get();
	}

	public static void setIFrameworkDriver(IFrameworkWebDriver driver) {
		_iframeworkWebDriver.set(driver);
	}

	public static void removeDriver() {
		_iframeworkWebDriver.remove();
	}

	protected static boolean isBrowserStillOpen(WebDriver webDriver) {
		try {
			return ((RemoteWebDriver)webDriver).getSessionId() != null;
		} catch (UnreachableBrowserException e) {
			return false;
		} catch (NoSuchWindowException e) {
			return false;
		}
	}

	public static WebDriver getAndCheckWebDriver() throws Exception {
		WebDriver webDriver = getDriver();
		if (webDriver != null) {
			if (isBrowserStillOpen(webDriver)) {
				return webDriver;
			} else {
				System.out.println("Webdriver has been closed meanwhile. Let's re-create it.");
				closeWebDriver();
			}
		}

		getIFrameworkDriver().createWebDriver();
		return getDriver();
	}

	public static void closeWebDriver() {
		WebDriver webDriver = getDriver();

		if (webDriver != null) {
			System.out.println(" === CLOSE WEBDRIVER: " + Thread.currentThread().getId() + " -> " + webDriver);

			try {
				RemoteWebDriver remoteWebDriver = (RemoteWebDriver) webDriver;
				if(remoteWebDriver != null){
					if(remoteWebDriver.getSessionId() != null){
						boolean isSafariOniOS = getIFrameworkDriver().getDeviceName().equalsIgnoreCase(IFrameworkConstants.DEVICE_IOS)
								&& getIFrameworkDriver().getApplicationType().equalsIgnoreCase(IFrameworkConstants.APP_TYPE_SAFARI);
						if(isSafariOniOS){
							webDriver.close();
						}

						webDriver.quit();
					}
				}
			} catch (UnreachableBrowserException ignored) {
				// It happens for Firefox. It's ok: browser is already closed.
			} catch (WebDriverException cannotCloseBrowser) {
				System.err.println("Cannot close browser normally: "
						+ cannotCloseBrowser.getMessage());
			} finally {
				killBrowser(webDriver);
				webDriver = null;
			}
		}
	}

	protected static void killBrowser(WebDriver webdriver) {
		if (webdriver instanceof Killable) {
			try {
				((Killable) webdriver).kill();
			} catch (Exception e) {
				System.err.println("Failed to kill browser " + webdriver + ':');
				e.printStackTrace();
			}
		}
	}
}
