package PageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

public class ProdductDescPageObjects {

	


	private static final Logger logger= LogManager.getLogger(ProdductDescPageObjects.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	
	//============= Locators for WebElements for SignIn activity =========================================//

		private By yourAccount= By.xpath("//div[@class='link_items actions-custom customer-links']");
		

	//============= Constructor ========================================================================//
		public ProdductDescPageObjects(WebDriver driver,Scenario scn)
		{
			this.driver= driver;
			this.scn=scn;
		}
		
	//=================== 1. Method to validate Mouse over=================================================//
		 public void mouseOverringValidation()
		    {
		    	WebElement ring=driver.findElement(By.xpath("//a[@class='parent_'][normalize-space()='Rings']"));
				Actions act=new Actions (driver);
				act.moveToElement(ring).perform();
				logger.info("Done Mouse hover on 'Ring' from Menu");
			    scn.log("Done Mouse hover on 'Ring' from Menu");
		    }
		    
		    public void subCategoryringValidation(List<String> ringsubcategory)
		    {
		    List<WebElement> subCategoryElement = driver.findElements(By.xpath("//*[@id=\"store.links\"]/nav[1]/div/div/div/div/ul/li[2]/div/div/div[1]/div/p"));

			for (int i = 0; i < ringsubcategory.size(); i++) {
				if (ringsubcategory.get(i).equals(subCategoryElement.get(i).getText())) {
					Assert.assertTrue(true);
				}
				else
				{
					Assert.fail();
				}
			}}
		    
		    public void productListPageTitleValidation()
		    {

		    	String productlistPageTitle= "Designer Diamond Rings @ Best Price - Candere by Kalyan Jewellers";
		    	WebDriverWait wait = new WebDriverWait(driver, 30);
		    	boolean p =wait.until(ExpectedConditions.titleIs(productlistPageTitle));
		    	Assert.assertEquals(true, p);
		    	logger.info("Validate product list page with its title, title is: "+ productlistPageTitle);
		    	scn.log("navigate to product list page , page title is: "+ productlistPageTitle);
		    
		    
		    }

		    
		    
		    
		    public void productdescPageValidation()
		    {

		    	Set<String> handles = driver.getWindowHandles(); // get all the open windows
		    	Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
		    	String original = it.next();// gives the parent window id
		    	String prodDescp = it.next();// gives the child window id

		    	driver.switchTo().window(prodDescp); // switch to product Descp
		    
		    }

		    
		    
			
		    }
		    
		    
		    

	
	
	
	
	
	
	
	
	
	

