package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_P1 {
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
	public void TC_01_WebElement() {
		WebElement element = driver.findElement(By.className(""));
		
		//Xoá dữ liệu trước khi nhập text -> dùng cho textbox, textarea, dropdown(edit table)
		element.clear();
		
		//Nhập dữ liệu -> dùng cho textbox, textarea, dropdown(edit table)
		element.sendKeys("");
		
		//click vào cái btn, link, checkbox, radio, image,...
		element.click();
		
		//
		String searchAttribute = element.getAttribute("placeholder");   //Hàm getAttribute trả về String
		
		
		//GUI: font size/color/location/position
		//lấy ra giá trị css 
		element.getCssValue("background-color"); //-> lấy ra màu nền
		//Vị trí của element so với web(bên ngoài)
		element.getLocation();
		//Kích thước của element(bên trong)
		element.getSize();
		// Location + size
		element.getRect();
		
		//chụp hình khi test case faild 
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BYTES);
		
		//Lấy ra tên thẻ HTML của element đó -> truyền vào cho một location khác
		element.getTagName();
		String emailTextboxTagname = driver.findElement(By.cssSelector("#Email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='email']"));
		
		//Lấy text error msg/ succes msg/ label/ header,...
		element.getText();
		
		//Khi nào nên dùng getText - getAttribute?
		//Khi cái giá trị mình cần lấy nó nằm bên ngoài - getText
		//Khi cái giá trị mình cần lấy nó nằm bên trong - getAttribute
		
		
		//Dùng để verify cái element có hiển thị hay không - Phạm vi: all các element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		//Dùng để verify cái element có thao tác được hay không - Phạm vi: all các element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		//Dùng để verify cái element có được chọn hay chưa - Phạm vi: checkbox / radio
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		element.submit();
	}

	@Test
	public void TC_02() {
		
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
