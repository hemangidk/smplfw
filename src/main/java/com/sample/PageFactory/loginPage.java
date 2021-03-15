package com.sample.PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sample.Util.BasePage;

public class loginPage extends BasePage{


	public loginPage(WebDriver driver, WebDriverWait mywait) {
		super(driver, mywait);
		// TODO Auto-generated constructor stub
	}


	@FindBy(xpath = "//input[@type='text']")
	WebElement txtName;

	@FindBy(xpath = "//input[@type='email']")
	WebElement txtEmail;

	@FindBy(xpath = "//div//a[contains(text(),'Create an account')]")
	WebElement btnCreateAccount;

	@FindBy(xpath = "//input[@placeholder=\"Password\"]")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@placeholder=\"Password confirmation\"]")
	WebElement txtCnfPassword;

	@FindBy(xpath = "//button[text()='I agree']")
	WebElement btnIAgree;

	@FindBy(xpath = "//label[contains(text(),'privacy policy')]/input[@type=\"checkbox\"]")
	WebElement chkBox2;
	@FindBy(xpath = "//label[contains(text(),'disclaimer')]/input[@type=\"checkbox\"]")
	WebElement chkBox3;

	@FindBy(xpath = "//button[contains(text(),'Register') and @type = \"submit\"]")
	WebElement btnSubmit;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	WebElement btnLogin;

	@FindBy(xpath = "//label[text()=' I have read and accept the DoeeX terms of use']/input[@type=\"checkbox\"]")
	WebElement chkBox1;

	@FindBy(xpath = "//div//strong[text()='There is no user with this email address']")
	WebElement capUserDoNotExist;

	@FindBy(xpath = "//div//span/strong[text()='This email address is already in use']" )
	WebElement capExistingEmail;
	
	public void setName(String sName) {
		mywait.until(ExpectedConditions.visibilityOf(txtName));
		txtName.sendKeys(sName);
	}

	public void setEmail(String sEmail) {
		mywait.until(ExpectedConditions.visibilityOf(txtEmail));
		txtEmail.sendKeys(sEmail);
	}

	// Set password in password textbox
	public void setPassword(String sPwd) {
		mywait.until(ExpectedConditions.visibilityOf(txtPassword));
		txtPassword.sendKeys(sPwd);
	}

	public void setCnfPassword(String sCnfPwd) {
		mywait.until(ExpectedConditions.visibilityOf(txtCnfPassword));
		txtCnfPassword.sendKeys(sCnfPwd);
	}

	// Click on login button
	public void clickChkBox1() {
		mywait.until(ExpectedConditions.visibilityOf(chkBox1));
		if (!chkBox1.isSelected()) {
			chkBox1.click();
		}
	}

	public void clickChkBox2() {
		mywait.until(ExpectedConditions.visibilityOf(chkBox2));
		if (!chkBox2.isSelected()) {
			chkBox2.click();
		}
	}

	public void clickChkBox3() {
		mywait.until(ExpectedConditions.visibilityOf(chkBox3));
		if (!chkBox3.isSelected()) {
			chkBox3.click();
		}
	}

	public void clickIAgree() throws InterruptedException {
		
		try {
			
			if (driver.findElements(By.xpath("//button[text()='I agree']")).size() > 0) {
				mywait.until(ExpectedConditions.visibilityOf(btnIAgree));
				mywait.until(ExpectedConditions.elementToBeClickable(btnIAgree));
			
				btnIAgree.click();
			} else {
				Reporter.log("We dont see Agree button during execution so continuing without click");
			}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}	
	}

	public void clickCreateAccount() throws InterruptedException {
		mywait.until(ExpectedConditions.visibilityOf(btnCreateAccount));
		btnCreateAccount.click();
//		cu.waitForBouncerDisapper(driver);
	}

	// Click on login button
	public void clickSubmit() throws InterruptedException {
		mywait.until(ExpectedConditions.visibilityOf(btnSubmit));
		btnSubmit.click();
		Thread.sleep(1000);
		Boolean bflag;
		try {
			mywait.until(ExpectedConditions.visibilityOf(txtEmail));
			bflag = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			bflag = false;

		}
		if (!bflag) {
			Assert.assertFalse(bflag, "Failed to create new account");
		}
	}

	public void clickLogin() throws InterruptedException {
		mywait.until(ExpectedConditions.visibilityOf(btnLogin));
		mywait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
//		cu.waitForBouncerDisapper(driver);
	}

	public void createAccount(String name, String email, String pwd) throws InterruptedException {
		// Fill user name
		this.clickIAgree();
		this.clickCreateAccount();
		// Fill password
		this.setName(name);
		this.setEmail(email);
		this.setPassword(pwd);
		this.setCnfPassword(pwd);
		this.clickChkBox1();
		this.clickChkBox2();
		this.clickChkBox3();
		this.clickSubmit();
		try {
			btnLogin.isDisplayed();
			Reporter.log("Account Created Successfully for email: " + email);
			
			
		} catch (Exception e) {
			if(capExistingEmail.isDisplayed()) {
				Assert.fail("User already exists in the application");
			}
			
		}

		
	}

	public void Login(String email, String pwd) throws InterruptedException {
		this.clickIAgree();
	//	driver.navigate().refresh();
		this.setEmail(email);
		this.setPassword(pwd);
		this.clickLogin();
		homePage objHome = new homePage(driver, mywait);
		
		try {
			objHome.btnLogout.isDisplayed();
			Reporter.log("login successful for user: " + email);

		} catch (Exception e) {
			try {
				if(capUserDoNotExist.isDisplayed())
				{
					Assert.fail("User do not exists in the application");
				}
				else
				{
					Assert.fail("Login not successful");
				}
			}
			catch (Exception e2) {
				System.out.println(e2.toString());
			}
			
			
			
		}	
//		cu.waitForBouncerDisapper(driver);
	}

	public void Logout() throws InterruptedException {
		homePage objHome = new homePage(driver, mywait);
		objHome.clickLogout();
		
		
		
		
	}
}
