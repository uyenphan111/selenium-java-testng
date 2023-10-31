package webdriver;

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

public class Topic_14_Topic_Popup_Fixed_Not_In_Dom {
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

		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);

		driver = new FirefoxDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Not_In_Dom_Tiki() {
		driver.get("https://tiki.vn/");
		
		//By: chưa tìm element
		By loginPopup = By.xpath("//button[@class='btn-close']/ancestor::div[contains(@class,'ReactModal__Content')]");
		
		
		//Verify popup chưa hiển thị khi chưa click vào Login btn
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0); 
		
		//Click Login btn
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(2);
		
		//Verify popup đang hiển thị
		// cách1:
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		// cách 2:
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector(".login-with-email")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(), "Mật khẩu không được để trống");
		
		//click close btn
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		//Verify không còn hiển thị 
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0); 
		
	}

	@Test
	public void TC_02_Not_In_Dom_Facebook() {
		driver.get("https://www.facebook.com/");
		
		By createNewAccount = By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']");
		
		Assert.assertEquals(driver.findElements(createNewAccount).size(), 0);
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(createNewAccount).size(), 1);
		
		driver.findElement(By.name("firstname")).sendKeys("Automation");
		driver.findElement(By.name("lastname")).sendKeys("FC");
		driver.findElement(By.name("reg_email__")).sendKeys("0378181883");
		driver.findElement(By.name("reg_passwd__")).sendKeys("uyen1234455");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("17");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Oct");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1979");
		driver.findElement(By.xpath("//label[text()='Female']")).click();
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']/img")).click();
		
		Assert.assertEquals(driver.findElements(createNewAccount).size(), 0);

	}

	@Test
	public void TC_03() {
		
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
