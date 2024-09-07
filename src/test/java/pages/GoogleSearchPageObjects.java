package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearchPageObjects {

    WebDriver driver = null;
    By searchTextBox = By.name("q");
    By searchButton = By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]");

    public GoogleSearchPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public void setTextInSearchTextBox(String text) {
        driver.findElement(searchTextBox).sendKeys(text);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void goToPreviousPage() {
        driver.navigate().back();
    }

    public void goToNextPage() {
        driver.navigate().forward();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    public List<WebElement> findMultipleElements() {
        return driver.findElements(By.name("q"));
    }

    public void findAttribute(WebElement element){
        System.out.println("Element's name : "+element.getAttribute("name"));
    }
}