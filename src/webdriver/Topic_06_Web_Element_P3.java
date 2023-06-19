package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_P3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, lastname, firstname, password, fullname;

	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By username5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biography = By.cssSelector("#bio");
	By javaCheckbox = By.cssSelector("#java");
	By passwordTextbox04 = By.id("new_password");
	By signupButton04 = By.id("create-account-enabled");

	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		Random rand = new Random();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		emailAddress = "autoahihi" + rand.nextInt(9999) + "@gmail.com";
		lastname = "phin";
		firstname ="phin";
		password ="123345677";
		fullname = lastname + " " + firstname;
	}

	@Test
	public void TC_01_Login_Empty() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys("228374@2133.344");
		driver.findElement(By.id("pass")).sendKeys("22837411");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
		
	
	@Test
	public void TC_03_Pw_Less_Than_6_Chars() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("228");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), 
		"Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Incorrect_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys("228123123");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Create_Account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(1);
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password); 
		driver.findElement(By.id("confirmation")).sendKeys(password); 
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div")).getText();
		
		Assert.assertTrue(contactInformation.contains(fullname));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, \"logo.png\")]")).isDisplayed());
		
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
