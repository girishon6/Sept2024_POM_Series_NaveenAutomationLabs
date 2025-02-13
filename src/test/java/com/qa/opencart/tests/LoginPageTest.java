package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class LoginPageTest extends BaseTest{

	
	@Test
	public void loginPageTitleTest()
	{
		String actTitle=lp.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
		
	}
	
	@Test
	public void loginPageUrlTest()
	{
		String actUrl=lp.getLoginPageURL();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	}
	
	@Test
	public void isForgotPasswordLinkExistsTest()
	{
		Assert.assertTrue(lp.isForgotPasswordLinkExists(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	
	@Test(priority=Integer.MAX_VALUE)
	public void doLoginTest()
	{
		hp=lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(hp.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
		
	}
	
	@Test
	public void logoTest()
	{
		Assert.assertTrue(commonsPage.islogoDisplayed(),AppError.LOGO_NOT_FOUND);
	}
	
	@DataProvider
	public Object[][] getFooterData()
	{
		return new Object[][]{{"About Us"},
			{"Returns"},
			{"Gift Certificates"},
			{"Wish List"}
		};
	}
	
	@Test(dataProvider="getFooterData")
	public void footerTest(String footer)
	{
		Assert.assertTrue(commonsPage.checkFooterList(footer));
	}
}
