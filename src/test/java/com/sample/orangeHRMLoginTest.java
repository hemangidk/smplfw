package com.sample;

import com.aventstack.extentreports.Status;
import com.sample.util.propertyUtil;
import com.sample.util.testBed;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class orangeHRMLoginTest extends testBed {

    @Test
    //	@Parameters("myName")
    public void login(Method method) throws InterruptedException {
        String email = propertyUtil.getProperty(getEnvFilePath(), "uname");
        String pwd = propertyUtil.getProperty(getEnvFilePath(), "pwd");
        test = extent.createTest(method.getName());
        test.log(Status.INFO, "The thread ID for method: " + method.getName() + "browser: " + TestBedBrowser + " is " + Thread.currentThread().getId());

        objLogin.Login(email, pwd);

        objHome.clickLogout();
        Thread.sleep(3000);
        //		objLogin.Logout();
    }
}
