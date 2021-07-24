package com.auto1.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverUtil {

    private final WebDriverWait webDriverWait;
    private final WebDriver webDriver;

    public WebDriverUtil(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(this.webDriver, 10);
    }

    public WebElement getElement(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void click(By by) {
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    public void selectByPartialText(By by, String text) {
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        List<WebElement> options = element.findElements(By.tagName("option"));
        for(WebElement option : options) {
            if(option.getText().contains(text)) {
                text = option.getText();
                break;
            }
        }
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void navigate(String url) {
        this.webDriver.get(url);
    }

    public List<WebElement> waitForCountOfElements(By by, int count) {
        return webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, count));
    }
}
