package org.amazon.pages;

import org.amazon.base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SelectedItemPage extends Base {

    public WebDriver driver;
    private static Logger log = LogManager.getLogger(SelectedItemPage.class.getName());


    @FindBy(xpath = "//div[@class='a-button-stack']//span[@id='submit.add-to-cart']")
     WebElement addToCart;

    @FindBy(css = "#newBuyBoxPrice")
     WebElement priceOfItem;

    public SelectedItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     *  Adding the item to the cart by clicking on addtoCart button
     */
    public AddToCartPage getAddToCart() {
        wait.until(ExpectedConditions.visibilityOf(addToCart));
        addToCart.click();
        log.info("Clicked on  addToCart button on SelectedItemPage");
        return new AddToCartPage(driver);
    }

    /**
     *  Retriving the price displayed on the selectedItemPage
     */
    public String getPriceOfItem() {
        wait.until(ExpectedConditions.visibilityOf(priceOfItem));
        String amount = priceOfItem.getText();
        return amount;
    }

}
