package com.sss.iframework.driver;

public abstract class IFrameworkRemoteDriver extends IFrameworkWebDriver {
  //default hub url
  protected String _hubUrl = "http://localhost:4444/wd/hub";

  public void setHubUrl(String hubUrl) {
    this._hubUrl = hubUrl;
  }

  public void setHubInfo(String ip, String port) {
    String _hubUrl = "http://%s:%s/wd/hub";
    this._hubUrl = String.format(_hubUrl, ip, port);
  }
}
