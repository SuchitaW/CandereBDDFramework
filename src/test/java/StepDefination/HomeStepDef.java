package StepDefination;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.FooterSectionObjects;
import PageObjects.HeaderSectionObjects;
import PageObjects.HomePageObjects;
import PageObjects.ProdductDescPageObjects;
import PageObjects.ProductListingPageObject;
import PageObjects.SignUpPageObject;
import core.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeStepDef {
	
	
	private static final Logger logger = LogManager.getLogger(HomeStepDef.class);
	WebDriver driver;

	WebDriverWait wait;
	JavascriptExecutor js;
	Scenario scn;
	String base_url = "https://www.candere.com/  ";
	
	

//============== Declare object reference of pageObjects classes =============================//
	
	//HomePageObjects homePageObject;
	HomePageObjects homePageObject;
	FooterSectionObjects footerPageObject;
	SignUpPageObject signUpPageObject;
	HeaderSectionObjects headerObject;
	ProductListingPageObject productListingObject;
	ProdductDescPageObjects productDescObject;
//====================== Before Hook =========================================================//	
	@Before
	public void setUp(Scenario scn)
	{
		this.scn=scn;

		//Get the desired browser to be invoked
		String browserName= WebDriverFactory.getBrowserName();
		driver=WebDriverFactory.getWebDriverForBrowser(browserName); 
		scn.log("Browser get invoked");

		//Initialize object of page objects classes 
		 homePageObject= new HomePageObjects(driver, scn);
	   footerPageObject=new FooterSectionObjects(driver, scn);
		 signUpPageObject= new SignUpPageObject(driver, scn);
		 headerObject= new HeaderSectionObjects(driver, scn);
		 productListingObject =new ProductListingPageObject(driver, scn);
		 productDescObject=new ProdductDescPageObjects(driver, scn);
	}

//====================== After Hook =========================================================//
	
	@After(order=2)
	//Capture screenshot if test case get failed
	public void captureScreenshot(Scenario scn)
	{
		if(scn.isFailed())
		{
			TakesScreenshot srnshot= ((TakesScreenshot)driver);
			byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get failed");
		}
		else
		{
			scn.log("Test case get passed, no screenshot is captured");
		}
	}
	
	@After(order=1)
	//Quit the browser
	public void tearDown(Scenario scn)
	{
		WebDriverFactory.quitTheBrowser();
		scn.log("Browser is quit");
	}
	
	
	
	
//====================== Background ================================================//
	@Given("User navigate to URL and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() {
		WebDriverFactory.navigateToURL(base_url);
	    }

//===================== Scenarios =================================================//

	@When("User is on landing page")
	public void user_is_on_landing_page() {
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}
	@Then("Validate current URL of landing page with expected URL")
	public void validate_current_url_of_landing_page_with_expected_url() {
		 homePageObject.validatePageURL();
		 scn.log("Validate current URL is: "+ driver.getCurrentUrl());
	}
	
	@Then("Validate title of landing page with expected title as {string}")
	public void validate_title_of_landing_page_with_expected_title_as(String titleOfPage) {
		homePageObject.validatePageTitle(titleOfPage);
		scn.log("Validate page title is: "+ driver.getTitle());
	}
		
	@Then("User see the logo of application")
	public void user_see_the_logo_of_application() {
		homePageObject.displayLogo();
		scn.log("Display the application logo on landing page");
	}
	
	@When("fetch the height of logo")
	public void fetch_the_height_of_logo() {
		homePageObject.fetchLogoHeight();
	}

	@Then("Height of logo should be {string}")
	public void height_of_logo_should_be(String height) {
		homePageObject.logoHeightValid(height);
	}
		
	@When("fetch the width of logo")
	public void fetch_the_width_of_logo() {
		 
		homePageObject.fetchLogoWidth();
		
	}

	@Then("Width of logo should be {string}")
	public void width_of_logo_should_be(String width) {
		homePageObject.logoWidthValid(width);
	}
	
//============================@HomePageHeaderLinks===============================================================
	

@When("User validate all headerlink are clickable or not")
public void user_validate_all_headerlink_are_clickable_or_not() throws Exception {
	
	headerObject.ClickOnOrderTracking();
	 headerObject.ClickOnFindExperienceCenter();
	 headerObject.ClickOnContactUs();
	 headerObject.ClickOnBlog();
    driver.navigate().back();
    headerObject.ClickOnSearchButton();
    headerObject.ClickOnUser();
    headerObject.ClickOnWishlist();
    driver.navigate().back();
    headerObject.ClickOnBag();
     driver.navigate().back(); 
   
}

@Then("Below header Links are displayed")
public void below_header_links_are_displayed(List<String> list) throws Exception {
	for (int i=0;i<list.size();i++) {
		headerObject.validateElementPresentInHeaderSection(list.get(i));	
		
}}
//==========================OrderTracking=================================================

@When("User click on ordertracking")
public void user_click_on_ordertracking()  {

	headerObject.orderTrackingPage();

}

@When("User enter {string}  and  {string}")
public void user_enter_and(String orderid, String emailaddress)  {

	headerObject.validateOrderTracking(orderid, emailaddress);
	
}
@When("User click on request info")
public void user_click_on_request_info()  {
headerObject.validateOrderTrackingReqInfo();
homePageObject.capturePassScreenshot(scn);

}

@Then("User opened with order tracking url {string}")
public void user_opened_with_order_tracking_url(String Name) {
	headerObject.orderTrackinglinkValidation(Name);
}

//========================================@ProductCategoryList===============================


@When("User see the product category")
public void user_see_the_product_category() {
   homePageObject.setProdCategory();
}

@Then("Validate product category as per expected product category listed below")
public void validate_product_category_as_per_expected_product_category_listed_below(List<String> prodList1) {
    homePageObject.validateProdCategory(prodList1);


}

@Then("Size of product category should be {int}")
public void size_of_product_category_should_be(Integer prodCatCount) {
    homePageObject.sizeOfProdCategory(prodCatCount);
}

//===============================Signup&Spin!=======================================================

@When("User scroll down to Sign up & Spin!")
public void user_scroll_down_to_sign_up_spin() {
    homePageObject.validateScrollSignUpSpin();
}

@When("User click on Sign up & Spin button")
public void user_click_on_sign_up_spin_button() throws Exception {
    homePageObject.clickOnSignUpSpin();
    //Thread.sleep(4000);
}
@Then("User are able to see the Sign Up form")
public void user_are_able_to_see_the_sign_up_form() {
   homePageObject.validateSignUpandSpinPageTitle();
}

//========================================ProductCategory====================


@When("User see the product category on the landing page")
public void user_see_the_product_category_on_the_landing_page() {

homePageObject.setProdCategoryList();
}
@When("Validate product category list as per expected product category listed below")
public void validate_product_category_list_as_per_expected_product_category_listed_below(List<String> prodList) {

homePageObject.validateProdCategorylist(prodList);
}
@When("User click on any product")
public void user_click_on_any_product() throws Exception {

homePageObject.clickOnAnyProdCategory();

Thread.sleep(3000);
}
@Then("The product list page is displayed")
public void the_product_list_page_is_displayed() {

homePageObject.validateProductListPageTitle();
}

@When("User scroll down to Show Me More")
public void user_scroll_down_to_show_me_more() {

homePageObject.scrolldownShowMeMore();
}

@When("User click on show me more")
public void user_click_on_show_me_more() {
    homePageObject.clickOnShowMeMore();
}
@Then("User are able to see the more collection")
public void user_are_able_to_see_the_more_collection() {
   homePageObject.validateShowMeMorePageTitle();
}

//============================ProductCategory========================================================

@When("User mouseover to the Ring of the landing page of the application")
public void user_mouseover_to_the_ring_of_the_landing_page_of_the_application() {
	WebElement ring=driver.findElement(By.xpath("//a[@class='parent_'][normalize-space()='Rings']"));
	Actions act=new Actions(driver);
	act.moveToElement(ring).build().perform();
}

@When("User see view all designs link should be visible")
public void user_see_view_all_designs_link_should_be_visible() {
	WebElement viewdesigns= driver.findElement(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[3]/div/div/div[2]/div/a"));
	viewdesigns.isDisplayed();
}
@When("User is able to see {int} sub categories")
public void user_is_able_to_see_sub_categories(int prodsubcatagory) {
	List<WebElement> prodMainCatListEle1 = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav[1]/div/div/div/div/ul/li[2]/div/div/div[1]/div/p"));
    Assert.assertEquals(prodsubcatagory,prodMainCatListEle1.size());

}

@Then("The categories having the option {string},{string},{string} and {string}")
public void the_categories_having_the_option_and(String subcategoryOneNameExp1, String subcategoryTwoNameExp1, String subcategoryThreeNameExp1,String subcategoryFourNameExp1) {
	List<WebElement> prodMainCatListEle1 = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[2]/div/div/div[1]/div/p"));

	Assert.assertEquals(subcategoryOneNameExp1, prodMainCatListEle1.get(0).getText());
Assert.assertEquals(subcategoryTwoNameExp1, prodMainCatListEle1.get(1).getText());
Assert.assertEquals(subcategoryThreeNameExp1, prodMainCatListEle1.get(2).getText());
Assert.assertEquals(subcategoryFourNameExp1, prodMainCatListEle1.get(3).getText());

}


@Then("Under Shop by Style category below options are visible")
public void under_shop_by_style_category_below_options_are_visible(List<String> Shopbystylelist) {
	List<WebElement> Shopbystyle = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav[1]/div/div/div/div/ul/li[2]/div/div/div[1]/div[1]/a"));
	
	for (int i = 0; i <Shopbystylelist.size(); i++) {
		if (Shopbystylelist.get(i).equals(Shopbystyle .get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
		
		}
}
@Then("Under Shop by Material category below options are visible")
public void under_shop_by_material_category_below_options_are_visible(List<String> ShopbyMaterialList) {
	List<WebElement> ShopbyMaterial = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav[1]/div/div/div/div/ul/li[2]/div/div/div[1]/div[2]/a"));

	for(int i=0;i<ShopbyMaterialList.size();i++)
	{
		if(ShopbyMaterialList.get(i).equals(ShopbyMaterial .get(i).getText())) {
			Assert.assertTrue(true);
		}
		else {
			Assert.fail();
		}
	}
}
@Then("Under Shop for category below options are visible")
public void under_shop_for_category_below_options_are_visible(List<String> ShopforList) {
	List<WebElement> ShopFor = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[2]/div/div/div[1]/div[3]/a"));

	for(int i=0;i<ShopforList.size();i++) {
		if(ShopforList.get(i).equals(ShopFor.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else {
			Assert.fail();
		}
	}
	
}
@Then("Under Shop By Occasion category below options are visible")
public void under_shop_by_occasion_category_below_options_are_visible(List<String> ShopforOccasionList) {
	List<WebElement> ShopByOccasion = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav[1]/div/div/div/div/ul/li[2]/div/div/div[1]/div[4]/a"));

	for(int i=0;i<ShopforOccasionList.size();i++) {
		if(ShopforOccasionList.get(i).equals(ShopByOccasion.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else {
			Assert.fail();
		}
	}
	
}


//================================ProductCategoryOffers===============================================================

@When("User mouseover to the Offers of the landing page of the application")
	public void user_mouseover_to_the_offers_of_the_landing_page_of_the_application() throws Exception {
	   WebElement offers=driver.findElement(By.xpath("//a[normalize-space()='Offers']"));
	   Actions act=new Actions(driver);
	   act.moveToElement(offers).build().perform();
	   //wait= new WebDriverWait(driver,50);
	}

	   @When("User are able to see all the offer product and click on any offer")
	   public void user_are_able_to_see_all_the_offer_product_and_click_on_any_offer() {
		   List<WebElement> offersprod = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[12]/div/div/div/div/a/img"));
		  homePageObject.capturePassScreenshot(scn);
		    offersprod.get(0).click();
			logger.info("Product offer");
		     scn.log("User see all the product offers");
	   }

	@Then("User successfully redirected to product list page {string}")
	public void user_successfully_redirected_to_product_list_page(String PLPPageTitle) {
		wait= new WebDriverWait(driver,20);
		
   boolean xyz = wait.until(ExpectedConditions.titleIs(PLPPageTitle));
       Assert.assertEquals(true, xyz);
   	logger.info("Validate title of offers page, so title is: "+ PLPPageTitle);
   	scn.log("Validate title of offers page, so title is: "+ PLPPageTitle);
	}

//=================================================@SubscribeNow====================================


@When("User scroldown to the botton of the landing page of the application")
public void user_scroldown_to_the_botton_of_the_landing_page_of_the_application() {
	js= (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,750)", "");
}

@When("User enters {string} and {string}")
public void user_enters_and(String emailid , String mobileno) {
	WebElement EmailId =driver.findElement(By.xpath("//input[@id='newsletter']"));
	EmailId.sendKeys(emailid);
	
	WebElement Mobileno =driver.findElement(By.xpath("//input[@id='mobile']"));
	Mobileno.sendKeys(mobileno);
}
@When("User is click on subscribe now")
public void user_is_click_on_subscribe_now() {
	WebElement Subscribenow =driver.findElement(By.xpath("//*[@id=\"newsletter-validate-detail\"]/div[2]/button/span"));
	Subscribenow.click();
}
@Then("Subscribe now message is displayed")
public void subscribe_now_message_is_displayed() throws Exception {
	
	Thread.sleep(3000);
    String exp = "Thank you for your subscription.";
    
    WebElement m = driver.findElement(By.xpath("//div[@class='newsletter-ajax-error']"));
    String act = m.getText();
    System.out.println(" Message is: "+ act);
    Assert.assertEquals(exp, act);
    logger.info("Validate Subscribe now, message is: "+ act);
scn.log("Navigate to Subscribe now , page message is: "+ act);
}

//=========================================@FooterLinksLists==============

@When("user scroldown to the botton of the landing page of the application")
public void user_scroldown_to_the_botton_of_the_landing_page_of_the_application1() throws InterruptedException {
	WebElement AboutUsElement = driver.findElement(By.xpath("//p[normalize-space()='ABOUT US']"));
     JavascriptExecutor js=((JavascriptExecutor) driver);


	js.executeScript("arguments[0].scrollIntoView(true);", AboutUsElement);

	Thread.sleep(5000);
}
@When("user is able to see {int} main options categories")
public void user_is_able_to_see_main_options_categories(int footerMainCatCount) {

	List<WebElement> footerMainCatListEle = driver.findElements(By.xpath("//div[@class='desktop_footer_link_item']//div[1]//nav[1]//p[1]"));
	Assert.assertEquals(footerMainCatCount, footerMainCatListEle.size());
}

@When("the categories having the option {string}, {string}, {string}, {string},{string} and {string}")
public void the_categories_having_the_option_and(String categoryOneNameExp, String categoryTwoNameExp, String categoryThreeNameExp, String categoryFourNameExp, String categoryFiveNameExp, String categorySixNameExp) {
	List<WebElement> footerMainCatListEle = driver.findElements(By.xpath("//div[@class='desktop_footer_link_item']//div[1]//nav[1]//p[1]"));
	Assert.assertEquals(categoryOneNameExp, footerMainCatListEle.get(0).getText());
	Assert.assertEquals(categoryTwoNameExp, footerMainCatListEle.get(1).getText());
	Assert.assertEquals(categoryThreeNameExp, footerMainCatListEle.get(2).getText());
	Assert.assertEquals(categoryFourNameExp, footerMainCatListEle.get(3).getText());
	Assert.assertEquals(categoryFiveNameExp, footerMainCatListEle.get(4).getText());
	Assert.assertEquals(categorySixNameExp, footerMainCatListEle.get(5).getText());
}




@Then("under ABOUT US category below options are visible")
public void under_about_us_category_below_options_are_visible(List<String> expectedAboutUsOptions) {
	List<WebElement> AboutUsOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[1]//a"));

	for (int i = 0; i < expectedAboutUsOptions.size(); i++) {
		if (expectedAboutUsOptions.get(i).equals(AboutUsOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
		
	}
}

@Then("under WHY CANDERE? category below options are visible")
public void under_why_candere_category_below_options_are_visible(List<String> expectedWHYCANDEROptions) {
	List<WebElement> WHYCANDEREOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[2]//a"));

	for (int i = 0; i < expectedWHYCANDEROptions.size(); i++) {
		if (expectedWHYCANDEROptions.get(i).equals(WHYCANDEREOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}

@Then("under EXPERIENCE CANDERE category below options are visible")
public void under_experience_candere_category_below_options_are_visible(List<String> expectedEXPERIENCECANDEREOptions) {
	List<WebElement> EXPERIENCECANDEREOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[3]//a"));

	for (int i = 0; i < expectedEXPERIENCECANDEREOptions.size(); i++) {
		if (expectedEXPERIENCECANDEREOptions.get(i).equals(EXPERIENCECANDEREOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}

@Then("under JEWELLERY GUIDES category below options are visible")
public void under_jewellery_guides_category_below_options_are_visible(List<String> expectedJEWELLERYGUIDESOptions) {

	List<WebElement> JEWELLERYGUIDESOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[4]//a"));

	for (int i = 0; i < expectedJEWELLERYGUIDESOptions.size(); i++) {
		if (expectedJEWELLERYGUIDESOptions.get(i).equals(JEWELLERYGUIDESOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}

@Then("under CONTACT US category below options are visible")
public void under_contact_us_category_below_options_are_visible(List<String> expectedCONTACTUSOptions) {
	List<WebElement> CONTACTUSOptionsActElement = driver.findElements(By.xpath("//div[@class='row_group flex_group']//div[5]//a"));

	for (int i = 0; i < expectedCONTACTUSOptions.size(); i++) {
		if (expectedCONTACTUSOptions.get(i).equals(CONTACTUSOptionsActElement.get(i).getText())) {
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}}

//===================================TwitterHandle================================================


@When("User click on twitter link")
public void user_click_on_twitter_link() {

footerPageObject.setTwitterLink();
footerPageObject.clickOnTwitterLink();
}

@When("navigate to twitter account page")
public void navigate_to_twitter_account_page() {
   footerPageObject.twitterAcPage();
}
@Then("User opened with twitter url {string}")
public void user_opened_with_twitter_url(String AcName) {
   footerPageObject.twitterAclinkValidation(AcName);
}


//========================================@SignInPage=========================================================

@Given("User see Your Account button")
public void user_see_your_account_button() {
signUpPageObject.signInBtnValidation();

}

@When("User click on Your Account button")
public void user_click_on_your_account_button() throws Exception {
	Thread.sleep(3000);
signUpPageObject.clickOnSignInBtn();
}
@Then("User is on Your Account page which have expected page title as {string}")
public void user_is_on_your_account_page_which_have_expected_page_title_as(String SignInPageTitle) {
   signUpPageObject.validateSignInPage(SignInPageTitle);
}


//=======================================@CreateAccount===============================

@When("User see Your Account and click on  SignUp  from home page")
public void user_see_your_account_and_click_on_sign_up_from_home_page() {
  signUpPageObject.clickOnSignInBtn();
  signUpPageObject.clickOnSignUp();
  
}

@When("User redirected to create account page of the application where title us {string}")
public void user_redirected_to_create_account_page_of_the_application_where_title_us(String SignInPageTitle) {
  signUpPageObject.validateSignInPage(SignInPageTitle);
}

@When("User enters  {string}  and  {string} and {string}")
public void user_enters_and_and(String name, String emailid, String mobileno) {
WebElement nameInputFieldElement = driver.findElement(By.xpath("//input[@id='firstname']"));
nameInputFieldElement.sendKeys(name);

WebElement emailIdInputFieldElement = driver.findElement(By.xpath("//input[@id='popup-email_address']"));
emailIdInputFieldElement.sendKeys(emailid);

WebElement mobilenoInputFieldElement = driver.findElement(By.xpath("//input[@id='phone']"));
mobilenoInputFieldElement.sendKeys(mobileno);

}

@When("User click on Create Account")
public void user_click_on_create_account() throws Exception  {
	WebElement createaccountButtonElement = driver.findElement(By.xpath("//span[normalize-space()='create account']"));
	createaccountButtonElement.click();
	Thread.sleep(7000);
}

@Then("User successfully redirected to {string}")
public void user_successfully_redirected_to(String SignInPageTitle)  {
	signUpPageObject.validateSignInPage(SignInPageTitle);
	signUpPageObject.validationtakescreenshot(scn);
	
	
	
}

//==============================LogIn================================================


@When("User see Your Account and click on  Log In  from home page")
public void user_see_your_account_and_click_on_log_in_from_home_page() throws Exception {
	signUpPageObject.clickOnSignInBtn();
	//Thread.sleep(3000);
	signUpPageObject.clickOnLogIn();
}

@When("User enters {string}")
public void user_enters(String Emailidmobileno) {
	WebElement nameInputFieldElement = driver.findElement(By.xpath("//input[@id='email-login-one']"));
	nameInputFieldElement.sendKeys(Emailidmobileno);

}

@When("User click on continue button")
public void user_click_on_continue_button() throws InterruptedException {
	WebElement continueButtonElement = driver.findElement(By.xpath("//button[@id='send2-login-one']"));
	continueButtonElement.click();
	WebElement a=driver.findElement(By.xpath("//p[normalize-space()='OTP has been sent successfully.']"));  
	 String s = a.getText();
	 scn.log(s);
    System.out.println("Alert text is: " + s); 
    Thread.sleep(10000);
}

@When("User enter otp manually and click on login")
public void user_enter_otp_manually_and_click_on_login() throws InterruptedException {
	Thread.sleep(10000);  
	WebElement loginButtonElement = driver.findElement(By.xpath("//form[@id='customer-popup-login-form-three']//button[@name='send']"));
	loginButtonElement.click();
	scn.log("User enter otp manually");
	//Thread.sleep(5000);
	
	
}

@Then("User successfully redirected to homepage {string}")
public void user_successfully_redirected_to_homepage(String SignInPageTitle) throws Exception {
  //Thread.sleep(3000);
	signUpPageObject.validateSignInPage(SignInPageTitle);
homePageObject.capturePassScreenshot(scn);

}

//=============================Search===================================
@When("User doubleclick  in search box")
public void user_doubleclick_in_search_box() throws Exception {
WebElement search=driver.findElement(By.xpath("//input[@id='search']"));

   Actions act=new Actions(driver);

     act.doubleClick(search).build().perform();
      
}

@Then("User see the popular search box is appear")
public void user_see_the_popular_search_box_is_appear() {
   homePageObject.capturePassScreenshot(scn);
}

//================================================@Searchprod1===================================



@When("User navigated to the home application url")
public void user_navigated_to_the_home_application_url() {
	homePageObject.vaildateLandingPageTitle(base_url);
	
	//scn.log("User navigated to the landing page of the application");

}



@When("User Search for product on search bar {string}")
public void user_search_for_product_on_search_bar(String prodName) throws Exception {

  homePageObject.searchProduct(prodName);

    //Thread.sleep(3000);


}
@Then("Search Result page is displayed with title contains {string}")
public void search_result_page_is_displayed_with_title_contains(String prodNameInTitle1) {
    homePageObject.validateSearchResult(prodNameInTitle1);
    
    }

//================================ @productlistpage============================================


@When("User mousehover on Earring option")
public void user_mousehover_on_earring_option() {
	productListingObject.mouseOverValidation();
	 
}

@When("User see the various catagory on screen")
public void user_see_the_various_catagory_on_screen(List<String> subcategory) {
	
	productListingObject.subCategoryValidation(subcategory);
	
}

@When("User select Shop by Material Gold and click on it")
public void user_select_shop_by_material_gold_and_click_on_it() {
	WebElement subMenuOption = driver.findElement(By.xpath("//a[@href='https://www.candere.com/jewellery/gold-earrings.html']")); 
	subMenuOption.click();
	logger.info("Various types of gold earring is display");
	 scn.log("Various types of gold earring is display");
}
@Then("The product listing page is displayed")
public void the_product_list_page_is_displayed1() {
	
	productListingObject.productListingPageTitleValidation();
}


//===================================
@When("User click on pendants on landing page")
public void user_click_on_pendants_on_landing_page() throws Exception {
	   WebElement pendants = driver.findElement(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[4]/a"));
	    pendants.click();
	   
}


@When("User able to click on more filter")
public void user_able_to_click_on_more_filter() {
	WebElement morefilter=driver.findElement(By.xpath("//dt[normalize-space()='MORE FILTER']"));
       morefilter.click();
}

@When("User select the any combination of product as per there choice")
public void user_select_the_any_combination_of_product_as_per_there_choice() throws Exception {
WebElement price=driver.findElement(By.xpath("//dt[@class='filter-options-title price active']"));
       price.click();

WebElement price1=driver.findElement(By.xpath("//div[@id='price']//li[contains(@data-label,'₹40,000.00 - ₹50,000.00')]"));
       price1.click();

WebElement rating=driver.findElement(By.xpath("//dt[@class='filter-options-title rating']"));
       rating.click();

WebElement rating1=driver.findElement(By.xpath("//*[@id=\"rating\"]/form/ol/li[1]"));
       rating1.click(); 
      
}

@Then("User are able to see the combination of product")
public void user_are_able_to_see_the_combination_of_product() throws Exception {
	 WebElement apply=driver.findElement(By.xpath("//div[@class='filter_btn_ apply__']"));
      apply.click();
      
    homePageObject.capturePassScreenshot(scn);
      Thread.sleep(3000);
}

//============================

@When("User scrolldown to the bootom of the page")
public void user_scrolldown_to_the_bootom_of_the_page() throws Exception {

JavascriptExecutor js=((JavascriptExecutor) driver);
//js.executeScript("arguments[0].scrollIntoView;", Loadmore1);
	   
//	   JavascriptExecutor js = (JavascriptExecutor) driver;
//	  // js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//	   
js.executeScript("window.scrollBy(0,2000)","");
js.executeScript("window.scrollBy(0,1000)","");
js.executeScript("window.scrollBy(0,1200)","");

Thread.sleep(4000);

}

@Then("User click on load more button")
public void user_click_on_load_more_button() throws Exception {
	 WebElement Loadmore1 = driver.findElement(By.xpath("(//div[@class='amscroll-load-button'])[1]"));
	    
		Loadmore1.click();
		Thread.sleep(4000);
		
		homePageObject.capturePassScreenshot(scn);
}

//================================================== @FilterBy================================
@When("User click on treading on landing page")
public void user_click_on_treading_on_landing_page() throws Exception {
	WebElement treading=driver.findElement(By.xpath("//*[@id=\"store.links\"]/nav[1]/div/div/div/div/ul/li[1]/a"));
	treading.click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("window.scrollBy(0,250)", "");
	   
	  // Thread.sleep(5000);

}

@When("User click on filter by price criteria")
public void user_click_on_filter_by_price_criteria() {
	WebElement Relevance=driver.findElement(By.xpath("//*[@id=\"sorter\"]/li[1]/a/span"));
	Relevance.click();
	
	WebElement Lowtohigh=driver.findElement(By.xpath("//*[@id=\"sorter\"]/li[3]/a/span"));
	Lowtohigh.click();
	
	WebElement Hightolow=driver.findElement(By.xpath("//*[@id=\"sorter\"]/li[4]/a/span"));
	
	Hightolow.click();
	
	
}



@When("User opened with treading url {string}")
public void user_opened_with_treading_url(String url) throws Exception {
	
productListingObject.validationofTrandingUrl(url);

}


@Then("Size of filter by category should be {int}")
public void size_of_filter_by_category_should_be(int filtercount) {
	
	List <WebElement> filterbyList1 =driver.findElements(By.xpath("//dl[@id='narrow-by-list']/div/dt"));

	Assert.assertEquals(filtercount, filterbyList1.size());
	
  logger.info("validate the Size of filter by category, size is: "+ filterbyList1.size());
 	   scn.log("validate the Size of filter by category, size is: "+ filterbyList1.size());

}

//=========================================@plpexpressdelivery================================

@When("User click on express delivery")
public void user_click_on_express_delivery() throws Exception {

	productListingObject.expressdeliveryValidation();

}


//================================ @plpmetalcolourchange ==============================
@When("User mouse over to background colour")
public void user_mouse_over_to_background_colour() {
WebElement bg =driver.findElement(By.xpath("//*[@id=\"amasty-shopby-product-list\"]/div[2]/ol/li[1]/div/div/div[1]/div/div[2]/div[2]/a[1]/span"));
Actions act=new Actions(driver);
act.moveToElement(bg).build().perform();
}


@Then("User click on change background like gold and rosegold")
public void user_click_on_change_background_like_gold_and_rosegold() throws Exception {
	WebElement bgcolour =driver.findElement(By.xpath("(//a[@class='metal-color'])[2]"));
	bgcolour.click();
	Thread.sleep(2000);
	homePageObject.capturePassScreenshot(scn);
}

//===================================================


@When("User are able to filter by product type pendants as per choice")
public void user_are_able_to_filter_by_product_type_pendants_as_per_choice() throws Exception {
WebElement producttype = driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[2]/dt"));
	
	Actions act= new Actions(driver);
	act.moveToElement(producttype).build().perform();
	
List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"narrow-by-list\"]/div[2]/dd/form/ol/li"));

for(int i=0;i<options.size();i++)
{

	String ptype=options.get(i).getText();

	if(ptype.contains("Pendant"));
	{
		
       options.get(i).click();	
		
		break;
	}
	
	}


}


@Then("User see the Currently Shopping by filter")
public void user_see_the_currently_shopping_by_filter() throws Exception {
	Thread.sleep(5000);
homePageObject.capturePassScreenshot(scn);


WebElement clearall=driver.findElement(By.xpath("//*[@id=\"am-shopby-container\"]/ol/li[2]/a/span"));

clearall.click();
//Thread.sleep(2000);

homePageObject.capturePassScreenshot(scn);

}

//===================================================PDP Page==============================================================



@When("User mousehover on Ring option")
public void user_mousehover_on_ring_option() {
    productDescObject.mouseOverringValidation();
}


@When("User see the various ringsubcatagory on screen")
public void user_see_the_various_ringsubcatagory_on_screen(List<String> ringsubcategory) {
    
	productDescObject.subCategoryringValidation(ringsubcategory);
	
}


@When("User select Shop by Material Diamond and click on it")
public void user_select_shop_by_material_diamond_and_click_on_it() {
	WebElement diamond = driver.findElement(By.xpath("//a[@href='https://www.candere.com/jewellery/diamond-rings.html'][normalize-space()='Diamond']")); 
	diamond.click();
	logger.info("Various types of diamond ring is display");
	 scn.log("Various types of diamond ring is display");
}
@When("Product listing page is displayed")
public void product_listing_page_is_displayed() {

productDescObject.productListPageTitleValidation();
}
@When("User click on any of the product")
public void user_click_on_any_of_the_product() {

	List<WebElement> firstProd = driver.findElements(By.xpath("//*[@id=\"amasty-shopby-product-list\"]/div[2]/ol/li"));
	// But as this step asks click on any link, we can choose to click on Index 0 of
	// the list
	firstProd.get(0).click();
	 //Thread.sleep(3000);
	logger.info("Product description page is display");
	scn.log("User clicked on a product");
}
@Then("Product Description is displayed in new tab")
public void product_description_is_displayed_in_new_tab() {
   productDescObject.productdescPageValidation();
   homePageObject.capturePassScreenshot(scn);
}


//=================================@pdpPageNotifyMe=================================




@When("User click on any product type earring")
public void user_click_on_any_product_type_earring() {
	List<WebElement> firstProd = driver.findElements(By.xpath("//ol[@class=\"products list items product-items\"]//li//div"));
	
	firstProd.get(0).click();
	 //Thread.sleep(3000);
	logger.info("Product description page is display");
	scn.log("User clicked on a product");
}

@When("User click on EXPLORE DGRP functionality")
public void user_click_on_explore_dgrp_functionality() {
	WebElement exploredgrp =driver.findElement(By.xpath("//span[@class='view_details show_priceBreakup explore_dgrp']"));
	exploredgrp.click();
}
@When("User checks the PLANS and TERMS & CONDITIONS")
public void user_checks_the_plans_and_terms_conditions() {
	WebElement plans =driver.findElement(By.xpath("//div[@class='grrp_tab_item']"));
	plans.click();
	
	homePageObject.capturePassScreenshot(scn);
	
}
@When("Close the EXPLORE DGRP window")
public void close_the_explore_dgrp_window() {
	WebElement close =driver.findElement(By.xpath("//div[@id='popupcloseBTN']//img[@alt='close']"));
	close.click();
}
@When("After that User click on Notify me when Price Drop")
public void after_that_user_click_on_notify_me_when_price_drop() {
	WebElement NotifyMe =driver.findElement(By.xpath("//a[normalize-space()='Notify me']"));
	NotifyMe.click();
}
@When("User enters  {string}  and  {string}")
public void user_enters_and1(String EmailId, String MobileNO) {
	WebElement Emailid =driver.findElement(By.xpath("//div[@class='input_group_ mb_25_']//input[@name='email']"));
	Emailid.sendKeys(EmailId);
	//Thread.sleep(3000);
	WebElement Mobile =driver.findElement(By.xpath("//input[@name='telephone']"));
	Mobile.sendKeys(MobileNO);
}
@When("User click on Notify me")
public void user_click_on_notify_me() {
	WebElement NotifyMe1 =driver.findElement(By.xpath("//a[@id='notifyme_submit']"));
	NotifyMe1.click();
}
@Then("User see the Thank U messagepopup and click on ok button")
public void user_see_the_thank_u_messagepopup_and_click_on_ok_button() {
    homePageObject.capturePassScreenshot(scn);
}

//===========================================@pdpPageViewDetails==============================================


@When("User click on View Details")
public void user_click_on_view_details() throws Exception {
	WebElement viewdetails =driver.findElement(By.xpath("//a[normalize-space()='View Details']"));
	viewdetails.click();
	Thread.sleep(3000);
}


@When("User scroll down to view detail")
public void user_scroll_down_to_view_detail() {
	js= (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,350)", "");
}
@When("User click on Metal details and Product Details")
public void user_click_on_metal_details_and_product_details() throws Exception {
	WebElement metal =driver.findElement(By.xpath("//div[@class='item title']"));
	metal.click();
	//Thread.sleep(3000);
	
	WebElement gemstone =driver.findElement(By.xpath("//div[@class='item title']"));
	gemstone.click();
	//Thread.sleep(3000);
}
@Then("User go back to up")
public void user_go_back_to_up() throws Exception {
	WebElement back =driver.findElement(By.xpath("//a[@id='back-to-top']"));
	back.click();
	Thread.sleep(3000);
}

//=====================================================

@When("User select the ring size")
public void user_select_the_ring_size() {
	WebElement ringsizedropdown =driver.findElement(By.xpath("//select[@id='mt_size']"));
	Select size=new Select(ringsizedropdown);
	size.selectByValue("25");
//	Thread.sleep(3000);
//	size.selectByIndex(4);
//	Thread.sleep(3000);
//	size.selectByVisibleText("10");
//	Thread.sleep(3000);
}

@When("User enters {string} and click on Check button")
public void user_enters_and_click_on_check_button(String pincode) throws Exception {
	WebElement pin = driver.findElement(By.xpath("//input[@id='eedpincode']"));
	pin.sendKeys(pincode);	
	WebElement checkbutton = driver.findElement(By.xpath("//input[@id='check-eed']"));
	checkbutton.click();
	Thread.sleep(3000);
}
@When("User see the Expected Delivery Date")
public void user_see_the_expected_delivery_date() {
	WebElement e=driver.findElement(By.xpath("//span[@class='productDeliveryDateMsg edd']"));  
	 String s1 = e.getText();
	 scn.log(s1);
    System.out.println("Alert text is: " + s1); 
  
}

@When("User click on add to cart and see the cart page")
public void user_click_on_add_to_cart_and_see_the_cart_page()  {
	WebElement addtocart = driver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
	addtocart.click();
	
	String cartPageTitle= "Shopping Cart";
	WebDriverWait wait = new WebDriverWait(driver, 30);
	boolean p =wait.until(ExpectedConditions.titleIs(cartPageTitle));
	Assert.assertEquals(true, p);
	logger.info("Validate product list page with its title, title is: "+ cartPageTitle);
	scn.log("navigate to product list page , page title is: "+ cartPageTitle);

	scn.log("Add to cart button is displayed");
    logger.info("Add to cart button is displayed");
}

//===========================cart===================================================



@When("On cart page user is able to change the product qty and and size")
public void on_cart_page_user_is_able_to_change_the_product_qty_and_and_size() throws InterruptedException {
	WebElement dropdownsize =driver.findElement(By.xpath("//select[@class='mt_size']"));
	Select select=new Select(dropdownsize);
	select.selectByValue("25");
	
	//Thread.sleep(3000);
	
	WebElement dropdownqty =driver.findElement(By.xpath("//select[@class='qty custom-qty']"));
	Select select1=new Select(dropdownqty);
	select1.selectByValue("2");
	Thread.sleep(5000);
//	WebElement checkout3 =driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[7]/div[2]/div[2]/button"));
//	checkout3.click();
	//Thread.sleep(18000);
}




@When("user click on checkout logIn pop is open")
public void user_click_on_checkout_log_in_pop_is_open() throws Exception {
	WebElement checkout = driver.findElement(By.xpath("//span[normalize-space()='Checkout Securely']"));
	checkout.click();
	Thread.sleep(3000);
	homePageObject.capturePassScreenshot(scn);
}

//==================================================

@When("User search for {string}")
public void user_search_for(String prodName) throws Exception {
	homePageObject.searchProduct(prodName);
Thread.sleep(3000);
}

@When("choose to buy the first item")
public void choose_to_buy_the_first_item() throws Exception {
	
	List<WebElement> firstProd = driver.findElements(By.xpath("//*[@id=\"amasty-shopby-product-list\"]/div[2]/ol/li"));
	// But as this step asks click on any link, we can choose to click on Index 0 of
	// the list
	firstProd.get(0).click();
	 Thread.sleep(3000);
	 logger.info("Clicked on First product link");
	
	scn.log("Clicked on First product link");
	
	Set<String> handles = driver.getWindowHandles(); // get all the open windows
	Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
	String original = it.next();// gives the parent window id
	String prodDescp = it.next();// gives the child window id

	driver.switchTo().window(prodDescp); // switch to product Descp

    logger.info("driver is switched back to prodDescp window");
	
	


}
@When("Add product to shopping cart")
public void add_product_to_shopping_cart() throws InterruptedException {
	WebElement addtocart = driver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
	addtocart.click();
	scn.log("Add to cart button is displayed");
    logger.info("Add to cart button is displayed");
	Thread.sleep(7000);

}
@When("User clicks on continue shopping")
public void user_clicks_on_continue_shopping() throws Exception {
	WebElement continueshopping = driver.findElement(By.xpath("//span[normalize-space()='Continue Shopping']"));
	JavascriptExecutor js=((JavascriptExecutor) driver);
    js.executeScript("arguments[0].scrollIntoView(true);", continueshopping);
	continueshopping.click();
	logger.info("Clicked on continue shopping button");
	Thread.sleep(5000);
}
@When("User clicks on My shopping bag and view shopping bag")
public void user_clicks_on_my_shopping_bag_and_view_shopping_bag() throws Exception {
	WebElement minibag = driver.findElement(By.xpath("//div[@class='link_items minicart-wrapper']"));
	Actions act =new Actions(driver);
	act.moveToElement(minibag).build().perform();
	WebElement viewshoppingbag = driver.findElement(By.xpath("//a[@class='action viewcart']"));
	viewshoppingbag.click();
	logger.info("Clicked on shopping bag button");

}
@When("Click on proceed to check out")
public void click_on_proceed_to_check_out() throws InterruptedException {
	Thread.sleep(3000);
WebElement checkout1 = driver.findElement(By.xpath("//button[@title='Checkout Securely']"));
checkout1.click();
logger.info("Clicked on proceed to checkout button");
	Thread.sleep(5000);
	
}
@Then("User should be asked to login before checkout")
public void user_should_be_asked_to_login_before_checkout() throws Exception {
//Thread.sleep(3000);
String checkOutPageTitle= "Shopping Cart";
	WebDriverWait wait = new WebDriverWait(driver, 50);
	boolean p =wait.until(ExpectedConditions.titleIs(checkOutPageTitle));
	Assert.assertEquals(true, p);
	logger.info("Validate checkout page with its title, title is: "+ checkOutPageTitle);
	scn.log("navigate to checkout page , page title is: "+ checkOutPageTitle);

	homePageObject.capturePassScreenshot(scn);

}


//======================================orderjourney -@e2esignupcheckout===========================


@When("User search for the  {string}")
public void user_search_for_the(String prodName) {
	homePageObject.searchProduct(prodName);
}


@When("choose to buy the first item in list")
public void choose_to_buy_the_first_item_in_list() throws Exception {
	List<WebElement> firstProd = driver.findElements(By.xpath("//*[@id=\"amasty-shopby-product-list\"]/div[2]/ol/li"));
	// But as this step asks click on any link, we can choose to click on Index 0 of
	// the list
	firstProd.get(0).click();
	 Thread.sleep(3000);
	logger.info("Product description page is display");
	scn.log("User clicked on a product");
	
	Set<String> handles = driver.getWindowHandles(); // get all the open windows
	Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
	String original = it.next();// gives the parent window id
	String prodDescp = it.next();// gives the child window id

	driver.switchTo().window(prodDescp);
}
@When("Add first product to the shopping cart")
public void add_first_product_to_the_shopping_cart() throws Exception {
	WebElement addtocart = driver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
	addtocart.click();
	
	scn.log("Add to cart button is displayed");
logger.info("Add to cart button is displayed");
	Thread.sleep(10000);
}
@When("User click on Checkout Securely")
public void user_click_on_checkout_securely() throws Exception {
//WebElement checkoutSecurely = driver.findElement(By.xpath("(//button[@title='Checkout Securely'])[1]"));
WebElement checkoutSecurely =new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@title='Checkout Securely'])[1]")));
Actions action =new Actions(driver);
action.moveToElement(checkoutSecurely).click().build().perform();


checkoutSecurely.click();
Thread.sleep(15000);
scn.log("Checkout Securely button is displayed");
logger.info("Checkout Securely button is displayed");
	

}
@When("User are able to see the signup and login popup and click on signup")
public void user_are_able_to_see_the_signup_and_login_popup_and_click_on_signup() {
	WebElement  signupcheckout = driver.findElement(By.xpath("//span[normalize-space()='Sign up']"));
	 signupcheckout.click();
	 
}
@When("Shipping page is open then user fill all details {string}  and  {string} and {string} and {string}  and  click on continue")
public void shipping_page_is_open_then_user_fill_all_details_and_and_and_and_click_on_continue(String lastname, String mobile, String zip, String flat ) throws Exception {

	WebElement Lastname =driver.findElement(By.xpath("(//input[@name='lastname'])[1]"));
	Lastname.sendKeys(lastname);
	
	WebElement MobileNumber =driver.findElement(By.xpath("(//input[@name='telephone'])[1]"));
	MobileNumber.sendKeys(mobile);
	
	WebElement ZipPostalCode =driver.findElement(By.xpath("(//input[@name='postcode'])[1]"));
	ZipPostalCode .sendKeys(zip);
	
	Thread.sleep(4000);
	
	WebElement Flat =driver.findElement(By.xpath("(//input[@name='street[0]'])[1]"));
	Flat .sendKeys(flat );
	
	
	Thread.sleep(3000);
}
@When("User see the payment page and click on UPI")
public void user_see_the_payment_page_and_click_on_upi() throws Exception {
	WebElement continue11 =new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='action primary']")));
	Actions action =new Actions(driver);
	action.moveToElement(continue11).click().build().perform();
    continue11.click();
    
    Thread.sleep(5000);
	
	WebElement upi = driver.findElement(By.xpath("//*[@id=\"payment_section_payment_page\"]/div[2]/div/div/div[1]/div[4]"));
	upi.click();
	Thread.sleep(5000);
	
}
@When("User enter {string} and click on placeorder")
public void user_enter_and_click_on_placeorder(String UPIId) throws Exception {
	WebElement UPIID = driver.findElement(By.xpath("//input[@id='vpa']"));
	UPIID.sendKeys(UPIId);
	Thread.sleep(3000);
}
@Then("User see the cander Justpay page")
public void user_see_the_cander_justpay_page() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}


}





//
//
//
//@When("User search for the  {string}")
//public void user_search_for_the(String prodName) {
//	homePageObject.searchProduct(prodName);
//}
//
//@When("choose to buy the first item in list")
//public void choose_to_buy_the_first_item_in_list() throws Exception {
//	List<WebElement> firstProd = driver.findElements(By.xpath("//*[@id=\"amasty-shopby-product-list\"]/div[2]/ol/li/div"));
//	// But as this step asks click on any link, we can choose to click on Index 0 of
//	// the list
//	firstProd.get(0).click();
//	 //Thread.sleep(3000);
//	 logger.info("Clicked on First product link");
//	
//	scn.log("Clicked on First product link");
//	
//	Set<String> handles = driver.getWindowHandles(); // get all the open windows
//	Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
//	String original = it.next();// gives the parent window id
//	String prodDescp = it.next();// gives the child window id
//
//	driver.switchTo().window(prodDescp); // switch to product Descp
//	 Thread.sleep(3000);
//
//    logger.info("driver is switched back to prodDescp window");
//}
//@When("Add first product to the shopping cart")
//public void add_first_product_to_the_shopping_cart() throws Exception {
//	WebElement addtocart = driver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
//	addtocart.click();
//	scn.log("Add to cart button is displayed");
//    logger.info("Add to cart button is displayed");
//	Thread.sleep(6000);
//}
//@When("User click on Checkout Securely")
//public void user_click_on_checkout_securely() throws Exception {
////	WebElement checkoutSecurely = driver.findElement(By.xpath("(//button[@title='Checkout Securely'])[1]"));
////	checkoutSecurely.click();
//	WebElement checkout = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[7]/div[2]/div[2]/button"));
//	checkout.click();
//	Thread.sleep(16000);
////	scn.log("Checkout Securely button is displayed");
////    logger.info("Checkout Securely button is displayed");
////	Thread.sleep(25000);
//}
//@When("User are able to see the signup and login popup and click on signup")
//public void user_are_able_to_see_the_signup_and_login_popup_and_click_on_signup() {
//	scn.log("Login/Signup is displayed");
//    logger.info("Login/Signup is displayed");
//    WebElement Signup = driver.findElement(By.xpath("//span[normalize-space()='Sign up']"));
//	Signup.click();
//}
//@When("Shipping page is open then user fill all details {string}  and  {string} and {string} and {string}  and  click on continue")
//public void shipping_page_is_open_then_user_fill_all_details_and_and_and_and_click_on_continue(String string, String string2, String string3, String string4) {
//    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
//}
//@When("User see the payment page and click on UPI")
//public void user_see_the_payment_page_and_click_on_upi() {
//    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
//}
//@When("User enter {string} and click on placeorder")
//public void user_enter_and_click_on_placeorder(String string) {
//    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
//}
//@Then("User see the cander Justpay page")
//public void user_see_the_cander_justpay_page() {
//    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
//}
//
//
//
//
//
//
//
//
//
//
//}