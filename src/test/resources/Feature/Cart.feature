@healthCheck
Feature: E-Commerce website healthcheck

  Background: Navigation to the URL
    Given User navigate to URL and open the landing page

  @URLRedirection
  Scenario: User naviaget to URL, User redirect to landing page with expected current URL
    When User is on landing page
    Then Validate current URL of landing page with expected URL

  @LandingPageTitle
  Scenario: User naviaget to URL, User redirect to landing page with expected page title
    When User is on landing page
   Then Validate title of landing page with expected title as "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
 
 
 #Validation criteria : User should be logged in to check out the product
 @cartPagecheckout
   Scenario Outline: User able to see prodct type Earrings on landing page
    When User mousehover on Ring option
    And User see the various ringsubcatagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
      | Shop By Occasion |
    And User select Shop by Material Diamond and click on it
    And Product listing page is displayed
    And User click on any of the product
    And Product Description is displayed in new tab
    And User select the ring size
     And User enters "<Pincode>" and click on Check button
  And User see the Expected Delivery Date
  And User click on add to cart and see the cart page
   And On cart page user is able to change the product qty and and size
  Then user click on checkout logIn pop is open
   
    Examples: 
       | Pincode | 
       |  400066 |
      
      
      #Product search and purchase from candere.com
  #User wants to search for Product and purchase from candere.com
  #Validation criteria : User should be logged in to check out the product 
       @cartPagecheckout2
  Scenario Outline: Product Search and add to shopping cart
    When User search for "<product>"
    And choose to buy the first item
    And Add product to shopping cart
    And User clicks on continue shopping
    And User clicks on My shopping bag and view shopping bag
    And Click on proceed to check out
    Then User should be asked to login before checkout

    Examples: 
      | product |
      | Earring |