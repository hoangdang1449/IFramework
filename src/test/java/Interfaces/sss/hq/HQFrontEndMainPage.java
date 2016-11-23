package Interfaces.sss.hq;

import com.sss.selenium.IFrameworkElementDefinition;
import org.openqa.selenium.By;

/**
 * Created by vin on 11/18/16.
 */
public class HQFrontEndMainPage {
    public static By triProduct = IFrameworkElementDefinition.Xpath("//a[@href='#/entity/list/product']");
    public static By btnCreateProduct = IFrameworkElementDefinition.Xpath("//a[@href='#/entity/create/product']");

}
