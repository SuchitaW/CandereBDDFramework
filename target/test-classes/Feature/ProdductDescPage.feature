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

@productdescpage
  Scenario: User able to see prodct type Earrings on landing page
    When User mousehover on Ring option
    And User see the various ringsubcatagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
      | Shop By Occasion |
    And User select Shop by Material Diamond and click on it
    And Product listing page is displayed
    And User click on any of the product
    Then Product Description is displayed in new tab
    
     @pdpPagevideobutton
  Scenario: User able to see prodct type Earrings on landing page
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
    And User click on video-product-button
    Then video-product-button is working as per excepted
    
    
    
     

    
    
  @pdpPageNotifyMe
 Scenario Outline: User able to see prodct type Earrings on landing page
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
      | Shop By Occasion |
    And User select Shop by Material Gold and click on it
    And The product listing page is displayed
    And User click on any product type earring
    And Product Description is displayed in new tab
    And User click on EXPLORE DGRP functionality
    And User checks the PLANS and TERMS & CONDITIONS
    And Close the EXPLORE DGRP window
    And After that User click on Notify me when Price Drop
    And User enters  "<EmailId>"  and  "<MobileNO>"
    And User click on Notify me
    Then User see the Thank U messagepopup and click on ok button

    Examples: 
      | EmailId                | MobileNO   |
      | suchitatayde@yahoo.com | 9078723790 |
    
    
    @pdpPageViewDetails
    Scenario: User able to see prodct type Earrings on landing page
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
      | Shop By Occasion |
    And User select Shop by Material Gold and click on it
    And The product listing page is displayed
    And User click on any product type earring
    And Product Description is displayed in new tab
    And User click on View Details
    And User scroll down to view detail
    And User click on Metal details and Product Details
    Then User go back to up
    
    
     @pdpringsize
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
  Then User click on add to cart and see the cart page
   
    Examples: 
       | Pincode | 
       |  400066 |
    
    
    
    
    
    
    
    
    