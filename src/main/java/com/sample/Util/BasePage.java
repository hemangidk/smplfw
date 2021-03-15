package com.sample.Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

//    private static final int TIMEOUT1 = 20;
//    private static final int POLLING = 100;
    protected CustUtil cu = new CustUtil();
    protected JavaScriptUtil js = new JavaScriptUtil();

    protected WebDriver driver;
    protected WebDriverWait mywait;

    public BasePage(WebDriver driver, WebDriverWait mywait) {
        this.driver = driver;
//        mywait = new WebDriverWait(driver, TIMEOUT, POLLING);
//        mywait =(WebDriverWait) new WebDriverWait(driver, 30);
        this.mywait = mywait;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TestBed.TIMEOUT), this);
    }

    protected void waitForElementToAppear(By locator) {
    	mywait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToDisappear(By locator) {
    	mywait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForTextToDisappear(By locator, String text) {
    	mywait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }
}
