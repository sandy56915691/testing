package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import PageClass.ProductPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;
import dataProvider.DataProviderClass;

public class Test_Case013 extends BaseClass {
	
	MainPage mp;
	LoginPage lp;
	LandingPage lap;
	
	@Test(priority = -1)
	public void preSteps() {
		driver = PageDriver.getDriverInstance().getDriver();
		mp = new MainPage(driver);
		lp = mp.login_method();
		lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
	}
	
	
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "search_data")
	public void check_total(String str) throws IOException, InterruptedException {
		
		
		ProductPage pp = lap.search_product(str);
		pp.add_to_cart(str);
		boolean result = pp.check_total();
		Assert.assertTrue(result);
		
		
	}

}
