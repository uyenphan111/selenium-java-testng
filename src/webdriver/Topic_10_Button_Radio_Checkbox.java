package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		/*
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		*/
		/*
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		*/
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		//Verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		
		driver.findElement(By.id("login_username")).sendKeys("0378181884");
		driver.findElement(By.id("login_password")).sendKeys("12345678");
		
		//Verify login button is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
	}

	//@Test
	public void TC_02_Defaut_Checkbox_Radio_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(3);
		//Click chọn 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(), 'Cancer')]/preceding-sibling::input")).click();
		//Click chọn 1 radio button
		driver.findElement(By.xpath("//label[contains(text(), 'I have a loose diet')]/preceding-sibling::input")).click();
		//Verify các checkbox / radio button đã được chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'Cancer')]/preceding-sibling::input")).isSelected());

		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'I have a loose diet')]/preceding-sibling::input")).isSelected());
		
		//Click để bỏ chọn checkbox
		driver.findElement(By.xpath("//label[contains(text(), 'Cancer')]/preceding-sibling::input")).click();
		
		//Verify các checkbox đã được bỏ chọn
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(), 'Cancer')]/preceding-sibling::input")).isSelected());

	}

	//@Test
	public void TC_03_Checkbox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		sleepInSecond(3);
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		//Dùng vòng lặp for để duyệt qua và click vào checkbox này
		for (WebElement checkBox : allCheckboxes) {
			checkBox.click();
		}
		//Dùng vòng lặp for để duyệt qua và verify tất cả
		for (WebElement checkBox : allCheckboxes) {
			Assert.assertTrue(checkBox.isSelected());
		}
		
		for (WebElement checkBox : allCheckboxes) {
			checkBox.click();
		}
		
		for (WebElement checkBox : allCheckboxes) {
			Assert.assertFalse(checkBox.isSelected());
		}
		//Case: nếu như gặp 1 check box có tên X thì mới click
		for (WebElement checkBox : allCheckboxes) {
			if(checkBox.getAttribute("value").equals("Anemia")) {
				checkBox.click();
			}	
		}
	}

	@Test
	public void TC_04_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(3);
		
		//Chọn
		checkToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		//Verify đã chọn
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		//Bỏ chọn
		unCheckToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		//Verify đã bỏ chọn
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
	}
	//Hàm chọn
	public void checkToCheckbox(By by) {
		// ! phủ định
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	//Hàm bỏ chọn
		public void unCheckToCheckbox(By by) {
			if(driver.findElement(by).isSelected()) {
				driver.findElement(by).click();
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
