package PageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.BaseClass;
import baseClass.PageDriver;

public class MainPage extends BaseClass {
	
	@FindBy(xpath="(//*[text()='My Account'])[1]")
	WebElement my_Account_btn;
	
	@FindBy(xpath = "(//*[text()='Login'])[1]")
	WebElement login;
	
	public MainPage(WebDriver driver){
		
		//AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 0);
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage login_method() {
		
		my_Account_btn.click();
		login.click();
		return new LoginPage(PageDriver.getDriverInstance().getDriver());
	}

}
