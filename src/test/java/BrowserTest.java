import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserTest {
	public static void main(String[] args) {
//		WebDriver firefoxDriver = new FirefoxDriver();
//		System.setProperty("webdriver.gecko.driver", "C:/Users/admin/eclipse-workspace/drivers/geckodriver.exe");
//		firefoxDriver.get("https://www.google.com/");
//		WebDriver chromeDriver = new ChromeDriver();
//		System.setProperty("webdriver.chrome.driver", "C:/Users/admin/eclipse-workspace/drivers/chromedriver.exe");
//		chromeDriver.get("https://www.google.com/");
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		chromeDriver.close();
//		chromeDriver.quit();
		WebDriverManager.chromedriver().setup();
		WebDriver chromeDriver = new ChromeDriver();
		chromeDriver.get("https://www.google.com/");
		String title = chromeDriver.getTitle();
		System.out.println("Title of the current window : "+title);
		WebElement googleTextBox = chromeDriver.findElement(By.id("APjFqb"));
		googleTextBox.sendKeys("amazon");
		waitMethod();
		//Above step can also be done like this
		googleTextBox.sendKeys("");
		chromeDriver.findElement(By.id("APjFqb")).sendKeys("amazon");
		waitMethod();
		
		// Get all the elements in a list of web elements
		List<WebElement> listOfInputs = chromeDriver.findElements(By.xpath("//input"));
		System.out.println("List of all Input Web Elements on "+title+" page : "+listOfInputs);
		chromeDriver.close();
		chromeDriver.quit();
	}
	
	public static void waitMethod() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
