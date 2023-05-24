package com.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sample.base.BaseUI;
import com.sample.pages.Reservepage;

public class ReservepageTest2 extends BaseUI {

	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=invokeBrowser();
		navigateToUrl("reserveURL");
	}
	@Test
	public void choose() {
		Reservepage r= new Reservepage(driver, logger);
		r.chooseflight();
		r.assertreserve();
	}
//	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
