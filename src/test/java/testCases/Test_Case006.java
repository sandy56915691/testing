package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import PageClass.EditPage;
import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;

public class Test_Case006 extends BaseClass {
	
	@Test
	public void test6() throws InterruptedException, IOException {
		WebDriver driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		LoginPage lp = mp.login_method();
		LandingPage lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		EditPage ep = lap.edit();
		ep.update_first_name();
		ep.click_continue();
		Thread.sleep(2000);
	}
}
