package com.auto1.page;

import com.auto1.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {

    private final By makeAndModel = new By.ById("carMakeFilter");
    private final By basic = new By.ById("basicFilter");
    private final By rangeEnd = new By.ById("rangeEnd");
    private final By searchResult = new By.ByXPath("//div[@class='ReactVirtualized__Grid grid___vxxpR']/div/div");
    private final By makeFilter = By.xpath("(//li[@class='item___1TF6q'])[1]");
    private final By modelFilter = By.xpath("(//li[@class='item___1TF6q'])[2]");
    private final By rangeFilter = By.xpath("(//li[@class='item___1TF6q'])[3]");
    private final WebDriverUtil webDriverUtil;

    public SearchPage(WebDriver webDriver) {
        this.webDriverUtil = new WebDriverUtil(webDriver);
    }

    public void navigateToSearchPage() {
        String url = "https://www.autohero.com/de/search/";
        this.webDriverUtil.navigate(url);
    }

    public void clickOnMake() {
        this.webDriverUtil.click(makeAndModel);
    }

    public void clickOnBasic() {
        this.webDriverUtil.click(basic);
    }

    public void selectMakeAndModel(String make, String model) {
        By make1 = new By.ByXPath("//li[@class='listItem___3FT95']/input[contains(@value,'" + make + "')]");
        By model1 = new By.ByXPath("//li[@class='listItem___1KT4l']/input[contains(@value,'" + model + "')]");
        this.webDriverUtil.click(make1);
        this.webDriverUtil.click(model1);
    }

    public void selectMaxRange(String maxRange) {
        this.webDriverUtil.selectByPartialText(rangeEnd, maxRange);
    }

    public List<WebElement> waitForResults() {
        return this.webDriverUtil.waitForCountOfElements(searchResult, 1);
    }

    public String getMakeFilter() {
        return this.webDriverUtil.getElement(makeFilter).getText();
    }

    public String getModelFilter() {
        return this.webDriverUtil.getElement(modelFilter).getText();
    }

    public String rangeFiler() {
        return this.webDriverUtil.getElement(rangeFilter).getText();
    }
}
