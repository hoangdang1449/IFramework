package com.sss.selenium;

import com.sss.iframework.IFrameworkNavigator;
import com.sss.iframework.driver.IFrameworkDriverManager;
import com.sss.iframework.driver.IFrameworkWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class IFrameworkAutomation {

  private static IFrameworkWebDriver getIFrameworkWebDriver() {
    return IFrameworkDriverManager.getIFrameworkDriver();
  } //end method

  //TODO: change public to private
  public static RemoteWebDriver getDriver() {
    return IFrameworkAutomation.getIFrameworkWebDriver().getWebDriver();
  }

  public static void open() {
    try {
      IFrameworkNavigator.open();
    } catch (Exception e) {
      System.err.println("Create WebDriver got error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void openURL(String URL) {
    try {
      IFrameworkNavigator.open(URL);
    } catch (Exception e) {
      System.err.println("Create WebDriver got error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static boolean waitForControl(By element, long timeout) {

    boolean isExist = false;
    try {
      IFrameworkAutomation.getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
      IFrameworkAutomation.getDriver().findElement(element);
      isExist = true;
    } catch (Exception e) {
      log.debug("waitForControl:" + e.getMessage());
      isExist = false;
    }
    return isExist;
  }

  public static boolean waitForControl(By element) {

    int timeout = IFrameworkSetting.getObjecWait();
    return waitForControl(element, timeout);
  }

  public static void waitForControlClose(By element) {
    int timeout = IFrameworkSetting.getObjecWait();
    try {
      WebDriverWait wait = new WebDriverWait(IFrameworkAutomation.getDriver(), timeout);
      wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    } catch (Exception e) {
      log.debug("waitForControlClose:" + e.getMessage());
    }
  }

  public static void waitForControlClose(By element, long timeout) {
    try {
      WebDriverWait wait = new WebDriverWait(IFrameworkAutomation.getDriver(), timeout);
      wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    } catch (Exception e) {
      log.debug("waitForControlClose:" + e.getMessage());
    }
  }

  public static boolean isPageDisplayed(String pageTitle) {

    boolean isExist = false;
    long timeout = IFrameworkSetting.getObjecWait() * 1000;
    try {
      IFrameworkAutomation.getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
      String title = IFrameworkAutomation.getDriver().getTitle();
      long getTickCount = System.currentTimeMillis();
      while (!title.equals(pageTitle)) {
        Thread.sleep(50);
        title = IFrameworkAutomation.getDriver().getTitle();
        if ((System.currentTimeMillis() - getTickCount) > timeout) {
          break;
        }
      }
      if (title.equals(pageTitle))
        isExist = true;
    } catch (Exception e) {
      isExist = false;
      System.err.println("isPageDisplayed: title=" + pageTitle + " - message: " + e.getMessage());
    }

    System.out.println(
        "isPageDisplayed - pageTitle: " + pageTitle + " >> " + isExist + " .." + Thread.currentThread().getId());
    return isExist;
  }

  public static void enter(By element, String value) {
    waitForControl(element);
    WebElement webElement = IFrameworkAutomation.getDriver().findElement(element);

    if (webElement == null) {
      System.out.println("....enter - element: " + element.toString() + " - value: " + value);
    }
    webElement.clear();
    webElement.sendKeys(value);
  }

  public static boolean isElementExists(By element) {
    return waitForControl(element);
  }

  public static boolean isElementDisplayed(By element) {
    boolean isDisplayed = false;
    boolean isExists = waitForControl(element);
    if (isExists) {
      try {
        isDisplayed = IFrameworkAutomation.getDriver().findElement(element).isDisplayed();
      } catch (Exception e) {
        log.debug("isElementDisplayed:" + e.getMessage());
      }
    }
    return isDisplayed;
  }

  public static void bringIntoView(By element) {
    waitForControl(element);
    WebElement webElement = IFrameworkAutomation.getDriver().findElement(element);
  }

  public static void clickByJS(By element) {
    System.out.println(">>>>> clickByJS");
    waitForControl(element);
    ((JavascriptExecutor) IFrameworkAutomation.getDriver()).executeScript("arguments[0].click();",
        IFrameworkAutomation.getDriver().findElement(element));
  }

  public static void click(By element) {
    waitForControl(element);
    WebElement webElement = IFrameworkAutomation.getDriver().findElement(element);

    System.out.println(">>> click: webElement: " + (webElement != null));

    try {
      webElement.click();
    } catch (ElementNotVisibleException ex) {
      System.err.println("click - error: " + ex.getMessage());
      clickByJS(element);
    }
  }

  public static void setCheckbox(By element, boolean isCheck) {
    if (!isChecked(element) && isCheck) {
      click(element);
    }
  }

  public static void select(By element, String value) {
    waitForControl(element);
    Select select = new Select(IFrameworkAutomation.getDriver().findElement(element));
    select.selectByValue(value);
  }

  public static void selectByVisibleText(By element, String text) {
    waitForControl(element);
    Select select = new Select(IFrameworkAutomation.getDriver().findElement(element));
    select.selectByVisibleText(text);
  }

  public static WebElement findElement(By element) {
    waitForControl(element);
    return IFrameworkAutomation.getDriver().findElement(element);
  }

  public static List<WebElement> findElements(By element) {
    waitForControl(element);
    return IFrameworkAutomation.getDriver().findElements(element);
  }

  public static String getTableCellValue(By element, int rowIndex, int colIndex) {
    String sResult = "";
    rowIndex = rowIndex - 1;
    colIndex = colIndex - 1;
    waitForControl(element);
    WebElement tableElement = findElement(element);
    List<WebElement> rows = tableElement.findElements(By.xpath("tbody/tr"));
    if (rowIndex > rows.size())
      return sResult;
    for (int i = 0; i < rows.size(); i++) {
      if (i == rowIndex) {
        WebElement row = rows.get(i);
        List<WebElement> cols = row.findElements(By.xpath("td"));
        if (colIndex > cols.size())
          return sResult;
        for (int j = 0; j < cols.size(); j++) {
          if (j == colIndex) {
            WebElement col = cols.get(j);
            sResult = col.getText();
            break;
          }
        }
        break;
      }
    }

    return sResult;
  }

  public static boolean isChecked(By element) {
    waitForControl(element);
    WebElement webElement = IFrameworkAutomation.getDriver().findElement(element);
    return webElement.isSelected();
  }

  public static String getSelectedComboboxItemText(By element) {
    String text = "";
    WebElement webElement = findElement(element);
    Select selectedValue = new Select(webElement);
    text = selectedValue.getFirstSelectedOption().getText();

    return text;
  }

  public static String getText(By element) {
    return findElement(element).getText();
  }

  public static void set(By element, boolean isCheck) {
    boolean check = isChecked(element);
    WebElement webElement = findElement(element);
    if ((isCheck && !check) || (!isCheck && check)) {
      webElement.click();
    }
  }

  public static void set(By element, boolean isCheck, boolean realState) {
    WebElement webElement = IFrameworkAutomation.getDriver().findElement(element);
    if ((isCheck && !realState) || (!isCheck && realState)) {
      webElement.click();
    }
  }

  public static String getAttribute(By element, String att) {
    waitForControl(element);
    WebElement webElement = IFrameworkAutomation.getDriver().findElement(element);
    return webElement.getAttribute(att);
  }

  public static void logReport(String report, Class<?> cls) {
    Log log = LogFactory.getLog(cls);
    log.info(report);
  }

  private static final Log log = LogFactory.getLog(IFrameworkAutomation.class);

  public static void focusElement(By element) {
    if (waitForControl(element)) {
      IFrameworkAutomation.getDriver().findElement(element).sendKeys(Keys.NULL);
    }
  }

  public static void selectDate(By element, String day, String month, String year) {
    String calYear = null;
    int expYear = 0;
    focusElement(element);

    if (!isElementExists(element)) {
      return;
    }

    click(By.className("datepicker-switch"));
    calYear = IFrameworkAutomation.getDriver().findElement(By.xpath("//div[2]/div[2]/table/thead/tr/th[2]")).getText();
    expYear = Integer.parseInt(calYear);
    try {
      int numSelectYear = Integer.parseInt(year) - Integer.parseInt(calYear);
      WebElement slectyear = IFrameworkAutomation.getDriver()
          .findElement(By.xpath("html/body/div[2]/div[1]/table/thead/tr[1]"));

      if (numSelectYear > 0) {
        for (int i = 0; i < numSelectYear; i++) {
          slectyear.findElement(By.className("next")).click();
        }
      } else if (numSelectYear < 0) {
        for (int i = 0; i > numSelectYear; i--) {
          slectyear.findElement(By.className("prev")).click();
        }
      }

      IFrameworkAutomation.getDriver()
          .findElement(By.xpath("//div[2]/div[2]/table/tbody/tr/td/span[" + (Integer.parseInt(month) + 2) + "]"))
          .click();
      IFrameworkAutomation.getDriver()
          .findElement(By.xpath("//table/tbody/tr/td[text()='" + day + "' and @class='day']")).click();
    } catch (Exception e) {
      System.out.println("msg : " + e.toString());
    }
  }

  public static void close() {
    IFrameworkDriverManager.closeWebDriver();
    //		IFrameworkAppium.stopServer();
  }
}
