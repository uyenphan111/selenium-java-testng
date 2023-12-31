package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath1 {
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
	
	}

	@Test
	public void TC_01_EmptyData() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
//		Verify
//		Assert.assertFalse -> Kiểm tra 1 điều kiện trả về đúng
//		Assert.assertTrủ -> Kiểm tra 1 điều kiện trả về sai
//		Assert.assertEqual -> Kiểm tra kết quả thực vế và kết quả mong đợi
		
		//Data type: Web element , string -> getText() = string
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
	}
	
	@Test
	public void TC_02_InvalidEmail() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Uyen");
		driver.findElement(By.id("txtEmail")).sendKeys("PhanUyen@12@as");
		driver.findElement(By.id("txtCEmail")).sendKeys("PhanUyen@12@as");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
	}
	
	@Test
	public void TC_03_IncorrectEmail() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Uyen");
		driver.findElement(By.id("txtEmail")).sendKeys("PhanUyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("PhanUyen@");
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
	}
	
	@Test
	public void TC_04_InvalidPassword() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Uyen");
		driver.findElement(By.id("txtEmail")).sendKeys("PhanUyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("PhanUyen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		
	}
	
	@Test
	public void TC_05_IncorrectPassword() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Uyen");
		driver.findElement(By.id("txtEmail")).sendKeys("PhanUyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("PhanUyen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword")).sendKeys("1235432");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
	}
	
	@Test
	public void TC_06_InvalidPhone() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Action 1
		driver.findElement(By.id("txtFirstname")).sendKeys("Phan Uyen");
		driver.findElement(By.id("txtEmail")).sendKeys("PhanUyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("PhanUyen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("01234567");
		
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify 1
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Action 2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("1234567");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify 2
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		
	}
	
	

	@AfterClass
	public void afterClass() {
		//Comment lại nếu muốn xem console
		//driver.quit(); 
		
	}
}
