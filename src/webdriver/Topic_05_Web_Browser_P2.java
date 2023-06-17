package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Container;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_P2 {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_Url() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		sleepInSecond(2);
		
		assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		
		assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
	}

	//@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		//Muốn lấy title của page vào, thì F12 page đó tag console: document.title -> enter sẽ trả ra kết quả title của page
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		
		//Muốn lấy title của page vào, thì F12 page đó tag console: document.title -> enter sẽ trả ra kết quả title của page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}

	//@Test
	public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		
		//Verify url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		//Back lại trang login
		driver.navigate().back();
		
		//Verify url login
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Forward đến trang register page
		driver.navigate().forward();
		
		//Muốn lấy title của page vào, thì F12 page đó tag console: document.title -> enter sẽ trả ra kết quả title của page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_PageSource_HTML() {
		driver.get("http://live.techpanda.org/");
		
		//Click My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		//Verify page HTML có chưa đoạn text mong muốn -> thực tế sẽ không dùng, vì sẽ getText và verify
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		//click Create an Account btn
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
	}

	public void sleepInSecond(long timeInScond) {
		try {
			Thread.sleep(timeInScond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
