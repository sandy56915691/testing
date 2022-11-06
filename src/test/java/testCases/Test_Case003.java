package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import baseClass.BaseClass;
import baseClass.PageDriver;
import dataProvider.DataProviderClass;

public class Test_Case003 extends BaseClass{

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "login_data")
	public void test3(String username, String password, String type) throws InterruptedException {
		
		WebDriver driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		LoginPage lp = mp.login_method();
		LandingPage lap = lp.login_method(username, password, type);
		if(type=="positive")
			lap.logOut();
		else
			Assert.assertNull(lap);
		Thread.sleep(2000);//commit
	}
}
