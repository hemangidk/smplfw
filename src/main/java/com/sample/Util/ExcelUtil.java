package com.sample.Util;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();

	public static void readExcel(String filePath, String fileName, String sheetName) throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {
			wb = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			wb = new HSSFWorkbook(inputStream);
		}

		Sheet sht = wb.getSheet(sheetName);
		int rowCount = sht.getLastRowNum() - sht.getFirstRowNum();

		for (int i = 0; i < rowCount + 1; i++) {
			Row row = sht.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				System.out.print(row.getCell(j).getStringCellValue() + "|| ");
			}
			System.out.println();
		}
	}

	public Object[][] ReadDataFromExcelFile(String FileName,String sheetname) throws IOException
	{
//		FileInputStream fileInputStream = new FileInputStream(
//				new java.io.File(".").getCanonicalPath() + "\\Data\\Ticket.xlsx"); // Excel sheet file location get
		
		FileInputStream fileInputStream = new FileInputStream(FileName); // mentioned here
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream); // get my workbook
		XSSFSheet worksheet = workbook.getSheet(sheetname);// get my sheet from workbook
		XSSFRow Row = worksheet.getRow(0); // get my Row which start from 0

		int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		int ColNum = Row.getLastCellNum(); // get last ColNum

		Object Data[][] = new Object[RowNum - 1][ColNum]; // pass my count data in array

		for (int i = 0; i < RowNum - 1; i++) // Loop work for Rows
		{
			XSSFRow row = worksheet.getRow(i + 1);

			for (int j = 0; j < ColNum; j++) // Loop work for colNum
			{
				if (row == null)
					Data[i][j] = "";
				else {
					XSSFCell cell = row.getCell(j);
					if (cell == null)
						Data[i][j] = ""; // if it get Null value it pass no data
					else {
						String value = formatter.formatCellValue(cell);
						Data[i][j] = value; // This formatter get my all values as string i.e integer, float all type
											// data value
					}
				}
			}
		}

		return Data;

	}

	
	public static void writeExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {
		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			wb = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			wb = new HSSFWorkbook(inputStream);
		}

		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		Row row = sheet.getRow(0);
		Row newRow = sheet.createRow(rowCount + 1);

		// Create a loop over the cell of newly created Row
		for (int j = 0; j < row.getLastCellNum(); j++) {
			Cell cell = newRow.createCell(j);
			cell.setCellValue(dataToWrite[j]);
		}

		// Close input stream
		inputStream.close();

		// Create an object of FileOutputStream class to create write data in excel file
		FileOutputStream outputStream = new FileOutputStream(file);

		// write data in the excel file
		wb.write(outputStream);

		// close output stream
		outputStream.close();
	}

	 public static void updateExcelCell( String fileNameWithPath, String sheetName,int irow, int icell, String dataToWrite)  {
//	        String excelFilePath = "JavaBooks.xls";
		 File file = new File(fileNameWithPath);
	        try {
	            FileInputStream inputStream = new FileInputStream(file);
	            Workbook workbook = WorkbookFactory.create(inputStream);
	 
	            Sheet sheet = workbook.getSheetAt(0);
                Row row = sheet.getRow(irow);
                Cell cell = row.getCell(icell);
                cell.setCellValue(dataToWrite);
	                 
	 
	            inputStream.close();
	 
	            FileOutputStream outputStream = new FileOutputStream(file);
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();
	             
	        } catch (IOException | EncryptedDocumentException  ex) {
	            ex.printStackTrace();
	        }
	        }
	  
	 
	
	// Main function is calling readExcel function to read data from excel file
	public static void main(String... strings) throws IOException {
//		String filePath = System.getProperty("user.dir");
//
//		// Call read file method of the class to read data
//
//		readExcel(filePath, "ExportExcel.xlsx", "ExcelGuru99Demo");
//
//		/////////////////////
//		// Create an array with the data in the same order in which you expect to be
//		///////////////////// filled in excel file
//
//		String[] valueToWrite = { "Mr. E", "Noida" };
//
//		// Create an object of current class
//
//		// Write the file using file name, sheet name and the data to be filled
//
//		writeExcel(System.getProperty("user.dir"), "ExportExcel.xlsx", "ExcelGuru99Demo", valueToWrite);
			updateExcelCell("D:\\doeexAutomation\\doeex_dev\\Data\\Mag_downloadable.xlsx","Sheet1",1,0,"QADecDwld2");
			updateExcelCell("D:\\doeexAutomation\\doeex_dev\\Data\\Mag_downloadable.xlsx","Sheet1",1,1,"QADecDwld2");

	}

}
