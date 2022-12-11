package PageClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.PageDriver;

public class ProductPage {

	@FindBy(xpath = "//div[@id='content']/h1")
	WebElement search_text;

	@FindBy(xpath = "//input[@id='input-search']")
	WebElement search_box;

	@FindBy(xpath = "(//div[@class='row'])[5]/div//h4/a")
	List<WebElement> search_result;

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md' and text()='Add to Cart']")
	List<WebElement> add_to_cart_list;
	
	@FindBy(xpath = "//button[@data-original-title='Add to Wish List']")
	List<WebElement> add_to_wish_list;
	
	@FindBy(xpath = "//button[@data-original-title='Compare this Product']")
	List<WebElement> add_to_compare_list;
	
	@FindBy(xpath = "//a[text()='product comparison']")
	WebElement product_comparison;

	@FindBy(xpath = "//span[@id='cart-total']")
	WebElement cart_total;

	@FindBy(xpath = "//span[@id='cart-total']/..")
	WebElement cart_button;

	@FindBy(xpath = "//table[@class='table table-striped']/tbody/tr/td[4]")
	List<WebElement> cart_items_price_list;

	@FindBy(xpath = "(//table[@class='table table-bordered']//tr/td)[2]")
	WebElement sub_total;
	
	@FindBy(xpath = "//span[text()='Checkout']")
	WebElement checkOut;

	@FindBy(xpath = "(//table[@class='table table-bordered']//tr[2]/td)[2]")
	WebElement total;


	public ProductPage(WebDriver driver){

		PageFactory.initElements(driver, this);
	}

	public boolean search_verification(String str) {

		if(search_text.getText().contains(str) && search_box.getAttribute("value").contains(str) && search_result.size()>=1) {

			System.out.println(str+" Found Successfully");
			return true;
		}
		else {
			System.out.println(str+" Not Found");
			return false;
		}
	}

	public void add_to_cart(String product) throws InterruptedException {
		if(add_to_cart_list.size()!=0) {
			for(WebElement element : add_to_cart_list) {
				element.click();
				Thread.sleep(1000);
			}

		}
		else
			System.out.println(product+" are not available");
	}
	
	public void add_to_wish_list(String product) throws InterruptedException {
		if(add_to_wish_list.size()!=0) {
			for(WebElement element : add_to_wish_list) {
				element.click();
				Thread.sleep(1000);
			}

		}
		else
			System.out.println(product+" are not available");
	}
	
	public void add_to_comparison_list(String product) throws InterruptedException {
		if(add_to_compare_list.size()!=0) {
			for(WebElement element : add_to_compare_list) {
				element.click();
				Thread.sleep(1000);
			}

		}
		else
			System.out.println(product+" are not available");
	}
	
	public ProductComparisonPage product_comparison_navigation() {
		
		product_comparison.click();
		return new ProductComparisonPage(PageDriver.getDriverInstance().getDriver());
	}

	public boolean check_total() throws InterruptedException {
		float sum = 0;
		cart_button.click();
		Thread.sleep(2000);
		for(WebElement element : cart_items_price_list) {
			String text = element.getText();
			float amount = Float.parseFloat(text.replaceAll("[^0-9.]", ""));
			sum=sum+amount;
		}
		float subTotal = Float.parseFloat(sub_total.getText().replaceAll("[^0-9.]", ""));
		float total_amount = Float.parseFloat(total.getText().replaceAll("[^0-9.]", ""));
		if(sum==subTotal && sum==total_amount)
			return true;
		else
			return false;
	}
	
	public Object checkout() {
		cart_button.click();
		if(cart_items_price_list.size()!=0) {
			checkOut.click();
			return new ShoppingCartPage(PageDriver.getDriverInstance().getDriver());
		}
		else
			return false;
	}

}
