package test_cases;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import base_resources.Base;
import base_resources.Provider;
import page_object_model.CarPage;
import page_object_model.Price_Discount;

public class CarListPageTestCases extends Base {
	WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	
	@BeforeTest
	public void setUp() throws IOException {
		log.info("Configurating and instancing driver");
		driver = setUpDriver();
		
		log.info("Navigating toward url: "+ prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
	}

	@Test(dataProvider = "dataSet", dataProviderClass = Provider.class)
	public void add_products_test(Map<String, Integer> data){
		log.info("Selecting products' list");
		CarPage cp = new CarPage(driver);
		cp.addElementCarList(data);
	
	}
	
	@Test
	public void find_price_disscount() {
		CarPage cp = new CarPage(driver);
		Price_Discount pd = cp.getLinkListOffers();
		pd.getPriceDiscount("Chocolatee");
	}
	
	@AfterTest
	public void tear_down()
	{
		log.info("Closing browser");
		driver.quit();
	}

	
}
