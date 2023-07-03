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

  @Account
  Scenario: User click on Your Account icon and navigate to respective page
    Given User see Your Account button
    When User click on Your Account button
    Then User is on Your Account page which have expected page title as "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

  @CreateAccount
  Scenario Outline: User is able to Create Account into the application
    When User see Your Account and click on  SignUp  from home page
    When User redirected to create account page of the application where title us "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
    And User enters  "<name>"  and  "<emailid>" and "<mobileno>"
    And User click on Create Account
    Then User successfully redirected to "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

    Examples: 
      | name   | emailid               | mobileno   |
      | Rhjhdweg | Hwejhjhg@gmail.com       | 9978976790 |
      | cjhdfder | ufdfdwweDFkjdd3e@gmail.com | 9054564567 |

  @login
  Scenario Outline: User is able to login into the application
    When User see Your Account and click on  Log In  from home page
    And User enters "<Emailid or mobileno>"
    And User click on continue button
    And User enter otp manually and click on login
    Then User successfully redirected to homepage "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

    Examples: 
      | Emailid or mobileno    |
      | suchitatayde@gmail.com |
      
      @Search
  Scenario: Validate doubleclicksearch is working
    When User doubleclick  in search box
    Then User see the popular search box is appear
    
    @Searchprod1
  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    When User navigated to the home application url
    And User Search for product on search bar "ring"
    Then Search Result page is displayed with title contains "Gold & Diamond Ring Designs for Men & Women - Candere by Kalyan Jewellers"
    
    
    
    
    #@Wishlist
  #Scenario Outline: Validate if a registered user is able to add item to wishlist and place an order.
    #When User is logged onto the Candere website as a registered user.
    #And User enters "<Emailid or mobileno>"
    #And User click on continue button
    #And User enter otp manually and click on login
    #Then User successfully redirected to homepage "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
    #When User search for "<product>"
    #And Choose to add product to wishlist
    #And Click on wishlist icon
    #And Move this item to bag
    #And Click on proceed to check out
    #Then User click on continue payment page is display
#
    #Examples: 
      #| Emailid or mobileno       | product |
      #| suchita.tayde@candere.com | Necklace |
    #
