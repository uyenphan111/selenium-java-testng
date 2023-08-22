package webdriver;

import static org.testng.Assert.assertEquals;

import java.awt.Container;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Dropdown_Default {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstname, lastname, emailAddress, companyname, day, month, year, password;
	String country, city, address, postalcode, phoneNumber, passwordNew;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		firstname = "Sofia";
		lastname = "Phan";
		emailAddress = "autoahihi" + rand.nextInt(9999) + "@gmail.com";
		companyname = "";
		day = "18";
		month = "October";
		year = "2000";
		password = "Uyen123###";
		passwordNew = "Uyensociu123##";
		country = "Switzerland";
		city = "ABC";
		address = "abc";
		postalcode = "123";
		phoneNumber = " 0123456789";
	}

	@Test
	public void TC_01_Register_New_Account() {
		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyname);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		driver.findElement(By.id("register-button")).click();
		sleepInSecond(3);

		// verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

	}

	@Test
	public void TC_02_Verify() {
		// Login bằng tài khoản vừa tạo thành công, verify dữ liệu trùng khớp với dữ
		// liệu đã đki
		driver.get("https://demo.nopcommerce.com/login");

		driver.findElement(By.name("Email")).sendKeys(emailAddress);
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.cssSelector(".login-button")).click();
		sleepInSecond(3);

		driver.findElement(By.cssSelector(".ico-account")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastname);
		//Verify Dropdown Default: khi chọn 1 item nào xong thì sẽ lên hiển thị đầu tiên getFirstSelectedOption
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

	}

	@Test
	public void TC_03_Add_New_Address() {
		driver.findElement(By.xpath("//a[text()='Addresses']")).click();
		driver.findElement(By.xpath("//button[text()='Add new']")).click();
		
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstname);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastname);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(country);
		driver.findElement(By.id("Address_City")).sendKeys(city);
		driver.findElement(By.id("Address_Address1")).sendKeys(address);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalcode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		

		driver.findElement(By.cssSelector("button.save-address-button")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstname + " " + lastname);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		//Assert.assertEquals(driver.findElement(By.cssSelector("li.company")), country);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), address);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(city));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalcode));
		
	}

	@Test
	public void TC_04_Change_Password_NotMatch() {
		driver.findElement(By.xpath("//a[text()='Change password']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.id("OldPassword")).sendKeys(password);
		driver.findElement(By.id("NewPassword")).sendKeys(passwordNew);
		driver.findElement(By.id("ConfirmNewPassword")).sendKeys("122344$123nhdh");
		driver.findElement(By.cssSelector("button.change-password-button")).click();
		
		//
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmNewPassword-error")).getText(), "The new password and confirmation password do not match.");
	}
	
	@Test
	public void TC_05_Change_Password_Match() {
		driver.findElement(By.id("NewPassword")).clear();
		driver.findElement(By.id("NewPassword")).sendKeys(passwordNew);
		driver.findElement(By.id("ConfirmNewPassword")).clear();
		driver.findElement(By.id("ConfirmNewPassword")).sendKeys(passwordNew);
		driver.findElement(By.cssSelector("button.change-password-button")).click();
		
	}
	
	public void sleepInSecond(long timeInScond) {
		try {
			Thread.sleep(timeInScond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Chạy chậm ổn định sẽ tốt hơn là chạy nhanh và dễ fail
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
