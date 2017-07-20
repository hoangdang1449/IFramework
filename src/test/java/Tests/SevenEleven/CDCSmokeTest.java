package Tests.SevenEleven;

import Actions.Common.Common;
import Interfaces.sss.portal.CDCPortalMainPage;

import com.sss.selenium.IFrameworkAutomation;

import javax.swing.tree.ExpandVetoException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CDCSmokeTest {
  @BeforeTest
  public void init() throws Exception {
    Common.config();
    IFrameworkAutomation.open();
  }

  @Test
  public void TC001_CDC_smoke_test() throws Exception {
    IFrameworkAutomation.openURL("http://dev-cdc-frontend.seven-system.com");
    CDCPortalMainPage.loginCDC();

    IFrameworkAutomation.click(CDCPortalMainPage.linkCurrentRefill);
    IFrameworkAutomation.waitForControl(CDCPortalMainPage.linkInventoryMonitor);
  }

  @AfterTest
  public void tearDown() throws Exception {
    IFrameworkAutomation.close();
  }
}