package com.auto1;

import com.auto1.page.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SearchTest {

    private WebDriver webDriver;
    private SearchPage searchPage;
    private final String make = "Volkswagen";
    private final String model = "Golf";
    private final Float maxRange = 25.0F;

    @BeforeClass
    public void beforeClass() {
        //Set chrome driver path
        System.setProperty("webdriver.chrome.driver", "/home/yogesh/Downloads/chromedriver_linux64/chromedriver");
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();
        this.searchPage = new SearchPage(this.webDriver);
    }

    @Test
    public void searchTest() {
        SoftAssert softAssert = new SoftAssert();
        try {
            searchPage.navigateToSearchPage();
            searchPage.clickOnMake();
            searchPage.selectMakeAndModel(make, model);
            searchPage.clickOnBasic();
            searchPage.selectMaxRange(String.valueOf(maxRange));
            searchPage.clickOnBasic();
            softAssert.assertEquals(searchPage.getMakeFilter().contains(make), true, "Filter does not contain make");
            softAssert.assertEquals(searchPage.getModelFilter().contains(model), true, "Filter does not contain model");
            softAssert.assertEquals(searchPage.rangeFiler().contains(maxRange.toString()), true, "Filter does not contain range");
        } catch (Exception ex) {
            ex.printStackTrace();
            softAssert.fail("Exception occurred", ex);
        }
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "searchTest")
    public void validateSearchResults() {
        SoftAssert softAssert = new SoftAssert();
        try {
            List<WebElement> results = searchPage.waitForResults();
            for (WebElement result : results) {
                String makeModel = result.findElement(By.xpath(".//h2[@class='title___uRijL adTitle___1MVoL']")).getText();
                String range = result.findElement(By.xpath(".//ul[@class='specList___2i0rY']/li[3]")).getText();
                Float rangeNumber = Float.valueOf(range.split("\n")[1].split(" ")[0]);
                softAssert.assertEquals(makeModel.contains(make), true, "Search result does not contain the make");
                softAssert.assertEquals(makeModel.contains(model), true, "Search result does not contain model");
                softAssert.assertEquals(rangeNumber.compareTo(maxRange), -1, "Search result range not less than " + maxRange);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            softAssert.fail("Exception occurred", ex);
        }
        softAssert.assertAll();
    }

    @AfterClass
    public void afterClass() {
        this.webDriver.close();
        this.webDriver.quit();
    }
}
