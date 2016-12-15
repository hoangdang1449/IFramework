package com.sss.testng;

import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IFrameworkVerificationFailures extends HashMap<ITestResult, List<Throwable>> {
  private static final long serialVersionUID = 1L;

  public static IFrameworkVerificationFailures getFailures() {
    if (failures == null) {
      failures = new IFrameworkVerificationFailures();
    }
    return failures;
  }

  List<Throwable> getFailuresForTest(ITestResult result) {
    List<Throwable> exceptions = get(result);
    return exceptions == null ? new ArrayList<Throwable>() : exceptions;
  }

  public void addFailureForTest(ITestResult result, Throwable throwable) {
    List<Throwable> exceptions = getFailuresForTest(result);
    exceptions.add(throwable);
    put(result, exceptions);
  }

  private IFrameworkVerificationFailures() {
    super();
  }

  private static IFrameworkVerificationFailures failures;
}
