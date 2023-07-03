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

  @DisplayLogo
  Scenario: User able to see logo of application on landing page
    When User is on landing page
    Then User see the logo of application

  @LogoHeight
  Scenario: Logo present on landing page with specific height dimension
    When fetch the height of logo
    Then Height of logo should be "53"

  @LogoWidth
  Scenario: Logo present on landing page with specific width dimension
    When fetch the width of logo
    Then Width of logo should be "240"

  @HomePageHeaderLinks
  Scenario: Validate Header links
    When User validate all headerlink are clickable or not
    Then Below header Links are displayed
      | Order Tracking         |
      | Candere logo           |
      | Find Experience Center |
      | Contact Us             |
      | Blog                   |
      | User                   |
      | Wishlist               |
      | Bag                    |
      | search text box        |

  @Ordertracking
  Scenario Outline: Validate Ordertracking is working as excepted
    When User click on ordertracking
    And User enter "<orderid>"  and  "<emailaddress>"
    And User click on request info
    Then User opened with order tracking url "https://www.candere.com/ordertracking/ordertracking/index"

    Examples: 
      | orderid    | emailaddress              |
      | 1000556321 | suchita.tayde@candere.com |
      | 1000556336 | mohit.gupta@candere.com   |

  @ProductCategoryList
  Scenario: User able to see product category on landing page
    When User see the product category
    Then Validate product category as per expected product category listed below
      | Trending            |
      | Rings               |
      | Earrings            |
      | Pendants            |
      | Necklace            |
      | Bangles & Bracelets |
      | Mangalsutra         |
      | Chains              |
      | Solitaires          |
      | Other Jewellery     |
      | Gifts               |
      | Offers              |
    And Size of product category should be 12

  @ProductSubcategory
  Scenario: Validate all product links on landing page of the application
    When User mouseover to the Ring of the landing page of the application
    And User see view all designs link should be visible
    And User is able to see 4 sub categories
    Then The categories having the option "Shop by Style","Shop by Material","Shop for" and "Shop By Occasion"
    And Under Shop by Style category below options are visible
      | Engagement   |
      | Solitaire    |
      | Couple Bands |
      | Casual       |
      | Eternity     |
      | Classic      |
      | Three Stone  |
      | Navratna     |
      | Religious    |
    And Under Shop by Material category below options are visible
      | Diamond        |
      | Gold           |
      | Platinum       |
      | Gemstone       |
      | Swarovski / CZ |
      | Silver + Gold  |
    And Under Shop for category below options are visible
      | Women |
      | Men   |
      | Kids  |
    And Under Shop By Occasion category below options are visible
      | Daily wear     |
      | Casual outings |
      | Festive        |
      | Anniversary    |
      | Wedding        |

  @ProductCategoryOffers
  Scenario: Validate all offers products on landing page of the application
    When User mouseover to the Offers of the landing page of the application
    And User are able to see all the offer product and click on any offer
    Then User successfully redirected to product list page "Buy Solitaire Diamond Jewellery Online in India | Designs @ Best Price | Candere by Kalyan Jewellers"

  @Signup&Spin!
  Scenario: User able to see Sign up & Spin! on landing page
    When User scroll down to Sign up & Spin!
    And User click on Sign up & Spin button
    Then User are able to see the Sign Up form

  @ProductCategory
  Scenario: User able to see product category list on landing page
    When User see the product category on the landing page
    And Validate product category list as per expected product category listed below
      | Stunning Rings       |
      | Classy Earrings      |
      | Stylish Mangalsutras |
      | Elegant Necklaces    |
      | Gorgeous Bracelets   |
      | Dainty Pendants      |
      | Statement Chains     |
    And User click on any product
    Then The product list page is displayed

  @ShowMeMore
  Scenario: User able to see Show Me More  on landing page
    When User scroll down to Show Me More
    And User click on show me more
    Then User are able to see the more collection

  @SubscribeNow
  Scenario Outline: Validate SubscribeNow Functionality
    When User scroldown to the botton of the landing page of the application
    And User enters "<emailid>" and "<mobileno>"
    And User is click on subscribe now
    Then Subscribe now message is displayed

    Examples: 
      | emailid                 | mobileno   |
      | sayeejadhav11@yahoo.com | 9035648990 |

  @FooterLinksLists
  Scenario: Validate all footer links on landing page of the application
    When user scroldown to the botton of the landing page of the application
    And user is able to see 6 main options categories
    And the categories having the option "ABOUT US", "WHY CANDERE?", "EXPERIENCE CANDERE", "JEWELLERY GUIDES","CONTACT US" and "FOLLOW US"
    Then under ABOUT US category below options are visible
      | About Our Company    |
      | Terms and Conditions |
      | Privacy Policy       |
      | FAQ                  |
      | Offers T&Cs          |
      | Customer Reviews     |
    And under WHY CANDERE? category below options are visible
      | WHY CANDERE?        |
      | 15-Day Returns      |
      | Cancel & Refund     |
      | Lifetime Exchange   |
      | DGRP                |
      | Certified Jewellery |
      | Candere Wallet      |
      | Jewellery Insurance |
    And under EXPERIENCE CANDERE category below options are visible
      | Lookbook       |
      | Stylery Blog   |
      | Video Gallery  |
      | Order Tracking |
      | Virtual Try On |
    And under JEWELLERY GUIDES category below options are visible
      | Diamond Education  |
      | Gemstone Education |
      | Metal Education    |
      | Size Guide         |
      | Gold Rate Guide    |
      | Jewellery Care     |
    And under CONTACT US category below options are visible
      | CONTACT US               |
      | support@candere.com      |
      | Find Experience Centre   |
      | Kalyan Store Locator     |
      | Kalyan Jewellers Website |
      | Kalyan Stores            |
      
@TwitterHandle
  Scenario: Validate twitter social media handle
    When User click on twitter link
    And navigate to twitter account page
    Then User opened with twitter url "https://twitter.com/i/flow/login"
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    