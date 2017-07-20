package Tests.google.testing;

import Actions.Common.Common;
import Actions.google.PageFactory;
import Interfaces.google.GoogleHomeEntity;
import com.sss.selenium.IFrameworkAssert;
import com.sss.selenium.IFrameworkAutomation;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class uitesting {

  @BeforeClass
  public void set_up() throws Exception {
    Common.config();
    PageFactory.GoogleHome().Open();
  }

  @Test(description = "Navigate Google Home")
  public void TC001_Navigate_Google_Home() throws Exception {
    PageFactory.GoogleHome().Search("This is testing");
    // IFrameworkAutomation.waitForControl(GoogleHomeEntity.Search);
    Thread.sleep(5000);
    String txtSearch = IFrameworkAutomation.getText(GoogleHomeEntity.Search);
    IFrameworkAssert.verifyEquals(txtSearch, "This is testing");
  }

  @Test(description = "Check button Search")
  public void TC002_Checkk_button_Search() throws Exception {
    //		IFrameworkAssert.verifyFalse(true, true);
  }

  @AfterTest
  public void after_test() throws Exception {
    IFrameworkAutomation.close();
  }
}
