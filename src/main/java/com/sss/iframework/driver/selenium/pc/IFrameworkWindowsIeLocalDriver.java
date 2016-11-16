package com.sss.iframework.driver.selenium.pc;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkLocalDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class IFrameworkWindowsIeLocalDriver extends IFrameworkLocalDriver {
	// TODO: hard code driver executable
	private String _ieDriverExecutable = "E:\\Workspace\\Eclipse workspace\\Selenium-TestNG\\Resources\\IEDriverServer.exe";

	public void setDriverExe(String driverExe) {
		this._ieDriverExecutable = driverExe;
	}

	@Override
	public String getDeviceName() {
		return IFrameworkConstants.DEVICE_PC;
	}

	public IFrameworkWindowsIeLocalDriver() {

	} // end method

	@Override
	public String getProvider() {
		return "selenium";
	}

	@Override
	public void createWebDriver() {
		File file = null;

		file = new File(_ieDriverExecutable);
		String sFile = file.getAbsolutePath();
		System.setProperty("webdriver.ie.driver", sFile);
		
		if(this._webDriver == null){
			System.out.println("*** IE driver is NULL");
		}else{
			System.out.println("*** IE driver is NOT NULL");
		}
		this._webDriver = new InternetExplorerDriver();		
		
		System.out.println(">>>>>>>>>>> IFrameworkWindowsIeLocalDriver - driver is CREATED!!");
	}
	
	@Override
	public String getApplicationType() {
		return IFrameworkConstants.APP_TYPE_IE;
	}
	
} // end class
