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

public class Test_Case021 extends BaseClass {

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
	public void remove_out_of_stock() throws InterruptedException {

		pcp = pp.product_comparison_navigation();
		Thread.sleep(1000);
		pcp.remove_out_of_stock();
	}

}
