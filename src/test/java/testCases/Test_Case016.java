package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import PageClass.ShoppingCartPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;
import dataProvider.DataProviderClass;

public class Test_Case016 extends BaseClass {
	
	MainPage mp;
	LoginPage lp;
	LandingPage lap;
	ShoppingCartPage scp;
	
	@BeforeClass
	public void preSteps() {
		driver = PageDriver.getDriverInstance().getDriver();
		mp = new MainPage(driver);
		lp = mp.login_method();
		lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		scp = lap.viewCart();
	}
	
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "shipping_data")
	public void estimate_shipping_and_tax_test(String country, String region, String pincode) throws IOException, InterruptedException {
		
		scp.estimateShippingAndTaxes(country, region, pincode);
		//lap.logOut();
		//Assert.assertFalse(result);		
		
	}

}
