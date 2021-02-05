package basicweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverWin {

	public static void main(String[] args) {

		// Explicit path of thye driver
//		System.setProperty("webdriver.gecko.driver",
//				"C:\\Users\\fasid\\eclipse-workspace\\libs\\selenium\\selenium_web_drivers\\drivers\\geckodriver.exe");

		WebDriver driver = new FirefoxDriver();

		// Opening flipkart using Selenium

		String baseURL = "https://www.flipkart.com/";
		driver.get(baseURL);

	}

}
