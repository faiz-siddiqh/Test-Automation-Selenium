package core.genericmethods;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.xml.stream.events.Characters;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScreenshots {

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
	}

	@Test
	public void testScreenshots() throws Exception {
		driver.get(baseURL);
		String destination = "Ben";
		String locator = "//input[@id='gosuggest_inputSrc']";
		String type = "xpath";

		gm.clickAndTypeAndWait(locator, type, destination);
	}

	@After
	public void tearDown() throws Exception {

		String fileName = getRandomString(10) + ".png";
		String directory = System.getProperty("user.dir") + "//screenshots//";

		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		driver.quit();
	}

	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		for (int i = 0; i < str.length(); i++) {
			int index = (int) (Math.random() * str.length());
			sb.append(str.charAt(index));
		}

		return sb.toString();
	}

}
