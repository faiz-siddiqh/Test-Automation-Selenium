package core.genericmethods;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Faiz-Siddiqh
 *
 */
public class GenericMethods {

	private WebDriver driver;

	public GenericMethods(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(String locator, String type) {

		type = type.toLowerCase();

		if (type.equals("id")) {
			return this.driver.findElement(By.id(locator));
		} else if (type.equals("xpath")) {
			return this.driver.findElement(By.xpath(locator));
		} else if (type.equals("cssselector")) {
			return this.driver.findElement(By.cssSelector(locator));
		} else if (type.equals("name")) {
			return this.driver.findElement(By.name(locator));
		} else if (type.equals("classname")) {
			return this.driver.findElement(By.className(locator));
		} else {
			System.out.println("Locator not supported");
			return null;

		}
	}

	public List<WebElement> getElementsAsList(String locator, String type) {

		type = type.toLowerCase();

		if (type.equals("id")) {
			return driver.findElements(By.id(locator));
		} else if (type.equals("classname")) {
			return driver.findElements(By.className(locator));
		} else if (type.equals("xpath")) {
			return this.driver.findElements(By.xpath(locator));
		} else if (type.equals("cssselector")) {
			return this.driver.findElements(By.cssSelector(locator));
		} else if (type.equals("name")) {
			return this.driver.findElements(By.name(locator));
		} else {
			System.out.println("Locator not supported ,Check the type");
			return null;
		}

	}

	public void clickAndWait(String locator, String type) throws InterruptedException {

		WebElement element = getElement(locator, type);
		element.click();
		Thread.sleep(2000);

	}

	public void clickAndWait(WebElement element) {
		element.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public void clickAndTypeAndWait(String locator, String type, String text) throws Exception {

		WebElement element = getElement(locator, type);
		element.click();
		Thread.sleep(2000);
		element.sendKeys(text);
		Thread.sleep(2000);

	}

	public boolean isElementPresent(String locator, String type) {

		List<WebElement> list = getElementsAsList(locator, type);

		if (list.size() == 0) {
			return false;
		}

		return true;
	}

	public boolean stateOfWebelement(String locator, String type) {

		WebElement element = getElement(locator, type);

		return element.isEnabled();

	}

}
