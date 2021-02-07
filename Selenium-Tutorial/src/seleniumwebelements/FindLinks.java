package seleniumwebelements;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import core.genericmethods.GenericMethods;

public class FindLinks {

	private WebDriver driver;
	private static GenericMethods gm;
	private String baseURL;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		baseURL = "https://www.goibibo.com/";
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		gm = new GenericMethods(driver);
	}

	@Test
	public void testLinks() {
		driver.get(baseURL);
		driver.navigate().to("https://www.goibibo.com/hotels/");
		List<WebElement> linksList = clickableLinks(driver);

		for (WebElement link : linksList) {
			String href = link.getAttribute("href");
			try {
				System.out.println("URL" + href + "returned " + linkStatus(new URL(href)));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public static List<WebElement> clickableLinks(WebDriver driver) {

		List<WebElement> linksToClick = new ArrayList<WebElement>();
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		elements.addAll(driver.findElements(By.tagName("img")));

		for (WebElement e : elements) {
			if (e.getAttribute("href") != null) {
				linksToClick.add(e);
			}
		}

		return linksToClick;
	}

	public static String linkStatus(URL url) {
		try {

			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.connect();
			String responseMessage = http.getResponseMessage();
			http.disconnect();
			return responseMessage;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@After
	public void tearDown() throws Exception {

	}

}
