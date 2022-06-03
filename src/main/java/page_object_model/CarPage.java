package page_object_model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import base_resources.Base;

public class CarPage {
	WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	@FindBy(css = "a[href*='offers']")
	WebElement linkListOffers;

	public CarPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addElementCarList(Map<String, Integer> data) {

		List<WebElement> list = driver.findElements(By.cssSelector("h4.product-name"));
		log.info("Products' list:");
		list.stream().forEach(s -> log.info(s.getText()));

		SoftAssert a = new SoftAssert();
		for (Map.Entry me : data.entrySet()) {

			try {
				log.info("Looking for element: " + me.getKey() + "-" + me.getValue());
				OptionalInt index = IntStream.range(0, list.size())
						.filter(e -> list.get(e).getText().contains((String) me.getKey())).findFirst();
				log.info("index of element: " + index.getAsInt());

				IntStream.range(1, (Integer) me.getValue() - 1)
						.forEach(i -> driver.findElements(By.cssSelector("a.increment")).get(index.getAsInt()).click());

				driver.findElements(By.cssSelector("div.product-action button[type='button']")).get(index.getAsInt())
						.click();
			} catch (Exception e) {

				a.assertTrue(false, "Element: " + me.getKey() + " doesn't found in the list");
				log.error("Element: " + me.getKey() + " doesn't found in the list");
			}

		}
		a.assertAll();
	}

	public Price_Discount getLinkListOffers() {
		linkListOffers.click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		return new Price_Discount(driver);
	}
}
