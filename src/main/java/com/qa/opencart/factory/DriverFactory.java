package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver =new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser Name is ==>" + browserName);
		highlight=prop.getProperty("highlight");
		optionsManager=new OptionsManager(prop);
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;

		default:
			System.out.println("Please pass the valid browser name.." + browserName);
			throw new FrameworkException("Invalid Browser name");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/**
	 * get driver using threadlocal
	 */
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialise the properties from .properties file
	 * 
	 * @return
	 */
	
	
	public Properties initProp() {
		
		
		String envName=System.getProperty("env");
		System.out.println("running on env:"+envName);
		FileInputStream ip=null;
		prop=new Properties();
		try {
		
			if(envName==null)
			{	
			System.out.println("no env is passed, Hence running on QA environment");
			ip = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
			}
			else {
			switch (envName.trim().toLowerCase()) {
			case "qa":
				ip = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
				break;
			case "dev":
				ip = new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);
				break;
			case "stage":
				ip = new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
				break;
			case "uat":
				ip = new FileInputStream(AppConstants.CONFIG_UAT_PROD_FILE_PATH);
				break;
			case "prod":
				ip = new FileInputStream(AppConstants.CONFIG_PROP_FILE_PATH);
				break;
			default:
				System.out.println("Env name is: "+envName+" Please pass the correct environment name: ");
				throw new FrameworkException("==Invalid environmet==");
				
				}
			}
			prop.load(ip);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	

		return prop;

	}
}	
//	prop=new Properties();
//	try {
//		FileInputStream ip= new FileInputStream(AppConstants.CONFIG_PROP_FILE_PATH);
//		prop.load(ip);
//	}
//		
//	catch (FileNotFoundException e) {
//		e.printStackTrace();
//	}
//	catch(IOException e)
//	{
//		e.printStackTrace();
//	}
//
//	return prop;
//}
//}	


