package com.sample.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sample.base.BaseUI;

public class Purchasepage extends BaseUI {

	public ExtentTest logger;
	public WebDriver driver;

	public Purchasepage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	By name=getLocator("name_name");
	By address=getLocator("address_name");
	By city=getLocator("city_name");
	By state=getLocator("state_name");
	By zipcode=getLocator("zipcode_name");
	By cardtype=getLocator("cardtype_name");
	By cardnumber=getLocator("cardnumber_name");
	By cardmonth=getLocator("cardmonth_id");
	By cardyear=getLocator("cardyear_id");
	By cardname=getLocator("cardname_xpath");
	By rememberme=getLocator("rememberme_name");
	By purchaseflight=getLocator("purchase_xpath");
	By confirmationdate=getLocator("date_xpath");
	By confirmationheading=getLocator("heading_xpath");
	
	public void entername(String nametext) {
		sendText(name, nametext);
		logger.log(Status.INFO, "Name is entered.");
	}
	
	public void enteraddress(String addresstext) {
		sendText(address, addresstext);
		logger.log(Status.INFO, "Address is entered.");
	}
	public void entercity(String citytext) {
		sendText(city, citytext);
		logger.log(Status.INFO, "City is entered.");
	}
	public void enterstate(String statetext) {
		sendText(state, statetext);
		logger.log(Status.INFO, "State is entered.");
	}
	public void enterzip(String ziptext) {
		sendText(state, ziptext);
		logger.log(Status.INFO, "Zipcode is entered.");
	}
	public void cardtype(String choice) {
		select(cardtype, choice);
		logger.log(Status.INFO, "CardType is selected.");
	}
	public void entercardmonth(String monthtext) {
		driver.findElement(cardmonth).clear();
		sendText(cardmonth, monthtext);
		logger.log(Status.INFO, "CardMonth is entered.");
	}
	public void entercardyear(String yeartext) {
		driver.findElement(cardyear).clear();
		sendText(cardyear, yeartext);
		logger.log(Status.INFO, "CardYear is entered.");
	}
	public void nameOnCard(String nameoncard) {
		sendText(cardname, nameoncard);
		logger.log(Status.INFO, "Name on card is entered.");
	}
	public void remember() {
		clickOn(rememberme, Duration.ofSeconds(30));
	}
	public void purchase() {
		clickOn(purchaseflight, Duration.ofSeconds(30));
	}

	public void assertPurchase() {
		String expectedHeading = "Thank you for your purchase today!";
		String actualHeading = driver.findElement(confirmationheading).getText();
		Assert.assertEquals(expectedHeading, actualHeading);
	}
}
