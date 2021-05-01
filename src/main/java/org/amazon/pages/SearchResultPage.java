package org.amazon.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage {

    public WebDriver driver;
    private static Logger log = LogManager.getLogger(SearchResultPage.class.getName());


    @FindBy(xpath = "//span[@class='a-price']/span[2]//span")
    List<WebElement> allItemsOnPage;


    @FindBy(css = ".a-size-base.a-link-normal.a-text-normal>span>span")
    List<WebElement> firstElementPrice;

    @FindBy(css = "img.s-image")
    WebElement firstImage;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     *   Retrieving the price of firstelement from result of searching a data
     */
    public String getFirstElementPrice() {
        StringBuffer str = new StringBuffer();
        str.append(allItemsOnPage.get(0).getText());
        str.append(allItemsOnPage.get(1).getText());
        str.append(".");
        str.append(allItemsOnPage.get(3).getText());
        log.info("Succesfully captured price of first element in SearchResultPage");
        return String.valueOf(str);
    }


    /**
     *   Selecting the first image in the list
     */
    public SelectedItemPage getFirstImage() {
        firstImage.click();
        log.info("Clicked on firstImage on SearchResultPage ");
        return new SelectedItemPage(driver);
    }

    /**
     *   Get the current page title
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        log.info("Captures the title of SearchResultPage");
        return title;
    }

}
