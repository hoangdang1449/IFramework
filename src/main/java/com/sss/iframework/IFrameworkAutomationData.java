package com.sss.iframework;

import com.sss.iframework.helper.xml.DeviceConfigItem;
import com.sss.selenium.IFrameworkAutomation;

public class IFrameworkAutomationData {
	protected IFrameworkAutomation _automation;
	protected DeviceConfigItem _deviceConfigItem;

  public IFrameworkAutomationData(IFrameworkAutomation iFrameworkAutomation, DeviceConfigItem deviceConfigItem){
		_automation = iFrameworkAutomation;
		_deviceConfigItem = deviceConfigItem;
	}

	public IFrameworkAutomation getIFrameworkAutomation(){
		return _automation;
	}

	public DeviceConfigItem getDeviceConfigItem(){
		return _deviceConfigItem;
	}
}
