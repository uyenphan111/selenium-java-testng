package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Topic_Popup_Random {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress;
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);

		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		emailAddress = "automation" + rand.nextInt(999) + "@gmail.com";
	}

	//@Test 
	public void TC_01_Random_In_Dom() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(40);
		
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		
		if (driver.findElement(lePopup).isDisplayed()) {
			driver.findElement(By.name("lepopup-20")).sendKeys(emailAddress);
			sleepInSecond(1);
			driver.findElement(By.cssSelector("a[class*='lepopup-button']")).click();
			sleepInSecond(5);
			
			//Verify
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(), "Thank you!");
			Assert.assertTrue(
					driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));
			
			//Đóng popup đi -> qua step tiếp theo
			//Sau ~5s page sẽ tự động đóng popup
			sleepInSecond(15);
		}
		
		String articleName = "Agile Testing Explained";
		
		driver.findElement(By.id("search-input")).sendKeys(articleName);
		driver.findElement(By.id("search-submit")).click();
		
		//Verify bài đầu tiên
		Assert.assertEquals(driver.findElement(By.cssSelector("li.post-item:first-child>div>h2>a")).getText(), articleName);
		
	}

	//@Test
	public void TC_02_Random_In_Dom_() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(40);
	
		By popup = By.id("tve-p-scroller");
		
		if (driver.findElement(popup).isDisplayed()) {
			//Close popup đi
			driver.findElement(By.cssSelector("div.thrv_icon")).click();
			sleepInSecond(3);
		}
		
		Assert.assertEquals(driver.getTitle(), "VNK EDU | Trung tâm đào tạo cơ điện, plc hàng đầu Việt Nam");
		
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("a[title='Khóa học ONLINE']")).click();
		driver.findElement(By.cssSelector("a[title='Khóa học ONLINE']~ul a[title='Thiết kế hệ thống Cấp thoát nước']")).click();
		
		Assert.assertTrue(
				driver.findElement(By.cssSelector("div.wrap-courses>div:first-child h4")).getText().contains("Khóa học Thiết kế Hệ thống cấp thoát nước cho công trình"));
	}

	@Test
	public void TC_03_Random_Not_In_Dom() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		
		By popupSale = By.cssSelector("div.popup-content");
		
		if (driver.findElements(popupSale).size() > 0 && driver.findElements(popupSale).get(0).isDisplayed()) {
			//Close popup
			driver.findElement(By.id("popup-name")).sendKeys("Automation");
			driver.findElement(By.id("popup-email")).sendKeys("auto@gmail.com");
			driver.findElement(By.id("popup-phone")).sendKeys("0378181883");
			
			driver.findElement(By.id("close-popup")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		
		String textSearch = "Khóa học thiết kế hệ thống Điện";
		
		driver.findElement(By.id("search-courses")).sendKeys(textSearch);
		driver.findElement(By.id("search-course-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.wrap-courses>div:last-child")).getText().contains(textSearch));
		
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
