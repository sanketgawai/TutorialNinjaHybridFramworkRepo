package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class utility {
//****insted of using extend key word and use inheritance we can simply use static method call it by class name
	
	public static final int WAIT_TIME=20;
	public static final int PAGE_WAIT_TIME=10;
	
	public static String generateEmailWithTimeStamp() {
		//simple by chaining
		Date date = new Date();
		//return date.toString().replace(" ", "_").replace(":", "_");
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "amotoori"+timestamp+"@gmail.com";
	}
	
	//*****Object[][] represents a two-dimensional array,where each element can be any Java object, like String, Integer, etc.
	public static Object[][] getTestDataFromexcel(String sheetName)//ye login me use ho raha hai->verifyLoginWithValidCredential
	{//***** need 3 dependency ->poi,poi-ooxml,poi-ooxml-schemas
		XSSFWorkbook workbook=null;//=null to  initialized
		File excelFile = new File(System.getProperty("user.dir")+"/src/main/java/com/tutorialsninja/qa/testdata/TutorialsNinjaTestData.xlsx");
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			//XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);//bellow line geving error show make it global
			workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		//sheet = workbook.getSheet(sheetName);//getSheet will return obj of excel of sheet
		//****move mouse on sheet create local veriable
		XSSFSheet sheet = workbook.getSheet(sheetName);//***getSheet will return obj of excel of sheet
		//once you get sheet need to find no of rows and columns in excel sheet
		int rows = sheet.getLastRowNum();//it will find no of row
		int columns = sheet.getRow(0).getLastCellNum();// it will find no of column
				
		Object [][] data = new Object[rows][columns];
		
		for(int i =0;i<rows;i++)
		{//****move mouse on row & cell create local veriable
			XSSFRow row = sheet.getRow(i+1);//**** write i+1, if i then we will get error
			
			for(int j=0;j<columns;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING: //** :
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					//data[i][j] = cell.getNumericCellValue();//**** if we use this line numaric data come with .0000, to avide this follow next line
					//data[i][j] = (int)cell.getNumericCellValue();//now we need to covert into string for that follow next line
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());//Overall, this expression converts the numeric value stored in the Excel cell into a string., cell.getNumericCellValue() gets the numeric value from the cell., (int) converts that numeric value to an integer., Integer.toString(...) converts the integer to its string representation. 
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
		
	}
	
	public static String captureScreendhot(WebDriver driver,String testName)
	{//***** and make sure in testCass dec public WebDriver driver; to get driver here
			File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";
			String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName+ ".png";
			try {
				FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return destinationScreenshotPath;
	}


	
}
