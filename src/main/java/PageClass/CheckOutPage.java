package PageClass;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
	
	@FindBy(xpath = "//input[@id='button-payment-address']")
	WebElement billing_details_continue_btn;
	
	@FindBy(xpath = "//input[@id='button-shipping-address']")
	WebElement delivery_details_continue_btn;
	
	@FindBy(xpath = "//input[@id='button-shipping-method']")
	WebElement delivery_method_continue_btn;
	
	@FindBy(xpath = "//input[@id='button-payment-method']")
	WebElement payment_method_continue_btn;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement terms_and_condition_checkbox;
	
	@FindBy(xpath = "//input[@id='button-confirm']")
	WebElement confirm_order_btn;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//a")
	List<WebElement> product_name_list;
	
	public CheckOutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void order_confirmation() throws InterruptedException {
		billing_details_continue_btn.click();
		Thread.sleep(1000);
		delivery_details_continue_btn.click();
		Thread.sleep(1000);
		delivery_method_continue_btn.click();
		Thread.sleep(1000);
		terms_and_condition_checkbox.click();
		payment_method_continue_btn.click();
		Thread.sleep(1000);
		confirm_order_btn.click();
		Thread.sleep(2000);
	}
	
	public List<String> getProductNames() throws InterruptedException{
		billing_details_continue_btn.click();
		Thread.sleep(1000);
		delivery_details_continue_btn.click();
		Thread.sleep(1000);
		delivery_method_continue_btn.click();
		Thread.sleep(1000);
		terms_and_condition_checkbox.click();
		payment_method_continue_btn.click();
		Thread.sleep(1000);
		List<String> name = new LinkedList<String>();
		for(int i=0;i<product_name_list.size();i++) {
			name.add(i, product_name_list.get(i).getText());
		}
		System.out.println(name);
		return name;
	}
	
	public void order_confirmation_standlone() {
		confirm_order_btn.click();
	}

}
