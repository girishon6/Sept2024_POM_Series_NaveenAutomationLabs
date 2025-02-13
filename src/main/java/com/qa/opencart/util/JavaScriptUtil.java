package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver)
	{
			this.driver=driver;
			js=(JavascriptExecutor)driver;
	}
	
	public String getPageTitleUsingJS()
	{
		return js.executeScript("return document.title;").toString();
		
	}
	
	public String getPageURL()
	{
		return js.executeScript("return document.URL;").toString();
	}

	public void refreshBrowserByJS()
	{
		js.executeScript("history.go(0);");
	}
	
	public void naviagteToBackPage()
	{
		js.executeScript("history.go(-1);");
	}
	
	public void navigateToForwardPage() 
	{
		js.executeScript("history.go(1);");
	}
	
	public void drawBorder(WebElement element)
	{
		js.executeScript("arguments[0].style.border='3px solid red'",element);
	}
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");//Grey
		for (int i = 0; i < 20; i++) {
			changeColor("rgb(0,200,0)", element);//Green
			changeColor(bgcolor, element);//Grey
		}
	}
	
	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	
}
