package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;

import com.qa.opencart.util.ElementUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil eleutil;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleutil=new ElementUtil(driver);
	}

	// By locators
	private By logout = By.linkText("Logout");
	private By headers = By.cssSelector("div#content >h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	// 2. actions methods
	public String getHomePageTitle() 
	{
		String title=eleutil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE,AppConstants.DEFAULT_TIME_OUT );
		System.out.println("Title==>" + title);
		return title;
	}

	public String getHomePageURL() {
		String url=eleutil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Url==>" + url);
		return url;
	}

	public boolean isLogOutLinkExist() {
		return eleutil.doIsElementDisplayed(logout);
	}
	
	public void logout()
	{		
		if(isLogOutLinkExist())
		{	
			eleutil.doClick(logout);
		}
		//pending WIP
	}

	public List<String> getHeadersList() 
	{
		List<WebElement> headersList=eleutil.waitForElementsVisible(headers, AppConstants.SHORT_TIME_OUT);
		List<String> headersNameList=new ArrayList<String>();
		for(WebElement e:headersList)
		{
			String header=e.getText();
			headersNameList.add(header);
			
		}
		return headersNameList;
	}
	
	public SearchResultsPage doSearch(String searchKey)
	{
		System.out.println("Search Key:"+searchKey);
		WebElement searchEle=eleutil.waitForElementVisibility(search, AppConstants.DEFAULT_TIME_OUT);
		eleutil.doSendKeys(searchEle, searchKey);
		eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}
