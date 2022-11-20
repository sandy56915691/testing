package dataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
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
		
		Object[] arr = {"Price (High > Low)","Price (Low > High)"};
		return arr;
	}
}
