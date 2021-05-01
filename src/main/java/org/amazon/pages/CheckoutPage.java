package org.amazon.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    public WebDriver driver;
    private static Logger log = LogManager.getLogger(CheckoutPage.class.getName());

    @FindBy(xpath = "//div[@id='address-book-entry-0']/div[2]")
    WebElement deliverAddressButton;

    @FindBy(css = ".a-color-price")
    WebElement finalAmountToBePaid;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     *     clicking on the deliverAddressButton on checkout Page
     */
    public void getDeliverAddressButton() {
        deliverAddressButton.click();
    }

    /**
     *     retrieving the amount displayed in final page before final payment
     */
    public String getFinalAmountBeforeFinalPayment() {
        return finalAmountToBePaid.getText();
    }


}
