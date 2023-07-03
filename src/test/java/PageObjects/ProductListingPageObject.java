package PageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class ProductListingPageObject {
	
	

	private static final Logger logger= LogManager.getLogger(ProductListingPageObject.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	
	//============= Locators for WebElements for SignIn activity =========================================//

		//private By yourAccount= By.xpath("//div[@class='link_items actions-custom customer-links']");
		

	//============= Constructor ========================================================================//
		public ProductListingPageObject(WebDriver driver,Scenario scn)
		{
			this.driver= driver;
			this.scn=scn;
		}
		
	//=================== 1. Method to validate Mouse over=================================================//
	    public void mouseOverValidation()
	    {
	    	WebElement earring=driver.findElement(By.xpath("//a[@class='parent_'][normalize-space()='Earrings']"));
			Actions act=new Actions (driver);
			act.moveToElement(earring).perform();
			logger.info("Done Mouse hover on 'Earring' from Menu");
		    scn.log("Done Mouse hover on 'Earring' from Menu");
	    }
	    
	    public void subCategoryValidation(List<String> subcategory)
	    {
	    List<WebElement> subCategoryElement = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav/div/div/div/div/ul/li[3]/div/div/div[1]/div/p"));

		for (int i = 0; i < subcategory.size(); i++) {
			if (subcategory.get(i).equals(subCategoryElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
		}}
	    
	    
	    public void productListingPageTitleValidation()
	    {

	    	String productlistPageTitle= "Latest Designs of Gold Earrings Online - Candere by Kalyan Jewellers";
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
	    	boolean p =wait.until(ExpectedConditions.titleIs(productlistPageTitle));
	    	Assert.assertEquals(true, p);
	    	logger.info("Validate product list page with its title, title is: "+ productlistPageTitle);
	    	scn.log("navigate to product list page , page title is: "+ productlistPageTitle);
	    
	    
	    }

		
	    public void validationofTrandingUrl(String url)
	    {

	    	WebElement treadingAcLinkElement =driver.findElement(By.xpath("//link[@href=\"https://www.candere.com/trending/hot.html\"][1]"));
	    	
	    	Assert.assertEquals(url, treadingAcLinkElement.getAttribute("href"));
	    	logger.info("Validate treading url is: "+treadingAcLinkElement.getAttribute("href"));
	       scn.log("Validate treading url  is: "+treadingAcLinkElement.getAttribute("href"));
	    	
	       JavascriptExecutor js = (JavascriptExecutor) driver;
	       js.executeScript("window.scrollBy(0,250)", "");
	      
	    }
	    
	    
	    

		public void expressdeliveryValidation() {
			// TODO Auto-generated method stub
			WebElement expressdelivery =driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[3]/dt"));
	    	expressdelivery.click();
//	    	String productlistPageTitle1= "Buy Hot Designs Online in India | Candere by Kalyan Jewellers";
//	    	WebDriverWait wait = new WebDriverWait(driver, 30);
//	    	boolean p =wait.until(ExpectedConditions.titleIs(productlistPageTitle1));
//	    	Assert.assertEquals(true, p);
//	    	logger.info("Validate product list page with its title, title is: "+ productlistPageTitle1);
//	    	scn.log("navigate to product list page , page title is: "+ productlistPageTitle1);
	    
		}
	      
	    
	    
	    
}   
	    
