package PageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.PageDriver;

public class LandingPage {
	
	@FindBy(xpath="//*[text()='Edit Account']")
	WebElement edit_Account_btn;
	
	@FindBy(xpath="(//*[text()='My Account'])[2]")
	WebElement my_Account_btn;
	
	@FindBy(xpath="(//*[text()='Logout'])[1]")
	WebElement logout;
	
	
	public LandingPage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public EditPage edit() {
		
		edit_Account_btn.click();
		return new EditPage(PageDriver.getDriverInstance().getDriver());
	}
	
	public void logOut() {
		
		my_Account_btn.click();
		logout.click();
	}

}
