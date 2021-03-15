package com.sample.PageFactory;

import java.awt.AWTException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sample.Util.BasePage;
import com.sample.Util.CustUtil;
import com.sample.Util.JavaScriptUtil;

public class ticketsPage extends BasePage {

	public ticketsPage(WebDriver driver, WebDriverWait mywait) {
		super(driver, mywait);
		// TODO Auto-generated constructor stub
	}

	private boolean acceptNextAlert = true;

	@FindBy(xpath = "//ul/li/a/span[contains(text(),'Tickets')]")
	WebElement menuTickets;

	@FindBy(xpath = "//div//button[contains(text(),'Create')]")
	WebElement btnCreate;

	@FindBy(xpath = "//input[@type='text']")
	WebElement txtTitle;

	@FindBy(xpath = "//div[@id=\"page-wrapper\"]//textarea")
	WebElement txtAreaBody;

	@FindBy(xpath = "//div[@id='page-wrapper']//button[contains(text(),'Create')]")
	WebElement btnCreateTkt;

	@FindBy(xpath = "//div[text()='Tickets has been created']") // css = "css=div.toasted.toasted-primary.success")
	// = "//div[@class=\"toasted-container
	// top-right\"]//div[text()='global.ticket_has_been_created']")
	WebElement toastrCreateSuccess;

	@FindBy(xpath = "//div[contains(text(),'Ticket has been removed')]")
	WebElement toastrDeletSuccess;

	@FindBy(xpath = "//div[@class=\"ibox\"]//following::div//textarea")
	WebElement txtAreaMessage;

	@FindBy(xpath = "//div/h5[contains(text(),'Messages')]/ancestor::div/following-sibling::div//button[@class=\"btn-default no-margins btn send-button\"]/i")
	WebElement btnTktSend;
	@FindBy(xpath = "//div/h5[contains(text(),'Messages')]/ancestor::div/following-sibling::div//button[@class=\"btn-default no-margins btn attach-button\"]/i")
	WebElement btnTktAttach;

	public void clickTickets() throws InterruptedException {
		System.out.println("----------------");
		js.execute_js(driver, "arguments[0].scrollIntoView();", menuTickets);
		mywait.until(ExpectedConditions.visibilityOf(menuTickets));
		menuTickets.click();
		cu.waitForBouncerDisapper(driver);
	}

	public void clickCreate() throws InterruptedException {
		mywait.until(ExpectedConditions.visibilityOf(btnCreate));
		btnCreate.click();
		cu.waitForBouncerDisapper(driver);
	}

	public void typeTitle(String sTitle) throws InterruptedException {
		Thread.sleep(2000);
//		mywait.until(ExpectedConditions.visibilityOf(txtTitle));
		mywait.until(ExpectedConditions.elementToBeClickable(txtTitle));
		txtTitle.click();
		txtTitle.clear();
		txtTitle.sendKeys(sTitle);

	}

	public void typeBody(String sBody) {
		mywait.until(ExpectedConditions.visibilityOf(txtAreaBody));
		txtAreaBody.clear();
		txtAreaBody.sendKeys(sBody);
	}

	public void clickCreateTicket() throws InterruptedException {
		mywait.until(ExpectedConditions.visibilityOf(btnCreateTkt));
		btnCreateTkt.click();
		cu.waitForBouncerDisapper(driver);
	}

	@FindBy(xpath = "//span[contains(text(),'Upload')]")
	WebElement btnUpload;
	@FindBy(xpath = "//div//button[contains(text(),'Go back')]")
	WebElement btnGoBack;

	public void clickGoBack() throws InterruptedException {

		mywait.until(ExpectedConditions.visibilityOf(btnGoBack));
		btnGoBack.click();
		cu.waitForBouncerDisapper(driver);
	}

	public void clickUpload() throws InterruptedException {

		mywait.until(ExpectedConditions.visibilityOf(btnUpload));
		btnUpload.click();
		cu.waitForBouncerDisapper(driver);
	}

	public void createTicket(String sTitle, String sTktBody, String sFilename)
			throws InterruptedException, AWTException {
		this.clickTickets();
		this.clickCreate();
		this.typeTitle(sTitle);
		this.typeBody(sTktBody);

		this.clickUpload();

		cu.uploadFileUsingRobot(sFilename);

		this.clickCreateTicket();
		try {
			cu.checkToastrDisplayed(toastrCreateSuccess);
		} catch (Exception e) {
			System.out.println("couldnt cath toastr message");
		}
		Thread.sleep(1000);

		Reporter.log("Ticket created Successfully");

	}
//	@FindBy(xpath = 
//	WebElement btnDelete;

	public void DeleteTicket(String stktName) throws InterruptedException {

		this.clickTickets();
		String sBtnDelete = "//div//td[contains(text(),\"" + stktName
				+ "\")]//following-sibling::td/div/button[contains(text(),'Delete')]";

		WebElement eDeleteLoc = driver.findElement(By.xpath(sBtnDelete));

		mywait.until(ExpectedConditions.elementToBeClickable(eDeleteLoc));
		eDeleteLoc.click();
		acceptNextAlert = true;

		Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure[\\s\\S]$"));

		int Cnt = 0;
		while (!toastrDeletSuccess.isDisplayed() && Cnt <= 10) {
			Thread.sleep(1000);
			Cnt++;
			System.out.println(Cnt);
		}
		Thread.sleep(5000);
		Boolean bflag;
		try {
			driver.findElement(By.xpath("//div//td/span[@title=\"" + stktName + "\"]")).isDisplayed();
			bflag = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			bflag = false;
		}
		Assert.assertFalse(bflag, stktName + " is still not deleted");
		Reporter.log("Ticket deleted Successfully");

	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	@FindBy(xpath = "//div//input[@placeholder=\"Search\"]")
	WebElement txtSearchBox;

	public void searchTicket(String sTicketName) throws InterruptedException {
		mywait.until(ExpectedConditions.visibilityOf(txtSearchBox));
		txtSearchBox.clear();
		txtSearchBox.sendKeys(sTicketName);
		Actions actbuilder = new Actions(driver);
		Action actionTabOut = actbuilder.sendKeys(txtSearchBox, Keys.TAB).build();
		actionTabOut.perform();
		cu.waitForBouncerDisapper(driver);
	}

	public void showTicket(String sTicketName) throws InterruptedException {
		String sLocBtnhShow = "//div//td[contains(text(),'" + sTicketName
				+ "')]/following-sibling::td//button[contains(text(),'Show')]";
		WebElement btnShow = driver.findElement(By.xpath(sLocBtnhShow));
		mywait.until(ExpectedConditions.visibilityOf(btnShow));
		btnShow.click();
		cu.waitForBouncerDisapper(driver);
		Assert.assertTrue(cu.VerifyTextContainsOnPage(driver, sTicketName), "Ticket name is not displayed ");
	}

	public void verifyTicketDetails(String sTicketName, String sTktBody) throws InterruptedException {

		Assert.assertTrue(cu.VerifyTextContainsOnPage(driver, sTicketName), "Ticket name is not displayed ");
		Assert.assertTrue(cu.VerifyTextContainsOnPage(driver, sTktBody), "Ticket Body is not displayed ");
//		Assert.assertTrue(cUtil.VerifyTextContainsOnPage(driver,sFileName.substring(sFileName.lastIndexOf("\\")+1)), "Ticket Body is not displayed ");
	}

	public void sendMessageFromTicket(String sMessage) throws InterruptedException {

		mywait.until(ExpectedConditions.visibilityOf(txtAreaMessage));

		txtAreaMessage.clear();
		txtAreaMessage.sendKeys(sMessage);

		mywait.until(ExpectedConditions.visibilityOf(btnTktSend));
		btnTktSend.click();
		cu.waitForBouncerDisapper(driver);
		driver.navigate().refresh();
		cu.waitForBouncerDisapper(driver);
		Assert.assertTrue(cu.VerifyTextContainsOnPage(driver, sMessage), "Message was not sent correctly");
	}

}
