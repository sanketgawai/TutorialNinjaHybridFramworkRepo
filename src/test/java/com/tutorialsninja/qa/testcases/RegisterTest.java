package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountSuccessPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.base.Basee;
import com.tutorialsninja.qa.utils.utility;

public class RegisterTest extends Basee{
//in selenium version 4.6.0 we get selenium manager which include inbuild drivers
//no need to provide driver path 
	
	//**** two way 1st this constructor here this constructor it will call the supper calss constructor which we conment out in base
//	 public  Register()
//	 {
//		super();//****This line calls the constructor of the superclass (the class that this class extends, if any). In this case, it's calling the constructor of the superclass without any arguments. 
//	 }
	
//****Verify Registering an Account by providing only the Mandatory fields
	public WebDriver driver; //*****public bec to get driver in extentreport
	RegisterPage registerPage;
	@BeforeMethod
	public void openApplicationAndSetup()
	{
//		driver.get("https://tutorialsninja.com/demo/");
		loadProperties();
		
		driver =initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		//driver.get("https://tutorialsninja.com/demo/");//base class me hi declare kiya hai
		HomePage homePage = new HomePage(driver);
		
		homePage.clickOnMyAccountDropMenu();
		registerPage =homePage.selectRegisterOption();
	}
	
	@Test(priority=1)//****we are geting data from property file but not working find the solv
	public void verifyRegisteringAnAccountWithMandatoryFields()  
	{		
		
		//driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		//driver.findElement(By.id("input-email")).sendKeys(utility.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys("12345");
		
		
		//registerPage.enterFirstName(dataProp.getProperty("firstName"));
		//registerPage.enterLastName(dataProp.getProperty("LastName"));
		//registerPage.enterEmailAddress(utility.generateEmailWithTimeStamp());
		//registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		//registerPage.enterPassword(prop.getProperty("validPaassword"));
		//registerPage.enterConfirmPassword(prop.getProperty("validPaassword"));
		//registerPage.selectPrivacyPolicy();
		//registerPage.clickContinueButton();
		//*****this line for above 6 line
		
		//registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("LastName"),utility.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPaassword"),prop.getProperty("validPaassword"));
		AccountSuccessPage accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("LastName"),utility.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPaassword"),prop.getProperty("validPaassword"));  
 
		//Assert.assertTrue(actualSuccessHeading.contains("Your Account Has Been Created!"));
		//String actualSuccessHeading = accountSuccessPage.retriveAccountSuccessPageHeading();
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfulyCreatedHeading"),"Accoutn Sucess Page is not Display");
	}
	
	//****Verify Registering an Account by providing all fields(select radio button subscrib)	
	@Test(priority=2)
	public void verifyRegisteringAnAccountWithAllMandatoryFields()
	{
		
		//registerPage.enterFirstName(dataProp.getProperty("firstName"));
		//registerPage.enterLastName(dataProp.getProperty("LastName"));
		//registerPage.enterEmailAddress(utility.generateEmailWithTimeStamp());
		//registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		//registerPage.enterPassword(prop.getProperty("validPaassword"));
		//registerPage.enterConfirmPassword(prop.getProperty("validPaassword"));
		//registerPage.selectYesNewesLetter();
		//registerPage.selectPrivacyPolicy();
		//registerPage.clickContinueButton();
		
		//registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("LastName"),utility.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPaassword"),prop.getProperty("validPaassword"));
		//AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		AccountSuccessPage accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("LastName"),utility.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPaassword"),prop.getProperty("validPaassword"));
		//String actualSuccessHeading = accountSuccessPage.retriveAccountSuccessPageHeading();
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfulyCreatedHeading"),"Accoutn Sucess Page is not Display");
	}
	
	@Test(priority=3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress()//testcase
	{
		//registerPage.enterFirstName(dataProp.getProperty("firstName"));
		//registerPage.enterLastName(dataProp.getProperty("LastName"));
		//registerPage.enterEmailAddress(prop.getProperty("validEmail"));//this validEmail is existing  email id
		//registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		//registerPage.enterPassword(prop.getProperty("validPaassword"));
		//registerPage.enterConfirmPassword(prop.getProperty("validPaassword"));
		//registerPage.selectYesNewesLetter();
		//registerPage.selectPrivacyPolicy();
		//registerPage.clickContinueButton();
																													//***this is ExistingEmailAddress
		registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("LastName"),prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPaassword"),prop.getProperty("validPaassword"));
		//String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning(); 
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains("Warning: E-Mail Address is already registered!"),"worning message regarding duplicate email id not displayed");
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()//testcase
	{
		
		registerPage.clickContinueButton();
		
		//String actualPrivacyWarning = registerPage.retrivePrivacyPolicyWarning(); 
		//Assert.assertTrue(actualPrivacyWarning.contains("Warning: You must agree to the Privacy Policy!"));
			
		//String actualFirstNameWarning = registerPage.retriveFirstNameWarning(); 
		//Assert.assertTrue(actualFirstNameWarning.contains("First Name must be between 1 and 32 characters!"),"name worning massage not displayed");
		
		//Assert.assertTrue(registerPage.retrivePrivacyPolicyWarning().contains("Warning: You must agree to the Privacy Policy!"));
		//Assert.assertTrue(registerPage.retriveFirstNameWarning().contains("First Name must be between 1 and 32 characters!"),"name worning massage not displayed");
		
		registerPage.displayStatusOfWarningMessage("Warning: You must agree to the Privacy Policy!", "First Name must be between 1 and 32 characters!");
		//*** these are three ways
	}
	
	@AfterMethod()
	public void closeBrowser()
	{
		driver.quit();
	}
}
