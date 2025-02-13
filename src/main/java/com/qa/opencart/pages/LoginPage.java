package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil= new ElementUtil(driver);
	}
		
	//1.By Locators
	private By email=By.id("input-email");
	private By pwd=By.id("input-password");
	private By forgottenPwd=By.linkText("Forgotten Password");
	private By submit=By.xpath("//input[@value='Login']");
	
	//2. actions methods
	public String getLoginPageTitle()
	{
		String title=eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Title==>"+title);
		return title;
	}
	
	public String getLoginPageURL()
	{
		String url=eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION,AppConstants.SHORT_TIME_OUT);
		System.out.println("Url==>"+url);
		return url;
	}
	
	public boolean isForgotPasswordLinkExists()
	{
		return eleutil.doIsElementDisplayed(forgottenPwd);
	}
	
	public HomePage doLogin(String userName, String passWord)
	{
		System.out.println("App creds are:==>"+userName+":"+passWord);
		eleutil.waitForElementVisibility(email, AppConstants.DEFAULT_TIME_OUT).sendKeys(userName);
		eleutil.doSendKeys(pwd, passWord);
		eleutil.doClick(submit);
		return new HomePage(driver);
	}
}
