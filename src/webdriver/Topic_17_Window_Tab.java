package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_ID() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String basicFormID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		switchToWindownById(basicFormID); 
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.findElement(By.cssSelector("textarea[title='Tìm kiếm']")).sendKeys("automation");
	
		
		String googleId = driver.getWindowHandle();
		
		switchToWindownById(googleId);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}
	
	//@Test
	public void TC_02_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Google");
		driver.findElement(By.cssSelector("textarea[title='Tìm kiếm']")).sendKeys("automation");
	
		switchToWindowByTitle("Selenium WebDriver");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		
		switchToWindowByTitle("Facebook – log in or sign up");
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("automation123");
		
		switchToWindowByTitle("Selenium WebDriver");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.cssSelector("input[data-view-id=\"main_search_form_input\"]")).sendKeys("Macbook pro 2022");
	
		closeAllWindowWithoutParent(parentID);
	}
	
	@Test
	public void TC_03_Live_Guru() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//li[2]")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//li[2]")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(2);
		
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		closeAllWindowWithoutParent(parentID);
		
	}
	
	public void switchToWindownById(String otherId) {
		Set<String> getAllWindowIds = driver.getWindowHandles();
		for (String id : getAllWindowIds) {
			if (!id.equals(otherId)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> getAllWindowIds = driver.getWindowHandles();
		for (String id : getAllWindowIds) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();

			if (actualPageTitle.equals(expectedTitle)) {
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParent(String parentId) {
		Set<String> getAllWindowIds = driver.getWindowHandles();
		for (String id : getAllWindowIds) {
			if (!id.equals(parentId)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentId);
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
