package PageClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.PageDriver;

public class LandingPage {
	
	@FindBy(xpath="//input[@name='search' and @placeholder = 'Search']")
	WebElement search_box;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement search_btn;

	@FindBy(xpath="//*[text()='Edit Account']")
	WebElement edit_Account_btn;

	@FindBy(xpath="//span[text()='My Account']")
	WebElement my_Account_btn;

	@FindBy(xpath="(//*[text()='Logout'])[1]")
	WebElement logout;

	@FindBy(xpath = "//span[text()='Currency']")
	WebElement currency_button;

	@FindBy(xpath = "//div[@class='btn-group open']/ul/li/button")
	List<WebElement> currency_dropdown;

	@FindBy(xpath = "//span[@id='cart-total']")
	WebElement cart;

	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li/a[text()='Desktops']")
	WebElement desktop;

	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li/a[text()='Desktops']/..//a[text()='Show All Desktops']")
	WebElement show_all_desktop;

	@FindBy(xpath = "//select[@id='input-sort']")
	WebElement sort_by_dropdown;

	@FindBy(xpath = "//select[@id='input-sort']/option")
	List<WebElement> sort_by_dropdown_list;

	@FindBy(xpath = "//*[@class='price-tax']")
	List<WebElement> price_ex_tax_list;

	@FindBy(xpath = "//div[@class='caption']//a")
	List<WebElement> products_name_list;
	
	@FindBy(xpath = "//span[@id='cart-total']/..")
	WebElement cart_button;
	
	@FindBy(xpath = "//strong[text()=' View Cart']")
	WebElement view_cart_button;
	
	@FindBy(xpath = "//span[text()='Checkout']")
	WebElement checkOut;

	@FindBy(xpath = "//footer")
	WebElement footer;


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

	public void currency_check(String str) throws InterruptedException {

		HashMap<String,String> map = new HashMap<String, String>();
		map.put("USD", "$");
		map.put("GBP", "£");
		map.put("EUR", "€");
		currency_button.click();
		//List<WebElement> currency_dropdown = PageDriver.getDriverInstance().getDriver().findElements(By.xpath("//div[@class='btn-group open']/ul/li/button"));
		for(WebElement val : currency_dropdown) {
			if(val.getAttribute("name").equals(str)) {
				val.click();
				if(cart.getText().contains(map.get(str))) 
					System.out.println("Test Pass with Currency = "+map.get(str));
				break;
			}

		}
	}

	public boolean sort_by_dropdown_click(String str) {

		HashMap<String, WebElement> map = new HashMap<String, WebElement>();
		desktop.click();
		show_all_desktop.click();
		sort_by_dropdown.click();
		for(WebElement dropdown : sort_by_dropdown_list)
			map.put(dropdown.getText(), dropdown);
		if(str.equals("Price (Low > High)")) {
			map.get(str).click();
			return sort_by_price_low_to_high();
		}
		else if(str.equals("Price (High > Low)")) {
			map.get(str).click();
			return sort_by_price_high_to_low();
		}
		else if(str.equals("Name (A - Z)")) {
			map.get(str).click();
			return sort_by_name_A_to_Z();
		}
		else if(str.equals("Name (Z - A)")) {
			map.get(str).click();
			return sort_by_name_Z_to_A();
		}
		else
			return false;

	}

	public boolean sort_by_price_low_to_high() {

		ArrayList<Float> al = new ArrayList<Float>();
		for(WebElement ele : price_ex_tax_list) {
			String text = ele.getText();
			String int_text=text.replaceAll("[^0-9.]", "");
			float price = Float.parseFloat(int_text);
			al.add(price);
		}
		ArrayList<Float> temp = new ArrayList<Float>(al);
		Collections.sort(temp);
		if(al.equals(temp))
			return true;
		else
			return false;
	}

	public boolean sort_by_price_high_to_low() {

		ArrayList<Float> al = new ArrayList<Float>();
		for(WebElement ele : price_ex_tax_list) {
			String text = ele.getText();
			String int_text=text.replaceAll("[^0-9.]", "");
			float price = Float.parseFloat(int_text);
			al.add(price);
		}
		ArrayList<Float> temp = new ArrayList<Float>(al);
		Collections.sort(temp);
		Collections.reverse(temp);
		if(al.equals(temp))
			return true;
		else
			return false;
	}

	public boolean sort_by_name_A_to_Z() {

		ArrayList<String> al = new ArrayList<String>();
		for(WebElement ele : products_name_list) {
			String text = ele.getText().toLowerCase();
			al.add(text);
		}
		ArrayList<String> temp = new ArrayList<String>(al);
		Collections.sort(temp);
		if(al.equals(temp))
			return true;
		else
			return false;
	}
	
	public boolean sort_by_name_Z_to_A() {

		ArrayList<String> al = new ArrayList<String>();
		for(WebElement ele : products_name_list) {
			String text = ele.getText().toLowerCase();
			al.add(text);
		}
		ArrayList<String> temp = new ArrayList<String>(al);
		Collections.sort(temp);
		Collections.reverse(temp);
		if(al.equals(temp))
			return true;
		else
			return false;
	}
	
	public ProductPage search_product(String product) throws InterruptedException {
		
		search_box.clear();
		Thread.sleep(200);
		search_box.sendKeys(product);
		search_btn.click();
		return new ProductPage(PageDriver.getDriverInstance().getDriver());
	}
	
	public ShoppingCartPage checkOut() {
		cart_button.click();
		checkOut.click();
		return new ShoppingCartPage(PageDriver.getDriverInstance().getDriver());
	}
	
	public ShoppingCartPage viewCart() {
		cart_button.click();
		view_cart_button.click();
		return new ShoppingCartPage(PageDriver.getDriverInstance().getDriver());
	}

	public int brokenLinks() throws IOException {

		List<WebElement> list = footer.findElements(By.tagName("a"));
		int count = 0;
		for(WebElement element : list) {
			String link = element.getAttribute("href");
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			if(connection.getResponseCode()>=400)
				count++;
		}
		return count;
	}

}
