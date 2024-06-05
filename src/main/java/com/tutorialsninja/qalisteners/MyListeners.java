package com.tutorialsninja.qalisteners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.utility;

public class MyListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
		
	//1->class implements ITestListener
		//2->can't import ITestListener -> change testng dependencies scope to ->compile
		//3->Source ->overried/implements method ->select method
	//4->delete methods body
	//5-> add this line after suite before test
		//<listeners>
			//<listener class-name="com.tutorialsninja.qalisteners.MyListeners"/>
		//</listeners>
	
	/*@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Project Test started");
	}
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"start executing");
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"got successfully executed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"got failed");
		System.out.println(result.getThrowable());//exception details will print
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"got Skipped");
		System.out.println(result.getThrowable());
	}
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("finish executing test");
	}*/
	//======================================================================================//
	@Override
	public void onStart(ITestContext context) {
		try {
				//ExtentReports extentReport = ExtentReporter.generateExtentReport();
				extentReport = ExtentReporter.generateExtentReport();
			}catch(Throwable e) {
				e.printStackTrace();
		}
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		//testName = result.getName();//it will give name of testcase
		//ExtentTest extentTest = extentReport.createTest(testName);//createTest method return obj of ExtentTest
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+"started execution");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS,result.getName()+"got successfull executed");
	}
	
	/*@Override
	public void onTestFailure(ITestResult result) {
		//*** bec other method is not creating screen shot folder so i creat this method, now screen shotfolder has been created
		String testName = result.getName();			//sourand with try/multi-catch
		 WebDriver driver=null;
		try {//*****to get driver from that failer class
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		TakesScreenshot t = (TakesScreenshot)driver;
		File srcScreenshot = t.getScreenshotAs(OutputType.FILE);
		File destinationScreenshotPath = new File(System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png");
		try {
			Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\Screenshots"));//import java.nio.file.Files;//import java.nio.file.Paths;
			FileHandler.copy(srcScreenshot, destinationScreenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String screenshotPathString = destinationScreenshotPath.getPath();//new
		
		System.out.println(result.getThrowable());//exception details will print
		System.out.println(testName+"got failed");
	
		extentTest.addScreenCaptureFromPath(screenshotPathString);
		extentTest.log(Status.INFO, result.getThrowable());//.log(Status status Markup markup)
		extentTest.log(Status.FAIL, testName+" got failed");//.log(Status status Markup markup)
	}*/
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		 WebDriver driver=null;
		try {//*****to get driver from that failer class//***** and make sure in testCass dec public WebDriver driver; to get driver here 
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";
//		try {
//			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String destinationScreenshotPath = utility.captureScreendhot(driver,result.getName());
		
		//to attach screenshot to report
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);//addScreenCaptureFromPath(String path)
		extentTest.log(Status.INFO, result.getThrowable());//.log(Status status Markup markup)
		extentTest.log(Status.FAIL, result.getName()+" got failed");//.log(Status status Markup markup)
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
				
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"got skipped");
	}
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();//***** if don't flush all details not added to report
		
		//***** below code for auto logic for extent report it will display report after test over 
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);//path of extent report 
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
