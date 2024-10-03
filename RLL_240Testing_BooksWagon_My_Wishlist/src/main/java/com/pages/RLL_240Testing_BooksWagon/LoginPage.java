package com.pages.RLL_240Testing_BooksWagon;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
    WebDriver driver;		
    
    // Locators for different elements on the page
    By myAccountDropdown = By.xpath("//span[contains(text(),'My Account')]");
    By loginPageLink = By.xpath("//div[@id='ctl00_divLogin']/a[@href='https://www.bookswagon.com/login']");
    By emailInputField = By.xpath("//input[@id='ctl00_phBody_SignIn_txtEmail']");
    By passwordInputField = By.xpath("//input[@id='ctl00_phBody_SignIn_txtPassword']");
    By loginSubmitButton = By.xpath("//a[@id='ctl00_phBody_SignIn_btnLogin']");
    By wishlistIconLabel = By.xpath("//label[@id='ctl00_lblWishlistCount']");
    By siteLogo = By.xpath("//img[@id='ctl00_imglogo']");
    By seventhCard = By.xpath("(//div[@class='card align-items-center'])[7]");
    By addToWishlistButton = By.xpath("//a[contains(@class, 'btn themeborder themecolor d-block mb-2') and contains(text(), 'Add to Wishlist')]");

    // Constructor to initialize WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to launch the Bookswagon homepage and maximize the window
    public void launchBooksWagon() {
        driver.get("https://www.bookswagon.com/");
        driver.manage().window().maximize();
    }

    // Method to perform login using provided username and password
    public void login(String username, String password) throws InterruptedException {
        WebElement profileIcon = driver.findElement(myAccountDropdown);
        
        // Hover over "My Account" to display login options
        Actions actions = new Actions(driver);
        actions.moveToElement(profileIcon).perform();
        Thread.sleep(1000);

        // Click on "Login" link to navigate to login page
        WebElement loginLink = driver.findElement(loginPageLink);
        loginLink.click();

        // Enter the username (email) and password
        WebElement emailField = driver.findElement(emailInputField);
        emailField.sendKeys(username);

        WebElement passwordField = driver.findElement(passwordInputField);
        passwordField.sendKeys(password);
        Thread.sleep(1000);

        // Click the login button to submit credentials
        WebElement loginBtn = driver.findElement(loginSubmitButton);
        loginBtn.click();

        // Return to the homepage by clicking on the site logo
        driver.findElement(siteLogo).click();
    }

    // Method to scroll to the 7th product card and click on it
    public void scrollAndClickOn7thCard() throws InterruptedException {
        WebElement targetCard = driver.findElement(seventhCard);

        // Scroll to the 7th product card
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetCard);
        Thread.sleep(1000);
        targetCard.click(); // Click on the card
    }

    // Method to add a product to the wishlist
    public void addToWishlist() throws InterruptedException {
        WebElement wishlistButton = driver.findElement(addToWishlistButton);
        wishlistButton.click(); // Click the "Add to Wishlist" button
        Thread.sleep(1000);
    }

    // Method to click on the wishlist icon to view the wishlist
    public void clickMyWishlist() {
        WebElement wishlistIcon = driver.findElement(wishlistIconLabel);
        wishlistIcon.click(); // Click on the wishlist icon
    }

    // Method to check if the wishlist icon is clickable
    public boolean isWishlistIconClickable() {
        WebElement wishlistIcon = driver.findElement(wishlistIconLabel);
        boolean isDisplayed = wishlistIcon.isDisplayed();  // Check if the icon is visible
        boolean isEnabled = wishlistIcon.isEnabled();      // Check if the icon is clickable

        if (isDisplayed && isEnabled) {
            System.out.println("Wishlist icon is displayed and clickable.");
            return true;
        } else {
            System.out.println("Wishlist icon is either not displayed or not clickable.");
            return false;
        }
    }
}

