package PageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.PageDriver;


public class LoginPage {


	@FindBy(xpath="//*[text()='E-Mail Address']//following-sibling::input")
	WebElement email_field;

	@FindBy(xpath = "//*[text()='Password']//following-sibling::input")
	WebElement pwd_field;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement login_button;

	@FindBy(xpath = "//*[text()=' Warning: No match for E-Mail Address and/or Password.']")
	WebElement warning;

	public LoginPage(WebDriver driver){

		PageFactory.initElements(driver, this);
	}


	public LandingPage login_method(String username, String password, String type) {

		email_field.sendKeys(username);
		pwd_field.sendKeys(password);
		login_button.click();
		if (type == "negative" && warning.isDisplayed())
			return null;
		else
			return new LandingPage(PageDriver.getDriverInstance().getDriver());

	}

}
