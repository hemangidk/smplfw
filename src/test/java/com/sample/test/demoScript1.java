package com.sample.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sample.Util.TestBed;

public class demoScript1 extends TestBed {



@Test
public void DemoTest1() throws InterruptedException {
//	loginPage objLogin = new loginPage(getDriver(), getWebDriverWait());
//	objLogin.Login("akshaayu5@yopmail.com", "Welcome@123");
//	objLogin.Logout();
}
@Test
public void DemoTest2() throws InterruptedException {
//	loginPage objLogin = new loginPage(getDriver(), getWebDriverWait());
//	objLogin.Login("akshaayu2@yopmail.com", "Welcome@123");
//	objLogin.Logout();
	Assert.assertTrue(true);
}
}