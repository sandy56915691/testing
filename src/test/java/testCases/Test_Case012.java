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

public class Test_Case012 extends BaseClass {
	
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "search_data")
	public void check_sort_by_filter(String str) throws IOException, InterruptedException {
		
		driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		LoginPage lp = mp.login_method();
		LandingPage lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		ProductPage pp = lap.search_product(str);
		boolean result = pp.search_verification(str);
		lap.logOut();
		Assert.assertTrue(result);
		
		
	}

}
