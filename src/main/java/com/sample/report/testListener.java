package com.sample.report;
import com.sample.util.logs4jUtil;
import org.testng.*;

public class testListener implements ITestListener	,ISuiteListener
{		
	public static logs4jUtil Log4j = new logs4jUtil();
    public String TCName = null;
	
	public void onStart(ISuite arg0) {

        TCName =arg0.getName();
		Reporter.log("About to begin executing Suite " + arg0.getName(), true);

	}
//
//	// This belongs to ISuiteListener and will execute, once the Suite is finished
//
//	public void onFinish(ISuite arg0) {
//
//		Reporter.log("About to end executing Suite " + arg0.getName(), true);
//
//	}
//	
//    public void onFinish(ITestContext Result) 					
//    {		
//                		
//    }		
//
//    public void onStart(ITestContext Result)					
//    {		
//            		
//    }		
//
//    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)					
//    {		
//    	
//    }		

    // When Test case get failed, this method is called.		
  	
    public void onTestFailure(ITestResult Result) 					
    {		
    System.out.println("The name of the testcase failed is :"+Result.getName());	
    Log4j.LogSumInfo("Failed Test Name: "+Result.getName());
    Log4j.LogSumInfo("Parameters passed as below: ");
    Object[] ls = Result.getParameters();
    for( Object s: ls)
    {
    	Log4j.LogSumInfo(s.toString());

    }		
    Log4j.LogSumInfo("=========================");
    }		

    // When Test case get Skipped, this method is called.		
  
    public void onTestSkipped(ITestResult Result)					
    {		
    System.out.println("The name of the testcase Skipped is :"+Result.getName());
   
    
    Log4j.LogSumInfo("Skipped Test Name: "+Result.getName());
    Log4j.LogSumInfo("Parameters passed as below: ");
    Object[] ls = Result.getParameters();
    for( Object s: ls)
    {
    	Log4j.LogSumInfo(s.toString());

    }		
    Log4j.LogSumInfo("=========================");
    }		

    // When Test case get Started, this method is called.		
    
    public void onTestStart(ITestResult Result)					
    {		
    System.out.println(Result.getName()+" test case started");					
    }		

    // When Test case get passed, this method is called.		
  
    public void onTestSuccess(ITestResult Result)					
    {		
//    System.out.println("The name of the testcase passed is :"+Result.getName());	
    Log4j.LogSumInfo("Passed Test Name: "+Result.getName());
    Log4j.LogSumInfo("Parameters passed as below: ");
    Object[] ls = Result.getParameters();
    for( Object s: ls)
    {
    	Log4j.LogSumInfo(s.toString());

    }		
    Log4j.LogSumInfo("=========================");
    }
}			
