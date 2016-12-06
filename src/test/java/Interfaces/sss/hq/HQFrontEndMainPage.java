package Interfaces.sss.hq;

import com.sss.selenium.IFrameworkAutomation;
import com.sss.selenium.IFrameworkElementDefinition;
import org.openqa.selenium.By;

/**
 * Created by vin on 11/18/16.
 */
public class HQFrontEndMainPage {
    private static By triProduct = IFrameworkElementDefinition.Xpath("//a[@href='#/entity/list/product']");
    private static By btnCreateProduct = IFrameworkElementDefinition.Xpath("//button[@class='btn btn-success']");

    private static By txtProductShortNameVN = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[1]/div/div[1]/div/div/input");
    private static By cboRetailBusinessType = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[1]/div/div[2]/div/div/select");
    private static By txtProductFullNameVN = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[1]/div/div/input");
    private static By txtProductShortNameEN = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[2]/div/div/input");
    private static By txtProductFullNameEN = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[3]/div/div/input");
    private static By cbbProductSubCategory = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div[4]/div/div/div");
    private static By cboPreservationTemperature = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[8]/div/div/select");
    private static By cboCountryOfOrigin = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[12]/div/div/div[1]");
    private static By cboBarcodeIssuer = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[13]/div/div/select");
    private static By cboBrandName = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[14]/div/div/div[1]/div");
    private static By txtMiniumDisplayQty = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[15]/div/div/input");
    private static By cboExpirationShelfLife = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[16]/div/div[1]/div/div/select");
    private static By txtTotalShelfLife = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[16]/div/div[2]/div/div/input");
    private static By txtShippingLimitation = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[17]/div/div[1]/div/div[1]/div/input");
    private static By txtSaleLimitation = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[17]/div/div[2]/div/div[1]/div/input");
    private static By cboVATInputCode = IFrameworkElementDefinition.Xpath("    private static By cboVATInputCode = IFrameworkElementDefinition.Xpath(\"\"//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[18]/div/div/div[1]/div)\n");
    private static By cboVATOutputCode = IFrameworkElementDefinition.Xpath("//*[@id=\"app\"]/div/div/div/div/section[2]/div[3]/div[2]/div/div/div/div/div[19]/div/div/div[1]/div");
    private static By btnSave = IFrameworkElementDefinition.Xpath("//button[contains(text(), 'Save And Move To Next Step')]");

    private static By itmChao = IFrameworkElementDefinition.Xpath("//*[contains(text(), 'Cafe lon')]");


    public static void createProduct (){
        IFrameworkAutomation.click(triProduct);
        IFrameworkAutomation.click(btnCreateProduct);

        IFrameworkAutomation.enter(txtProductShortNameVN, "Cháo dinh dưỡng");
        IFrameworkAutomation.selectByVisibleText(cboRetailBusinessType, "BOM");
        IFrameworkAutomation.enter(txtProductFullNameVN, "");

        IFrameworkAutomation.click(cbbProductSubCategory);
        IFrameworkAutomation.enter(cbbProductSubCategory, "");
//        IFrameworkAutomation.click();

        IFrameworkAutomation.click(btnSave);
    }
}
