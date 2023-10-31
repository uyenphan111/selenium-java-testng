package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Exercise {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, dateOfBirth, monthOfBirth, yearOfBirth, email, companyName, passWord;
	String country, city, addressName, zipCode, phoneNumber;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		firstName = "Phan";
		lastName = "Uyen";
		dateOfBirth = "18";
		monthOfBirth = "October";
		yearOfBirth = "2000";
		email = "phanuyen" + getRandomNumber() + "@gmail.com";
		companyName = "Weedigital";
		passWord = "Uyensociu$$$12345";

		country = "United States";
		city = "Alaska";
		addressName = "aba 50h";
		zipCode = "";
		phoneNumber = "0378181883";
	}

	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropdown("div#root", ".menu span.text", "Elliot Fu");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#root")).getText(), "Elliot Fu");
		
		
		selectItemInDropdown("div#root", ".menu span.text", "Justen Kitsune");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#root")).getText(), "Justen Kitsune");

	}
	
	@Test
	public void TC_03_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndSelectInDropDown("input.search", "div[role='option'] span.text", "Australia");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div[role='alert']")).getText(), "Australia");
		
		enterAndSelectInDropDown("input.search", "div[role='option'] span.text", "Bahamas");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div[role='alert']")).getText(), "Bahamas");
	}
	
	//Viết hàm
	public void selectItemInDropdown(String parentCss, String allItemsCss, String expectedTextItem) {
		//1
		driver.findElement(By.cssSelector(parentCss)).click();
		
		//2 Wait
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemsCss)));
		
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemsCss));
		
		//3
		for (WebElement tempItem : speedDropdownItems) {
			String textItem = tempItem.getText();
			System.out.println(textItem);
			
			if (textItem.equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
			
		}
	}
	
	//Viết hàm enter và select
	public void enterAndSelectInDropDown(String textBoxCss, String allItemCss, String expectedTextItem) {
		//1
		driver.findElement(By.cssSelector(textBoxCss)).sendKeys(expectedTextItem);
		
		//2
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		
		//
		for (WebElement tempItem : speedDropdownItems) {
			String textItem = tempItem.getText();
			System.out.println();
			
			if (textItem.equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public void sleepInSecond(long timeInScond) {
		try {
			Thread.sleep(timeInScond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Chạy chậm ổn định sẽ tốt hơn là chạy nhanh và dễ fail
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
