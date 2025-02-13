package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup()
	{
		hp=lp.doLogin("test200@gmail.com", "Test$123");
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
				{"macbook","MacBook Pro"},
				{"macbook","MacBook Air"},
				{"macbook","MacBook"}
		};	
	}	
	
	@Test(dataProvider="getProductData")
	public void productSearchHeaderTest(String searchKey, String productname)
	{
		sp=hp.doSearch(searchKey);
		pi=sp.selectProduct(productname);
		String actualProductHeader=pi.getProductName();
		Assert.assertEquals(actualProductHeader,productname);
		
	}
	
	@DataProvider
	public Object[][] getProductImageData()
	{
		return new Object[][] {
				{"macbook","MacBook Pro",4},
				{"macbook","MacBook Air",4},
				{"macbook","MacBook",5}
		};
	}
	
	
	@Test(dataProvider="getProductImageData")
	public void productImagesCountTest(String searchKey, String productName, int imgCount)
	{
		sp=hp.doSearch(searchKey);
		pi=sp.selectProduct(productName);
		int actualProductImageCount=pi.getProductImagesCount();
		Assert.assertEquals(actualProductImageCount,imgCount);
		
	}
	
	@Test
	public void productInfoTest()
	{
		sp=hp.doSearch("macbook");
		pi=sp.selectProduct("MacBook Pro");
		Map<String,String>productInfoMap=pi.getProductInfo();
		productInfoMap.forEach((k,v)->System.out.println(k+":"+v));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"),"In Stock");
		softAssert.assertEquals(productInfoMap.get("Header"),"MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("price"),"$2,000.00");
		softAssert.assertEquals(productInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(productInfoMap.get("Images count"),"4");
		softAssert.assertEquals(productInfoMap.get("exTax"),"$2,000.00");
		
		softAssert.assertAll();
		
	}

}
