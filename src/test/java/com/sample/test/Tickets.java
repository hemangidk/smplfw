package com.sample.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.PageFactory.loginPage;
import com.sample.PageFactory.ticketsPage;
import com.sample.Util.CustUtil;
import com.sample.Util.ExcelUtil;
import com.sample.Util.PropertyUtil;
import com.sample.Util.TestBed;

public class Tickets extends TestBed {
	WebDriver driver;
	WebDriverWait mywait;

	loginPage objLogin;
	ticketsPage objTktpage;

	 
	@BeforeClass
	public void beforeclass() throws IOException {
		WebDriver driver = getDriver();
		WebDriverWait mywait = getWebDriverWait();

		objLogin = new loginPage(driver, mywait);
		objTktpage = new ticketsPage(driver, mywait);
	}

	ExcelUtil excelutil = new ExcelUtil();
	public String tktname = "AutoTkt" + CustUtil.GetRandomInteger();
	public String getFileName() throws IOException {
		return new java.io.File(".").getCanonicalPath() + "\\Data\\img.jpg";
	}


	@DataProvider(name = "clientTicketsData")
	public Object[][] clientTicketsDataDP() throws FileNotFoundException, IOException {
		return new Object[][] { { freeuser_email, freeuser_pwd, tktname, "Body of the ticket",
			getFileName() }, };
//		return new Object[][] { { "akshaayu6@yopmail.com", "Welcome@123", tktname ,"Body of the ticket","D:\\doeexAutomation\\Data\\img.jpg"}, };
//			return excelutil.ReadDataFromExcelFile(new java.io.File(".").getCanonicalPath() + "\\Data\\Ticket.xlsx","Data");
	}

	@Test(dataProvider = "clientTicketsData", enabled = true, priority = 1)
	public void createTicketTest(String email, String pwd, String TktName, String sTktBody, String sFilename)
			throws Exception {

		objLogin.Login(email, pwd);

		objTktpage.createTicket(TktName, sTktBody, sFilename);
		objLogin.Logout();
	}

	@Test(dataProvider = "clientTicketsData", enabled = true, priority = 2, dependsOnMethods = { "createTicketTest" })
	public void searchAndVerifyTicketDetailsAndSendMessageAsClient(String email, String pwd, String TktName,
			String sTktBody, String sFilename) throws Exception {
		objLogin.Login(email, pwd);
		objTktpage.clickTickets();
		objTktpage.searchTicket(TktName);
		objTktpage.showTicket(TktName);
		objTktpage.verifyTicketDetails(TktName, sTktBody);

		objTktpage.sendMessageFromTicket("This is sample message from client " + CustUtil.getCurrentDateTimeStamp());
		objTktpage.clickGoBack();
		objLogin.Logout();
	}

}
