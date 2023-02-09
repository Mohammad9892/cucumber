package stepDefinitions;

import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GreenKartStepDefinition {
public ChromeDriver driver;
public String landingPageProductName;
public String offerPageProductName;
	
	@Given("User is on GreenCart Landing page")
	public void user_is_on_green_cart_landing_page() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	    
	}
	@When("user searched with Shortname {string} and extracted actual name of product")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
		Thread.sleep(3000);
		landingPageProductName = driver.findElement(By.cssSelector("h4.product-name")).getText().split("-")[0].trim();
	    System.out.println(landingPageProductName +"is extracted from Home Page");
	    
	    
	}
	@Then("user search for the {string} shortname in offer page")
	public void user_search_for_the_same_shortname_in_offer_page_to_check_if_product_exist(String shortName) throws InterruptedException {
		
		driver.findElement(By.linkText("Top Deals")).click();
		Set<String> s1=  driver.getWindowHandles();
		java.util.Iterator<String> i1 = s1.iterator();
		String parentWindow = i1.next();
		String childWindow = i1.next();
		
		driver.switchTo().window(childWindow);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
		Thread.sleep(3000);
		offerPageProductName = driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();
	    
	}
	
	 @And("^validate product name in offers page with Landing Page$")
	    public void validate_product_name_in_offers_page_eith_landing_page() throws Throwable {
		 
		 Assert.assertEquals(offerPageProductName, landingPageProductName);
	        
	    }

}
