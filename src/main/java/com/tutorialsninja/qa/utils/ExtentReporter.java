package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.exec.OS;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() throws IOException {
		
//*****1)add extentreports dependency ,to import ExtentReports, ExtentReports library needed get from maven repo-> extentreports from aventstack
		ExtentReports extentReport = new ExtentReports();
//*****2)https://extentreports.com/->DOCS->Version5->Java->gettingStarted->Reporters(these are diff type of report we use sparkrepoter)->copy code of ExtentSparkReporter->to add theam,Report name,doc name,date
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");//ye path likha hi folder banjayega wahape
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);//give path of report
//*****3)config->set configration->like Theme, ReportName,DocumentTitle,Timestamp,ApplicationURL,BrowserName,OS,userName,javaVersion,Email Address,Password
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialNinja Test Automation Results");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy  hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);//extentReport is diff type of reporter we are using sparkreporter so attach
		
		
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\Config.properties");
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		//give data from property file->ex->configProp.getProperty("url")		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("BrowserName", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPaassword"));
		//*****to find out userName,javaVersion,uerName
		//*****System.getProperties().list(System.out);
		//System.out.println(System.getProperty("os.name"));
		//System.out.println(System.getProperty("user.name"));
		
		//some additional info what is url,browser,username of person who run script,os, javaversion
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
				
		return extentReport;
		//*****4) in listner class in onstart method write ->ExtentReports ententReport = ExtentReporter.generateExtentReport(); 
	}
}
