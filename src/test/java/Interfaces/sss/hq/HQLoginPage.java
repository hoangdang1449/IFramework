package Interfaces.sss.hq;

import com.sss.selenium.IFrameworkAutomation;
import com.sss.selenium.IFrameworkElementDefinition;
import org.openqa.selenium.By;

/** Created by vin on 11/16/16. */
public class HQLoginPage {
  public static By txbUser = IFrameworkElementDefinition.Xpath("//input[@type='text' and @placeholder='Username']");
  public static By txbPass = IFrameworkElementDefinition.Xpath("//input[@type='password' and @placeholder='Password']");
  public static By btnLogin = IFrameworkElementDefinition.Xpath("//input[@type='button' and @value='Log in']");

  public static void login(String user, String pass) throws Exception {
    IFrameworkAutomation.waitForControl(txbUser);

    IFrameworkAutomation.enter(txbUser, user);
    IFrameworkAutomation.enter(txbPass, pass);
    IFrameworkAutomation.click(btnLogin);
  }
}
