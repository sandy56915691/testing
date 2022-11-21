package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public File file;
	public FileInputStream fis;
	public Properties prop;
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;

	@BeforeClass
	public void before() throws IOException {

		if(Objects.isNull(PageDriver.getDriverInstance().getDriver())) {//This if statement we have written because in our test case class someone may call driver = new ChromeDriver(); again
			file = new File(System.getProperty("user.dir")+"/src/test/resources/config/config.properties");
			fis = new FileInputStream(file);
			prop = new Properties();
			FileObject.setPropertyInstance(prop);
			FileObject.getPropertyInstance().load(fis);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			PageDriver.getDriverInstance().setDriver(driver);
			PageDriver.getDriverInstance().getDriver().manage().window().maximize();
			PageDriver.getDriverInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			PageDriver.getDriverInstance().getDriver().get(FileObject.getPropertyInstance().getProperty("url"));
		}
	}



	@AfterClass
	public void after() {

		PageDriver.getDriverInstance().getDriver().close();
		PageDriver.remove();
	}

}
