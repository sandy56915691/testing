package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import PageClass.WishListPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;

public class Test_Case018 extends BaseClass {
	
	MainPage mp;
	LoginPage lp;
	LandingPage lap;
	WishListPage wlp;
	
	@BeforeClass
	public void preSteps() throws InterruptedException {
		driver = PageDriver.getDriverInstance().getDriver();
		mp = new MainPage(driver);
		lp = mp.login_method();
		lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		
	}
	
	@Test()
	public void remove_out_of_stock() throws IOException, InterruptedException {
		
		wlp = lap.viewWishList();
		boolean result = wlp.remove_out_of_stock_items();
		Assert.assertTrue(result,"Zero Out Of Stock Items");	
		
	}

}
