package com.testcases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.sample.base.BaseUI;
import com.sample.pages.Registerpage;
import com.sample.utils.FileIO;

@Listeners(com.sample.utils.ListenerUtils.class)

public class RegisterpageTest extends BaseUI {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = invokeBrowser();
		navigateToUrl("registerURL");
	}

	@Test(priority = 1, dataProvider = "testdata_valid")
	public void register_valid(String name, String company, String email, String password, String confirmpassword) {
		Registerpage reg = new Registerpage(driver, logger);
		reg.registername(name);
		reg.registercompany(company);
		reg.registeremail(email);
		reg.registerpassword(password);
		reg.registerconfrimpassword(confirmpassword);
		reg.registerclick();
		reg.assertRegisteration();
	}

	@DataProvider(name = "testdata_valid")
	public Object[][] testdataValid() throws IOException {
		testdatavalid = FileIO.datahandling("Sheet4");
		return testdatavalid;
	}

	@AfterMethod
	public void teardown() {
		driver.close();
	}
}
