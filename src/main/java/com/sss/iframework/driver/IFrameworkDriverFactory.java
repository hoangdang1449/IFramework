package com.sss.iframework.driver;

import com.sss.iframework.helper.ClassLoaderHelper;
import com.sss.iframework.helper.ReflectionHelper;
import com.sss.iframework.helper.xml.DeviceConfigItem;

import java.net.URISyntaxException;
import java.util.Hashtable;

public class IFrameworkDriverFactory {
  public static IFrameworkWebDriver createInstance(DeviceConfigItem deviceConfigItem)
      throws InstantiationException, IllegalAccessException {
    IFrameworkWebDriver driver = null;
    String device = deviceConfigItem.getDevice();
    String provider = deviceConfigItem.getProvider();
    String appType = deviceConfigItem.getApptype();
    boolean isGrid = false;

    if (deviceConfigItem.getGridConfigItem() != null)
      isGrid = deviceConfigItem.getGridConfigItem().isUsingGrid();

    Class<?> driverClass;
    try {
      driverClass = ClassLoaderHelper.getDriverClass(device, provider, appType, isGrid);
      Object objDriver = driverClass.newInstance();
      //set all property
      Hashtable<String, String> providerProperties = deviceConfigItem.getProviderProperties();
		for (String attr : providerProperties.keySet()) {
			String value = providerProperties.get(attr);
			String setFuncName = String.format("set%s", attr);
			ReflectionHelper.invokeFunctionStr(objDriver, setFuncName, value, true);
		}

      if (isGrid) {
        ReflectionHelper.invokeFunctionEx(
            objDriver,
            "setHubInfo",
            deviceConfigItem.getGridConfigItem().getIp(),
            deviceConfigItem.getGridConfigItem().getPort());
      }
      driver = (IFrameworkWebDriver) objDriver;
    } catch (URISyntaxException e) {
      System.out.print("Cannot get configuration to create driver!!!");
    }

    return driver;
  }
}
