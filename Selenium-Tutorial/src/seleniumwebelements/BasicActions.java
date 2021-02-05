package seleniumwebelements;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.genericmethods.GenericMethods;

/**
 * 
 * @author Faiz-Siddiqh
 *
 */
public class BasicActions {

	private WebDriver driver;
	private String baseURL;

	private GenericMethods gm;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		baseURL = "https://www.goibibo.com/";
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		gm = new GenericMethods(driver);

		WebDriverWait wait = new WebDriverWait(driver, 3);
		// wait.until(ExpectedConditions.visibilityOf(element));

	}

	@Test
	public void clickAndType() throws Exception {
		driver.get(baseURL);
		String clickXpath = "//input[@id='gosuggest_inputSrc']";
		String inputSearch = "delhi";
		String type = "xpath";

		// element.click();
//		Thread.sleep(3000);
//		element.sendKeys(inputSearch);
//		Thread.sleep(3000);	

		gm.clickAndTypeAndWait(clickXpath, type, inputSearch);

	}

	@Test
	public void navigateBtwPages() throws Exception {
		driver.get(baseURL);
		String URL = "https://www.goibibo.com/hotels/";

		driver.navigate().to(URL);

		Thread.sleep(3000);

		driver.navigate().back();

		Thread.sleep(3000);

		driver.navigate().forward();

		Thread.sleep(3000);
		driver.navigate().back();
		driver.navigate().refresh();

	}

	@Test
	/*
	 * To check whether an element is enabled or disabled
	 */
	public void checkStateOfWebElement() {
		try {
			driver.get("https://www.google.com/");
			String locator = "//input[@name='iflsig']";
			String type = "xpath";
			System.out.println(gm.stateOfWebelement(locator, type));
		} finally {

		}
		System.out.println("hi");
	}

	@Test

	/*
	 * Working with diff Web Elements 1)AutoComplete 2)Calendar 3)Dropdowns
	 */

	public void diffWebElements() throws Exception {

		driver.get(baseURL);

		// 1.Example on AutoComplete
		String destination = "Ben";
		String fullDestn = "Bengaluru, India";
		String locator = "//input[@id='gosuggest_inputSrc']";
		String type = "xpath";

		gm.clickAndTypeAndWait(locator, type, destination);

		String autoCompleteListLocator = "react-autosuggest-1";
		type = "id";
		// System.out.println(gm.getElement(autoCompleteListLocator,
		// type).getAttribute("innerHTML"));

		WebElement element = gm.getElement(autoCompleteListLocator, type);

		List<WebElement> liElements = element.findElements(By.tagName("li"));
		Thread.sleep(3000);
		for (WebElement eachElement : liElements) {
			if (eachElement.getText().contains(fullDestn)) {
				gm.clickAndWait(eachElement);
				Thread.sleep(4000);
				break;
			}

		}

		// 2. Testing Dates from Calendar

		String dateToBeClicked = "31";
		String departureLocatorId = "departureCalendar";

		gm.clickAndWait(departureLocatorId, type);
		Thread.sleep(5);
		String dateLocator = "//div[@class='DayPicker-Day' and @aria-disabled='false']/div[@class='calDate']";
		type = "xpath";
		List<WebElement> dateList = gm.getElementsAsList(dateLocator, type);

		for (WebElement date : dateList) {
			if (date.getText().equals(dateToBeClicked)) {
				gm.clickAndWait(date);
				// driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				Thread.sleep(4000);
				break;
			}

		}

		// 3 . Testing on dropdowns

		String travellingClassLocatorId = "pax_link_common";
		String addAdultButtonId = "adultPaxPlus";
		type = "id";

		gm.clickAndWait(travellingClassLocatorId, type);
		gm.clickAndWait(addAdultButtonId, type);

		String dropDownLocator = "//select[@id='gi_class']/option";
		String valueToBeSelected = "Premium Economy";
		type = "xpath";

		List<WebElement> dropDownOptions = gm.getElementsAsList(dropDownLocator, type);

		for (WebElement option : dropDownOptions) {

			if (option.getText().equals(valueToBeSelected)) {
				gm.clickAndWait(option);
				// System.out.println("Clicked On "+option.getText());
				Thread.sleep(4000);
				break;
			}

		}

	}

	@Test

	/*
	 * 4. Working with actions class- 1)Selecting options on Mouse Hover 2)Drag and
	 * drop elements
	 */

	public void mouseHover() throws InterruptedException {

		driver.get(baseURL);

		String type = "xpath";
		String locator = "//li[@class='hdrMoreLink']";

		WebElement mainElement = gm.getElement(locator, type);

		Actions action = new Actions(driver);
		action.moveToElement(mainElement).perform();

		Thread.sleep(4000);

		String subElementXpath = "//div[contains(@class,'tip tip_top')]//a[contains(@href,'destinations')]";
		type = "xpath";

		WebElement subElement = gm.getElement(subElementXpath, type);
		action.moveToElement(subElement).click().perform();

		Thread.sleep(4000);

	}

	@Test

	public void testDragAndDrop() {
		driver.get("https://jqueryui.com/droppable/");

		driver.switchTo().frame(0);

		String fromElementId = "draggable";
		String toElementId = "droppable";
		String type = "id";
		WebElement fromElement = gm.getElement(fromElementId, type);
		WebElement toElement = gm.getElement(toElementId, type);

		Actions action = new Actions(driver);

		// 1 ) By Drag and Drop

		action.dragAndDrop(fromElement, toElement).build().perform();

		// OR 2) BY click and hold,move and drop

		// action.clickAndHold(fromElement).moveToElement(toElement).release().build().perform();

	}

	@Test

	public void testSlider() throws InterruptedException {

		driver.get("https://jqueryui.com/slider/");
		driver.switchTo().frame(0);

		Thread.sleep(4000);

		String sliderXpath = "//div[@id='slider']/span";
		String type = "xpath";
		WebElement slider = gm.getElement(sliderXpath, type);

		Actions action = new Actions(driver);

		action.dragAndDropBy(slider, 100, 50).perform();
		Thread.sleep(3000);

	}

	@Test
	/*
	 * Test on Keyboard keys -Keys class
	 */
	public void testKeyboardKeys() throws InterruptedException {

		driver.get("https://google.com/");

		String locator = "//input[@class='gLFyf gsfi']";
		String type = "xpath";
		WebElement element = gm.getElement(locator, type);

		element.click();
		element.sendKeys("Hey Google");
		Thread.sleep(3000);
		// Sending a combination of a key
		element.sendKeys(Keys.CONTROL + "a");// or
		// element.sendKeys(Keys.chord(Keys.COMMAND,"a"));

		// Sending a key
		element.sendKeys(Keys.ENTER);

		// Similarly Using Action class
		driver.navigate().back();

		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.sendKeys("Hey Google").perform();
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(4000);

		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();// copying all ,ctrl+a
		Thread.sleep(3000);
	}

	@Test
	/*
	 * To work with new window opened after clicking a link
	 */

	public void handlesTest() throws InterruptedException {

		driver.get(" URL ");
		// Get Handle
		String parentHandle = driver.getWindowHandle();

		// Find the link button
		WebElement element = driver.findElement(By.id(" "));
		element.click();

		// Get all Handles

		Set<String> handles = driver.getWindowHandles();

		// Switching between handles

		for (String handle : handles) {

			if (!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				Thread.sleep(3000);
				// Perform some operation

				driver.close();
				break;
			}
		}

		// Switch back to original handle
		driver.switchTo().window(parentHandle);

	}

	@Test
	/*
	 * Working with different iframes
	 * 
	 */

	public void switchToIframe() throws InterruptedException {

		// go to URL
		driver.get(" ");

		// switch to the iframe where element is present
		driver.switchTo().frame(" ");

		// Perform some function
		WebElement element = driver.findElement(By.id(" "));
		element.click();

		// Switch back to parent window
		driver.switchTo().defaultContent();
		Thread.sleep(4000);

	}

	@After

	public void tearDown() throws Exception {
		Thread.sleep(4000);
		driver.close();
	}

}
