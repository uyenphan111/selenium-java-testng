package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_P2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Textbox
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Automation testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox  is not displayed");
		}
	
		//Text area
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Automation Grid");
			System.out.println("Education TextArea is displayed");
		} else {
			System.out.println("Education TextArea is not displayed");
		}
		
		//Radio
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age Under 18 Radio is displayed");
		} else {
			System.out.println("Age Under 18 Radio is not displayed");
		}
		
		//Username5 Text
		if (driver.findElement(username5Text).isDisplayed()) {
			System.out.println("Username5 Text is displayed");
		} else {
			System.out.println("Username5 Text Radio is not displayed");
		}
	}

	//@Test
	public void TC_02_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password Textbox is enable");
		} else {
			System.out.println("Password Textbox is disable");
		}
		
		if (driver.findElement(biography).isEnabled()) {
			System.out.println("biography TextArea is enable");
		} else {
			System.out.println("biography TextArea is disable");
		}
	}

	//@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(javaCheckbox).isSelected());
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaCheckbox).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(javaCheckbox).isSelected());
	}
	
	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("phuyenh8@gmail.com");
		

		
		driver.findElement(passwordTextbox04).sendKeys("abc");
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox04).clear();
		driver.findElement(passwordTextbox04).sendKeys("ABC");
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox04).clear();
		driver.findElement(passwordTextbox04).sendKeys("ABC123");
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox04).clear();
		driver.findElement(passwordTextbox04).sendKeys("ABC125$");
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox04).clear();
		driver.findElement(passwordTextbox04).sendKeys("AABN4@@#$%");
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox04).clear();
		driver.findElement(passwordTextbox04).sendKeys("AABaxndN4@@#$%");
		sleepInSecond(3);
		
		//Verify
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
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
