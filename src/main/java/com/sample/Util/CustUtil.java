package com.sample.Util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustUtil {
  protected JavaScriptUtil js = new JavaScriptUtil();

	public static int GetRandomInteger() {
		Random r1 = new Random();
		return r1.nextInt(1000);
	}

	public void clearDirectory(String Dir) throws IOException, InterruptedException {
		File file = new File(Dir);
		String[] myFiles;
		if (file.isDirectory()) {
			myFiles = file.list();
			for (int i = 0; i < myFiles.length; i++) {
				File myFile = new File(file, myFiles[i]);
				myFile.delete();
			}
		} else if (file.isFile()) {
			file.delete();
		}
	}

	public static void DeleteDirectoryRecursively(String DirPath) throws IOException {
//		String DirPath = "D:\\test";
		Path directory = Paths.get(DirPath);
		System.out.println("Deleting folder: " + directory);

		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
				Files.delete(file); // this will work because it's always a File
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				Files.delete(dir); // this will work because Files in the directory are already deleted
				return FileVisitResult.CONTINUE;
			}
		});
	}

	public void getAllCookies(WebDriver driver, String sFileNameWithPath) {
//		File file = new File("d:\\a\\Cookies1.data");
		File file = new File(sFileNameWithPath);
		try {
			// Delete old file if exists
			file.delete();
			file.createNewFile();
			FileWriter fileWrite = new FileWriter(file);
			BufferedWriter Bwrite = new BufferedWriter(fileWrite);
			// loop for getting the cookie information

			// loop for getting the cookie information
			for (Cookie ck : driver.manage().getCookies()) {
				Bwrite.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";"
						+ ck.getExpiry() + ";" + ck.isSecure()));
				Bwrite.newLine();
			}
			Bwrite.close();
			fileWrite.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void uploadFileUsingRobot(String sFilename) throws AWTException, InterruptedException {
		Thread.sleep(1000);
		StringSelection ss = new StringSelection(sFilename);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
	}

	public void checkToastrDisplayed(WebElement ToastrLoc) throws InterruptedException {
		int Cnt = 0;
		try
		{
		while (!ToastrLoc.isDisplayed() && Cnt <= 30) {
			Thread.sleep(1000);
			Cnt++;
			System.out.println("waiting for toastr" + ToastrLoc.getTagName() + " elapsed time in sec: " + Cnt);
		}
		}
		catch(Exception e)
		{
			System.out.println( e.toString() +" -- Could not find the toaster message");
		}
	}

	public void waitForBouncerDisapper(WebDriver driver) throws InterruptedException {
//		String sLoc = "//div[@id=\"page-wrapper\"]//div[@class=\"ibox-content sk-loading\"]";
		int Cnt = 0;
//
		String sLoc = "//div[@class=\"gray-bg\"]//div[@class=\"ibox-content sk-loading\"]/div[@class=\"sk-spinner sk-spinner-double-bounce\"]/div";
		
		while (driver.findElements(By.xpath(sLoc)).size() > 0 ) {
			Thread.sleep(1000);
			Cnt++;
			System.out.println("waiting for bouncer to dissappear. Elapsed time in sec: " + Cnt);
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
	
		
//		    if (driver.findElement(By.xpath(sLoc)).isDisplayed()) {
//		        new WebDriverWait(driver, TestBed.TIMEOUT).until(ExpectedConditions.not(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(sLoc)))));
//		    }
		
	
	}
	
	

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private boolean acceptNextAlert = true;

	public String closeAlertAndGetItsText(WebDriver driver) {
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

	public boolean VerifyTextContainsOnPage(WebDriver driver, String textToVerify) {
		driver.getPageSource();
		return driver.getPageSource().contains(textToVerify);
	}
	

	public boolean verifyElementNotDisplayed(WebDriver driver, WebElement ele) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		boolean bstatus = verifyElementNotDisplayed(ele);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return bstatus;
	}

	public boolean verifyElementNotDisplayed(WebElement ele) {
		try {
			return (!ele.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getCurrentDateTimeStamp() {
		Date objDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_mmm_dd_hh_mm_ss");
		objDate = new Date();
		// Current System Date and time is assigned to objDate
		return (sdf.format(objDate));
	}

	public void scrollToElement(WebDriver driver, WebElement ele) throws InterruptedException {

		try {
			js.execute_js(driver, "arguments[0].scrollIntoView();", ele);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}