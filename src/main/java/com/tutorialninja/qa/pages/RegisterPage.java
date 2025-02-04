package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement paswordConfirmField;

	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter']")
	private WebElement yesNewsletterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneText)
	{
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) 
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String passwordConfirmText)
	{
		paswordConfirmField.sendKeys(passwordConfirmText);
	}
	
	public void selectPrivacyPolicy() 
	{
		privacyPolicyField.click();
	}
	
	public void clickContinueButton()
	{
		continueButton.click();
	}
	
	public void selectYesNewesLetter()
	{
		yesNewsletterOption.click();
	}
	
	
	public String retrieveDuplicateEmailAddressWarning()
	{
		String duplicateEmailWarningText=duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrivePrivacyPolicyWarning()
	{
		 String privacyPolicyWarningText = privacyPolicyWarning.getText();
		 return privacyPolicyWarningText;
	}
	
	public String retriveFirstNameWarning()
	{
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String passwordConfirmText)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		paswordConfirmField.sendKeys(passwordConfirmText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText,String passwordConfirmText)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		paswordConfirmField.sendKeys(passwordConfirmText);
		yesNewsletterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public boolean displayStatusOfWarningMessage(String expectedprivacyPolicyWarning,String expectedfirstNameWarning)
	{
		String actualPrivacyPolicyWarningText = privacyPolicyWarning.getText();
		boolean privacyPolicyWarningStatus = actualPrivacyPolicyWarningText.contains(expectedprivacyPolicyWarning);
		
		String acutalfirstNameWarningText = firstNameWarning.getText();
		boolean firstNameWarningStatus = acutalfirstNameWarningText.equals(expectedfirstNameWarning);
		//***** return privacyPolicyWarningStatus && firstNameWarningStatus;
		return privacyPolicyWarningStatus && firstNameWarningStatus;
	}
}
