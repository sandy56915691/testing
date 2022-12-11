package PageClass;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.UtilityClass;

public class ShoppingCartPage {

	@FindBy(xpath = "//span[@class='text-danger']/..//following-sibling::td[2]//button[@data-original-title='Remove']")
	List<WebElement> star_products_cross_button;
	
	@FindBy(xpath = "//span[@class='text-danger']/..//following-sibling::td[2]//button[@data-original-title='Remove']")
	WebElement star_products_cross_button_single;

	@FindBy(xpath = "//input[@type='text' and contains(@name,'quantity')]")
	List<WebElement> quantity;

	@FindBy(xpath = "(//table[@class='table table-bordered'])[2]//tbody//tr//td[5]")
	List<WebElement> unit_price;

	@FindBy(xpath = "(//table[@class='table table-bordered'])[2]//tbody//tr//td[6]")
	List<WebElement> total_price;
	
	@FindBy(xpath = "//a[normalize-space(text())='Use Coupon Code']")
	WebElement use_coupon_code_link;
	
	@FindBy(xpath = "//label[normalize-space(text())='Enter your coupon here']/../div/input")
	WebElement enter_coupon_code;
	
	@FindBy(xpath = "//label[normalize-space(text())='Enter your coupon here']/..//span/input")
	WebElement apply_coupon_button;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement warning_invalid_coupon_code;
	
	@FindBy(xpath = "//a[normalize-space(text())='Estimate Shipping & Taxes']")
	WebElement estimate_shipping_and_taxes_link;
	
	@FindBy(xpath = "//label[normalize-space(text())='Country']//following-sibling::div/select")
	WebElement country_select;
	
	@FindBy(xpath = "//label[normalize-space(text())='Country']//following-sibling::div/select/option")
	List<WebElement> country_select_options;
	
	@FindBy(xpath = "//label[normalize-space(text())='Region / State']//following-sibling::div/select")
	WebElement region_select;
	
	@FindBy(xpath = "//label[normalize-space(text())='Region / State']//following-sibling::div/select/option")
	List<WebElement> region_select_options;
	
	@FindBy(xpath = "//label[normalize-space(text())='Post Code']//following-sibling::div/input")
	WebElement post_code_input;
	
	@FindBy(xpath = "//button[normalize-space(text())='Get Quotes']")
	WebElement get_quotes_button;
	
	@FindBy(xpath = "//label[contains(.,'Flat Shipping Rate')]")
	WebElement shipping_rate_label;
	
	@FindBy(xpath = "//label[contains(.,'Flat Shipping Rate')]/input")
	WebElement shipping_rate_input_button;
	
	@FindBy(xpath = "//input[@value='Apply Shipping']")
	WebElement apply_shipping_button;
	
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement success_apply_shipping;
	
	@FindBy(xpath = "//input[@value='Apply Shipping']//preceding-sibling::button")
	WebElement cancel_button;
	
	@FindBy(xpath = "//strong[text()='Flat Shipping Rate:']/../..//preceding-sibling::tr//td[2]")
	WebElement sub_total_amount;
	
	@FindBy(xpath = "//strong[text()='Flat Shipping Rate:']/..//following-sibling::td")
	WebElement flat_shipping_rate_amount;
	
	@FindBy(xpath = "//strong[text()='Flat Shipping Rate:']/../..//preceding-sibling::tr//following-sibling::tr[2]/td[2]")
	WebElement total_amount;
	
	@FindBy(xpath = "//a[text()='Checkout']")
	WebElement checkout_button;


	public ShoppingCartPage(WebDriver driver){

		PageFactory.initElements(driver, this);
	}

	public void delete_star_products() throws InterruptedException {
		if(star_products_cross_button.size()!=0) {
			for(@SuppressWarnings("unused") WebElement ele : star_products_cross_button) {
				
				star_products_cross_button_single.click();
				Thread.sleep(1000);
			}
			System.out.println("Products deleted successfully");
		}
		else
			System.out.println("No star Products");
	}

	public boolean price_verification() {
		boolean flag = true;
		if(quantity.size()!=0) {
			for(int i=0; i<quantity.size()-1;i++) {
				int quantity_of_item = Integer.parseInt(quantity.get(i).getAttribute("value"));
				float unit_price_of_item = Float.parseFloat(unit_price.get(i).getText().replaceAll("[^0-9.]", ""));
				float total_calculated_price = quantity_of_item*unit_price_of_item;
				float total_price_per_product = Float.parseFloat(total_price.get(i).getText().replaceAll("[^0-9.]", ""));
				if(total_price_per_product!=total_calculated_price)
					flag=false;
			}
		}
		else {
			System.out.println("Cart Is Empty");
			flag=false;
		}
		return flag;
	}
	
	public void estimateShippingAndTaxes(String country, String region, String postCode) throws InterruptedException {
		
		UtilityClass.javascriptexec(500);
		estimate_shipping_and_taxes_link.click();
		HashMap<String, WebElement> country_map = new HashMap<String, WebElement>();
		HashMap<String, WebElement> region_map = new HashMap<String, WebElement>();
		for(WebElement element : country_select_options) {
			country_map.put(element.getText(), element);
		}
		country_map.get(country).click();
		region_select.click();
		Thread.sleep(1000);
		for(WebElement element : region_select_options) {
			region_map.put(element.getText(), element);
		}
		region_map.get(region).click();
		post_code_input.sendKeys(postCode);
		get_quotes_button.click();
		shipping_rate_input_button.click();
		apply_shipping_button.click();
		if(success_apply_shipping.getText().contains("Success"))
			System.out.println(success_apply_shipping.getText());
	}
	
	public boolean useCouponCode(String str) throws InterruptedException {
		boolean flag=true;
		use_coupon_code_link.click();
		enter_coupon_code.sendKeys(str);
		apply_coupon_button.click();
		Thread.sleep(1000);
		if(warning_invalid_coupon_code.getText().contains("Warning")) {
			System.out.println(warning_invalid_coupon_code.getText());
			flag=false;
		}
		return flag;
	}
}
