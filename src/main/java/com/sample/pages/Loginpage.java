package com.sample.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sample.base.BaseUI;

public class Loginpage extends BaseUI {

	public WebDriver driver;
	public ExtentTest logger;

	public Loginpage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	By email = getLocator("email_id");
	By password = getLocator("password_xpath");
	By rememberme = getLocator("rememberme_name");
	By loginbtn = getLocator("login_xpath");

	public void email(String emailtext) {
		sendText(email, emailtext);
		logger.log(Status.INFO, "Email is entered");
	}
	public void password(String passwordtext) {
		sendText(password, passwordtext);
		logger.log(Status.INFO, "Password is entered");
	}
	public void loginclick() {
		clickOn(loginbtn, Duration.ofSeconds(30));
	}
	public void loginpageAssert() throws Exception  {
			String expected="Account";
			String actual=driver.getTitle();
			System.out.println(actual);
			Assert.assertEquals(actual,expected);
		}
}
