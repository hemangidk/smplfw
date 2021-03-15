package com.sample.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sample.Util.BasePage;
import com.sample.Util.CustUtil;

public class homePage extends BasePage {

	public homePage(WebDriver driver, WebDriverWait mywait) {
		super(driver, mywait);
	}


	@FindBy(xpath = "//ul[@id='side-menu']/li/div/div/span/strong")
	WebElement capUserName;

	public String getHomeUsername() throws InterruptedException {
		Thread.sleep(2000);
		mywait.until(ExpectedConditions.visibilityOf(capUserName));
//		System.out.println(capUserName.getText());
		return capUserName.getText();

	}

	@FindBy(linkText = "Home")
	WebElement menuHome;

	@FindBy(xpath = "//ul/li/a/span[contains(text(),' Dashboard ')]")
	WebElement menDashboard;

	@FindBy(xpath = "//ul/li/a[contains(text(),'Create feed')]")
	WebElement menuCreateFeed;

	@FindBy(xpath = "//div/a/span[contains(text(),'Create feed') ]")
	WebElement btnCreateFeed;

	@FindBy(xpath = "//div//a/span[text()=' Feeds ']")
	WebElement menuFeed;
	@FindBy(xpath = "//ul/li/a[contains(text(),'All feed')]")
	WebElement menuAllFeed;

	@FindBy(xpath = "//ul/li/a/span[contains(text(),'Account')]")
	WebElement menuAccounts;

	@FindBy(xpath = "//ul/li/a/span[contains(text(),'Tickets')]")
	WebElement menuTickets;

	@FindBy(xpath = "//ul/li/a[@href=\"/orders\"]")
	WebElement menuManagedAccount;

	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/home\"]")
	WebElement menuDashBoard;

	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/accounts\"]")
	WebElement menuUsers;

	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/translations\"]")
	WebElement menuTranslations;

	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/tutorials\"]")
	WebElement menuTutorials;

	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/logs\"]")
	WebElement menuLogs;

	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/type_suggestions\"]")
	WebElement menuTypeSuggestions;

	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/export_presets\"]")
	WebElement menuExportPresets;

	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/mapping_suggestions\"]")
	WebElement menuMappingSuggestions;
	@FindBy(xpath = "//div//ul/li/a[@href=\"/admin/orders\"]/span[contains(text(),\"Managed project requests\")]")
	WebElement menuManagedProjectRequests;

	@FindBy(xpath = "//ul/li/a[contains(text(),'Logout')]")
	WebElement btnLogout;

	public void clickHome() throws InterruptedException {
		mywait.until(ExpectedConditions.visibilityOf(menuHome));
		menuHome.click();
		cu.waitForBouncerDisapper(driver);
	}

	public void clickLogout() throws InterruptedException {
		loginPage objLogin = new loginPage(driver, mywait);
		mywait.until(ExpectedConditions.visibilityOf(btnLogout));
		btnLogout.click();
		Thread.sleep(2000);
		mywait.until(ExpectedConditions.visibilityOf(objLogin.btnLogin));
		Reporter.log("logout Successful");
	}

	public void verifyHomePageCreateFeedBtn() throws InterruptedException {
	//	this.clickHome();
		mywait.until(ExpectedConditions.visibilityOf(btnCreateFeed));
		Assert.assertEquals(btnCreateFeed.isDisplayed(), true, "Create feed button do not exist on home page");
	}

	public void verifyFreeUserMenus() {

		Assert.assertEquals(menuHome.isDisplayed(), true, "home page do not exists");
		Assert.assertEquals(menuFeed.isDisplayed(), true, "Feed page do not exist");
		Assert.assertEquals(menuCreateFeed.isDisplayed(), true, "Create page tab do not exist");
		Assert.assertEquals(menuAllFeed.isDisplayed(), true, "Add feeds page do not exist");
		Assert.assertEquals(menuAccounts.isDisplayed(), true, "Accounts page do not exist");
		Assert.assertEquals(menuTickets.isDisplayed(), true, "Tickets tab do not exist");

		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuManagedAccount), false, "Managed account tab do exist");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuDashBoard), false, "Dashboard page do  exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuUsers), false, "Users page do exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuTranslations), false, "Translations page do exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuTutorials), false, "Tutorials page do exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuLogs), false, "Logs page do exist");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuTypeSuggestions), false,
				"Type Suggestions page do exist");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuMappingSuggestions), false,
				"Mapping suggestions page do exist");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuManagedProjectRequests), false,
				"Managed Project Requeste tab do exist");
	}

	public void verifyAdminUserMenus() {

		Assert.assertEquals(menuFeed.isDisplayed(), true, "Feed page do not exist");
		Assert.assertEquals(menDashboard.isDisplayed(), true, "Dahboard page do not exists");
		Assert.assertEquals(menuUsers.isDisplayed(), true, "Users page do not exist");
		Assert.assertEquals(menuTranslations.isDisplayed(), true, "Translations page do not exist");
		Assert.assertEquals(menuTutorials.isDisplayed(), true, "Tutorials page do not exist");
		Assert.assertEquals(menuLogs.isDisplayed(), true, "Logs page do not exist");
		Assert.assertEquals(menuTypeSuggestions.isDisplayed(), true, "Type Suggestions page do not exist");
		Assert.assertEquals(menuMappingSuggestions.isDisplayed(), true, "Mapping suggestions page do not exist");
		Assert.assertEquals(menuTickets.isDisplayed(), true, "Tickets tab do not exist");
		Assert.assertEquals(menuManagedProjectRequests.isDisplayed(), true,
				"Managed Project Requeste tab do not exist");

		Assert.assertEquals(cu.verifyElementNotDisplayed(driver,menuHome), false, "home page do not exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver,menuCreateFeed), false, "Create page tab do exist");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver,menuAllFeed), false, "Add feeds page do exist");
	}

	public void verifyManagedUserMenus() {

		Assert.assertEquals(menuHome.isDisplayed(), true, "home page do not exists");
		Assert.assertEquals(menuFeed.isDisplayed(), true, "Feed page do not exist");
		Assert.assertEquals(menuCreateFeed.isDisplayed(), true, "Create page tab do not exist");
		Assert.assertEquals(menuAllFeed.isDisplayed(), true, "Add feeds page do not exist");
		Assert.assertEquals(menuAccounts.isDisplayed(), true, "Accounts page do not exist");
		Assert.assertEquals(menuTickets.isDisplayed(), true, "Tickets tab do not exist");
		Assert.assertEquals(menuManagedAccount.isDisplayed(), true, "Managed account tab do not exist");

		////////
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuDashBoard), false, "Dashboard page do  exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuUsers), false, "Users page do exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuTranslations), false, "Translations page do exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuTutorials), false, "Tutorials page do exists");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuLogs), false, "Logs page do exist");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuTypeSuggestions), false,
				"Type Suggestions page do exist");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuMappingSuggestions), false,
				"Mapping suggestions page do exist");
		Assert.assertEquals(cu.verifyElementNotDisplayed(driver, menuManagedProjectRequests), false,
				"Managed Project Requeste tab do exist");
	}
}
