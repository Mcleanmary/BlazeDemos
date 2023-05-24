package com.sample.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sample.base.BaseUI;

public class Reservepage extends BaseUI {
	public ExtentTest logger;
	public WebDriver driver;

	public Reservepage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	By from = getLocator("fromport_name");
	By to = getLocator("toPort_name");
	By findflights = getLocator("findflights_xpath");

	By chooseflight = getLocator("chooseflight_xpath");
	
	public void fromPort(String choice) {
		select(from, choice);
		logger.log(Status.INFO, "FromPort selected");
	}
	public void toPort(String choice) {
		select(to, choice);
		logger.log(Status.INFO, "ToPort selected");
	}
	public void find() {
		clickOn(findflights, Duration.ofSeconds(30));
	}
	public void chooseflight() {
		clickOnMultiple(chooseflight, Duration.ofSeconds(30));
	}

	public void assertreserve() {
		String mainHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String current : handles) {
			if (!current.equals(mainHandle)) {
				driver.switchTo().window(current);
				String expected = "BlazeDemo Purchase";
				String actual = driver.getTitle();
				Assert.assertEquals(actual, expected);
				System.out.println(actual + "" + expected);
			}

		}

	}
}
