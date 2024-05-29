package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class InternetTestDynamicControlsPOM {

	WebDriver driver = null;
	By dynamicControlTag = By.xpath("//a[normalize-space()='Dynamic Controls']");
	By checkBox = By.xpath("//input[@type='checkbox']");
	By removeCheckBoxButton = By.xpath("//button[normalize-space()='Remove']");
	By addCheckBoxButton = By.xpath("//button[normalize-space()='Add']");
	By enableTextBox = By.xpath("//button[normalize-space()='Enable']");
	By textBox = By.xpath("//input[@type='text']");
}
