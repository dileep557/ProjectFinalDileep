package stepdefination;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.pages.RLL_240Testing_BooksWagon.LoginPage;
import com.pages.RLL_240Testing_BooksWagon.MyWishlistPage;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyWishlistStepDefinitions {
    WebDriver driver;
    MyWishlistPage myWishlistPage;
    LoginPage loginPage;
    Logger log1;
    
    // Extent Reports instance
    ExtentReports extent;
    ExtentTest test;

    // Locators defined at the top with meaningful variable names
    By wishlistHeader = By.xpath("//h1[contains(text(), 'My Wishlist')]");
    By wishlistItemQuantity = By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_txtqty']");
    By allItemsCheckboxes = By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_chkAdd']");
    By cartItemCountLabel = By.xpath("//label[@id='ctl00_lblTotalCartItems']");
    By noItemsMessage = By.xpath("//div[@class='no-items' and contains(text(), 'You do not have any item(s) in Wishlist')]");

    public MyWishlistStepDefinitions() {
        // Initialize WebDriver and Page Object
        driver = new ChromeDriver();
        this.myWishlistPage = new MyWishlistPage(driver);
        loginPage = new LoginPage(driver);
        log1 = Logger.getLogger(MyWishlistStepDefinitions.class);
       

        // Initialize Extent Reports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/MyWishlistTestReport.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Wishlist Tests");
        htmlReporter.config().setReportName("Wishlist Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
    }

    // Step definition: Launch BooksWagon website
    @Given("user launches the BooksWagon website for the wishlist")
    public void user_launches_the_bookswagon_website_for_the_wishlist() {
        test = extent.createTest("Launch BooksWagon Website");
        loginPage.launchBooksWagon();
        test.pass("BooksWagon website launched successfully.");
        log1.info("user launches the BooksWagon website for the wishlist");
        
    }

    // Step definition: Log in with username and password
    @Given("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) throws InterruptedException {
        test = extent.createTest("Login with username and password");
        loginPage.login(username, password);
        test.pass("User logged in successfully with username: " + username);
    }

    // Step definition: Click on the wishlist icon
    @Given("the user is click on the Wishlist icon")
    public void the_user_is_click_on_the_Wishlist_icon() {
        test = extent.createTest("Click on Wishlist Icon");
        loginPage.clickMyWishlist();
        test.pass("Wishlist icon clicked successfully.");
    }

    // Step definition: Verify the wishlist page header
    @Then("the user should see the header {string}")
    public void the_user_should_see_the_header(String expectedHeader) {
        test = extent.createTest("Verify Wishlist Page Header");
        boolean isOnWishlistPage = myWishlistPage.verifyOnWishlistPage();
        if (isOnWishlistPage) {
            String actualHeader = myWishlistPage.getWishlistHeaderText();
            assertEquals("Header does not match.", expectedHeader, actualHeader);
            test.pass("Header matches: " + actualHeader);
        } else {
            test.fail("User is not on the Wishlist page.");
            fail("User is not on the Wishlist page.");
        }
    }

    // Step definition: Click the "Add" button to increase quantity
    @When("the user clicks the add button")
    public void the_user_clicks_the_add_button() throws InterruptedException {
        test = extent.createTest("Click Add Button");
        myWishlistPage.clickAddButton();
        test.pass("Add button clicked.");
    }

    // Step definition: Verify that the quantity has increased
    @Then("the quantity should increase")
    public void the_quantity_should_increase() throws InterruptedException {
        test = extent.createTest("Verify Quantity Increase");
        WebElement qtyElement = driver.findElement(wishlistItemQuantity);
        int initialQty = Integer.parseInt(qtyElement.getAttribute("value"));

        myWishlistPage.clickAddButton();
        qtyElement = driver.findElement(wishlistItemQuantity);
        int updatedQty = Integer.parseInt(qtyElement.getAttribute("value"));

        if (updatedQty > initialQty) {
            test.pass("Quantity increased from " + initialQty + " to " + updatedQty);
        } else {
            test.fail("Quantity did not increase. Initial: " + initialQty + ", Updated: " + updatedQty);
        }
        assertTrue(updatedQty > initialQty, "Quantity did not increase. Initial: " + initialQty + ", Updated: " + updatedQty);
    }

    // Step definition: Click the "Subtract" button to decrease quantity
    @When("the user clicks the subtract button")
    public void the_user_clicks_the_subtract_button() throws InterruptedException {
        test = extent.createTest("Click Subtract Button");
        myWishlistPage.clickSubButton();
        test.pass("Subtract button clicked.");
    }

    // Step definition: Verify that the quantity has decreased
    @Then("the quantity should decrease")
    public void the_quantity_should_decrease() throws InterruptedException {
        test = extent.createTest("Verify Quantity Decrease");
        WebElement qtyElement = driver.findElement(wishlistItemQuantity);
        int initialQty = Integer.parseInt(qtyElement.getAttribute("value"));

        myWishlistPage.clickSubButton();
        qtyElement = driver.findElement(wishlistItemQuantity);
        int updatedQty = Integer.parseInt(qtyElement.getAttribute("value"));

        if (updatedQty < initialQty) {
            test.pass("Quantity decreased from " + initialQty + " to " + updatedQty);
        } else {
            test.fail("Quantity did not decrease. Initial: " + initialQty + ", Updated: " + updatedQty);
        }
        assertTrue(updatedQty < initialQty, "Quantity did not decrease. Initial: " + initialQty + ", Updated: " + updatedQty);
    }

    // Step definition: Select all items in the wishlist
    @When("the user selects all items")
    public void the_user_selects_all_items() {
        test = extent.createTest("Select All Items");
        myWishlistPage.selectAllCheckbox();
        test.pass("All items selected.");
    }

    // Step definition: Verify all items are selected
    @Then("all items should be selected")
    public void all_items_should_be_selected() {
        test = extent.createTest("Verify All Items Selected");
        List<WebElement> checkboxes = driver.findElements(allItemsCheckboxes);
        for (WebElement checkbox : checkboxes) {
            assertTrue(checkbox.isSelected(), "Checkbox is not selected.");
        }
        test.pass("All items are selected.");
    }

    // Step definition: Select all items and add them to the cart
    @When("the user selects all items and clicks Add to Cart")
    public void the_user_selects_all_items_and_clicks_add_to_cart() throws InterruptedException {
        test = extent.createTest("Select All Items and Add to Cart");
        myWishlistPage.clickAllAddToCart();
        test.pass("Items added to cart.");
    }

    // Step definition: Verify that the cart item count has increased
    @Then("the cart item count should increase")
    public void the_cart_item_count_should_increase() {
        test = extent.createTest("Verify Cart Item Count Increase");
        WebElement cartCountElement = driver.findElement(cartItemCountLabel);
        int updatedCount = Integer.parseInt(cartCountElement.getText());
        assertTrue(updatedCount > 0, "Cart item count did not increase. Expected count to be greater than 0 but got: " + updatedCount);
        test.pass("Cart item count increased to: " + updatedCount);
    }

    // Step definition: Remove an item from the wishlist
    @When("the user removes an item")
    public void the_user_removes_an_item() {
        test = extent.createTest("Remove Item from Wishlist");
        myWishlistPage.removeItemAndVerify();
        test.pass("Item removed from wishlist.");
    }

    // Step definition: Verify the message displayed after removing an item
    @Then("the user should see the message {string}")
    public void the_user_should_see_the_message(String expectedMessage) {
        test = extent.createTest("Verify Message after Removing Item");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noItemsMessage));

        WebElement noItemsElement = driver.findElement(noItemsMessage);
        assertTrue(noItemsElement.isDisplayed(), "No items message is not displayed.");

        String actualText = noItemsElement.getText();
        assertEquals("Message displayed is incorrect.", expectedMessage, actualText);
        test.pass("Message displayed correctly: " + actualText);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
        extent.flush(); // Write the report
    }
}

