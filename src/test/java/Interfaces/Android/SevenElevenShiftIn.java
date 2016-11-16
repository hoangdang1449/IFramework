package Interfaces.Android;

import org.openqa.selenium.By;

/**
 * Created by vin on 11/7/16.
 */
public class SevenElevenShiftIn extends AndroidScreenEntity{
    public static By TxtTittle = ByUIA("text","LOGIN");
    public static By TxtShiftInbyID = ByUIA("text","Login by ID");
    public static By TxvID = By.xpath("//android.widget.RelativeLayout[@index='3']/android.widget.TextView[@index='1']");
    public static By Btn1 = By.xpath("//android.widget.Button[@text='1']");
    public static By Btn2 = By.xpath("//android.widget.Button[@text='2']");
    public static By Btn3 = By.xpath("//android.widget.Button[@text='3']");
    public static By Btn4 = By.xpath("//android.widget.Button[@text='4']");
}
