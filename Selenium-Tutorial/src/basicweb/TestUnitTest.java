package basicweb;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestUnitTest {

	static WebDriver driver;
	String baseURL;

	/*
	 * Practice testcase
	 */
	
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		baseURL = "https://www.amazon.in/";
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test
	public void test() {
		driver.get(baseURL);
		String searchBar = "//div[@id='nav-search']//input[@type='text']";
		String toSearch = "Samsung galaxy M51";
		String submit = "//div[@class='nav-right']//input[@type='submit']";
		String purchaseLink = "//div[@class='a-section a-spacing-none']//img[contains(@alt,'M51')and contains(@data-image-index,'1')]";

		WebElement element = driver.findElement(By.xpath(searchBar));
		element.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		element.sendKeys(toSearch);
		driver.findElement(By.xpath(submit)).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath(purchaseLink)).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().refresh();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(2000);
		driver.close();
	}

}
