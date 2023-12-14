package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		//apply 15s cho các điều kiện/trạng thái cụ thể
		
		explicitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_01_Visible() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//explicitWait = new WebDriverWait(driver, 5);
		
		//Click vào btn start
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//wait cho đến khi element hiển thị 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Invisible() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//explicitWait = new WebDriverWait(driver, 50);
		
		//Click vào btn start
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//Wait cho icon loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_03_Not_Enough_Time() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//explicitWait = new WebDriverWait(driver, 3);
		
		//Click vào btn start
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//wait cho đến khi element hiển thị 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
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
		driver.quit();
	}
}
