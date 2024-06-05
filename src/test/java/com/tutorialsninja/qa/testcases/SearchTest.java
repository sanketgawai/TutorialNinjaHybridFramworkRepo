package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.SearchPage;
import com.tutorialsninja.qa.base.Basee;
import com.tutorialsninja.qa.utils.utility;

public class SearchTest extends Basee{
	public WebDriver driver;//*****public bec to get driver in extentreport
	SearchPage searchPage;
	HomePage homePage;
	@BeforeMethod
	public void openApplicationAndSetup() 
	{
//		System.setProperty("webdriver.chrome.driver","E:\\software testing\\selenium\\new browser chromium chrome driver\\chromedriver-win64\\chromedriver.exe");
//		ChromeOptions co = new ChromeOptions();
//		co.setBinary("E:\\software testing\\selenium\\new browser for testing chromium\\chrome-win64\\chrome.exe");
//		co.addArguments("--remote-allow-origins=*");
//		driver = new ChromeDriver(co);
//		//driver = new FirefoxDriver();
//		//driver = new EdgeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver=initializeBrowserAndOpenApplication("chrome");
		loadProperties();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
	
	@Test(priority=1)
	public void verifySearchWithVlidProduct()
	{
		
		
		//driver.findElement(By.xpath("//div[@id='search']/input")).sendKeys("HP");
		//driver.findElement(By.xpath("//div[@id='search']/input")).sendKeys(dataProp.getProperty("validProduct"));
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		SearchPage searchPage =homePage.clickOnSearchButton();
		
		//WebElement laptop =driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div[1]/h4/a"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid Product is not display");
	}
	
	@Test(priority=2)//we make this testcase fail
	public void verifySearchWithInvalidProduct()
	{
		
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		SearchPage searchPage=homePage.clickOnSearchButton();
		
		String actualSearchMessage = searchPage.retriveNoProductMessageText();
		
		//this work ->Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.","no product in search result");
		Assert.assertEquals(actualSearchMessage, "There is space product that matches the search criteria.","no product in search result");
	}
	
	@Test(priority=3)
	public void verifySearchWithoutProduct() throws InterruptedException
	{
		SearchPage searchPage=homePage.clickOnSearchButton();
		
		String actualSearchMessage = searchPage.retriveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.","no product in search result");
	}
		
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}
