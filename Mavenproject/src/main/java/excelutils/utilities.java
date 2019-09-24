package excelutils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Font;

public class utilities {
Workbook wb;
	public utilities() throws Throwable
	{
	FileInputStream fi =new  FileInputStream("C:\\Users\\deepthi.m\\workspace\\Mavenproject\\Testinputs\\inputsheet.xlsx");
	wb=WorkbookFactory.create(fi);
	}
public int rowcount(String sheetname){
return wb.getSheet(sheetname).getLastRowNum();
}
public int colcount(String sheetname,int row)
{

	return wb.getSheet(sheetname).getRow(row).getLastCellNum();

}

public String readdata(String sheetname,int row,int column){
	String data=" ";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	 {
	int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
     data=String.valueOf(celldata);
	 
	 } else {
	data= wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
return data;	
}	
public void setcelldata(String sheetname,int row,int column,String status) throws Throwable{
Sheet ws=wb.getSheet(sheetname);
Row rownum=ws.getRow(row);
Cell cell=rownum.createCell(column);
cell.setCellValue(status);
if (status.equalsIgnoreCase("pass")) {
	CellStyle style= wb.createCellStyle();


	Font font=wb.createFont();
	//Apply Colour To Text
	font.setColor(IndexedColors.GREEN.index);
	//Apply Bold To Text
	font.setBold(true);
	//Set Font
	style.setFont(font);
	//Set Cell Style
	rownum.getCell(column).setCellStyle(style);
}else
	if(status.equalsIgnoreCase("Fail"))
	{
		//Create Cell Style
		CellStyle style=wb.createCellStyle();
		//Craete Font
		Font font=wb.createFont();
		//Apply colour to text
		font.setColor(IndexedColors.RED.index);
		//Apply Bold to text
		font.setBold(true);
		//Set Font
		style.setFont( font);
		//Set Cell Style
		rownum.getCell(column).setCellStyle(style);
	}else
		if(status.equalsIgnoreCase("Not Executed"))
		{
			//Create Cell Style
			CellStyle style=wb.createCellStyle();
			//Craete Font
			 Font font = wb.createFont();
			//Apply colour to text
			font.setColor(IndexedColors.BLUE.index);
			//Apply Bold to text
			font.setBold(true);
			//Set Font
			style.setFont(font);
			//Set Cell Style
			rownum.getCell(column).setCellStyle(style);
		}

FileOutputStream fos=new FileOutputStream("C:\\Users\\deepthi.m\\workspace\\Mavenproject\\testoutputs\\outputsheet.xlsx");
wb.write(fos);
fos.close();
	
}

public static void main(String[] args) throws Throwable 
{
utilities excel=new utilities();

System.out.println(excel.rowcount("Sheet1"));

System.out.println(excel.colcount("Sheet1", 1));

System.out.println(excel.readdata("Sheet1", 1, 1));


excel.setcelldata("Sheet1", 1, 2, "Pass");
excel.setcelldata("Sheet1", 2, 2, "Fail");
excel.setcelldata("Sheet1", 3, 2, "Not Executed");
}



}
