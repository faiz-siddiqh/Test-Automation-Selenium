package pageobjectmodel;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageFactory {

	private WebDriver driver;
	private String baseURL;
	PageClass pageclass;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		pageclass = new PageClass(driver);
		baseURL = "https://www.goibibo.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@Test
	public void test() throws InterruptedException {
		driver.get(baseURL);
//		pageclass.clickOnFrom();
//		pageclass.enterFrom();
		pageclass.clickOnDepartureDate();
		pageclass.selectDate("31");
		Thread.sleep(4000);
	}

	@After
	public void tearDown() throws Exception {

	}

}
