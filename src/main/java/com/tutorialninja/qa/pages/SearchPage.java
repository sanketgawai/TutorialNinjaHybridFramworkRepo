package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(xpath="//*[@id='content']/div[3]/div/div/div[2]/div[1]/h4/a")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//input[@id='button-search']/following-sibling::p")
	private WebElement noProductMessage;
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusOfHPValidProduct()
	{
		boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retriveNoProductMessageText()
	{
		String noProductMessageText = noProductMessage.getText();
		return noProductMessageText;
	}
}
