package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;
import dataProvider.DataProviderClass;

public class Test_Case011 extends BaseClass {
	
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "sort_by_filter")
	public void check_sort_by_filter(String str) throws IOException, InterruptedException {
		
		driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		LoginPage lp = mp.login_method();
		LandingPage lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		boolean result = lap.sort_by_dropdown_click(str);
		lap.logOut();
		Assert.assertTrue(result);
		
	}

}
