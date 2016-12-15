package com.sss.iframework.driver.selenium.pc;

import com.sss.iframework.IFrameworkConstants;
import com.sss.iframework.driver.IFrameworkLocalDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;

/** Created by vin on 11/17/16. */
public class IFrameworkWindowsEdgeLocalDriver extends IFrameworkLocalDriver {
  private String _edgeDriverExecutable;

  public void setDriverExe(String driverExe) {
    this._edgeDriverExecutable = driverExe;
  }

  public IFrameworkWindowsEdgeLocalDriver() {};

  @Override
  public String getDeviceName() {
    return IFrameworkConstants.DEVICE_PC;
  }

  @Override
  public String getProvider() {
    return "selenium";
  }

  @Override
  public String getApplicationType() {
    return IFrameworkConstants.APP_TYPE_EDGE;
  }

  @Override
  public void createWebDriver() throws Exception {

    File file = null;

    file = new File(_edgeDriverExecutable);
    String sFile = file.getAbsolutePath();
    System.setProperty("webdriver.edge.driver", sFile);

    if (this._webDriver == null) {
      System.out.println("*** Edge driver is NULL");
    } else {
      System.out.println("*** Edge driver is NOT NULL");
    }
    this._webDriver = new EdgeDriver();

    System.out.println(">>>>>>>>>>> IFrameworkWindowsEdgeLocalDriver - driver is CREATED!!");
  }
}
