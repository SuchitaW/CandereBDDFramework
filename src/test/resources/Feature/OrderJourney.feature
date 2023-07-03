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

  @e2eCheckoutSignUp
  Scenario Outline: Validate if User should be sign up in to check out the product
    When User search for the  "<product>"
    And choose to buy the first item in list
    And Add first product to the shopping cart
    And User click on Checkout Securely 
    And User are able to see the signup and login popup and click on signup
    And User enters  "<name>"  and  "<emailid>" and "<mobileno>"
    And User click on Create Account
    And Shipping page is open then user fill all details "<lastname>"  and  "<mobile>" and "<zip>" and "<flat>"  and  click on continue
    And User see the payment page and click on UPI
    And User enter "<UPIId>" and click on placeorder
    Then User see the cander Justpay page

    Examples: 
      | product | name | emailid            | mobileno   | lastname | mobile     | zip    | flat                                   | UPIId         |
      | ring | vwqewe | dgfe@gmail.com | 8087347612| rane     | 9023458934 | 400068 | flat no 201,sanlkalp chsl dahisar east | anything@payu |

  @e2eSignupCheckout
  Scenario Outline: Validate if User should be sign up in to check out the product
    When User see Your Account and click on  SignUp  from home page
    And User enters  "<name>"  and  "<emailid>" and "<mobileno>"
    And User click on Create Account
    And User click on product type Necklace
    And User choose to buy the first item
    And Product Desc is displayed in new tab 
    And User click on add to cart 
   And User click on Checkout Securely button
   And Shipping page is open then user click on continue
    #And Shipping page is open then user fill all details "<lastname>"  and  "<mobile>" and "<zip>" and "<flat>"  and  click on continue
    And User see the payment page and click on UPI
    And User enter "<UPIId>" and click on placeorder
    Then User see the cander Justpay page

    Examples: 
      | name | emailid         | mobileno   | product |
      | txzcxa|dsmnmbghhg@gmail.com | 9056745678 | Earring |
#
  #@e2eLoginCheckout
  #Scenario Outline: User is able to order single product with multiple quantity from the app
    #When User see Your Account and click on  Log In  from home page
    #And User enters "<Emailid or mobileno>"
    #And User click on continue button
    #And User enter otp manually and click on login
    #And User click on Rings
    #And Choose to buy the first item of the ring
    #And Product Description is displayed in new tab
    #And User select the ring size as per choice and check if size change the price also get updated or not
    #And User enters "<Pincode>" and click on Check button
    #And User see the Expected Delivery Date
    #And Add product to shopping cart
    #And On cart page user is able to change the product qty and and size
    #And Shipping page is open then user click on continue
    #And User see the payment page and click on UPI
    #And User enter "<UPIId>" and click on placeorder
    #Then User see the cander Justpay page
#
    #Examples: 
      #| Emailid or mobileno       | Pincode | UPIId         |
      #| suchita.tayde@candere.com |  400068 | anything@payu |
#
  #@e2eCheckoutLogin
  #Scenario Outline: Validate if User should be sign up in to check out the product
    #When User search for "<product>"
    #And choose to buy the first item
    #And Add product to shopping cart
    #And Click on proceed to check out after ACT
    #And user click on checkout signup pop is open
    #And User see login page and new user click on  SignUp  from checkout
    #And User enters  "<name>"  and  "<emailid>" and "<mobileno>"
    #And User click on Create Account
    #And Shipping page is open then user fill all details "<lastname>"  and  "<mobile>" and "<zip>" and "<flat>"  and  click on continue
    #And User see the payment page and click on UPI
    #And User enter "<UPIId>" and click on placeorder
    #Then User see the cander Justpay page
#
    #Examples: 
      #| product | name | emailid            | mobileno   | lastname | mobile     | zip    | flat                                   | UPIId         |
      #| Earring | Shiv | shivrane@gmail.com | 9967345556 | rane     | 9023458934 | 400068 | flat no 201,sanlkalp chsl dahisar east | anything@payu |
