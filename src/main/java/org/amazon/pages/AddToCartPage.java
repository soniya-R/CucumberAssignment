package org.amazon.pages;

import org.amazon.base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddToCartPage extends Base {
    public WebDriver driver;
    private static Logger log = LogManager.getLogger(AddToCartPage.class.getName());

    @FindBy(css = "#hlb-ptc-btn-native")
    private WebElement checkoutButton;

    @FindBy(css = "#hlb-subcart .a-color-price.hlb-price.a-inline-block.a-text-bold")
    private WebElement priceOnAddToCartPage;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicking on checkout button on AddToCartPage
     */
    public CheckoutPage checkout() {
        wait.until(ExpectedConditions.visibilityOf(checkoutButton));
        checkoutButton.click();
        log.info("Successfully clicked on checkOutButton on checkout page");
        return new CheckoutPage(driver);
    }

    /**
     * retrieving the price of the object that is displayed on Add to cart page
     */
    public String getPriceOnAddToCartPage() {
        String amount = priceOnAddToCartPage.getText();
        return amount;
    }
}
