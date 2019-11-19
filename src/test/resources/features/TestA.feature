@TestA
Feature: Locus Pre-Screening Assignment - QA

  Scenario: Using selenium Automate the below process.
    When I Open "https://www.flipkart.com/"
    Then I Search for "Shoes" in the search bar from home screen
    Then I Verify the search results page
    And I Select price filter of max Rs 2000
    When I Search and Apply the brand "Puma"
    Then I Open the details page of the first result
    Then I Change the size
    And I click on Buy Now
