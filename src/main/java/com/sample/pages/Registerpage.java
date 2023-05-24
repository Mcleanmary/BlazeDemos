package com.sample.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sample.base.BaseUI;

public class Registerpage extends BaseUI {

	public ExtentTest logger;
	public WebDriver driver;
	
	public Registerpage (WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
	}
	
	By name=getLocator("name_id");
	By company=getLocator("company_id");
	By email=getLocator("email_id");
	By password=getLocator("password_id");
	By confirmpassword=getLocator("passwordconfirm_id");
	By registerbtn=getLocator("register_xpath");
	
	public void registername(String nametext) {
		sendText(name, nametext);
		logger.log(Status.INFO, "Name is entered.");
	}
	public void registercompany(String companytext) {
		sendText(company, companytext);
		logger.log(Status.INFO, "Company is entered.");
	}
	public void registeremail(String emailtext) {
		sendText(email, emailtext);
		logger.log(Status.INFO, "Email is entered.");
	}
	public void registerpassword(String passwordtext) {
		sendText(password, passwordtext);
		logger.log(Status.INFO, "Password is entered.");
	}
	public void registerconfrimpassword(String confirmpasswordtext) {
		sendText(confirmpassword, confirmpasswordtext);
		logger.log(Status.INFO, "Confirm Password is entered.");
	}
	public void registerclick() {
		clickOn(registerbtn, Duration.ofSeconds(30));
	}
	public void assertRegisteration() {
		String expected="Registration Success";
		String actual=driver.getTitle();
		System.out.println(actual);
		Assert.assertEquals(actual,expected);
	}
}
