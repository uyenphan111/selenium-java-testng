package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
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
		
		//Mở trang Register 
		driver.get("https://demo.nopcommerce.com/register");
	
	}

	@Test
	public void TC_01_Id() {
		//Tháo tác lên element đầu tiên phải tìm được element đó bằng hàm: findElement 
		//Find theo cái gì: theo locator tương ứng, ver2.3 được support: Id/Name/LastName/Link/Linktext/PatialLinkText/CSS/Xpath
		//Find được Element rồi thì sẽ action lên element đó: click/sendkeys: Nhập ,...
		driver.findElement(By.id("FirstName")).sendKeys("Automation testing");
	}

	@Test
	public void TC_02_Class() {
		//Mở trang search
		driver.get("https://demo.nopcommerce.com/search");
		
		//Nhập text vào trang sea
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
	}

	@Test
	public void TC_03_Name() {
		//Click vào advanced checkbox  
		driver.findElement(By.name("advs")).click();
	}
	
	@Test
	public void TC_04_TagName() {
		System.out.println(driver.findElement(By.tagName("input")).getSize());
	}
	
	@Test
	public void TC_05_LinkText() {
		//Click vào đường link Addresses (tuyệt đối)
//		driver.findElement(By.linkText("Addresses")).click();
	}
	
	@Test
	public void TC_06_PatialLinkText() {
		//Click vào đường link Apply for vendor account (tương đối) > chạy 
		//driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_CSS() {
		//Mở lại trang register. Vì sau khi chạy TC_06 thì đã ở màn hình khác
		driver.get("https://demo.nopcommerce.com/register");
		//1 kết hợp thẻ với kí tự đặt biệt # - đại diện cho id viết tắt
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Phan"); 
		
		//2 Phương pháp chuẩn của selenium khi tìm với 
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Uyen");
		
		//3 Kết hợp với Name
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("phanthithuyen111@gmail.com");
		
		//Or có thể kết hợp với bất kì attribute  > học sau ở bài  input[name='Password']
		driver.findElement(By.cssSelector("input[id='Password']")).sendKeys("Uyen");
	}
	
	@Test
	public void TC_08_XPATH() {
		//Mở lại trang register. Vì sau khi chạy TC_06 thì đã ở màn hình khác
		driver.get("https://demo.nopcommerce.com/register");
		//1 
		driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("Selenium");
				
		//2 
		driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Automation");
				
		//3 
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("phanthithuyen111@gmail.com");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
