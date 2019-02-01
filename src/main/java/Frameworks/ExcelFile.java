package Frameworks;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFile {

	public String[][] readExcel(String filePath, String sheetName) throws IOException {

		File file = new File(filePath);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workBook = null;

		String fileExtensionName = filePath.substring(filePath.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {
			workBook = new XSSFWorkbook(inputStream);
		}
		else if (fileExtensionName.equals(".xls")) {
			workBook = new HSSFWorkbook(inputStream);
		}

		// Read sheet inside the workbook by its name
		Sheet workSheet = workBook.getSheet(sheetName);

		//int rowCount = (workSheet.getLastRowNum() - workSheet.getFirstRowNum() + 1);
		int rowCount = workSheet.getLastRowNum()+1;
		int colCount = workSheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[rowCount][colCount];

		// Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount; i++) {
			Row row = workSheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < colCount; j++) {
				// Print Excel data in console
				Cell cell = row.getCell(j);
				String value = new DataFormatter().formatCellValue(cell);
				data[i][j] = value;
			}
		}
		return data;
	}
}