package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	//stale element exception -> when we move to one page to another page and come back to page
		//the priviously created webelement will be broken driver.findElement = connection will lose
		//thats why we use pom(page object module), design -> PageFactory
	
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(xpath="//div[@id='search']/input")
	private WebElement searchBoxField;

	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccountDropMenu()
	{
		myAccountDropMenu.click();
	}

//	public void selectLoginOption()
//	{
//		loginOption.click();
//		
//	}
	public LoginPage selectLoginOption()
	{
		loginOption.click();
		return new LoginPage(driver);
	}
	
//	public void selectRegisterOption() 
//	{
//		registerOption.click();
//	}
	public RegisterPage selectRegisterOption() 
	{
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductIntoSearchBoxField(String productText)
	{
		searchBoxField.sendKeys(productText);
	}
	
//	public void clickOnSearchButton()
//	{
//		searchButton.click();
//	}
	public SearchPage clickOnSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}
}
