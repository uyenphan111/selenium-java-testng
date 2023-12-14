package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	Actions action;
	
	String butterflyFileName = "butterfly.jpg";
	String fogFileName = "fog.jpg";
	String treeFileName = "tree.jpg";
	
	String butterflyFilePath = projectPath + "/File Upload/" + butterflyFileName;
	String fogFilePath = projectPath + "/File Upload/" + fogFileName;
	String treeFilePath = projectPath + "/File Upload/" + treeFileName;


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(butterflyFilePath);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(fogFilePath);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(treeFilePath);
		sleepInSecond(1);
		
		//Verify file được load thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + butterflyFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fogFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + treeFileName + "']")).isDisplayed());
		
		//Click "start" upload file
		List<WebElement> startButtons =  driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : startButtons) {
			button.click();
			sleepInSecond(3);
		}
		
		//Verify upload file thành công(link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + butterflyFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fogFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + treeFileName + "']")).isDisplayed());
	
		//Verify upload file thành công(img)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+ butterflyFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+ fogFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+ treeFileName + "')]"));
		
	}

	@Test
	public void TC_02_Multiple_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(butterflyFilePath + "\n" + fogFilePath + "\n" + treeFilePath);
		sleepInSecond(1);
		
		
		//Verify file được load thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + butterflyFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fogFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + treeFileName + "']")).isDisplayed());
		
		//Click "start" upload file
		List<WebElement> startButtons =  driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : startButtons) {
			button.click();
			sleepInSecond(3);
		}
		
		//Verify upload file thành công(link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + butterflyFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fogFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + treeFileName + "']")).isDisplayed());
	
		//Verify upload file thành công(img)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+ butterflyFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+ fogFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+ treeFileName + "')]"));
	}

	
	public boolean isImageLoaded (String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
