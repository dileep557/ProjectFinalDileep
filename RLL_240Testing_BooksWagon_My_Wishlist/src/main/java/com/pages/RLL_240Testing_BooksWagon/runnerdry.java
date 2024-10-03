package com.pages.RLL_240Testing_BooksWagon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class runnerdry {
	
	public static void main(String[] args) throws InterruptedException {
        // Create an instance of the Login class
       // Login login = new Login();
//		
//		WebDriver driver = new FirefoxDriver();
//		
//		logdummy login= new logdummy();
//		 login.init1(driver);
//
//        // User credentials
//        String username = "7731960046"; // Replace with actual username
//        String password = "Dkyp12@@"; // Replace with actual password
//
//        // Log in with the provided credentials
//        login.Lanuch_BooksWagon();
//        login.login(username, password);

        // Click on the wishlist icon
        

        // Verify if the wishlist icon is clickable
//        WebDriver driver = login.driver; // Access the driver from Login class
//        WebElement wishlistIcon = driver.findElement(By.xpath("//label[@id='ctl00_lblWishlistCount']"));
//        
//        assertTrue("Wishlist icon is not displayed", wishlistIcon.isDisplayed());
//        assertTrue("Wishlist icon is not clickable", wishlistIcon.isEnabled());
//
//        // Close the driver
//        driver.quit();
		
		// TODO Auto-generated method stub
//		
//		WebDriver driver= new ChromeDriver();
//		
//		Login log= new Login();
//		log.init1(driver);
//		log.Lanuch_BooksWagon();
//		log.login("7731960046", "Bhavi@11");
//		log.myWishlist();
//		log.isclickable();
//		log.verifyOnWishlistPage();
		// System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
	        WebDriver driver = new ChromeDriver();
 
	        // Instantiate Page Objects
	        LoginPage loginPage = new LoginPage(driver);
	        MyWishlistPage wishlistPage = new MyWishlistPage(driver);
 
	        // Launch and login
	        loginPage.launchBooksWagon();
	        loginPage.login("9198473884", "Dkyp12@@");
 
	        // Scroll and click the 7th card
	        loginPage.scrollAndClickOn7thCard();
 
	        // Add to Wishlist
	        loginPage.addToWishlist();
 
	        // Verify if the wishlist icon is clickable and user is on the Wishlist page
	       // wishlistPage.clickMyWishlist();
	       // wishlistPage.isWishlistIconClickable();
	        wishlistPage.verifyOnWishlistPage();
	       // wishlistPage.makeWishlistPublic();
	        wishlistPage.clickAddButton();
	        wishlistPage.clickAllAddToCart();
	        wishlistPage.selectAllCheckbox();
	        wishlistPage.clickAllAddToCart();
            wishlistPage.clickSubButton();
	        wishlistPage.removeItemAndVerify();
 
	        // Close the driver
	        driver.quit();
 
	}
		
		
    }
	
	
	

	    

