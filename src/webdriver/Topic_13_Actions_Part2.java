package webdriver;

import java.util.List;
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

public class Topic_13_Actions_Part2 {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Click_And_Hover() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		//click vào số 1 - click và giữ chưa thả chuột
		action.clickAndHold(listNumber.get(0))
			//di chuột đển số 8
			.moveToElement(listNumber.get(7))
			//nhả chuột trái ra
			.release()
			//excute
			.perform();
		sleepInSecond(2);
		
		List <WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 8);
	}

	@Test
	public void TC_02_Click_And_Hover_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//Chạy được cho cả Mac/Window
		Keys key = null;
		if (osName.contains("Window")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		//Nhấn phím command xuống
		//action.keyDown(Keys.COMMAND).perform();
		action.keyDown(key).perform();
		//click vào các số random
		action.click(listNumber.get(0))
			.click(listNumber.get(4))
			.click(listNumber.get(8))
			.click(listNumber.get(11)).perform();
		//nhả phím command ra
		action.keyUp(key).perform();
		sleepInSecond(2);
		//Verify
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 4);
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
		driver.quit();
	}
}
