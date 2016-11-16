package Actions.Common;

public class Constants {

	//Variables for browser
	public static String FIREFOX_BROWSER = "Firefox";
	public static String IE_BROWSER = "IE";
	public static String CHROME_BROWSER = "Chrome";

  //Variable for url

	public static String SCRUM_BOARD_URL = "http://192.168.190.117:3333";
	public static String GOOGLE_URL = "http://www.google.com.vn/";
	public static String Username = "john";
	public static String Password ="tester";
	public static String YAHOO_URL ="http://login.yahoo.com";

	public static String TITLE_ERROR_IS_REQUIRED = "The Title field is required.";
	public static String DESCRIPTION_ERROR_IS_REQUIRED = "The Description field is required.";
	public static String ESTIMATE_ERROR_IS_REQUIRED = "The Estimate (Hour) field is required.";
	public static String SPENT_ERROR_IS_REQUIRED = "The Spent (Hour) field is required.";
	public static String ASSIGNEE_ERROR_IS_REQUIRED = "The Assignee field is required.";

	public static String TITLE_ERROR_IS_OUT_OF_RANGE = "Title must not be over 50 characters.";
	public static String DESCRIPTION_ERROR_IS_OUT_OF_RANGE = "Description must not be over 500 characters.";
	public static String ESTIMATE_ERROR_IS_OUT_OF_RANGE = "The field Estimate (Hour) must be between 0 and 48.";
	public static String SPENT_ERROR_IS_OUT_OF_RANGE = "The field Spent (Hour) must be between 0 and 48.";
	public static String ASSIGNEE_ERROR_IS_OUT_OF_RANGE = "The field Assignee must be a string or array type with a maximum length of '20'.";

	public static String ESTIMATE_ERROR_IS_INVALID = "The field Estimate (Hour) must be a number.";
	public static String SPENT_ERROR_IS_INVALID = "The field Spent (Hour) must be a number.";

	public static String USERNAME_ERROR_IS_REQUIRED = "The User Name field is required.";
	public static String PASSWORD_ERROR_IS_REQUIRED = "The Password field is required.";
	public static String LOGIN_ERROR = "Incorrect user name or password.";

	public static String REMEMBERME_LABEL = "Remember me";

	//---------- Test Data --------
	public static String STRING_GREATER_THAN_500_CHARACTERS = "In ABT keywords are called actions. Actions are the tasks that are executed in a test and function like building-blocks that can be combined in any order to design a test. This allows non-technical test engineers and business analysts to design their tests as a series of actions, and then execute the tests automatically without writing code.ABT test design takes place in a spreadsheet-like UI within TestArchitect. All test assets, actions, test data and any necessary GUI interface information, are stored in their own spreadsheets that are called �modules�. Users then create individual test modules using actions that call assets from the other modules when the tests are executed.";
	public static String STRING_GREATER_THAN_50_CHARACTERS = "Actions are the tasks that are executed in a test and function like building-blocks that can be combined in any order to design a test.";
	public static String VALID_INPUT_VALUE = null;
}
