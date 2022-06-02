package page_object_model;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_resources.Base;

public class Price_Discount {

	WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	@FindBy(css = "a[aria-label='Next']")
	WebElement nextButton;

	public Price_Discount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void getPriceDiscount(String name) {

		OptionalInt index;
		boolean isPresent = false;
		
		while (nextButton.getAttribute("aria-disabled").equals("false") && isPresent == false) {
			
			List<WebElement> elements = driver.findElements(By.xpath("//td[1]"));
			index = IntStream.range(0, elements.size()).filter(e -> elements.get(e).getText().contains(name))
					.findFirst();
			if (index.isPresent()) {
				log.info("Element: " + getElementList(index.getAsInt(), "//td[1]") + " Price: "
						+ getElementList(index.getAsInt(), "//td[2]") + " Discount: "
						+ getElementList(index.getAsInt(), "//td[3]"));
				isPresent = true;
			} else {
				nextButton.click();
			}
			nextButton = driver.findElement(By.cssSelector("a[aria-label='Next']"));

		}
		if(isPresent==false) {
			log.error("Element: "+name+" didn't find");
		}

	}

	public String getElementList(int index, String xpath) {

		return driver.findElements(By.xpath(xpath)).get(index).getText();
	}

	public WebElement getNextButton() {
		return nextButton;
	}
}
