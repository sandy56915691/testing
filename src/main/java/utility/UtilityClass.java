package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseClass.PageDriver;

public class UtilityClass {

	public  WebDriver driver = PageDriver.getDriverInstance().getDriver();
	public  JavascriptExecutor js  = (JavascriptExecutor) driver;

	public static void javascriptexec(int num) {

		UtilityClass uc = new UtilityClass();
		String script = "window.scrollBy(0,"+num+")";
		uc.js.executeScript(script);
	}

	public static void javascriptexec(WebElement element) {
		
		UtilityClass uc = new UtilityClass();
		String script = "arguments[0].scrollIntoView(true)";
		uc.js.executeScript(script,element);
	}

}
