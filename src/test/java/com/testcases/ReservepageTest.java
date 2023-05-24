package com.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sample.base.BaseUI;
import com.sample.pages.Reservepage;
import com.sample.pages.Welcomepage;
import com.sample.utils.FileIO;

@Listeners(com.sample.utils.ListenerUtils.class)

public class ReservepageTest extends BaseUI {

	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = invokeBrowser();
		navigateToUrl("websiteURL");
		}
	
	@DataProvider(name = "testdata_valid")
	public Object[][] testdataValid() throws IOException {
		testdatavalid = FileIO.datahandling("Sheet6");
		return testdatavalid;
	}
	
	@Test(dataProvider = "testdata_valid")
	public void reserve(String from,String to) {
		Reservepage r= new Reservepage(driver, logger);
		r.fromPort(from);
		r.toPort(to);
		r.find();
		r.chooseflight();
		r.assertreserve();
	}
	
//	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
