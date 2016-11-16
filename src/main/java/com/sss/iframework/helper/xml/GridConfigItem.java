package com.sss.iframework.helper.xml;

public class GridConfigItem extends ConfigItem {
	private String ip;
	private String port;
	private boolean usingGrid;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public boolean isUsingGrid() {
		return usingGrid;
	}

	public void setUsingGrid(boolean usingGrid) {
		this.usingGrid = usingGrid;
	}
}
