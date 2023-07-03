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

  @productlistpage
  Scenario: User able to see prodct type Earrings on landing page
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
      | Shop By Occasion |
    And User select Shop by Material Gold and click on it
    Then The product listing page is displayed
    
     @plpFilterByProductType
  Scenario: User able to see Filter By on productlisting page
    When User click on pendants on landing page
    And User are able to filter by product type pendants as per choice
    Then User see the Currently Shopping by filter
    
    
     @plpMoreFilter
  Scenario: User able to see Filter By on productlisting page
    When User click on pendants on landing page
    And User able to click on more filter
    And User select the any combination of product as per there choice
    Then User are able to see the combination of product
    
    @plpLoadMore
  Scenario: User able to see Filter By on productlisting page
    When User click on pendants on landing page
    And User scrolldown to the bootom of the page
    Then User click on load more button
    
    @FilterBy
  Scenario: User able to see Filter By on productlisting page
    When User click on treading on landing page
    And User opened with treading url "https://www.candere.com/trending/hot.html"
    And User click on filter by price criteria
    Then Size of filter by category should be 7
    
    @plpexpressdelivery
  Scenario: User able to see prodct type Earrings on landing page
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
      | Shop By Occasion |
    And User select Shop by Material Gold and click on it
    And The product listing page is displayed
    Then User click on express delivery
    
    @plpmetalcolourchange
  Scenario: User able to see prodct type Earrings on landing page
    When User mousehover on Earring option
    And User see the various catagory on screen
      | Shop by Style    |
      | Shop by Material |
      | Shop for         |
      | Shop By Occasion |
    And User select Shop by Material Gold and click on it
    And User mouse over to background colour
    Then User click on change background like gold and rosegold
    
    
    #@plpwishlisticon
  #Scenario: User able to see prodct type Earrings on landing page
    #When User mousehover on Earring option
    #And User see the various catagory on screen
      #| Shop by Style    |
      #| Shop by Material |
      #| Shop for         |
      #| Shop By Occasion |
    #And User select Shop by Material Gold and click on it
    #And User click on wishlist icon
    #Then User are able to see the login popup
    
     #@PLPvideobutton
  #Scenario: User able to see prodct type Earrings on landing page
    #When User mousehover on Earring option
    #And User see the various catagory on screen
      #| Shop by Style    |
      #| Shop by Material |
      #| Shop for         |
      #| Shop By Occasion |
    #And User select Shop by Material Gold and click on it
    #And User click on video-product-button
    #Then video-product-button is working as per excepted
