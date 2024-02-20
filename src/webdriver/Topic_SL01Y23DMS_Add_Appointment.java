package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic_SL01Y23DMS_Add_Appointment {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String username, password, channel, firstName, lastName, idNumber, idNumberIssuedDate, dob, pob, phoneNumber, email;
	String permanentAddress, mofNo, mofDate;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		
		username = "uyenptt";
		password = "Xhdaf81@bd722#";
		channel = "Financial Consultant";
		firstName = "Phan";
		lastName = "Violet_" + getRandomNumber();	
		idNumber = "FC-" + getRandomNumber();
		idNumberIssuedDate = "01/01/2024";
		dob = "18/10/2000";
		pob = "Đồng Tháp";
		phoneNumber = "037818" + getRandomNumber();
		email = "violetphan" + getRandomNumber() + "@gmail.com";
		permanentAddress = getRandomNumber() + " Phạm Hùng";
		mofNo = "01254";
		mofDate = "01/01/2024";
	}   

	@Test
	public void TC_01() {
		driver.get("https://sl-ies-development.unit.vn/dms/login");
		sleepInSecond(2);
		
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Distribution Management System']")).getText(), 
				"Distribution Management System");
		
		driver.findElement(By.xpath("//p[text()='Movements']/parent::a")).click();
		sleepInSecond(1);
		driver.findElement(By.cssSelector("button[btntext='Add']")).click();
		driver.findElement(By.cssSelector("button[btntext='Add Appointment']")).click();
		
		enterAndSelectInDropDown("input[name='channel']", "ul[role='listbox'] li[role='option']", "Financial Consultant");
		selectItemInDropdown("div#agentType", "div#react-select-2-listbox div[role='option']", "SM - Giám đốc kinh doanh");
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		driver.findElement(By.name("idNumber")).sendKeys(idNumber);
		driver.findElement(By.id("idNumberIssuedDate")).sendKeys(idNumberIssuedDate);
		enterAndSelectInDropDown("input[name='idNumberIssuedPlace']", "ul[role='listbox'] li[role='option']", "Thành phố Hà Nội");
		enterAndSelectInDropDown("input[name='gender']", "ul[role='listbox'] li[role='option']", "Female");
		driver.findElement(By.id("dob")).sendKeys(dob);
		driver.findElement(By.name("pob")).sendKeys(pob);
		enterAndSelectInDropDown("input[name='maritalStatus']", "ul[role='listbox'] li[role='option']", "Divorced");
		driver.findElement(By.id("phoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.name("email")).sendKeys(email);
		enterAndSelectInDropDown("input[name='education']", "ul[role='listbox'] li[role='option']", "University");
		enterAndSelectInDropDown("input[name='career']", "ul[role='listbox'] li[role='option']", "SalesMan");
		
		scrollToElementOnTop("//p[text()='Permanent Address']");
		
		enterAndSelectInDropDownXpath("//input[@name='permanentProvince']/preceding-sibling::div//input", "//div[@role='listbox']/div[@role='option']", "Thành phố Hồ Chí Minh");
		enterAndSelectInDropDownXpath("//input[@name='permanentDistrict']/preceding-sibling::div//input", "//div[@role='listbox']/div[@role='option']", "Quận 8");
		enterAndSelectInDropDownXpath("//input[@name='permanentWard']/preceding-sibling::div//input", "//div[@role='listbox']/div[@role='option']", "Phường 10");
		driver.findElement(By.name("permanentAddress")).sendKeys(permanentAddress);
		
		By checkbox = By.xpath("//span[text()='Is the same as Permanent address']/preceding-sibling::span");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		//Assert.assertTrue(driver.findElement(checkbox).isSelected());
		
		scrollToElementOnTop("//p[text()='Agent structure']");
		
		enterAndSelectInDropDownXpath("//input[@name='introAgentCode']/preceding-sibling::div//input", "//div[@role='listbox']/div[@role='option']", "80000003 ");
		driver.findElement(By.name("mofNo")).sendKeys(mofNo); 
		driver.findElement(By.name("mofDate")).sendKeys(mofDate); 
		driver.findElement(By.cssSelector("button[btntext=\'Submit\']")).click();
		sleepInSecond(1);
		//popup confirm submit
		driver.findElement(By.id("popup-action-btn-confirm")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Data is created successfully!']")).getText(), "Data is created successfully!");
		//popup confirm successfully
		driver.findElement(By.id("popup-action-btn-confirm")).click();
	}

	//Hàm selectable dropdown
	public void selectItemInDropdown(String parentCss, String allItemsCss, String expectedTextItem) {
		//1
		driver.findElement(By.cssSelector(parentCss)).click();
		
		//2 Wait
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemsCss)));
		
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemsCss));
		
		//3
		for (WebElement tempItem : speedDropdownItems) {
			String textItem = tempItem.getText();
			System.out.println(textItem);
			
			if (textItem.equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}
	}
	
	//Hàm selectable dropdown XPATH
		public void selectItemInDropdownXpath(String parentXpath, String allItemsXpath, String expectedTextItem) {
			//1
			driver.findElement(By.xpath(parentXpath)).click();
			
			//2 Wait
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
			
			List<WebElement> speedDropdownItems = driver.findElements(By.xpath(allItemsXpath));
			
			//3
			for (WebElement tempItem : speedDropdownItems) {
				String textItem = tempItem.getText();
				System.out.println(textItem);
				
				if (textItem.contains(expectedTextItem)) {
					tempItem.click();
					break;
				}
			}
		}
	
	//Hàm editable dropdown
	public void enterAndSelectInDropDown(String textBoxCss, String allItemCss, String expectedTextItem) {
		//1 - nhập expectedTextItem -> xổ ra các item matching
		driver.findElement(By.cssSelector(textBoxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		
		//2 - Chờ cho tất cả các item được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		
		//
		for (WebElement tempItem : speedDropdownItems) {
			String textItem = tempItem.getText();
			System.out.println();
			
			if (textItem.contains(expectedTextItem)) {
				//sleepInSecond(1);
				tempItem.click();
				break;
			}
		}
	}
	
	//--
	//Hàm editable dropdown XPATH
	public void enterAndSelectInDropDownXpath(String textBoxXpath, String allItemXpath, String expectedTextXpath) {
		//1 - nhập expectedTextItem -> xổ ra các item matching
		driver.findElement(By.xpath(textBoxXpath)).sendKeys(expectedTextXpath);
		sleepInSecond(1);
		
		//2 - Chờ cho tất cả các item được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		List<WebElement> speedDropdownItems = driver.findElements(By.xpath(allItemXpath));
		
		//
		for (WebElement tempItem : speedDropdownItems) {
			String textItem = tempItem.getText();
			System.out.println();
			
			if (textItem.contains(expectedTextXpath)) {
				//sleepInSecond(1);
				tempItem.click();
				break;
			}
		}
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
		
	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", getElement(locator));
		
	}
	
	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false)", getElement(locator));
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
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
