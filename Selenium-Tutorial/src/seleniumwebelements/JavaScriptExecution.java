package seleniumwebelements;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import core.genericmethods.GenericMethods;

public class JavaScriptExecution {

	private WebDriver driver;
	private String baseURL;
	private GenericMethods gm;
	private JavascriptExecutor js;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		gm = new GenericMethods(driver);

		js = (JavascriptExecutor) driver;

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

	}

	@Test
	public void test() {

		// Navigating to website
		js.executeScript("window.location = 'https://www.goibibo.com/';");
		WebElement element = (WebElement) js.executeScript("return document.getElementById('gosuggest_inputSrc');");
		element.sendKeys("Bengaluru");

		// size of window

		long width = (long) js.executeScript("return window.innerWidth;");
		long height = (long) js.executeScript("return window.innerHeight;");

		System.out.println("The width and height are " + width + "*" + height);

	}

	@Test

	public void scroll() throws InterruptedException {

		js.executeScript("window.location = 'https://www.udemy.com/';");

		// Scroll down
		js.executeScript("window.scrollBy(0,1900);");
		Thread.sleep(4000);

		// Scroll up
		js.executeScript("window.scrollBy(0,-1900);");
		Thread.sleep(4000);

		// Scroll into view
		WebElement element = driver.findElement(By.xpath(
				"//div[contains(@class,'top-categories--category-card') ]//a[ (text()='Design')and (@href='/courses/design/')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(3000);

		// clicking on that element

		/*
		 * Sometimes some elements are visible but not clickable using the
		 * selenium-click() method and throws ElementNotInteractableException . Then we
		 * should use the below method using js command
		 * 
		 */
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);

	}

	 @Test
	/*
	 * Automating alert and pop up
	 */
	public void test1() throws InterruptedException {

		driver.get("");

		driver.findElement(By.id(""));

		// clicking on the confirmation button
		driver.findElement(By.id(" ")).click();
		Thread.sleep(4000);

		// switching command to alert window

		Alert alert = driver.switchTo().alert();
		alert.accept(); // to accept
		alert.dismiss(); // to dismiss

	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(5000);
	}

}
