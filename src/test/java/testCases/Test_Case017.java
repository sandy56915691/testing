package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import PageClass.ProductPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;
import dataProvider.DataProviderClass;

public class Test_Case017 extends BaseClass {
	
	MainPage mp;
	LoginPage lp;
	LandingPage lap;
	ProductPage pp;
	
	@BeforeClass
	public void preSteps() throws InterruptedException {
		driver = PageDriver.getDriverInstance().getDriver();
		mp = new MainPage(driver);
		lp = mp.login_method();
		lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		
	}
	
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "search_data")
	public void add_to_wishlist(String product) throws IOException, InterruptedException {
		
		pp = lap.search_product(product);
		pp.search_verification(product);
		pp.add_to_wish_list(product);
		//scp.estimateShippingAndTaxes(country, region, pincode);
		//lap.logOut();
		//Assert.assertFalse(result);		
		
	}

}
