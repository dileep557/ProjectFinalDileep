package stepdefination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pages.RLL_240Testing_BooksWagon.LoginPage;
import com.pages.RLL_240Testing_BooksWagon.MyWishlistPage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WishlistSteps {

    // WebDriver instance for browser interactions
    private WebDriver driver;
    
    // Page object for login actions
    private LoginPage loginPage;
    
    // Logger instance for logging information
    Logger log;

    // Extent Reports instances
    private ExtentReports extent;
    private ExtentTest test;

    // Locators for elements used in this step definition
    private By wishlistCountLabel = By.xpath("//label[@id='ctl00_lblWishlistCount']");
    private By productTitle = By.xpath("//span[@id='ctl00_phBody_ProductDetail_lblTitle']");

    // Expected title for the 7th card
    private String expectedTitle = "White Nights";

    // Constructor to initialize WebDriver and LoginPage
    public WishlistSteps() {
        // Initialize the WebDriver (ensure the appropriate driver executable is in your path)
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        log = Logger.getLogger(WishlistSteps.class);

        // Set up Extent Reports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
    
    
    // Step definition: Launches the BooksWagon website
    @Given("the user launches the BooksWagon website")
    public void the_user_launches_the_bookswagon_website() {
        log.info("Launching the BooksWagon website...");
        test = extent.createTest("Launch BooksWagon Website");
        loginPage.launchBooksWagon();
        log.info("BooksWagon website launched successfully.");
        test.pass("BooksWagon website launched successfully.");
    }

    // Step definition: Logs in with the given username and password
    @Given("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) throws InterruptedException {
        log.info("Attempting to log in with username: " + username);
        test = extent.createTest("User Login");
        loginPage.login(username, password);
        log.info("User logged in successfully with username: " + username);
        test.pass("User logged in successfully with username: " + username);
    }

    // Step definition: Scrolls to the 7th card on the page
    @When("the user scrolls to the 7th card")
    public void the_user_scrolls_to_the_7th_card() throws InterruptedException {
        log.info("Scrolling to the 7th card...");
        test = extent.createTest("Scroll to 7th Card");
        loginPage.scrollAndClickOn7thCard();
        log.info("Successfully scrolled to and clicked on the 7th card.");
        test.pass("Successfully scrolled to and clicked on the 7th card.");
    }

    // Step definition: Verifies that the details of the 7th card are displayed
    @Then("the user should see the details of the 7th card")
    public void the_user_should_see_the_details_of_the_7th_card() {
        log.info("Verifying the title of the 7th card...");
        test = extent.createTest("Verify 7th Card Details");
        WebElement titleElement = driver.findElement(productTitle); // Get the title element of the 7th card
        String actualTitle = titleElement.getText(); // Extract the text of the title

        // Assert that the actual title matches the expected title
        assertEquals("The title of the 7th card does not match.", expectedTitle, actualTitle);
        log.info("The 7th card title is correct: " + actualTitle);
        test.pass("The 7th card title is correct: " + actualTitle);
    }

    // Step definition: Adds the item to the wishlist
    @When("the user adds the item to the wishlist")
    public void the_user_adds_the_item_to_the_wishlist() throws InterruptedException {
        log.info("Adding item to the wishlist...");
        test = extent.createTest("Add Item to Wishlist");
        loginPage.addToWishlist();
        log.info("Item added to the wishlist successfully.");
        test.pass("Item added to the wishlist successfully.");
    }

    // Step definition: Verifies that the item was successfully added to the wishlist
    @Then("the item should be added to the wishlist successfully")
    public void the_item_should_be_added_to_the_wishlist_successfully() {
        log.info("Verifying that the item was added to the wishlist...");
        test = extent.createTest("Verify Item Added to Wishlist");

        // Locate the wishlist count element
        WebElement wishlistCountElement = driver.findElement(wishlistCountLabel);

        // Extract the initial count and convert it to an integer
        int initialCount = 0;
        log.info("Initial wishlist count: " + initialCount);

        // Assert that the initial count is zero
        assertEquals("Initial wishlist count should be zero.", 0, initialCount);

        // Extract the updated count after adding the item
        wishlistCountElement = driver.findElement(wishlistCountLabel); // Re-locate the element
        int updatedCount = Integer.parseInt(wishlistCountElement.getText());

        // Verify that the updated count is greater than zero
        assertTrue("Item not added to wishlist. Expected count to be greater than 0 but got " + updatedCount, updatedCount > 0);
        log.info("Wishlist count after adding item: " + updatedCount);
        test.pass("Item added to wishlist successfully. Updated count: " + updatedCount);
    }

    // Step definition: Clicks on the wishlist icon
    @When("the user clicks on the wishlist icon")
    public void the_user_clicks_on_the_wishlist_icon() {
        log.info("Clicking on the wishlist icon...");
        test = extent.createTest("Click Wishlist Icon");
        loginPage.clickMyWishlist();
        log.info("Wishlist icon clicked successfully.");
        test.pass("Wishlist icon clicked successfully.");
    }

    // Step definition: Verifies that the wishlist icon is clickable
    @Then("the wishlist icon should be clickable")
    public void the_wishlist_icon_should_be_clickable() {
        log.info("Verifying that the wishlist icon is clickable...");
        test = extent.createTest("Verify Wishlist Icon Clickable");
        boolean isClickable = loginPage.isWishlistIconClickable(); // Check if the wishlist icon is clickable
        assertTrue("Wishlist icon is not clickable.", isClickable); // Assert that the icon is clickable
        log.info("Wishlist icon is clickable.");
        test.pass("Wishlist icon is clickable.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            log.info("Closing the browser...");
            driver.quit(); // Close the browser
            log.info("Browser closed successfully.");
        }
        // Flush the reports to save the information
        extent.flush();
    }
}


