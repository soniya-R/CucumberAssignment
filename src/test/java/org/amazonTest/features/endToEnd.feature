Feature: End to end feature


  Background:
    Given User is on Amazon homepage
    When  User search for an item with partial text "qa test" and click on required text "qa testing for beginners"

  Scenario: Validating price on item page, is same to that appear on search result page
    When User click on first search item on the searchResultPage
    Then assert price of item on SearchResultPage and SelectedItemPage

  Scenario:Validating price on add to cart page, is same to that appear on search result page
    When User is on SearchItemResultPage and click on add to card button
    Then assert price on AddToCart is same as on searchResultPage

  Scenario: Validating price on checkout page, is same to that appear on search result page
    When User is on Add to cart page and click on checkout button
    Then assert price on CheckOutPage is same as on searchResultPage







