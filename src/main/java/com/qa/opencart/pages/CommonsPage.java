package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CommonsPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	public CommonsPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);
		
	}
	
	By logo=By.className("img-responsive");
	By footerLinks=By.xpath("//footer//a");
	
	public boolean islogoDisplayed()
	{
		return eleutil.doIsElementDisplayed(footerLinks);
	}
	
	public List<String> getFootersList()
	{
		List<WebElement> footerList = eleutil.waitForElementsPresence(footerLinks, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Total number of footers: "+footerList.size());
		List<String> footers=new ArrayList<String>();
		for(WebElement fl:footerList)
		{
			String footerText=fl.getText();
			footers.add(footerText);
		}
		return footers;
		
	}
	
	public boolean checkFooterList(String footName)
	{
		return getFootersList().contains(footName);
	}

}
