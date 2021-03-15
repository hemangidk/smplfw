/**
 * 
 */
package com.sample.test;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author user
 *
 */
public class OpenFacebook {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.print(new java.io.File(".").getCanonicalPath());
		final String chromedriver = new java.io.File(".").getCanonicalPath() + "\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromedriver);
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		
		driver.close();


	}

}
