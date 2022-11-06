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

}
