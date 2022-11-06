package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageClass.EditPage;
import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;

public class Test_Case007 extends BaseClass {
	
	@Test
	public void test7() throws InterruptedException {
		WebDriver driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		LoginPage lp = mp.login_method();
		LandingPage lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		EditPage ep = lap.edit();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(ep.first_name_check(), FileObject.getPropertyInstance().getProperty("first_name"));
		softAssert.assertEquals(ep.last_name_check(), "Garg");
		softAssert.assertEquals(ep.telephone_check(), "7988556447");
		softAssert.assertEquals(ep.email_check(), "sandy5691@gmail.com");
		softAssert.assertAll();
		Thread.sleep(2000);
	}
}
