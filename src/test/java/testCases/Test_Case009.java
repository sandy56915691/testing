package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import PageClass.LandingPage;
import PageClass.LoginPage;
import PageClass.MainPage;
import baseClass.BaseClass;
import baseClass.FileObject;
import baseClass.PageDriver;

public class Test_Case009 extends BaseClass {
	
	@Test
	public void test_broken_links() throws IOException {
		
		driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		LoginPage lp = mp.login_method();
		LandingPage lap = lp.login_method(FileObject.getPropertyInstance().getProperty("username"),
				FileObject.getPropertyInstance().getProperty("password"), "positive");
		int num_of_broken_links = lap.brokenLinks();
		System.out.println("Broken Links are = "+num_of_broken_links);
	}

}
