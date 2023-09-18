package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Checkbox_Radio_Custom {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		/*
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		*/
		
	
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		driver = new FirefoxDriver();
		//Khởi tạo -> add kiểu
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	
	}

	@Test
	public void TC_01() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

//		Thẻ input bị ẩn nhưng vẫn dùng để click
//		Hàm click() của WebElement không thể thao tác được với element bị ẩn
//		Nên dùng hàm click() của JavaScript để click
//		Selenium cung cấp 1 thư việc để có thể nhúng các đoạn code JS vào kịch bản test được → Javascripexcutor
	
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")));
	
//		Verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	
}

	@Test
	public void TC_02() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Ngãi']");
		
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(2);
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		sleepInSecond(2);
		
		//verify
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
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
		//driver.quit();
	}
}
