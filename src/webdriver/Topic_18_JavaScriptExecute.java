package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_JavaScriptExecute {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_Tech_Panda() {
		//navigateToUrlByJs("http://live.techpanda.org/");
		driver.get("http://live.techpanda.org/");
		sleepInSecond(5);
		
		Assert.assertEquals(excuteForBrowser("return document.URL"), "http://live.techpanda.org/");
		Assert.assertEquals(excuteForBrowser("return document.domain"), "live.techpanda.org");
		
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
		sleepInSecond(3);
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
	
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		Assert.assertEquals(getTitleByJS(), "Customer Service");
		
		scrollToElementOnTop("//span[text()='Newsletter']");
		hightlightElement("//span[text()='Newsletter']");
		
		driver.findElement(By.id("newsletter")).sendKeys("abc" + getRanDomNumber() + "@gmail.vn");
		hightlightElement("//button[@title='Subscribe']");
		
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		//navigateToUrlByJS("http://demo.guru99.com/v4/");
		driver.get("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		Assert.assertEquals(excuteForBrowser("return document.domain"), "demo.guru99.com");
	}  

	@Test
	public void TC_02_HTML5_Validation_Message() {
		driver.get("https://warranty.rode.com/register");
		sleepInSecond(3);
		
		String buttonSubmit = "//button[@type='submit']";
		String TextboxName = "//input[@id='name']";
		String TextboxEmail = "//input[@id='email']";
		String TextboxPassword = "//input[@id='password']";
		String TextboxConfirmPassword = "//input[@id='password_confirmation']";
		
		clickToElementByJS(buttonSubmit);
		
		Assert.assertEquals(getElementValidationMessage(TextboxName), "Please fill out this field.");
		
		getElement(TextboxName).sendKeys("Automation");
		clickToElementByJS(buttonSubmit);
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(TextboxEmail), "Please fill out this field.");
		
		getElement(TextboxEmail).sendKeys("abc" + getRanDomNumber() + "@gmail.com");
		clickToElementByJS(buttonSubmit);
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(TextboxPassword), "Please fill out this field.");
		
		getElement(TextboxPassword).sendKeys("abc1111com");
		clickToElementByJS(buttonSubmit);
		sleepInSecond(1);
		Assert.assertEquals(getElementValidationMessage(TextboxConfirmPassword), "Please fill out this field.");
		
		getElement(TextboxConfirmPassword).sendKeys("abc1111com");
		clickToElementByJS(buttonSubmit);
		sleepInSecond(3);
		
		//Assert.assertEquals(getTitleByJS(), "Warranty Registration | RØDE MICROPHONES - RØDE Warranty");
	}
	
	public Object excuteForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	
	public String getTitleByJS() {
		return (String) jsExecutor.executeScript("return document.title");
	}
	
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.locator = '" + url + "'");
	}
	
	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String orginalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, orginalStyle);
	}
	
	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false)", getElement(locator));
	}
	
	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", getElement(locator));
	}
	
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click()", getElement(locator));
	}
	
	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage", getElement(locator));
	}
	
	public boolean isImageLoaded (String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
 	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public int getRanDomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
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
