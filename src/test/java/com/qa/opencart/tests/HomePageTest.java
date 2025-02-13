package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class HomePageTest extends BaseTest
{
	private static final String Priority = null;

	@BeforeClass
	public void homePageSetup()
	{
		hp=lp.doLogin(prop.getProperty("username"),prop.getProperty("password") );
	}
	
	@Test
	public void getHomePageTitleTest()
	{
		Assert.assertEquals(hp.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test
	public void getHomePageURLTest()
	{
		Assert.assertTrue(hp.getHomePageURL().contains(AppConstants.HOME_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	}
	
	@Test
	public void isLogOutLinkExistTest()
	{
		Assert.assertTrue(hp.isLogOutLinkExist(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	
	@Test
	public void headersListTest()
	{
		List<String> ActualHeaders = hp.getHeadersList();
		System.out.println("Headers List==>"+ActualHeaders);
	}
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object[][] 
		{
			
			{"macbook",4},
			{"imac",2},
			{"samsung",2},
			{"canon",1},
			{"airtel",0}
		};
	}
	
	@Test(dataProvider="getSearchData")
	public void searchTest(String searchKey, int resultCount)
	{
		sp=hp.doSearch(searchKey);
		Assert.assertEquals(sp.getProductResultCount(), resultCount);
	}
}
