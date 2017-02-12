package Tests.SevenEleven;

import com.sss.selenium.IFrameworkAutomation;
import com.sss.utility.IFrameworkRESTFullAPI;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by vin on 1/9/17.
 */
public class StoreOrderWorkFlowTest {
    @BeforeTest
    public void init() {
        IFrameworkAutomation.open();
    }

    @Test
    public void storeOrderWorkFlow() throws Exception {
        IFrameworkRESTFullAPI.getInstance().sendGet("");
    }

    @AfterTest
    public void tearDown() {
        IFrameworkAutomation.close();
    }
}
