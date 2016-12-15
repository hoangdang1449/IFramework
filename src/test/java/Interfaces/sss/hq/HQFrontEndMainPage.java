package Interfaces.sss.hq;

import org.openqa.selenium.By;

import com.sss.selenium.IFrameworkAutomation;
import com.sss.selenium.IFrameworkElementDefinition;

import org.openqa.selenium.WebElement;

/** Created by vin on 11/18/16. */
public class HQFrontEndMainPage {
  private static By triProduct =
      IFrameworkElementDefinition.Xpath("//a[@href='#/entity/list/product']");
  private static By btnCreateProduct =
      IFrameworkElementDefinition.Xpath("//button[@class='btn btn-success']");

  private static By txtProductShortNameVN =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[1]/div/div[1]/div/div/div[1]/input");
  private static By cboRetailBusinessType =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[1]/div/div[2]/div/div/select");
  private static By txtProductFullNameVN =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[1]/div/div/div[1]/input");
  private static By txtProductShortNameEN =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[2]/div/div/input");
  private static By txtProductFullNameEN =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[3]/div/div/input");
  private static By cbbProductSubCategory =
      IFrameworkElementDefinition.Xpath("//*[@id=\"react-select-5--value\"]/div[2]/input");
  private static By cboPreservationTemperature =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[8]/div/div/select");
  private static By cboCountryOfOrigin =
      IFrameworkElementDefinition.Xpath("//*[@id=\"react-select-6--value\"]/div[2]/input");
  private static By cboBarcodeIssuer =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[13]/div/div/select");
  private static By cboBrandName =
      IFrameworkElementDefinition.Xpath("//*[@id=\"react-select-7--value\"]/div[2]/input");
  private static By txtMiniumDisplayQty =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[15]/div/div/div[1]/input");
  private static By cboExpirationShelfLife =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[16]/div/div[1]/div/div/select");
  private static By txtTotalShelfLife =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[16]/div/div[2]/div/div/div[1]/input");
  private static By txtShippingLimitation =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[17]/div/div[1]/div/div[1]/div/div[1]/input");
  private static By txtSaleLimitation =
      IFrameworkElementDefinition.Xpath(
          "//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[17]/div/div[2]/div/div[1]/div/div[1]/input");
  private static By cboVATInputCode =
      IFrameworkElementDefinition.Xpath("//*[@id=\"react-select-8--value\"]/div[2]/input");
  private static By cboVATOutputCode =
      IFrameworkElementDefinition.Xpath("//*[@id=\"react-select-9--value\"]/div[2]/input");
  private static By btnSave =
      IFrameworkElementDefinition.Xpath("//button[contains(text(), 'Save And Move To Next Step')]");

  private static By itmChao =
      IFrameworkElementDefinition.Xpath("//*[contains(text(), 'Cafe lon')]");

  public static void createNormalMerchandiseProduct(String productName)
      throws InterruptedException {
    IFrameworkAutomation.click(triProduct);
    IFrameworkAutomation.click(btnCreateProduct);

    long a = System.currentTimeMillis() * 1000;

    IFrameworkAutomation.enter(txtProductShortNameVN, productName + a);
    IFrameworkAutomation.selectByVisibleText(cboRetailBusinessType, "Normal Merchandise");
    IFrameworkAutomation.enter(txtProductFullNameVN, productName);

    selectCustomCombobox(cbbProductSubCategory, "Sushi");

    IFrameworkAutomation.selectByVisibleText(cboPreservationTemperature, "Ambient");
    selectCustomCombobox(cboCountryOfOrigin, "Vietnam");

    IFrameworkAutomation.selectByVisibleText(cboBarcodeIssuer, "Manufacture");
    selectCustomCombobox(cboBrandName, "7-Select");
    IFrameworkAutomation.enter(txtMiniumDisplayQty, "10");
    IFrameworkAutomation.enter(txtTotalShelfLife, "250");
    IFrameworkAutomation.enter(txtShippingLimitation, "20");
    IFrameworkAutomation.enter(txtSaleLimitation, "30");

    selectCustomCombobox(cboVATInputCode, "Hóa đơn trực tiếp");
    selectCustomCombobox(cboVATOutputCode, "Không chịu thuế GTGT đầu ra");

    IFrameworkAutomation.click(btnSave);
  }

  public static void selectCustomCombobox(By element, String value) throws InterruptedException {
    //        IFrameworkAutomation.click(element);

    IFrameworkAutomation.waitForControl(element);
    WebElement webElement = IFrameworkAutomation.getDriver().findElement(element);

    if (webElement == null) {
      System.out.println("....enter - element: " + element.toString() + " - value: " + value);
    }

    webElement.sendKeys(value);
    Thread.sleep(5000);

    //Didn't work with Unicode character
    IFrameworkAutomation.click(By.xpath("//*[contains(text(), '" + value + "')]"));
  }
}
