package baseClass;

import org.openqa.selenium.WebDriver;

public class PageDriver {

	private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	private static PageDriver driver = null;
	
	private PageDriver() {};

	public static PageDriver getDriverInstance() {

		if(driver==null) {
			driver = new PageDriver();
			return driver;
		}
		else
			return driver;
	}

	public  WebDriver getDriver() {
		return webDriver.get();
	}

	public void setDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	//For assigning null value to webDriver so that it can be eligible to Garbage collection
	public static void remove() {
		webDriver.remove();
	}
}
