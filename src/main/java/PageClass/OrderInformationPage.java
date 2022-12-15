package PageClass;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderInformationPage {
	
	@FindBy(xpath = "//td[b[text()='Order ID:']]")
	WebElement orderId_and_dateAdded;
	
	@FindBy(xpath = "//div[@class='table-responsive']//table//tbody//tr//td[1]")
	List<WebElement> products_name_list;
	
	@FindBy(xpath = "//h3[text()='Order History']//following::table//tbody//td[2]")
	WebElement order_status;
	
	@FindBy(xpath = "//h3[text()='Order History']//following::table//tbody//td[1]")
	WebElement date_added;
	
	@FindBy(xpath = "//tfoot//tr[3]//td[3]")
	WebElement total_amount;

	public OrderInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getOrderID() {
		String text = orderId_and_dateAdded.getText();
		String[] arr = text.split(":");
		String orderId = arr[1].replaceAll("[^0-9]", "");
		return orderId;
	}
	
	public int getProductsCount() {
		return products_name_list.size();
	}
	
	public String getOrderStatus() {
		return order_status.getText();
	}
	
	public String getTotal() {
		return total_amount.getText();
	}
	
	public String getDateAdded() {
		return date_added.getText();
	}
	
	public List<String> getProductNames(){
		List<String> names = new LinkedList<String>();
		for(int i=0;i<products_name_list.size();i++)
			names.add(i, products_name_list.get(i).getText());
		System.out.println(names);
		return names;
	}
}
