package org.amazonTest.stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.amazon.base.Base;
import org.amazon.pages.*;
import org.testng.Assert;

import java.io.IOException;

public class EndToEnd extends Base {

    String priceOnSelectedItemPage;
    String amount;
    String priceOnAddToCartPage;
    String finalPayableAmount;


    @Before
    public void before() throws IOException {
        driver = initializeDriver();
        Homepage homepage = new Homepage(driver);
        SignInPage signInPage;
        String email="saammpple@gmail.com";
        String password="123!@#";
        signInPage = homepage.getSignIn();
        signInPage.signIn(email,password);
    }

    @After
    public void after()
    {
        driver.quit();
    }

    @Given("User is on Amazon homepage")
    public void user_is_on_amazon_homepage()  {
        Homepage homepage = new Homepage(driver);
        Boolean isVisible = homepage.getUserNameOnHomepage();
        Assert.assertTrue(isVisible);
    }

    @When("User search for an item with partial text {string} and click on required text {string}")
    public void user_search_for_an_item_with_partial_text_and_click_on_required_text(String partialWord, String requiredText) {
        Homepage homepage = new Homepage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage = homepage.searchFunction(partialWord,requiredText);
         amount = searchResultPage.getFirstElementPrice();
    }

    @When("User click on first search item on the searchResultPage")
    public void user_click_on_first_search_item_on_the_search_result_page() {
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        SelectedItemPage selectedItemPage = new SelectedItemPage(driver);
        searchResultPage.getFirstImage();
         priceOnSelectedItemPage = selectedItemPage.getPriceOfItem();
    }

    @Then("assert price of item on SearchResultPage and SelectedItemPage")
    public void assert_price_of_item_on_search_result_page_and_selected_item_page() {
        Assert.assertEquals(amount,priceOnSelectedItemPage,"Amount on searchResultPage and priceOnSelectedItemPage does not match ");

    }


    @When("User is on SearchItemResultPage and click on add to card button")
    public void user_is_on_search_item_result_page_and_click_on_add_to_card_button() {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        SelectedItemPage selectedItemPage = new SelectedItemPage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        selectedItemPage = searchResultPage.getFirstImage();
        addToCartPage = selectedItemPage.getAddToCart();
        priceOnAddToCartPage = addToCartPage.getPriceOnAddToCartPage();
    }

    @Then("assert price on AddToCart is same as on searchResultPage")
    public void assert_price_on_add_to_cart_is_same_as_on_search_result_page() {
        Assert.assertEquals(amount,priceOnAddToCartPage,"Amount on searchResultPage and addToCartPage does not match ");
    }

    @When("User is on Add to cart page and click on checkout button")
    public void user_is_on_add_to_cart_page_and_click_on_checkout_button() {
        SelectedItemPage selectedItemPage = new SelectedItemPage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        selectedItemPage = searchResultPage.getFirstImage();
        addToCartPage = selectedItemPage.getAddToCart();
        checkoutPage = addToCartPage.checkout();
        checkoutPage.getDeliverAddressButton();
        finalPayableAmount = checkoutPage.getFinalAmountBeforeFinalPayment();

 }
    @Then("assert price on CheckOutPage is same as on searchResultPage")
    public void assert_price_on_check_out_page_is_same_as_on_search_result_page() {
        Assert.assertEquals(amount,finalPayableAmount, "Amount don't match");

    }


}
