package Tests.SevenEleven;

import Actions.Common.Common;
import com.sss.selenium.IFrameworkAutomation;
import com.sss.utility.IFrameworkRESTFullAPI;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/** Created by vin on 11/18/16. */
public class ProductRegisterTest {
    @BeforeTest
    public void init() throws Exception {
        Common.config();
    }

    @Test
    public void T001_Login() throws Exception {
        // IFrameworkAutomation.openURL("https://dev-hq-frontend.ssf.vn/");
        // HQLoginPage.login("admin4", "123456");
        // HQFrontEndMainPage.createNormalMerchandiseProduct("Testing Automation");
        IFrameworkRESTFullAPI.getInstance().sendGet("https://dev-hq-backend.ssf.vn/health");
    }

    @AfterTest
    public void tearDown() {
        IFrameworkAutomation.close();
    }
}
