package Actions.google;

import Actions.Common.Constants;
import Interfaces.google.GoogleHomeEntity;
import com.sss.selenium.IFrameworkAutomation;

public class Google {
  public static synchronized Google getGoogleHome() {
    if (instance == null)
      instance = new Google();
    return instance;
  }

  private static Google instance = null;

  //----------------------------------- business actions -----------------------------------

  public void Open() throws Exception {
    IFrameworkAutomation.openURL(Constants.GOOGLE_URL);
  }

  public void Search(String a) throws Exception {
    IFrameworkAutomation.enter(GoogleHomeEntity.Search, a);
    IFrameworkAutomation.click(GoogleHomeEntity.Submit);
  }
}
