package PageClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListPage {
	
	@FindBy(xpath = "//td[text()='Out Of Stock']//following-sibling::td[2]/a")
	List<WebElement> out_of_stock_remove_btn;
	
	@FindBy(xpath = "//td[text()='In Stock']//following-sibling::td[2]/button")
	List<WebElement> in_stock_add_to_cart_btn;
	
	public WishListPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public boolean remove_out_of_stock_items() {
		if(out_of_stock_remove_btn.size() != 0) {
			for(WebElement element : out_of_stock_remove_btn) {
				try{
					element.click();
				}
			catch(Exception e) {
				out_of_stock_remove_btn.get(0).click();
			}
				}
			return true;
		}
		else
			return false;
	}
	
	public boolean add_in_stock_items() {
		System.out.println(in_stock_add_to_cart_btn.size());
		if(in_stock_add_to_cart_btn.size() != 0) {
			for(WebElement element : in_stock_add_to_cart_btn) {
				element.click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			return true;
		}
		else
			return false;
		
	}

}
