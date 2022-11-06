package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import PageClass.MainPage;
import baseClass.BaseClass;
import baseClass.PageDriver;

public class Test_Case002 extends BaseClass{
	
	@Test
	public void test2() throws InterruptedException {
		
		WebDriver driver = PageDriver.getDriverInstance().getDriver();
		MainPage mp = new MainPage(driver);
		mp.login_method();
		Thread.sleep(3000);
	}
}
