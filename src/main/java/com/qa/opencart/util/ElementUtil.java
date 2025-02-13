package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsutil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsutil=new JavaScriptUtil(driver);
	}

	public void highlightElement(WebElement element)
	{
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsutil.flash(element);
		}
	}
	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		highlightElement(element);
		return element;
	}
	
	

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public void doSendKeys(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	public void doSendKeys(String locator, String value, String keyValue) {
		getElement(getLocator(locator, value)).sendKeys(keyValue);
	}
	
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
		
	public void doClick(String locator, String value)
	{
		getElement(getLocator(locator,value)).click();
	}
	
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public String doGetText(String locator, String value)
	{
		return getElement(getLocator(locator,value)).getText();
	}

	public boolean doIsElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("element is not displayed");
			return false;
		}
	}
	
	public static By getLocator(String locatorType, String locatorValue) {

		By locator = null;
		switch (locatorType.toUpperCase().trim()) {
		case "ID":
			locator = By.id(locatorValue);
			break;
		case "NAME":
			locator = By.name(locatorValue);
			break;
		case "CLASSNAME":
			locator = By.className(locatorValue);
			break;
		case "LINKTEXT":
			locator = By.linkText(locatorValue);
			break;
		case "PARTIALLINKTEXT":
			locator = By.partialLinkText(locatorValue);
			break;
		case "CSSSELECTOR":
			locator = By.cssSelector(locatorValue);
			break;
		case "XPATH":
			locator = By.xpath(locatorValue);
			break;
		case "TAGNAME":
			locator = By.tagName(locatorValue);
			break;
		default:
			System.out.println("Invalid Locator, Please use the right locator");
			break;
		}
		return locator;
	}
	
	//***************Select Tag Dropdown Utils***************
	
	public void doSelectDropdownByIndex(By locator,int index)
	{
		Select dayDropDown=new Select(getElement(locator));
		dayDropDown.selectByIndex(index);
		
	}
		
	public void doSelectDropdownByVisibletext(By locator,String visibletext)
	{
		Select monthDropDown = new Select(getElement(locator));
		monthDropDown.selectByVisibleText(visibletext);
	}
	
	public void doSelectDropdownByValue(By locator, String value)
	{
		Select yearDropdown = new Select(getElement(locator));
		yearDropdown.selectByValue(value);
	}
	
	public int getDropDownOptionsCount(By locator) 
	{
		Select countrySel = new Select(getElement(locator));
		List<WebElement> countryList = countrySel.getOptions();

		int CountryListSize = countryList.size();
		return CountryListSize;
	}

	public List<String> getDropDownOptionsTextsList(By locator)
	{
		Select countrySel= new Select(getElement(locator));
		List<WebElement> options = countrySel.getOptions();
		List<String> al = new ArrayList<String>(); 
		for(WebElement e:options)
		{
			String countryName=e.getText();
			al.add(countryName);
		}
		
		return al;
	}
	
	public void printDropDownOptionsText(By locator)
	{
		Select select=new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		
		for(WebElement op:options)
		{
			String text=op.getText();
			System.out.println(text);
		}
		
	}
	
	//***************Actions Class methods***********
	
	public void handleTwoLevelMenuSubmenuHandling(By addOn,By visaService) throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.moveToElement(getElement(addOn)).build().perform();
		getElement(visaService).click();
	}
	
	public void doActionsSendKeys(By locator, CharSequence...text)
	{
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), text).build().perform();
	}
	
	public void doActionsClick(By locator)
	{
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	
	/**
	 * send keys with a pause while entering characters
	 * @param locator
	 * @param str
	 * @param pauseTime
	 */
	public void doSendKeysWithPause(By locator, String str, long pauseTime)
	{	
		Actions act=new Actions(driver);
		char[] ch=str.toCharArray();
		
		for(char c:ch)
		{
			act.sendKeys(getElement(locator), String.valueOf(c))
				.pause(pauseTime).build().perform();
			
		}
	}
	
	//************Wait Methods**************
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. 
	 * This does notnecessarily mean that the element is visible.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	public WebElement waitForElementPresence(By locator, long timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		highlightElement(element);
		return element;
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	public WebElement waitForElementVisibility(By locator, long timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));	
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		highlightElement(element);
		return element;
	}
	
	/**
	 * An expectation for checking that the title contains a case-sensitive substring
	 * @param str
	 * @param timeouts
	 * @return
	 */
	public String waitForTitleContains(String str, long timeouts)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try 
		{ 
			if(wait.until(ExpectedConditions.titleContains(str)))
			{	
				return driver.getTitle();
			}
		} 
		catch (TimeoutException e)
		{
			System.out.println(str+" Title Not found");
			
		}
		return null;
	}
	
	
	/**
	 *  An expectation for checking the title of a page.
		title the expected title, which must be an exact match
	 * @param str
	 * @param timeouts
	 * @return
	 */
	public String waitForTitleIs(String str, long timeouts)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try 
		{ 
			if(wait.until(ExpectedConditions.titleIs(str)))
			{	
				return driver.getTitle();
			}
		} 
		catch (TimeoutException e)
		{
			System.out.println(str+" Title Not found");
			
		}
		return null;
	}
	
	
	/**
	 * An expectation for the URL of the current page to contain specific text
	 * @param str
	 * @param timeouts
	 * @return
	 */
	
	public String waitForURLContains(String str, long timeouts)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeouts));
		try 
		{
			if(wait.until(ExpectedConditions.urlContains(str)))
			{
				return driver.getCurrentUrl();
			}	
		} 
		catch (TimeoutException e) 
		{
			System.out.println("url not found");
			
		}
		return null;
		
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
		} catch (TimeoutException e) {
			return Collections.emptyList();
		}
		
	}
	
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
		

}
