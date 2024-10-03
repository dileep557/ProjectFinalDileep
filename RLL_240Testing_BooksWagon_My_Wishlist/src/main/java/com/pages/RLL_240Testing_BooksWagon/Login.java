package com.pages.RLL_240Testing_BooksWagon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Login {
	
	         WebDriver driver;
	    
	     public void Credential(String username, String password) { 
	    	
	        
	        
	        driver = new ChromeDriver();
	 
	        
	        driver.get("https://www.bookswagon.com/"); 
	        
	        WebElement profileIcon = driver.findElement(By.xpath("//*[@id=\"aspnetForm\"]/header/div[2]/div/div[3]/ul/li[1]/a/span[1]/img"));

	       
	        Actions actions = new Actions(driver);

	        // Hover over the profile icon
	        actions.moveToElement(profileIcon).perform();
	        
	        WebElement Logint = driver.findElement(By.xpath("//div[@id='ctl00_divLogin']/a[@href='https://www.bookswagon.com/login']"));
	        
	        
	        Logint.click();
	        
	        
	        
	        
	      
	        WebElement usernameField = driver.findElement(By.xpath("//input[@id=\"ctl00_phBody_SignIn_txtEmail\"]")); // Replace with the actual ID
	        usernameField.sendKeys(username);

	     
	        WebElement passwordField = driver.findElement(By.xpath("//input[@id=\"ctl00_phBody_SignIn_txtPassword\"]")); // Replace with the actual ID
	        passwordField.sendKeys(password);

	      
	        WebElement loginButton = driver.findElement(By.xpath("//a[@id=\"ctl00_phBody_SignIn_btnLogin\"]")); // Replace with the actual ID
	        loginButton.click();

	     
	        try {
	            Thread.sleep(3000); 
	            
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    public void wishlistIcon() {
	        
	        WebElement wishlistIcon = driver.findElement(By.xpath(" //label[@id=\"ctl00_lblWishlistCount\"]")); // Replace with the actual ID
	        wishlistIcon.click();
	    }


	    public void isclickable() {
	        // Check if the wishlist icon is displayed and enabled
	        WebElement wishlistIcon = driver.findElement(By.xpath("//label[@id='ctl00_lblWishlistCount']"));
	        
	        if (wishlistIcon.isDisplayed()) {
	            System.out.println("Wishlist icon is displayed.");
	        } else {
	            System.out.println("Wishlist icon is not displayed.");
	        }
	        
	        if (wishlistIcon.isEnabled()) {
	            System.out.println("Wishlist icon is clickable.");
	        } else {
	            System.out.println("Wishlist icon is not clickable.");
	        }

	        // Close the driver after the test
	        driver.quit();
	    }
	
	
	   
		//String url = "https://www.bookswagon.com/";

	
		
	
	

}
