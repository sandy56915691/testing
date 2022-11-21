package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import PageClass.ShoppingCartPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;

public class Test_Case014 extends BaseClass {
	
	@Test()
	public void check_total() throws IOException, InterruptedException {
		
		driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		LoginPage lp = mp.login_method();
		LandingPage lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		ShoppingCartPage scp = lap.viewCart();
		scp.delete_star_products();
		boolean result = scp.price_verification();
		lap.logOut();
		Assert.assertTrue(result);
		
		
	}

}
