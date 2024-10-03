@BooksWagonHome
Feature: BooksWagon Home page  Functionality 

  Background:
    Given the user launches the BooksWagon website
    And the user logs in with username "9198473884" and password "Dkyp12@@"
    
 @AddwishList
  Scenario: User scrolls to the 7th card and clicks it
    When the user scrolls to the 7th card
    Then the user should see the details of the 7th card
    When the user adds the item to the wishlist
    Then the item should be added to the wishlist successfully

  @wishlishIcon
  Scenario: User checks if the wishlist icon is clickable
    When the user clicks on the wishlist icon
    Then the wishlist icon should be clickable
    
    
