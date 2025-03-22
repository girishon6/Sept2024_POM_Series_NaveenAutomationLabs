package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;

@Listeners(ChainTestListener.class)
public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	
	protected LoginPage lp;
	protected HomePage hp;
	protected SearchResultsPage sp;
	protected ProductInfoPage pi;
	protected CommonsPage commonsPage;
	
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setUp(String browserName, String browserVersion, String testName)
	{
		df=new DriverFactory();
		prop=df.initProp();
		
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		driver=df.initDriver(prop);
		lp=new LoginPage(driver);
		commonsPage=new CommonsPage(driver);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
}
