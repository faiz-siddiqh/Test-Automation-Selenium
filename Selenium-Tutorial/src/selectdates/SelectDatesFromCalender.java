package selectdates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import core.genericmethods.GenericMethods;

/**
 * 
 * @author Faiz-Siddiqh
 *
 */
public class SelectDatesFromCalender {

	private WebDriver driver;
	private String baseURL;
	private GenericMethods gm;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		baseURL = "https://www.goibibo.com/";
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		gm = new GenericMethods(driver);

	}

	@Test
	public void test() throws Exception {
		driver.get(baseURL);

		// click on Departure

		gm.clickAndWait("departureCalendar", "id");
		selectDateInCalender("05/03/2021");
		gm.clickAndWait("returnCalendar", "id");
		selectDateInCalender("08/07/2021");

	}

	public void selectDateInCalender(String date) throws Exception {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date expectedDate = dateFormat.parse(date);

			String day = new SimpleDateFormat("dd").format(expectedDate);
			String month = new SimpleDateFormat("MMMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);
			String idMonth = new SimpleDateFormat("MM").format(expectedDate);

			String expectedMonthYear = month + " " + year;
			String locatorForDate = year + idMonth + day;
			while (true) {

				String displayDate = gm.getElement("//div[@class='DayPicker-Caption']", "xpath").getText();

				if (expectedMonthYear.equals(displayDate)) {
					gm.clickAndWait("fare_" + locatorForDate, "id");
					break;
				} else if (expectedDate.compareTo(currentDate) > 0) {
					gm.clickAndWait("//span[contains(@class,'DayPicker-NavButton--next')]", "xpath");
				} else {
					gm.clickAndWait("//span[contains(@class,'DayPicker-NavButton--prev')]", "xpath");
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() {
		// driver.close();
	}
}
