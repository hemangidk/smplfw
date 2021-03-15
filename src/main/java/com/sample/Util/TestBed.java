package com.sample.Util;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.sample.PageFactory.homePage;

@Listeners({ com.sample.Util.ListenerTest.class, CustReportListener.class })

public abstract class TestBed {
	protected static final int TIMEOUT = 20;
	public String freeuser_email = PropertyUtil.getProperty(getEnvFilePath(), "freeuser_email");
	public String freeuser_pwd = PropertyUtil.getProperty(getEnvFilePath(), "freeuser_pwd");

	private WebDriver driver;

	private WebDriverWait mywait;

//    @BeforeSuite
//    public void beforeSuite() {
//      System.out.println("BeforeSuite");
//    }

	@BeforeTest(alwaysRun = true)
	public void beforeTest() throws Exception {
		System.out.println("***********************************");
//		 System.out.println("Testcase start time stamp: "+CustUtil.getCurrentDateTimeStamp());
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.out.println("***********************************");
		System.out.println("Testcase start time stamp: " + CustUtil.getCurrentDateTimeStamp());
	}

	@BeforeClass
	public void beforeClass() throws IOException {

//		System.out.println("----@BeforeClass from testbed-----");
//        System.setProperty("headless", "false"); // You can set this property elsewhere
//        String headless = System.getProperty("headless");
//
//        ChromeDriverManager.chromedriver();
//        if("true".equals(headless)) {
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--headless");
//            driver = new ChromeDriver(chromeOptions);
//        } else {
//            driver = new ChromeDriver();
//        }

		final String ChromeDriver = new java.io.File(".").getCanonicalPath() + "\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", ChromeDriver);
		final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

		ChromeOptions options = new ChromeOptions();

//		String sExtXpathchecker = new java.io.File(".").getCanonicalPath() + "\\Xpath-Finder_0.20.0_0.crx";// "D:\\doeexAutomation\\CSS-and-XPath-checker_v0.20.0.crx";
		//options.addExtensions(new File(sExtXpathchecker));// ));
		//options.addArguments("--headless");
		options.addArguments("--incognito");
		options.addArguments(new String[] { "--start-maximized" });
		
		options.merge((Capabilities) capabilities);

		driver = (WebDriver) new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		mywait = (WebDriverWait) new WebDriverWait(driver, 15);

//		String envfilepath = new java.io.File(".").getCanonicalPath() + "\\config\\env.properties";
		String envfilepath = getEnvFilePath();
//			String devURL = "";
//		  System.out.println(new java.io.File(".").getCanonicalPath());
		String produrl = PropertyUtil.getProperty(envfilepath, "URL");
		// "https://dashboard.doeex.com/login";
		System.out.println(produrl);
		driver.get(produrl);
//			System.out.println("Driver in before method "+driver);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		// Current System Date and time is assigned to objDate
		System.out.println("Testcase end time stamp: " + CustUtil.getCurrentDateTimeStamp());
//		  String strDateFormat = "hh:mm:ss a dd-MMM-yyyy"; //Date format is Specified
//		  SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); 

		// Here will compare if test is failing then only it will enter into if
		// condition
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File(
						"./Screenshots/" + result.getName() + "_" + CustUtil.getCurrentDateTimeStamp() + ".png"));
				System.out.println("Screenshot taken");
				homePage objHome = new homePage(driver, mywait);
				objHome.clickLogout();
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}

	}

	private StringBuffer verificationErrors = new StringBuffer();

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		if (null != driver) {
			driver.close();
			driver.quit();
		}
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@AfterSuite
	public void afterSuite() {
//        if(null != driver) {
//            driver.close();
//            driver.quit();
//        }
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWebDriverWait() {
		return mywait;
	}
	 public static String getEnvFilePath() 
	    {
	    	String filename ="";
	      try {
	    	 
	    	  filename=new java.io.File(".").getCanonicalPath()+"\\config\\env.properties"; // +"\\config\\env_prod.properties";//
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			Assert.fail("Not able to locate: "+filename);
			
		}
	      return filename;
	    	 }
	    
}