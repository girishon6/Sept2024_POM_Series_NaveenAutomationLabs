package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private By productName = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

	public String getProductName() {
		String itemName = eleutil.doGetText(productName);
		System.out.println("Product Name:===>" + itemName);
		return itemName;

	}

	public int getProductImagesCount() {
		int imagesCount = eleutil.waitForElementsPresence(productImages, AppConstants.DEFAULT_TIME_OUT).size();
		System.out.println(getProductName() + ": Images Count" + imagesCount);
		return imagesCount;

	}

	public Map<String, String> getProductInfo() {
		productMap = new HashMap<String, String>();
		//productMap = new LinkedHashMap<String, String>();
		//productMap = new TreeMap<String, String>();
		productMap.put("Header", getProductName());
		productMap.put("Images count", getProductImagesCount()+"");
		getProductMetaData();
		getProductPriceData();
		System.out.println("-----------------------");
		return productMap;
	}

	public void getProductMetaData() {
		List<WebElement> metaList = eleutil.waitForElementsPresence(productMetadata, AppConstants.DEFAULT_TIME_OUT);
		for (WebElement e : metaList) {
			String metaString = e.getText();
			String meta[] = metaString.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);

		}
	}

	//$2,000.0
	//Ex tax:$2,000.0
	public void getProductPriceData() {
		List<WebElement> priceList = eleutil.waitForElementsPresence(productPriceData,AppConstants.DEFAULT_TIME_OUT);
		String productPrice=priceList.get(0).getText().trim();
		String productExTax=priceList.get(1).getText().split(":")[1].trim();
		productMap.put("price",productPrice);
		productMap.put("exTax", productExTax);
	}

}
