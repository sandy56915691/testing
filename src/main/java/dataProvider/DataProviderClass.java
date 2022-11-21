package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import baseClass.BaseClass;

public class DataProviderClass extends BaseClass {

	@DataProvider(name = "login_data")
	public Object[][] dataSupplier(){

		Object[][] arr = {{"sandy5691@gmail.com","Garg25@$","positive"},
				{"sandy5691@gmail.com","Garg26@$","negative"},
				{"sandy76@gmail.com","Garg25@$","negative"}};
		return arr;
	}

	@DataProvider(name = "currency_data")
	public Object[] dataSupplier2(){

		Object[] arr = {"USD","GBP","EUR"};
		return arr;
	}

	@DataProvider(name = "sort_by_filter")
	public Object[] dataSupplier3(){

		Object[] arr = {"Price (High > Low)","Price (Low > High)","Name (A - Z)","Name (Z - A)"};
		return arr;
	}
	
	@DataProvider(name = "search_data", indices = {0,3})
	public Object[] dataSupplier4(){

		Object[] arr = {"phone","laptop","apple","macbook"};
		return arr;
	}
	
	@DataProvider(name = "shipping_data")
	public Object[][] dataSupplier5() throws IOException{
		
		
		file = new File(System.getProperty("user.dir")+"/src/test/resources/data/data.xlsx");
		fis = new FileInputStream(file);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet("shipping");
		int row = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();
		Object[][] arr = new Object[row][col];
		for(int i=1; i<=row;i++) {
			for(int j=0; j<col; j++) {
				arr[i-1][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		return arr;
	}
}


