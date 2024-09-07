package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPageObjects {

    WebDriver driver = null;
    By searchTextBox = By.name("q");
    By searchButton = By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]");
    By showSearchButton = By.xpath("/html/body/div[1]/div[4]/div[1]/div");

    public GoogleSearchPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public void setTextInSearchTextBox(String text) {
        driver.findElement(searchTextBox).sendKeys(text);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
}