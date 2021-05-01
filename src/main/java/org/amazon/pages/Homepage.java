package org.amazon.pages;

import org.amazon.base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Homepage web elements initialization
 */
public class Homepage extends Base {

    public WebDriver driver;
    private static Logger log = LogManager.getLogger(Homepage.class.getName());


    @FindBy(css = "#nav-signin-tooltip .nav-action-inner")
    WebElement signIn;

    @FindBy(css = "#twotabsearchtextbox")
    WebElement searchTextBox;

    @FindBy(css = "#nav-search-submit-button")
    WebElement searchIcon;

    @FindBy(css = "#nav-link-accountList-nav-line-1")
    WebElement userNameOnHomepage;

    @FindBy(xpath="//div[contains(@id,'issDiv')]")
    List<WebElement> autoSuggestion;

    public Homepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Enter partial data and click from autosuggestion below search box
     */
    public SearchResultPage searchFunction(String str,String data)  {
      searchTextBox.sendKeys(str);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        autoSuggestion.stream().filter(dat->dat.getText().contains(data))
              .findFirst().get().click();
        log.info("Successfully clicked on the required search data based on partial text ");
        return new SearchResultPage(driver);
    }

    /**
     *     Clicking on the sign-in button on homepage
     */
    public SignInPage getSignIn() {
        wait.until(ExpectedConditions.visibilityOf(signIn));
        signIn.click();
        return new SignInPage(driver);
    }


    public Boolean getUserNameOnHomepage() {
        System.out.println(userNameOnHomepage);
        return userNameOnHomepage.isDisplayed();
    }
}
