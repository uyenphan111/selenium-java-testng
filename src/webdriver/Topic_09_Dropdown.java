package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	
	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemIndropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button")).getText(), "Slow");
		
		//Verify: Dựa vào HTML của dropdown, sau khi chọn xong thì cái text item sẽ nằm ngay thẻ 
		selectItemIndropdown("span#files-button", "ul#files-menu div[role='option']", "ui.jQuery.js");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button")).getText(), "ui.jQuery.js");
		
		selectItemIndropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Dr.");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button")).getText(), "Dr.");
		
	}
	

	@Test
	public void TC_02_VeuJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemIndropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
	
		selectItemIndropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
	
	}

	@Test
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemIndropdown("div#root", "span.text", "Christian");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#root")).getText(), "Christian");
	
		selectItemIndropdown("div#root", "span.text", "Stevie Feliciano");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#root")).getText(), "Stevie Feliciano");
	}
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemIndropdown("input.search", "span.text", "Algeria");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
	
		selectItemIndropdown("input.search", "span.text", "Bahrain");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bahrain");
	}
	
	//Viết Hàm
	public void selectItemIndropdown(String actionParent, String allItems, String expectedTextItem) {
		//Miễn làm xao mà click vào nó xổ ra là được -> Xem element này là parent 
		driver.findElement(By.cssSelector(actionParent)).click();

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItems))); 
		
		List<WebElement> speedDropdowmItem = driver.findElements(By.cssSelector(allItems));
		
		for (WebElement tempItem : speedDropdowmItem) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			if (itemText.equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}
	}
	
	public void enterAndSelectItemIndropdown(String textboxCss, String allItems, String expectedTextItem) {
		//Miễn làm xao mà click vào nó xổ ra là được -> Xem element này là parent 
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItems))); 
		
		List<WebElement> speedDropdowmItem = driver.findElements(By.cssSelector(allItems));
		
		for (WebElement tempItem : speedDropdowmItem) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			if (itemText.equals(expectedTextItem)) {
				sleepInSecond(1);
				tempItem.click();
				break;
			}
		}
	}
	public void sleepInSecond(long timeInScond) {
		try {
			Thread.sleep(timeInScond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Chạy chậm ổn định sẽ tốt hơn là chạy nhanh và dễ fail
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
