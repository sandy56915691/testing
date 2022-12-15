package testCases;

import java.io.IOException;

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

public class Test_Case025 extends BaseClass {

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
		ohp = lap.viewOrderHistory();
		scp = lap.shopping_cart_navigation();
		scp.delete_star_products();
		cop = scp.checkout_navigation();
		Thread.sleep(1000);
		cop.order_confirmation();
		Thread.sleep(1000);
		ohp=lap.viewOrderHistory();
		String orderId_History = ohp.getOrderID();
		String order_status_History = ohp.getOrderStatus();
		int number_of_products_History = ohp.getNumberOfProducts();
		String total_History = ohp.getTotal();
		String date_added_history = ohp.getDateAdded();
		oip=ohp.navigate_to_orderInfoPage();
		Thread.sleep(1000);
		String orderId_Information = oip.getOrderID();
		int number_of_products_Information = oip.getProductsCount();
		String order_status_Information = oip.getOrderStatus();
		String total_Information = oip.getTotal();
		String date_added_Information = oip.getDateAdded();
		softAssert.assertEquals(orderId_History, orderId_Information);
		softAssert.assertEquals(number_of_products_History, number_of_products_Information);
		softAssert.assertEquals(order_status_History,order_status_Information);
		softAssert.assertEquals(total_History, total_Information);
		softAssert.assertEquals(date_added_history, date_added_Information);
		softAssert.assertAll();
	}

}
