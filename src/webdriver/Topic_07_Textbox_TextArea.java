package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(9999));
	String passportNumber = "99686-530-86-8265";
	String comment = "Lorem ipsum is placeholder text commonly used\n in the graphic";

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
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("Admin");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("admin123");
		WebElement submitBtn = driver.findElement(By.cssSelector("button.orangehrm-login-button"));
		submitBtn.click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		
		driver.findElement(By.name("firstName")).sendKeys("Sofia");
		driver.findElement(By.name("lastName")).sendKeys("Phan");
		//web lỗi không sử dụng câu lệch clear();
		//driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).clear(); 

		WebElement employeeIDTextbox = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		//employeeIDTextbox.sendKeys(Keys.chord(Keys.CONTROL, ('a')));
		employeeIDTextbox.sendKeys(Keys.DELETE);
		employeeIDTextbox.sendKeys(employeeID);
		
		//Bug rùi nào giỏi gòi fix tiếp :( huhu
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("So" + employeeID);
		driver.findElement(By.xpath("label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Uyensociu$$$12345");
		driver.findElement(By.xpath("label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Uyensociu$$$12345" );
		driver.findElement(By.xpath("button[contains(.,' Save ')]")).click();
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Sofia");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Phan");
		Assert.assertEquals(driver.findElement(By.xpath("label[text()='Confirm Password']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div//button[text()=' Add ']")).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[text()=' Save ']"));
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
		driver.findElement(By.cssSelector(".oxd-userdropdown-tab")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(2);
		
		username.sendKeys("So" + employeeID);
		password.sendKeys("\"Uyensociu$$$12345\"");
		submitBtn.click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Sofia");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Phan");
		Assert.assertEquals(driver.findElement(By.xpath("label[text()='Confirm Password']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
	
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
	}
		

	//@Test
	public void TC_02_Verify_Employee() {
		
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
