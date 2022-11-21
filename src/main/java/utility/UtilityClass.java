package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import baseClass.PageDriver;

public class UtilityClass {
	
	static WebDriver driver = PageDriver.getDriverInstance().getDriver();
	static JavascriptExecutor js;
	
	public static void javascriptexec(int num) {
		
		js = (JavascriptExecutor) driver;
		String script = "window.scrollBy(0,"+num+")";
		js.executeScript(script);
	}

}
