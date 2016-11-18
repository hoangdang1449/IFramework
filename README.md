# IFramework

----------

> This is automation framework is built base on Selenium with Java language. The goal is making automation framework can reuse automation script for multiple platform and devices. It also include Appium for control mobile devices.


----------


[TOC]


----------

## Environment

- Java: 1.8.0+
- Selenium: 3.0.0+
- TestNG: 6.8+
- Appium: 1.5.3+

## Usage

Package IFramework is core of automation framework. It will provide features:
- Handle and manage selenium webdriver base on value user input at xml (driver). It also handle Appium
- Catch exception to loggin and handling
- Helper to read from xml and
`Framework also use log4j and testng as logging and manage test suites`

>IFramework only provide core library as backbone for automation framework. You can write other layer for testing approach which can use Keywork-Driven, BDD, TDD,... or any testing approach that you want. As example, I have write sample in repo call: Selenium-TestNG which will use IFramework as core and use Page Object Pattern, Keywork-Driven for testing approach.

## Overview
- Selenium with Java language
- TestNG which provide parallel execution and good support for executing test
- Custom report - HTML report and sending email report
- Design for easy extend and customize but still keep it stable and not effect to test script
- Can write test script with other test approach (Keyword-driven, BDD, API testing, Data-Driven…)

### Platform support

- Selenium: for both Mac and Windows
	-	Chrome
	-	Firefox
	-	Safari
	-	Edge
- Appium: 
	- Android
	- IOS

### IFramework Structure

![Alt text](1479441481178.png)

>Main: this is core of framework. It contain selenium, testng, log4j, utility and some of clash help to detect driver, platform, handler, report,...
>Test: this is for testing area. We will write test script, structure test data, interface (page object), method used for interact with application under test.

#### Main Package
![Alt text](1479441723275.png)

> - Handle all of driver type for framework  
> - Catch exceptions for framework 
> - Helper to read xml  
> - Mange driver

#### Test Package
![Alt text](1479441906031.png)

>This is just sample test structure. You can have other structure like BDD or something else if you want. IFramework flexible and can apply many test approach

## TestNG xml Example

### Single run test suite

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >

<suite name="IFramework" parallel="tests" thread-count="5">
	<listeners>
		<listener class-name="com.sss.testng.IFrameworkMethodListener" />
		<listener class-name="com.sss.testng.IFrameworkTestListener" />
		<listener class-name="com.sss.testng.IFrameworkTestSuiteListener" />
		<listener class-name="com.sss.testng.IFrameworkSendReport"/>
		<listener class-name="org.uncommons.reportng.HTMLReporter" />
	</listeners>

	<parameter name="log4j" value="true" />

	<test name="Selenium - TestNG - Chrome on PC">
		<parameter name="device" value="pc">
			<provider name="selenium" apptype="Chrome">
                 <driverexe>src/test/java/Resources/chromedriver</driverexe>
			</provider>
		</parameter>

		<classes>
			<class name="Tests.google.testing.uitesting">
			</class>
		</classes>
	</test>

</suite>

```

There are several listener class which have their own mission. `IFrameworkMethodListener`, `IFrameworkTestListener`,  `IFrameworkTestSuiteListener` used for listener test, test suite, and method are called and running. `IFrameworkSendReport` used for sending html report after execution. You can comment this listener if you don't want to send out report. In order to custom send report to particular email address you can edit at `IFrameworkSendEmail` with your address. `HTMLReporter` used for create html report after execution, it only create result after test.

`test` tag define test suite for running. It also contain `parameter` tag which have **name** and **value** for define: pc, mac, androi or ios. Inside this tag, we have `provider` tag which have **name** and **apptype** for define: selenium or appium (name), Chrome, Firefox, Safari, and Edge (apptype).

### Multiple run test suite

`class` for call test class which TestNG call to run. You can also **include** to run each test method or just **exclude** to remove any test method. Example:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >

<suite name="IFramework" parallel="tests" thread-count="5">
	<listeners>
		<listener class-name="com.sss.testng.IFrameworkMethodListener" />
		<listener class-name="com.sss.testng.IFrameworkTestListener" />
		<listener class-name="com.sss.testng.IFrameworkTestSuiteListener" />
		<listener class-name="com.sss.testng.IFrameworkSendReport"/>
		<listener class-name="org.uncommons.reportng.HTMLReporter" />
	</listeners>

	<parameter name="log4j" value="true" />

	<test name="Selenium - TestNG - Chrome on PC">
		<parameter name="device" value="pc">
			<provider name="selenium" apptype="Edge">
                 <driverexe>src/test/java/Resources/MicrosoftWebDriver.exe</driverexe>
			</provider>
		</parameter>

		<classes>
			<class name="Tests.google.testing.uitesting">
				<methods>
					<exclude name="TC003_Remove_User"></exclude>
					<include name="TC001_Navigate_Google_Home"></include>
					<include name="TC002_Remember_Me_checkbox"></include>
				</methods>
			</class>
		</classes>
	</test>

</suite>
```

You can add multiple test suite in same TestNG xml for parallel running test. Just add `<suite name="IFramework" parallel="tests" thread-count="5">` to open this feature of TestNG. 

### Run Android Native app

IFramework work well with Android Native app with Appium inside. It will call Appium service if it's installed in environment, so please install Appium service by NodeJs:

```
npm install -g appium
```

IFramework use same method for web browser and android native app. So you can run test with same test script for both web app and native app.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >

<suite name="IFramework">
	<listeners>
		<listener class-name="org.uncommons.reportng.HTMLReporter" />
		<listener class-name="com.sss.testng.IFrameworkMethodListener" />
		<listener class-name="com.sss.testng.IFrameworkTestListener" />
		<listener class-name="com.sss.testng.IFrameworkTestSuiteListener"/>
	</listeners>

	<test name="Selenium - TestNG">
		<parameter name="device" value="android">
			<provider name="appium" apptype="Native">
				<apppackage>com.mobile.seveneleven</apppackage>
				<appactivity>com.mobile.seveneleven.ui.shiftin.ShiftInActivity</appactivity>
                <devicename>SSS MiPad</devicename>
				<version>4.4.4</version>
			</provider>
		</parameter>
		<classes>
			<class name="Tests.SevenEleven.SmokeTest"></class>
		</classes>
	</test>

</suite> 
```