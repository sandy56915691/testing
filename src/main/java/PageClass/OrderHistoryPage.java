package PageClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.PageDriver;

public class OrderHistoryPage {
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']/tbody/tr")
	List<WebElement> number_of_orders_list;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//a")
	List<WebElement> view_order_info_list;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//tr//td[1]")
	List<WebElement> orderId_list;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//tr//td[3]")
	List<WebElement> number_of_products_list;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//tr//td[4]")
	List<WebElement> order_status_list;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//tr//td[5]")
	List<WebElement> total_history_list;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody//tr//td[6]")
	List<WebElement> date_added_list;
	
	@FindBy(xpath = "//a[text()='>']")
	List<WebElement> next_page_list;

	public OrderHistoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public int order_count() throws InterruptedException {
		int count = 0;
		int size = 0;
		do {
			count = count+number_of_orders_list.size();
			size = next_page_list.size();
			if(size!=0)
				next_page_list.get(0).click();
				Thread.sleep(1000);
		}while(size!=0);
		System.out.println(count);
		return count;
	}
	
	public String getOrderID() {
		String text=orderId_list.get(0).getText();
		String orderId = text.replaceAll("[^0-9]", "");
		return orderId;
	}
	
	public String getOrderStatus() {
		return order_status_list.get(0).getText();
	}
	
	public String getTotal() {
		return total_history_list.get(0).getText();
	}
	
	public String getDateAdded() {
		return date_added_list.get(0).getText();
	}
	
	public int getNumberOfProducts() {
		String text = number_of_products_list.get(0).getText();
		int product_count = Integer.parseInt(text);
		return product_count;
	}
	
	public OrderInformationPage navigate_to_orderInfoPage() {
		
		view_order_info_list.get(0).click();
		return new OrderInformationPage(PageDriver.getDriverInstance().getDriver());
		
	}
}
