package orangehrm;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM {

	public static String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	WebDriver chromeDriver;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().window().maximize();
		chromeDriver.get(baseUrl);
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	}

	@AfterTest
	public void tearDown() {
		waitForNow();
		chromeDriver.close();
		chromeDriver.quit();
	}

	@Test(priority = 1)
	public void invalidPasswordLoginTest() {

		waitForNow();
		chromeDriver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		chromeDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123");
		chromeDriver.findElement(By.xpath("//button[@type='submit']")).submit();
		String expectedMessage = "Invalid credentials";
		String actualMessage = chromeDriver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Validating that the invalid credentials message is displayed after invlaid credentials entered");
	}

	@Test(priority = 2)
	public void loginTest() {

		chromeDriver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		chromeDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		chromeDriver.findElement(By.xpath("//button[@type='submit']")).submit();
		Assert.assertEquals(chromeDriver.getTitle(), "OrangeHRM", "Validating the page title is correct after login");
	}

	@Test
	public void logoutTest() {
		waitForNow();
		chromeDriver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		chromeDriver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		Assert.assertEquals(chromeDriver.getTitle(), "OrangeHRM", "Validating the page title is correct after logout");
	}

	public static void waitForNow() {
		try {
			Thread.sleep(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
