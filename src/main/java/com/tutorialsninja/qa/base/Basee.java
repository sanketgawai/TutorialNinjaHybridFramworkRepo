package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.tutorialsninja.qa.utils.utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basee {
	
	WebDriver driver;
	public Properties prop;//***** this public is so imp, if not use prop.getProperty(),won't come in test class
	public Properties dataProp;
	
//	public Basee()//****ye constructor hai testclass me testclass ke constructor me super(); write karege to ye call hoga inheritance needed 
//	{
//		prop = new Properties();
//		//File propFile = new File(System.getProperty("user.dir"+"/src/main/java/com/tutorialninja/qa/config/Config.properties"));
//		File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialninja/qa/config/Config.properties");
//		//FileInputStream file = new FileInputStream(propFile);
//		//prop.load(file);
//		try {
//			FileInputStream file = new FileInputStream(propFile);
//			prop.load(file);
//		}catch(Throwable e)//(Throwable e)->Throwable is grand parent,(Exception e)->parent
//		{
//			e.printStackTrace();
//		}
//		
//		//**for testdata.properties
//				dataProp = new Properties();					//  or-> / also will work
//				File dataPropFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/testdata/testdata.properties");
//				try {
//						FileInputStream datafis = new FileInputStream(dataPropFile);
//						dataProp.load(datafis);//****this is most important
//				}catch(Throwable e) {
//				
//					e.printStackTrace();
//				}
//	}
	
	public void loadProperties()//***** 1)need apache poi-> poi,poi-ooxml, poi-ooxml-schemas
	{
		//**for Config.properties
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialninja/qa/config/Config.properties");
//		//FileInputStream file = new FileInputStream(propFile);
//		//prop.load(file);
		try {
			FileInputStream file = new FileInputStream(propFile);
			prop.load(file);//***** this is most imp//****read property like key and pair
		}catch(Throwable e)//(Throwable e)->Throwable is grand parent,(Exception e)->parent
		{
			e.printStackTrace();
		}
		
				//**for testdata.properties
				dataProp = new Properties();					//  or-> / also will work
				File dataPropFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/testdata/testdata.properties");
				try {
						FileInputStream datafis = new FileInputStream(dataPropFile);
						dataProp.load(datafis);//****this is most important
				}catch(Exception e) {
				
					e.printStackTrace();
				}
	}
	
//	public void loadProperties2()
//	{
//		//**for testdata.properties
//		dataProp = new Properties();					//  or-> / also will work
//		File dataPropFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/testdata/testdata.properties");
//		try {
//				FileInputStream datafis = new FileInputStream(dataPropFile);
//				dataProp.load(datafis);//****this is most important
//		}catch(Exception e) {
//		
//			e.printStackTrace();
//		}
//	}
	

	public WebDriver initializeBrowserAndOpenApplication(String browserName) 
	{
				
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new  EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(utility.WAIT_TIME,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(utility.PAGE_WAIT_TIME, TimeUnit.SECONDS);//it sets the page load timeout to 10 seconds. This means that if a page takes longer than 10 seconds to load, it will throw a timeout exception.
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(prop.getProperty("url"));//***** ye method url lake degi properties file se
		return driver;
	}
	
//	public void takeScreenShot() throws IOException
//	{
//		TakesScreenshot t = (TakesScreenshot)driver;
//		File src = t.getScreenshotAs(OutputType.FILE);
//		File dest = new File("F:\\movies season\\new\\screenshot");
//		FileHandler.copy(src,dest);
//	}
}
