package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class GoogleSearchTest {

	public static void main(String[] args) {
		googleSearch();
	}

	public static void googleSearch() {
		WebDriverManager.chromedriver().setup();
		WebDriver chromeDriver = new ChromeDriver();

		//Go to Google.com
		chromeDriver.get("https://www.google.com/");

		// Enter search text in the text box
		WebElement googleTextBox = chromeDriver.findElement(By.id("APjFqb"));
		googleTextBox.sendKeys("amazon");
		waitMethod();
		googleTextBox.sendKeys(Keys.CONTROL, "a");
		waitMethod();
		googleTextBox.sendKeys(Keys.DELETE);
		waitMethod();
		googleTextBox.sendKeys("amazon.com");
		// Click on the Search Button
		chromeDriver.findElement(By.name("btnK")).click();
		waitMethod();
		System.out.println("Test Completed Successfully");
		chromeDriver.close();
		chromeDriver.quit();
	}

	public static void waitMethod() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}