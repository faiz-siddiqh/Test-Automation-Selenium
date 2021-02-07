package pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageClass {
	
	WebDriver driver;

	@FindBy(xpath = "//input[@id='gosuggest_inputSrc']")
	WebElement fromDestination;

	@FindBy(id = "departureCalendar")
	WebElement departureDate;

	@FindBy(xpath = "//div[@class='DayPicker-Day' and @aria-disabled='false']/div[@class='calDate']")
	List<WebElement> dates;
	
	public PageClass(WebDriver driver) {
		driver=this.driver;
		PageFactory.initElements(driver,this);
	}

	public void clickOnFrom() {
		fromDestination.click();
	}

	public void enterFrom() {
		fromDestination.sendKeys("Bengaluru");
	}

	public void clickOnDepartureDate() {
		departureDate.click();
	}

	public void selectDate(String dateToBeClicked) {

		for (WebElement date : dates) {
			if (date.getText().equals(dateToBeClicked)) {
				date.click();
				break;
			}
		}

	}
}
