package com.sample.report;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

public class reportListener implements IReporter{
   public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory) {
      
      //Iterating over each suite included in the test
      for (ISuite suite : suites) {
            
         //Following code gets the suite name
         String suiteName = suite.getName();
            
         //Getting the results for the said suite
         Map<String, ISuiteResult> suiteResults = suite.getResults();
         for (ISuiteResult sr : suiteResults.values()) {
            ITestContext tc = sr.getTestContext();
            System.out.println("******** Passed tests for suite '" + suiteName +
               "' is:" + tc.getPassedTests().getAllResults().size());
            System.out.println("******** Failed tests for suite '" + suiteName +
               "' is:" + tc.getFailedTests().getAllResults().size());
            System.out.println("******** Skipped tests for suite '" + suiteName +
               "' is:" + tc.getSkippedTests().getAllResults().size());
            Reporter.log("########Total passed: "+tc.getPassedTests().getAllResults().size());
            Reporter.log("########Total Failed: "+tc.getFailedTests().getAllResults().size());
            Reporter.log("########Total skipped: "+tc.getSkippedTests().getAllResults().size());
         }
      }
   }
}