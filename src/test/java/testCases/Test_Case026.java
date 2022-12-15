package testCases;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageClass.CheckOutPage;
import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import PageClass.OrderHistoryPage;
import PageClass.OrderInformationPage;
import PageClass.ProductPage;
import PageClass.ShoppingCartPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;
import dataProvider.DataProviderClass;

//TO Verify Order information

public class Test_Case026 extends BaseClass {

	MainPage mp;
	LoginPage lp;
	LandingPage lap;
	ProductPage pp;
	ShoppingCartPage scp;
	CheckOutPage cop;
	OrderHistoryPage ohp;
	OrderInformationPage oip;
	int number_of_orders_before;
	int number_of_orders_after;

	@BeforeClass
	public void preSteps() {
		driver = PageDriver.getDriverInstance().getDriver();
		mp = new MainPage(driver);
		lp = mp.login_method();
		lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
	}


	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "search_data")
	public void add_to_cart(String str) throws IOException, InterruptedException {

		pp = lap.search_product(str);
		pp.add_to_cart(str);

	}

	@Test
	public void order_confirmation() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		scp = lap.shopping_cart_navigation();
		scp.delete_star_products();
		cop = scp.checkout_navigation();
		Thread.sleep(1000);
		List<String> names_confirmation_page = cop.getProductNames();
		Thread.sleep(1000);
		cop.order_confirmation_standlone();
		Thread.sleep(1000);
		ohp=lap.viewOrderHistory();
		Thread.sleep(1000);
		oip=ohp.navigate_to_orderInfoPage();
		Thread.sleep(1000);
		List<String> names_information_page = oip.getProductNames();
		softAssert.assertEquals(names_confirmation_page, names_information_page);
		softAssert.assertAll();
	}

}
