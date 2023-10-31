package webdriver;

import java.util.List;
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

public class Topic_16_Frame_Iframe {
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
		public void TC_01_Iframe_Kyna() {
			driver.get("https://skills.kynaenglish.vn/");
			
			//Verify iframe Facebook hiển thị
			Assert.assertTrue(driver.findElement(By.cssSelector("iframe[src*='kyna.vn']")).isDisplayed());
			
			//Verify số lương like fb 167k
			
			driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src*='kyna.vn']")));
			
			String likeFb = driver.findElement(By.xpath("//div[text()='167K followers']")).getText();
			System.out.println(likeFb);
			
			//switch về main page
			driver.switchTo().defaultContent();
			
			//switch vào iframe chat
			driver.switchTo().frame("cs_chat_iframe");
			
			//Click vào iframe chat
			driver.findElement(By.cssSelector("div[class*='chatButton_ButtonBar']")).click();
			
			//Nhập dữ liệu vào các field
			driver.findElement(By.cssSelector("input[ng-model='login.username']")).sendKeys("automation");
			driver.findElement(By.cssSelector("input[ng-model='login.phone']")).sendKeys("0381738474");
			new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
			driver.findElement(By.cssSelector("textarea[ng-model='login.content']")).sendKeys("aaaaaaaaaaaaaaaa");
			
			driver.switchTo().defaultContent();
			
			driver.findElement(By.id("live-search-bar")).sendKeys("excel");
			driver.findElement(By.cssSelector("button.search-button")).click();
			
			List<WebElement> listCourses = driver.findElements(By.cssSelector("div.content>h4"));
			
			for (WebElement course : listCourses) {
				Assert.assertTrue(course.getText().contains("Excel"));
			}
		}

		@Test
		public void TC_02_Frame_Banking() {
			driver.get("https://netbanking.hdfcbank.com/netbanking/");
			
			driver.switchTo().frame("login_page");
			
			driver.findElement(By.name("fldLoginUserId")).sendKeys("automation");
			sleepInSecond(2);
			driver.findElement(By.cssSelector("a.login-btn")).click();
			sleepInSecond(4);
			
			driver.switchTo().defaultContent();
			Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
		
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
