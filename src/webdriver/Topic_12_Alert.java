package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	Alert alert;
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
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	

//	@Test
	public void TC_01_Accept() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		
		//Wait và switch qua luôn 
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify Alert Title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		//Accept
		alert.accept();
		
		//
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

//	@Test
	public void TC_02_Confirm() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		
		//Wait và switch qua luôn 
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify Alert Title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//Accept
		alert.dismiss();
		
		//
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	//@Test
	public void TC_03_Prompt() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		
		//Wait và switch qua luôn 
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify Alert Title đúng như mong đợi
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//Tạo 1 biến lưu trữ text để sendkey và verify
		String courseName = "Fullstack Selenium Java";
		
		//Nhập text
		alert.sendKeys(courseName);
		sleepInSecond(2);
		
		//Accept
		alert.accept();
		sleepInSecond(2);
		
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courseName);
	}
	
	@Test
	public void TC_04_Authentication() {
		
		driver.get(passUserAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	//Viết hàm sử lý cắt + nối chuỗi
	public String passUserAndPassToUrl(String url, String username, String password) {
		
		String[] arrayUrl = url.split("//");
		
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];	
		
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
