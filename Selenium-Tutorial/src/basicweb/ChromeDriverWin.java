package basicweb;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverWin {

	public static void main(String[] args) {

//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\fasid\\eclipse-workspace\\libs\\selenium\\selenium_web_drivers\\drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		String baseURL = "https://www.amazon.in/";
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		/*
		 * Difference between Thread.Sleep and ImplicitlWait -->Both are used to tell
		 * the webdriver to wait for certain amount of time before it ends The default
		 * wait time in Selenium is 500 milli seconds or 0.5 second
		 * 
		 * but incase of Thread.Sleep(20) the driver has to wait for 20 seconds even if
		 * the element was fount at 6 seconds where as in ImplicitlyWait(20),The driver
		 * will move on to next step when element was found after 6 seconds and will not
		 * wait for complete 20 seconds else it throws "NoElementFoundException"
		 */
		
		/*
		 * Explicit wait is to wait to tell the web driver to wait for certain conditions [Expected conditions]
		 * or the maximum time exceeded before throwing ElementNotVisibleException 
		 * Applicable only for one line, unlike implicitlyWait 
		 * 
		 * WebDriver wait=new WebDriverWait(driver,10);
		 * wait.untill(ExpectedConditions.elementIsClickable(WebElement));
		 * wait.untill(ExpectedConditions.elementToBeSelected(WebElement));
		 */
		
		WebElement element = driver.findElement(By.id("twotabsearchtextbox"));
		element.click();

		element.sendKeys("Samsung M51");

		WebElement phone = driver.findElement(By.className("nav-right"));
		phone.click();

//		WebElement getPhone = driver.findElement(By.className("a-section aok-relative"));
//		getPhone.click();

		driver.close();

	}

}
