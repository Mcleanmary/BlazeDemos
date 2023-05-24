package com.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sample.base.BaseUI;
import com.sample.pages.Loginpage;
import com.sample.utils.FileIO;

@Listeners(com.sample.utils.ListenerUtils.class)

public class LoginpageTest extends BaseUI {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = invokeBrowser();
		navigateToUrl("loginURL");
	}

	@DataProvider(name = "testdata_valid")
	public Object[][] testdataValid() throws IOException {
		testdatavalid = FileIO.datahandling("Sheet5");
		return testdatavalid;
	}

	@Test(priority = 1, dataProvider = "testdata_valid")
	public void login_valid(String email, String password) throws Exception {
		Loginpage l = new Loginpage(driver, logger);
		l.email(email);
		l.password(password);
		l.loginclick();
		l.loginpageAssert();
	}
	
	@AfterMethod
	public void teardown() {

		driver.close();
	}
}
