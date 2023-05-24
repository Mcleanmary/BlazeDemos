package com.testcases;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.sample.base.BaseUI;
import com.sample.pages.Welcomepage;
import com.sample.utils.FileIO;

@Listeners(com.sample.utils.ListenerUtils.class)

public class WelcomepageTest extends BaseUI {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = invokeBrowser();
		navigateToUrl("websiteURL");
		}

	@Test(priority=1)
	public void title() {
		String expected="BlazeDemo";
		String actual=driver.getTitle();
		Assert.assertEquals(actual,expected);
	}

	@Test(priority = 2,dataProvider = "testdata_valid")
	public void fromTo_valid(String from,String to) {
		Welcomepage w = new Welcomepage(driver, logger);
		w.fromPort(from);
		w.toPort(to);
		w.find();
		String fromActual=driver.findElement(By.name("fromPort")).getAttribute("value");
		String toActual=driver.findElement(By.name("toPort")).getAttribute("value");
		Assert.assertEquals(fromActual,from);
		Assert.assertEquals(toActual, to);
	}
	
	@DataProvider(name = "testdata_valid")
	public Object[][] testdataValid() throws IOException {
		testdatavalid = FileIO.datahandling("Sheet1");
		return testdatavalid;
	}
	
	@Test(priority = 3,dataProvider = "testdata_invalid")
	public void fromTo_invalid(String from,String to) {
		Welcomepage w = new Welcomepage(driver, logger);
		w.fromPort(from);
		w.toPort(to);
		w.find();
		String fromActual=driver.findElement(By.name("fromPort")).getAttribute("value");
		String toActual=driver.findElement(By.name("toPort")).getAttribute("value");
		Assert.assertEquals(fromActual,"Paris");
		Assert.assertEquals(toActual, "Buenos Aires");
	}

	@DataProvider(name = "testdata_invalid")
	public Object[][] testdataInvalid() throws IOException {
		testdatainvalid = FileIO.datahandling("Sheet2");
		return testdatainvalid;
	}
	
	@Test(priority = 4)
	public void fromTo_null() {
		Welcomepage w = new Welcomepage(driver, logger);
		w.fromPort("");
		w.toPort("");
		w.find();
		String fromActual=driver.findElement(By.name("fromPort")).getAttribute("value");
		String toActual=driver.findElement(By.name("toPort")).getAttribute("value");
		Assert.assertEquals(fromActual,"Paris");
		Assert.assertEquals(toActual, "Buenos Aires");
	}

	@AfterMethod
	public void teardown() {
		driver.close();
	}

}
