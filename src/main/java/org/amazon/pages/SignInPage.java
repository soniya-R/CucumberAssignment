package org.amazon.pages;

import org.amazon.base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends Base {
    WebDriver driver;
    private static Logger log = LogManager.getLogger(SignInPage.class.getName());


    @FindBy(css = "#ap_email")
    private WebElement userEmail;

    @FindBy(css = "#continue")
    private WebElement continueButton;

    @FindBy(css = "#ap_password")
    private WebElement userPassword;

    @FindBy(css = "#signInSubmit")
    private WebElement signInButton;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public Homepage signIn(String email,String password )
    {
        wait.until(ExpectedConditions.visibilityOf(userEmail));
        userEmail.sendKeys(email);
        continueButton.click();
        wait.until(ExpectedConditions.visibilityOf(userPassword));
        userPassword.sendKeys(password);
        signInButton.click();
        log.info("Successfully Logged In");
        return new Homepage(driver);
    }

}

