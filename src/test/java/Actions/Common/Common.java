package Actions.Common;

import com.sss.selenium.IFrameworkSetting;

public class Common {

	public static void config() {
		// Configure browser to launch
		IFrameworkSetting.setObjecWait(15);
		/*IFrameworkSetting.setChromeDriverExecutable(new File("Resources","chromedriver.exe").getAbsolutePath());
		IFrameworkSetting.setIeDriverExecutable(new File("Resources","IEDriverServer.exe").getAbsolutePath());*/
		
	}

}
