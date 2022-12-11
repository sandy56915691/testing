package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import PageClass.ProductComparisonPage;
import PageClass.ProductPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;
import dataProvider.DataProviderClass;

public class Test_Case020 extends BaseClass {
	
	MainPage mp;
	LoginPage lp;
	LandingPage lap;
	ProductPage pp;
	ProductComparisonPage pcp;
	
	@BeforeClass
	public void aa_preSteps() throws InterruptedException {
		driver = PageDriver.getDriverInstance().getDriver();
		mp = new MainPage(driver);
		lp = mp.login_method();
		lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		
	}
	
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "search_data")
	public void add_to_comparison(String product) throws IOException, InterruptedException {
		
		pp = lap.search_product(product);
		pp.search_verification(product);
		pp.add_to_comparison_list(product);
				
		
	}
	
	@Test()
	public void max_price_add_to_cart() {
		
		pcp = pp.product_comparison_navigation();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pcp.add_to_cart();
	}

}
