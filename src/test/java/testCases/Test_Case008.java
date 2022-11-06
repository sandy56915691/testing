package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;

public class Test_Case008 extends BaseClass {
	
	@Test
	public void test8() throws InterruptedException {
		WebDriver driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		LoginPage lp = mp.login_method();
		LandingPage lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"), 
				FileObject.getPropertyInstance().getProperty("password"), "positive");		
			lap.logOut();
		Thread.sleep(2000);
	}
}
