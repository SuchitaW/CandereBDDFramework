package PageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class HomePageObjects {
	
	
	private static final Logger logger= LogManager.getLogger(HomePageObjects.class);
	WebDriver driver;
	Scenario scn;
	WebDriverWait wait;
	JavascriptExecutor js;

//============= Locators for WebElements on Landing page============================================//
	
	private By elementSearchBoxElement = By.xpath("//input[@id='search']");
    private By searchButtonElement = By.xpath("//button[@title='Search']");

	private By logoImage= By.xpath("//img[@title='Candere']");
	private By prodCatgory= By.xpath("//div[@class='header_top_menu']//li");
	
//============= Expected Results ====================================================================//
	
	String expCurrentURL= "https://www.candere.com/";
	
//============= Constructor ========================================================================//
	public HomePageObjects(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	

	//============ 1. Method to validate page URL =======================================================//
	
	//Capture screenshot if test case get passed
	public void capturePassScreenshot(Scenario scn) {
			
			TakesScreenshot srnshot= ((TakesScreenshot)driver);
			byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of passed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get passed");	
		}
	
	
	public void validatePageURL()
	{
		wait= new WebDriverWait(driver,20);
		boolean a = wait.until(ExpectedConditions.urlToBe(expCurrentURL));
		Assert.assertEquals(true,a);
	    logger.info("validate current URL of page ,so URL is: "+ driver.getCurrentUrl());
	}
	
//============ 2. Method to validate page Title =======================================================//
    public void validatePageTitle(String pageTitle)
    {
    	String actPageTitle= driver.getTitle();
    	Assert.assertEquals(pageTitle, actPageTitle);
        logger.info("Validate title of page, title is:" + actPageTitle);
    }
    
 //============ 3. Method to display a logo =============================================================//
    public void displayLogo()
    {
    	WebElement logo =driver.findElement(logoImage);
    	Assert.assertEquals(true, logo.isDisplayed());
    	logger.info("Display the logo on landing page");
    }
    
//============ 4. Method to fetch logo height =======================================================//   
    public void fetchLogoHeight()
    {
    	WebElement logo =driver.findElement(logoImage);
    	String logoHeight= logo.getAttribute("height");
    	logger.info("Height of logo is: "+ logoHeight);
    	scn.log("Height of logo is: "+ logoHeight);
    }
    
//============ 5. Method to validate logo height =======================================================//   
    public void logoHeightValid(String height)
    {
    	WebElement logo =driver.findElement(logoImage);
    	Assert.assertEquals(height, logo.getAttribute("height"));
    	logger.info("Validate the height of logo");
    	scn.log("Validate the height of logo");
    }
   
//============ 6. Method to fetch logo width =======================================================//   
    public void fetchLogoWidth()
    {
    	WebElement logo =driver.findElement(logoImage);
    	String logoWidth= logo.getAttribute("width");
    	logger.info("Width of logo is: "+ logoWidth);
    	scn.log("Width of logo is: "+ logoWidth);
    }
 
//============ 7. Method to validate logo width =======================================================//   
    public void logoWidthValid(String width)
    {
    	WebElement logo =driver.findElement(logoImage);
    	Assert.assertEquals(width, logo.getAttribute("width"));
    	logger.info("Validate the width of logo");
    	scn.log("Validate the width of logo");
    }

 //================================== 8.Method to validate ProductCategoryList ===============================================================//

    public void setProdCategory()
    {
    	List <WebElement> prodCategoryList =driver.findElements(prodCatgory);
    	Assert.assertEquals(false, prodCategoryList.isEmpty());
    	logger.info("Display the product category list, size of list is: "+ prodCategoryList.size());
    	scn.log("Product category is displayed on page with size is: "+ prodCategoryList.size());
    }

    public void validateProdCategory(List<String> prodList1)
    {
    	List <WebElement> prodCategoryList =driver.findElements(prodCatgory);
    	{
    		for(int i=0; i< prodCategoryList.size(); i++)
    		{
    			if(prodCategoryList.get(i).getText().equals(prodList1.get(i).toString()))
        		{
        			Assert.assertTrue(true);
        			logger.info(prodCategoryList.get(i).getText()+ " is matched with expected "+ prodList1.get(i).toString()+" product name in datatable");
        		}
    			else
    			{
    				Assert.fail();
        			logger.fatal(prodCategoryList.get(i).getText()+ " is not matched with expected "+ prodList1.get(i).toString()+" product name in datatable");
    			}
    		}
    		logger.info("Validate the product category with expected datatable");
    		scn.log("Validate the product category with expected datatable");
    	}
    }
  
    public void sizeOfProdCategory(int prodCount)
    {
 	   List <WebElement> prodCategoryList =driver.findElements(prodCatgory);
 	   Assert.assertEquals(prodCount, prodCategoryList.size());
 	   logger.info("validate the Size of product category, size is: "+ prodCategoryList.size());
 	   scn.log("validate the Size of product category, size is: "+ prodCategoryList.size());
    }
    
    //================================== 8.Method to validate Signup&Spin! ========================================   
    
    public void validateScrollSignUpSpin(){
	    {
	    	js= (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,350)", "");
            
	    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    	
	    }	
	    } 
    
	   
	   
		    public void clickOnSignUpSpin() throws Exception{
			    {
			    	
		            WebElement SignUpSpin =driver.findElement(By.xpath("//a[normalize-space()='Sign up & Spin!']"));
		            SignUpSpin.click();
		            //Thread.sleep(3000);
			    	//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			    	logger.info("Clicked on Sign up & Spin! button");
			    	
			    } 
	    
		    }
     
		    public void validateSignUpandSpinPageTitle()
		    
		 {
		    	String expected ="Create New Customer Account";
		    	String actPageTitle= driver.getTitle();
		    	
		    	Assert.assertEquals(expected, actPageTitle);
		        logger.info("Validate title of page, title is:" + actPageTitle);
		        scn.log("Title of the page is: "+ actPageTitle);
		       
		    }
	
		    
		  //================================== 8.Method to validate ProductCategoryList ===============================================================//

		    public void setProdCategoryList()
		    {
		    	js= (JavascriptExecutor)driver;
	            js.executeScript("window.scrollBy(0,450)", "");
	            
		    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    	
		    	List <WebElement> prodCategoryList1 =driver.findElements(By.xpath("//p[@class='categories__list--title']"));
		    	Assert.assertEquals(false, prodCategoryList1.isEmpty());
		    	logger.info("Display the product category list, size of list is: "+ prodCategoryList1.size());
		    	scn.log("Product category is displayed on page with size is: "+ prodCategoryList1.size());
		    }

		    public void validateProdCategorylist(List<String> prodList)
		    {
		    	List <WebElement> prodCategoryList1 =driver.findElements(By.xpath("//p[@class='categories__list--title']"));
		    	{
		    		for(int i=0; i< prodCategoryList1.size(); i++)
		    		{
		    			if(prodCategoryList1.get(i).getText().equals(prodList.get(i).toString()))
		        		{
		        			Assert.assertTrue(true);
		        			logger.info(prodCategoryList1.get(i).getText()+ " is matched with expected "+ prodList.get(i).toString()+" product name in datatable");
		        		}
		    			else
		    			{
		    				Assert.fail();
		        			logger.fatal(prodCategoryList1.get(i).getText()+ " is not matched with expected "+ prodList.get(i).toString()+" product name in datatable");
		    			}
		    		}
		    		logger.info("Validate the product category with expected datatable");
		    		scn.log("Validate the product category with expected datatable");
		    	}
		    }
		  
		    public void clickOnAnyProdCategory()
		    {
		 	   
		    	WebElement Prodcat = driver.findElement(By.xpath("//img[@alt='Gorgeous Bracelets']"));
		    	Prodcat.click();		    	
		    	WebDriverWait wait = new WebDriverWait(driver, 30);
		    	logger.info("Product Listing page is display");
		    	scn.log("User clicked on a any product");
		    }	
		    	
		    public void validateProductListPageTitle() {
		    
		    String productlistPageTitle= "Buy Bracelets Online in India I Bracelets Designs @ Best Price | Candere by Kalyan Jewellers";

			WebDriverWait wait = new WebDriverWait(driver, 30);
			boolean p =wait.until(ExpectedConditions.titleIs(productlistPageTitle));
			Assert.assertEquals(true, p);
			logger.info("Validate product list page with its title, title is: "+ productlistPageTitle);
			scn.log("navigate to product list page , page title is: "+ productlistPageTitle);
		    
		    
		    }

		    public void scrolldownShowMeMore()
		    {
		 	   
		    	js= (JavascriptExecutor)driver;
	            js.executeScript("window.scrollBy(0,650)", "");
		    	
		    }	
		    
		    public void clickOnShowMeMore()
		    {
		 	   
		    	WebElement showMeMore = driver.findElement(By.xpath("//a[normalize-space()='Show Me More']"));
		    	showMeMore.click();		    	
		    	WebDriverWait wait = new WebDriverWait(driver, 30);
		    	logger.info("More Collection page is display");
		    	scn.log("User clicked on Show Me More");
		    }	
		    
		    
		    public void validateShowMeMorePageTitle() {
			    
			    String ShowMeMorePageTitle= "Candere Collections | Candere by Kalyan Jewellers";

				WebDriverWait wait = new WebDriverWait(driver, 30);
				boolean p =wait.until(ExpectedConditions.titleIs(ShowMeMorePageTitle));
				Assert.assertEquals(true, p);
				logger.info("Validate Show Me More page with its title, title is: "+ ShowMeMorePageTitle);
				scn.log("navigate to Show Me More page , page title is: "+ ShowMeMorePageTitle);
			    
			    
			    }
		    
		//=========================================    @Searchprod1=======================
		    
		    public void vaildateLandingPageTitle(String base_url)
		    {
		      logger.info("Browser invoked with URL as -> " + base_url);
		      String expected = "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store";
		      String actual =driver.getTitle();
		      Assert.assertEquals("Page Title validation",expected,actual);
		      scn.log("Base URL -> " + base_url + " title validation is successful");
		      logger.info("Base URL -> " + base_url + " title validation is successful");
		    }
		    
		    public void validateSearchResult(String  prodNameInTitle1) {
		    	//Wait for titile
				WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
				webDriverWait1.until(ExpectedConditions.titleIs("2k+ Rings - " + prodNameInTitle1));
				//2k+ Rings - Gold & Diamond Ring Designs for Men & Women - Candere by Kalyan Jewellers
				//Assertion for Page Title
				Assert.assertEquals("Page Title validation", "2k+ Rings - " + prodNameInTitle1, driver.getTitle());
				scn.log("Page title validation for 2k+ Rings -  "+ prodNameInTitle1 + " is successful");
				logger.info("Page title validation for Product name in title ->" + prodNameInTitle1 + " is successful");
			
		    }
	
		    public void searchProduct(String prodName) {
				
		    	wait= new WebDriverWait(driver,20);
	          WebElement elementSearchBox = wait.until(ExpectedConditions.elementToBeClickable(elementSearchBoxElement));
		    	elementSearchBox.sendKeys(prodName);
		    	logger.info("Sent the keys to search box as -> " + prodName);
	    	driver.findElement(searchButtonElement).click();
		    	logger.info("Clicked on search button");
						
				
			}



}





   

