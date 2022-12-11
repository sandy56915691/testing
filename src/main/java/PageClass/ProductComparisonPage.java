package PageClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.PageDriver;
import utility.UtilityClass;

public class ProductComparisonPage {
	
	@FindBy(xpath = "//td[text()='Product']//following-sibling::td")
	List<WebElement> product_name_list;

	@FindBy(xpath = "//td[text()='Price']//following-sibling::td")
	List<WebElement> product_price_list;
	
	@FindBy(xpath = "//td[text()='Model']//following-sibling::td")
	List<WebElement> product_model_list;
	
	@FindBy(xpath = "//td[text()='Brand']//following-sibling::td")
	List<WebElement> product_Brand_list;
	
	@FindBy(xpath = "//td[text()='Summary']//following-sibling::td")
	List<WebElement> product_Summary_list;
	
	@FindBy(xpath = "//td[text()='Weight']//following-sibling::td")
	List<WebElement> product_Weight_list;
	
	@FindBy(xpath = "//td[text()='Dimensions (L x W x H)']//following-sibling::td")
	List<WebElement> product_Dimensions_list;

	@FindBy(xpath = "//*[text()='Availability']/..//td")
	List<WebElement> availablity_list;

	By add_to_cart = By.xpath("(//*[@value='Add to Cart'])[3]");

	public ProductComparisonPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	public int max_price() {

		List<Float> price = new ArrayList<Float>();
		for(WebElement price_element : product_price_list) {
			System.out.println(price_element.getText());
			Float price_num = Float.parseFloat(price_element.getText().replaceAll("[^0-9.]", ""));
			price.add(price_num);
		}

		int index = price.indexOf(Collections.max(price));
		return index;
	}

	public void add_to_cart() {
		int index = max_price();
		int final_index = index+1;
		PageDriver.getDriverInstance().getDriver().findElement(By.xpath("(//*[@value='Add to Cart'])["+final_index+"]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remove_out_of_stock() throws InterruptedException {
		int N = availablity_list.size();
		int flag = N;
		while(flag!=0) {		
			int index = -1;
			for(int i=0; i<availablity_list.size();i++) {
				if(availablity_list.get(i).getText().equals("Out Of Stock")) {
					index=i;
					break;
				}
			}
			if(index!=-1) {	
				WebElement element = PageDriver.getDriverInstance().getDriver().
						findElement(By.xpath("(//a[text()='Remove'])["+index+"]"));
				UtilityClass.javascriptexec(element);
				Thread.sleep(2000);
				String text = PageDriver.getDriverInstance().getDriver().
						findElement(By.xpath("(//td[text()='Product']//following-sibling::td//strong)["+(index)+"]")).getText();
				element.click();
				System.out.println(text+" is removed");
				Thread.sleep(1000);
			}
			flag--;
		}
	}
	
	public void product_details() {
		int N = product_name_list.size();
		UtilityClass.javascriptexec(300);
		for(int i=0;i<N;i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Product", product_name_list.get(i).getText());
			map.put("Price", product_price_list.get(i).getText());
			map.put("Model", product_model_list.get(i).getText());
			map.put("Brand", product_Brand_list.get(i).getText());
			map.put("Summary",product_Summary_list.get(i).getText());
			map.put("Weight", product_Weight_list.get(i).getText());
			map.put("Dimension", product_Dimensions_list.get(i).getText());
			for(Map.Entry<String, String> entry : map.entrySet())
				System.out.println(entry.getKey()+" = "+entry.getValue());
			System.out.println("-------------------------------------------------------------------------");
		}
	}

}
