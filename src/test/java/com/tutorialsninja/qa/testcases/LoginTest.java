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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.base.Basee;
import com.tutorialsninja.qa.utils.utility;

//***** searcg -> maven-archetype-quickstart
//org.apache.maven.archetypes ||  maven-archetype-quickstart  || 1.4
public class LoginTest extends Basee {
//****in selenium version 4.6.0 we get selenium manager which include inbuild drivers
//no need to provide driver path 
	
//***Verify logging into the Application using valid credentials
//***Verify logging into the Application using invalid credentials (i.e. Invalid email address and Invalid Password)
	
	public WebDriver driver;//****public bec to get driver in extentreport 
	//public Login()
	//{
		//super();
	//}
	LoginPage loginPage;
	@BeforeMethod
	public void openApplicationAndSetup()
	{
		loadProperties();//properties files se data le ne ki method
		
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		loginPage =homePage.selectLoginOption();
		
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")//we can also use method name
	public void verifyLoginWithValidCredential(String email, String password) 
	{
		//driver.findElement(By.id("input-email")).sendKeys("vaxokin223@em2lab.com");
		//driver.findElement(By.id("input-password")).sendKeys("loww");
		//***** here we get email & pass from Config.properties file
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty(email));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty(password));
		//driver.findElement(By.id("input-email")).sendKeys(email);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		
		
		//loginPage.enterEmailAddress(email);
		//loginPage.enterPassword(password);
		//loginPage.clickOnLoginButton();
		//AccountPage accountPage= new AccountPage(driver);
		//*****for all above for line this one line//email,pass,click
		AccountPage accountPage=loginPage.login(email,password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "Edit your Account Information option is not displayed");
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData()
	{
		//Object[][]  data = {{"amotooricap9@gmail.com","12345"},
				//{"amotooricap1@gmail.com","12345"},{"amotooricap3@gmail.com","12345"}};
		Object[][] data = utility.getTestDataFromexcel("Login");//Login sheet name hai
		return data;
	}
	
	@Test(enabled=false)
	public void vrifyLoginWithInvalidCredential()
	{
		//driver.findElement(By.id("input-email")).sendKeys("vaxokin2234@gmail.com");
		//to send diff email each time we add date&time init
		//***** if we use wrong mail 5 time it wont allow same mail again so we use TimeStamp here it will change each time
		//driver.findElement(By.id("input-email")).sendKeys("vaxokin2234"+generateTimeStamp()+"@gmail.com");
		
		
		//driver.findElement(By.id("input-email")).sendKeys(utility.generateEmailWithTimeStamp());
		
		//loginPage.enterEmailAddress((utility.generateEmailWithTimeStamp()));
		//System.out.println((utility.generateEmailWithTimeStamp()));
		//loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		//loginPage.clickOnLoginButton();
		//*****for all above for line this one line//email,pass,click
		loginPage.login((utility.generateEmailWithTimeStamp()),dataProp.getProperty("invalidPassword"));
		
		//String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWorningMessage =dataProp.getProperty("emailPasswordNoMatchWarning");
		//Assert.assertTrue(actualWarningMessage.contains(expectedWorningMessage));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")));
	}
	
	@Test(enabled=false)
	public void verifyLoginWithInvalidEmailAndValidPass()
	{
		
		//loginPage.enterEmailAddress((utility.generateEmailWithTimeStamp()));
		//loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		//loginPage.clickOnLoginButton();
		//*****for all above for line this one line//email,pass,click
		loginPage.login((utility.generateEmailWithTimeStamp()),dataProp.getProperty("invalidPassword"));
		
		//String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWorningMessage =dataProp.getProperty("emailPasswordNoMatchWarning");
		//Assert.assertEquals(actualWarningMessage, expectedWorningMessage,"Waring message not matching");
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")));
	}
	
	@Test(enabled=false)
	public void verifyLoginWithValidEmailAndInvalidPass()
	{
		
		//loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		//loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		//loginPage.clickOnLoginButton();
		//*****for all above for line this one line//email,pass,click
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		
		//String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWorningMessage =dataProp.getProperty("emailPasswordNoMatchWarning");
		//Assert.assertEquals(actualWarningMessage, expectedWorningMessage,"Waring message not matching");
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")));
	}
	
	@Test(enabled=false)
	public void verifyLoginWithWithoutProvideCredential()
	{
		
		
		loginPage.clickOnLoginButton();
		//String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWorningMessage =dataProp.getProperty("emailPasswordNoMatchWarning");
		//Assert.assertEquals(actualWarningMessage, expectedWorningMessage,"Waring message not matching");
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")));
	}
	
	@AfterMethod()
	public void tearDown()
	{
		driver.quit();
	}
	
//	public String generateEmailWithTimeStamp()
//	{//simple by chaining
//		Date date = new Date();
//		//return date.toString().replace(" ", "_").replace(":", "_");
//		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
//		return "amotoori"+timestamp+"@gmail.com";
//	}
	
}
