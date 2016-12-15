package com.sss.testng;

import com.sss.selenium.IFrameworkSetting;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

import java.util.List;

public class IFrameworkMethodListener extends IFrameworkSetting implements IInvokedMethodListener {
  public void beforeInvocation(IInvokedMethod method, ITestResult result) {
    //issue: Run multiple test case on different browsers
    //parse test suite xml by using ITestListener

    /*XmlTest xml = result.getTestClass().getXmlTest();
      	String platForm = xml.getParameter("platform");
      	String xmlFile = xml.getSuite().getFileName();
      	Document doc = parseXml(xmlFile);
    boolean grid = xml.getParameter("grid").equals("1") ? true : false;
      	IFrameworkSetting.setPort(getGridPort(doc));
      	IFrameworkSetting.setRunWithGrid(grid);
      	IFrameworkSetting.setIpAddress(getGridIp(doc));
      	IFrameworkSetting.setUserBrowser(getBrowser(doc));
      	IFrameworkSetting.setPlatForm(platForm);
      	IFrameworkSetting.setRemoteAddress(getRemote(doc));
          log.debug("Before invocation of " + method.getTestMethod().getMethodName());*/
  }

  public void afterInvocation(IInvokedMethod method, ITestResult result) {
    log.debug("After invocation of " + method.getTestMethod().getMethodName());
    Reporter.setCurrentTestResult(result);
    if (method.isTestMethod()) {
      IFrameworkVerificationFailures allFailures = IFrameworkVerificationFailures.getFailures();

      // Add an existing failure for the result to the failure list.
      if (result.getThrowable() != null) {
        allFailures.addFailureForTest(result, result.getThrowable());
      }

      List<Throwable> failures = allFailures.getFailuresForTest(result);
      int size = failures.size();

      if (size > 0) {
        result.setStatus(ITestResult.FAILURE);
        if (size == 1) {
          result.setThrowable(failures.get(0));
        } else {
          StringBuffer message =
              new StringBuffer("Mulitple failures (").append(size).append("):\n");
          for (int failure = 0; failure < size - 1; failure++) {
            message.append("Failure ").append(failure + 1).append(" of ").append(size).append("\n");
            message.append(Utils.stackTrace(failures.get(failure), false)[1]).append("\n");
          }
          Throwable last = failures.get(size - 1);
          message.append("Failure ").append(size).append(" of ").append(size).append("\n");
          message.append(last.toString());
          Throwable merged = new Throwable(message.toString());
          merged.setStackTrace(last.getStackTrace());
          result.setThrowable(merged);
        }
      }
    }
  }

  private static final Log log = LogFactory.getLog(IFrameworkMethodListener.class);
}
