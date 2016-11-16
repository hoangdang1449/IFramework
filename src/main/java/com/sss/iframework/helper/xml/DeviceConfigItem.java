package com.sss.iframework.helper.xml;

import java.util.Hashtable;

public class DeviceConfigItem extends ConfigItem {
	private String device;
	private String provider;
	private String apptype;
	private Hashtable<String, String> providerProperty;
	private GridConfigItem _gridConfigItem;

	public DeviceConfigItem() {
		providerProperty = new Hashtable<String, String>();
	}

	public void setGridConfigItem(GridConfigItem gridConfigItem){
		_gridConfigItem = gridConfigItem;
	}

	public GridConfigItem getGridConfigItem(){
		return _gridConfigItem;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProviderProperty(String name) {
		return providerProperty.get(name);
	}

	public void setProviderProperty(String key, String value) {
		providerProperty.put(key, value);
	}

	public Hashtable<String, String> getProviderProperties(){
		return providerProperty;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
}
