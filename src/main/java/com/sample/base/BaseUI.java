package com.sample.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sample.utils.DateUtils;
import com.sample.utils.FileIO;

public class BaseUI {

	public static WebDriver driver;
	public static Properties prop;
	public static String browser_choice;
	public static String[][] testdatavalid;
	public static String[][] testdatainvalid;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static String timestamp = DateUtils.getTimeStamp();

	public BaseUI() {
		prop = FileIO.initProperties();
	}

	/**** Invoke Browser ****/
	public static WebDriver invokeBrowser() {

		browser_choice = prop.getProperty("browserName");
		try {
			if (browser_choice.equalsIgnoreCase("firefox")) {
				driver = BrowserSetup.getFirefoxdriver();
			} else if (browser_choice.equalsIgnoreCase("edge")) {
				driver = BrowserSetup.getEdgedriver();
			} else if (browser_choice.equalsIgnoreCase("chrome")) {
				driver = BrowserSetup.getChromeDriver();
			} else {
				throw new Exception("Invalid");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return driver;
	}

/**Navigates to the provided URL **/
	public static void navigateToUrl(String WebsiteUrlKey) {
		try {
			driver.get(prop.getProperty(WebsiteUrlKey));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**** Get By locator using locator key ****/
	public static By getLocator(String locatorKey) {
		if (locatorKey.endsWith("_id")) {
			return By.id(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_name")) {
			return By.name(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_classname")) {
			return By.className(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_xpath")) {
			return By.xpath(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_css")) {
			return By.cssSelector(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_linkText")) {
			return By.linkText(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_partialLinkText")) {
			return By.partialLinkText(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_tagName")) {
			return By.tagName(prop.getProperty(locatorKey));
		}
		return null;
	}

	/**** Check if an element is present ****/
	public static boolean isElementPresent(By locator, Duration timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**** Send text to an element ****/
	public static void sendText(By locator, String text) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.presenceOfElementLocated(locator));
			driver.findElement(locator).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**** Click on element ****/
	public static void clickOn(By locator, Duration timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
			driver.findElement(locator).click();
			;
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

 	/**** Click on multiple elements ****/
	public static void clickOnMultiple(By locator, Duration timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
			List<WebElement> elements = driver.findElements(locator);
			System.out.println(elements.size());
			for (int i = 0; i < elements.size(); i++) {
				String s=Keys.chord(Keys.CONTROL,Keys.ENTER);
				elements.get(i).sendKeys(s);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**** Select from dropdown ****/
	public static void select(By locator, String choice) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(locator));
			WebElement dropdown = driver.findElement(locator);
			dropdown.click();
			Select s1 = new Select(dropdown);
			s1.selectByValue(choice);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	/**** Take Screenshot ........ 
	 * @return 
	 * @throws IOException ****/
	public static String screenshot() throws IOException {
			String srcname = "Failed test" + BaseUI.timestamp + ".jpg";
			TakesScreenshot takescr = ((TakesScreenshot) driver);
			File src = takescr.getScreenshotAs(OutputType.FILE);
			File srcpath=new File(System.getProperty("user.dir")+("/Screenshots/" + srcname));
			FileUtils.copyFile(src,	srcpath);
		return srcpath.getAbsolutePath();
	}
}
