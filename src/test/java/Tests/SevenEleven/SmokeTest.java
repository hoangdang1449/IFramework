package Tests.SevenEleven;

import Actions.Common.Common;
import Interfaces.Android.SevenElevenShiftIn;
import com.sss.selenium.IFrameworkAssert;
import com.sss.selenium.IFrameworkAutomation;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sss.selenium.*;

/** Created by vin on 11/7/16. */
public class SmokeTest {
  @BeforeClass
  public void set_up() throws Exception {
    Common.config();
    IFrameworkAutomation.open();
  }

  @Test(description = "Navigate SevenEleven Shift In")
  public void TC001_Navigate_SeveEleven_Shift_In() throws Exception {
    IFrameworkAutomation.isElementExists(SevenElevenShiftIn.TxtTittle);
    IFrameworkAutomation.click(SevenElevenShiftIn.TxtShiftInbyID);
    IFrameworkAutomation.isElementExists(SevenElevenShiftIn.TxvID);
    IFrameworkAutomation.isElementExists(SevenElevenShiftIn.Btn1);

    IFrameworkAutomation.click(SevenElevenShiftIn.Btn1);
    IFrameworkAutomation.getText(SevenElevenShiftIn.TxvID);
    IFrameworkAutomation.click(SevenElevenShiftIn.Btn2);
    IFrameworkAutomation.click(SevenElevenShiftIn.Btn3);
    IFrameworkAutomation.click(SevenElevenShiftIn.Btn4);

    String a = IFrameworkAutomation.getText(SevenElevenShiftIn.TxvID);
    IFrameworkAssert.verifyEquals(a, "12345");
  }

  @AfterTest
  public void after_test() throws Exception {
    IFrameworkAutomation.close();
  }
}
