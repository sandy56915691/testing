package PageClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.BaseClass;
import baseClass.FileObject;

public class EditPage extends BaseClass {

	@FindBy(name="firstname")
	private WebElement first_name;

	@FindBy(name = "lastname")
	private WebElement last_name;

	@FindBy(name = "email")
	private WebElement email;

	@FindBy(name = "telephone")
	private WebElement telephone;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continue_button;

	public EditPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	public String first_name_check() {

		return first_name.getAttribute("value");
	}

	public String last_name_check() {

		return last_name.getAttribute("value");
	}

	public String email_check() {

		return email.getAttribute("value");
	}

	public String telephone_check() {

		return telephone.getAttribute("value");
	}
	
	public void update_first_name() throws IOException {
		
		first_name.clear();
		first_name.sendKeys("Neetu");
		prop = FileObject.getPropertyInstance();
		FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/test/resources/config/config.properties"));
		prop.setProperty("first_name", "Neetu");
		prop.store(fos,null);
	}
	
	public void click_continue() {
		
		continue_button.click();
	}
}
