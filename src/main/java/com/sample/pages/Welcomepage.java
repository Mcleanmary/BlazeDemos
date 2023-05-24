package com.sample.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sample.base.BaseUI;

public class Welcomepage extends BaseUI {
	public ExtentTest logger;
	public WebDriver driver;

	public Welcomepage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	By from = getLocator("fromport_name");
	By to = getLocator("toPort_name");
	By findflights = getLocator("findflights_xpath");
	
/**
	public void titlecheck(){
		String expected="BlazeDemo";
		String actual=driver.getTitle();
		Assert.assertEquals(actual,expected);
		logger.log(Status.INFO, "Correct page loaded");
	}
**/
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

}