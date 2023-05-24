package com.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sample.base.BaseUI;
import com.sample.pages.Purchasepage;
import com.sample.pages.Welcomepage;
import com.sample.utils.FileIO;

@Listeners(com.sample.utils.ListenerUtils.class)

public class PurchasepageTest extends BaseUI {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = invokeBrowser();
		navigateToUrl("purchaseURL");
	}

	@DataProvider(name = "testdata_valid")
	public Object[][] testdataValid() throws IOException {
		testdatavalid = FileIO.datahandling("Sheet3");
		return testdatavalid;
	}
	
	@Test(priority = 1,dataProvider = "testdata_valid")
	public void data_valid(String name, String address, String city, String state, String zip, String cardtype,
		String cardmonth, String cardyear,String nameoncard) {
		Purchasepage p = new Purchasepage(driver, logger);
		p.entername(name);
		p.enteraddress(address);
		p.entercity(city);
		p.enterstate(state);
		p.enterzip(zip);
		p.cardtype(cardtype);
		p.entercardmonth(cardmonth);
		p.entercardyear(cardyear);
		p.nameOnCard(nameoncard);
		p.remember();
		p.purchase();
		p.assertPurchase();
	}
	
//	@AfterMethod
	public void teardown() {
		driver.close();
	}
}
