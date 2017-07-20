package Interfaces.sss.portal;

import org.openqa.selenium.By;

import com.sss.selenium.IFrameworkAutomation;
import com.sss.selenium.IFrameworkElementDefinition;

import org.openqa.selenium.WebElement;

public class CDCPortalMainPage {
  //Login page
  public static By inputUserName = IFrameworkElementDefinition.CssSelector("input[placeholder='Username']");
  public static By inputPassword = IFrameworkElementDefinition.CssSelector("input[type='password']");
  public static By buttonLogin = IFrameworkElementDefinition.CssSelector("input[type='button'][value='LOGIN']");

  //Main Page menu items
  public static By linkTodayDelivery = IFrameworkElementDefinition.CssSelector("a[href='#/today_delivery']");
  public static By linkPreviousDelivery = IFrameworkElementDefinition.CssSelector("a[href='#/previous_deliveries']");
  public static By linkCurrentRefill = IFrameworkElementDefinition.CssSelector("a[href='#/refill']");
  public static By linkHistory = IFrameworkElementDefinition.CssSelector("a[href='#/history_refill']");
  public static By linkInventoryMonitor = IFrameworkElementDefinition.CssSelector("a[href='#/inventory_monitor']");
  public static By linkDailyInventoryDetail = IFrameworkElementDefinition
      .CssSelector("a[href='#/daily_inventory_detail']");
  public static By linkInventoryChange = IFrameworkElementDefinition.CssSelector("a[href='#/inventory_change']");
  public static By linkMasterDataExport = IFrameworkElementDefinition.CssSelector("a[href='#/master-data-export']");

  //Business actions
  public static void loginCDC() {
    IFrameworkAutomation.enter(inputUserName, "cdc1@ssv.com");
    IFrameworkAutomation.enter(inputPassword, "123456");
    IFrameworkAutomation.click(buttonLogin);
  }
}