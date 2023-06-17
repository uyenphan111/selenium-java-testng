package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_P1 {
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
	public void TC_01() {
		//Các hàm tương tác với WebBrowser
		
		//=1: đóng browser đó
		//>=2: đóng tab/window đang đứng 
		driver.close();  //**
		
		//không quan tâm có bao nhiêu tab/window -> đóng browser
		driver.quit();  //**
		
		//Tìm 1 element
		WebDriver emailTextbox = (WebDriver) driver.findElement(By.xpath("//input[@id='email']")); //**
		
		//Tìm nhiều element
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));  //*
		
		//Mở ra 1 url bất kì
		driver.get("https://automationhacks.io/");  //**
		
		//Trả về url của page hiện tại - verify url
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationhacks.io/"); 
		
		
		//Trả về source code HTML của Page hiện tại
		//Verify tương đối
		Assert.assertTrue(driver.getPageSource().contains(""));
		
		//Trả về title của page hiện tại
		Assert.assertEquals(driver.getTitle(), "Automation Hacks");
		
		//Lấy ra Id của tab/window đang đứng(active)
		String loginID = driver.getWindowHandle();  //**
		
		//Lấy ra tất cả ID của tab/window
		Set<String> allIDs = driver.getWindowHandles();  //*
		
		//Cookies.cache
		Options opt = driver.manage();
		
		//login thành công -> lưu lại
		opt.getCookies(); //*
		
		//set cookies -> vào lại không cần phải login
		opt.logs();
		
		Timeouts time = opt.timeouts();
		
		//Khoảng thời gian chờ element xuất hiện trong x giây
		time.implicitlyWait(5, TimeUnit.SECONDS);  //5s = 5000ms  //**
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		//Khoảng thời gian chờ page load xuất hiện trong x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		//Khoảng thời gian chờ đoạn script thực thi xong trong x giây
		time.setScriptTimeout(5, TimeUnit.MINUTES);
		
		
		Window win = opt.window();
		win.fullscreen(); 
		win.maximize();  //** dùng nhiều - vì cần phóng to để test
		
		
		//Test GUI: font/style/color/position,... ít dùng 
		win.getPosition();
		win.getSize();
		
		Navigation nav = driver.navigate();
		//Tương ứng với 3 btn nav trên web
		nav.back(); 
		nav.forward(); 
		nav.refresh();
		nav.to("https://automationhacks.io/"); // = driver.get("https://automationhacks.io/"); - driver.get được dùng nhiều
		
		TargetLocator tar = driver.switchTo();
		//Có 3 bài học riêng - sẽ học sau
		tar.alert();  //*
		tar.frame("");  //*
		tar.window("");  //*
		
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















