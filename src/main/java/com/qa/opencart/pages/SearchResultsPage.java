package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage 
{
	private WebDriver driver;
	private ElementUtil eleutil;
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	By productResults=By.cssSelector("div.product-thumb");
	
	public int getProductResultCount()
	{	
		int resultcount=eleutil.waitForElementsPresence(productResults, AppConstants.DEFAULT_TIME_OUT).size();
		System.out.println("Result Count:"+resultcount);
		return resultcount;
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		System.out.println("Product name"+productName);
		eleutil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
		
	}

}
