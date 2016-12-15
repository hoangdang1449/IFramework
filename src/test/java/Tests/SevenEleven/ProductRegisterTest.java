package Tests.SevenEleven;

import Actions.Common.Common;
import Interfaces.sss.hq.HQFrontEndMainPage;
import Interfaces.sss.hq.HQLoginPage;
import com.sss.selenium.IFrameworkAutomation;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

/** Created by vin on 11/18/16. */
public class ProductRegisterTest {
  @AfterTest
  public void init() {
    Common.config();
  }

  @Test
  public void T001_Login() throws Exception {
    IFrameworkAutomation.openURL("https://dev-hq-frontend.ssf.vn/");
    HQLoginPage.login("admin4", "123456");
    HQFrontEndMainPage.createNormalMerchandiseProduct("Testing Automation");
  }
}
