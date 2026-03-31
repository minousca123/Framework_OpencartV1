package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fi=new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;	
	}
	
	public int getColCount(String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int col = row.getLastCellNum();
		wb.close();
		fi.close();
		return col;
	}

	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e){
			data="";
		}
		
		wb.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File xFile = new File(path);
		if(!xFile.exists())   //if file does not exists, create new file
		{
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetName)==-1) //if sheet does not exist, create New Sheet
			wb.createSheet(sheetName);
		sheet = wb.getSheet(sheetName);
		
		if(sheet.getLastRowNum()==-1) //if row does not exist, create New row
			sheet.createRow(rowNum);
		row = sheet.getRow(rowNum);
		
		cell=row.createCell(colNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();		
	}
	
	public  void fillGreenColor(String sheetName,int rowNum,int colNum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
				
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	
	public void fillRedColor(String sheetName,int rowNum,int colNum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		
		cell.setCellStyle(style);		
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}
