package com.tutorialsninja.qa.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Temp {
	
	Properties prop;

	public String generateEamilWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "amotoori"+timestamp+"@gmail.com";
	}

	public void getDatafromPropertyFile()
	{
		prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\Config\\Config.properties");
		try {
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}

	public Object[][] getDataFromExcelSheet(String sheetName)
	{
		FileInputStream fis;
		XSSFWorkbook workbook=null;
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\testdata\\ninja.xlsx");
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		}catch(Throwable e){
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows =sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][columns];
		
		for(int i =0;i<rows;i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<columns;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				switch(cellType) {
				case STRING: 
					data[i][j] = cell.getRichStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
			
		}
		return data;
	}
	
	
}

