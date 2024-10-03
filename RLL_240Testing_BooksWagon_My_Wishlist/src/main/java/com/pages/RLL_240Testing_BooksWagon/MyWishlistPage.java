
package com.pages.RLL_240Testing_BooksWagon;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyWishlistPage {
    WebDriver driver;

    // Locators for different elements on the Wishlist page
    By wishlistIconLabel = By.xpath("//label[@id='ctl00_lblWishlistCount']");
    By wishlistHeader = By.xpath("//h1[@class='m-0' and contains(text(), 'My Wishlist')]");
    By addButton = By.xpath("//button[@id='add']");
    By subtractButton = By.xpath("//button[@id='sub']");
    By totalItemsText = By.xpath("//span[@id='ctl00_phBody_WishList_lvWishList_lblTotalQty']");
    By removeItemButton = By.xpath("//a[@id='ctl00_phBody_WishList_lvWishList_ctrl0_ImageButton1']");
    By noItemsMessage = By.xpath("//div[@class='no-items' and contains(text(), 'You do not have any item(s) in Wishlist')]");
    By selectAllCheckbox = By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_chkAll']");
    By quantityField = By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_txtqty']");
    By addToCartButton = By.xpath("//a[@id='imgAddtoCart']");
    By cartItemCountLabel = By.xpath("//label[@id='ctl00_lblTotalCartItems']");

    // Constructor to initialize WebDriver
    public MyWishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to verify if the user is on the Wishlist page by checking the header text
    public boolean verifyOnWishlistPage() {
        WebElement wishlistHeaderElement = driver.findElement(wishlistHeader);
        String actualText = wishlistHeaderElement.getText();
        String expectedText = "My Wishlist";

        if (actualText.equals(expectedText)) {
            System.out.println("User is on the Wishlist page.");
            return true;
        } else {
            System.out.println("User is not on the Wishlist page.");
            return false;
        }
    }

    // Method to retrieve the Wishlist header text
    public String getWishlistHeaderText() {
        WebElement wishlistHeaderElement = driver.findElement(wishlistHeader);
        return wishlistHeaderElement.getText();
    }

    // Method to click the "+" (add) button and verify that the quantity increases
    public void clickAddButton() throws InterruptedException {
        WebElement addButtonElement = driver.findElement(addButton);
        WebElement qtyElement = driver.findElement(quantityField);

        int initialQty = Integer.parseInt(qtyElement.getAttribute("value"));

        if (addButtonElement.isDisplayed() && addButtonElement.isEnabled()) {
            addButtonElement.click();
            System.out.println("Add button clicked successfully.");
            Thread.sleep(2000);

            int updatedQty = Integer.parseInt(qtyElement.getAttribute("value"));
            if (updatedQty > initialQty) {
                System.out.println("Quantity increased successfully.");
            } else {
                throw new RuntimeException("Quantity did not increase after clicking the add button.");
            }
        } else {
            throw new RuntimeException("Add button is either not displayed or not clickable.");
        }
    }

    // Method to click the "-" (subtract) button and verify that the quantity decreases
    public void clickSubButton() throws InterruptedException {
        WebElement subButtonElement = driver.findElement(subtractButton);
        WebElement qtyElement = driver.findElement(quantityField);

        int initialQty = Integer.parseInt(qtyElement.getAttribute("value"));

        if (subButtonElement.isDisplayed() && subButtonElement.isEnabled()) {
            subButtonElement.click();
            System.out.println("Subtract button clicked successfully.");
            Thread.sleep(2000);

            int updatedQty = Integer.parseInt(qtyElement.getAttribute("value"));
            if (updatedQty < initialQty) {
                System.out.println("Quantity decreased successfully.");
            } else {
                throw new RuntimeException("Quantity did not decrease after clicking the subtract button.");
            }
        } else {
            throw new RuntimeException("Subtract button is either not displayed or not clickable.");
        }
    }

    // Method to remove an item from the Wishlist and verify the removal
    public void removeItemAndVerify() {
        WebElement removeButtonElement = driver.findElement(removeItemButton);
        removeButtonElement.click();
        System.out.println("Remove button clicked successfully.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noItemsMessage));

        WebElement noItemsElement = driver.findElement(noItemsMessage);
        if (noItemsElement.isDisplayed()) {
            String actualText = noItemsElement.getText();
            String expectedText = "You do not have any item(s) in Wishlist";
            if (actualText.equals(expectedText)) {
                System.out.println("Item successfully removed from wishlist.");
            } else {
                System.out.println("Incorrect message displayed. Expected: " + expectedText + ", but found: " + actualText);
            }
        } else {
            System.out.println("No items message is not displayed.");
        }
    }

    // Method to click the "Select All" checkbox
    public void selectAllCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement selectAllElement = wait.until(ExpectedConditions.elementToBeClickable(selectAllCheckbox));
            if (selectAllElement.isDisplayed() && selectAllElement.isEnabled()) {
                selectAllElement.click();
                System.out.println("Select All checkbox clicked successfully.");
            } else {
                throw new RuntimeException("Select All checkbox is either not displayed or not clickable.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Select All checkbox not found or timeout occurred.");
            throw e;
        }
    }

    // Method to add all items from the Wishlist to the cart and verify the cart count
    public void clickAllAddToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement selectAllElement = wait.until(ExpectedConditions.elementToBeClickable(selectAllCheckbox));
            if (selectAllElement.isSelected()) {
                WebElement initialCartCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCountLabel));
                int initialCartCount = Integer.parseInt(initialCartCountElement.getText());
                System.out.println("Initial cart item count: " + initialCartCount);

                List<WebElement> addToCartButtons = driver.findElements(addToCartButton);
                for (WebElement button : addToCartButtons) {
                    if (button.isDisplayed() && button.isEnabled()) {
                        button.click();
                        System.out.println("Add to Cart button clicked successfully.");
                        Thread.sleep(3000);
                        driver.navigate().back();

                        WebElement updatedCartCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCountLabel));
                        int updatedCartCount = Integer.parseInt(updatedCartCountElement.getText());
                        System.out.println("Updated cart item count: " + updatedCartCount);
                    }
                }

                WebElement finalCartCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCountLabel));
                int finalCartCount = Integer.parseInt(finalCartCountElement.getText());
                if (finalCartCount > initialCartCount) {
                    System.out.println("Cart item count increased successfully.");
                } else {
                    throw new RuntimeException("Cart item count verification failed.");
                }
            } else {
                System.out.println("Select All checkbox is not clicked.");
            }
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Select All checkbox or Add to Cart buttons not found.");
            throw e;
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
            throw e;
        }
    }
    
    public void deleteFromCart() {
        // XPath for the delete button in the cart
        By deleteButton = By.xpath("//a[@id='ctl00_phBody_BookCart_lvCart_ctrl0_imgDelete']");

        // Locate the delete button using the provided XPath and click it
        WebElement deleteBtn = driver.findElement(deleteButton);
        deleteBtn.click();
        
        // Optionally, wait for the item to be removed or the page to refresh
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.invisibilityOf(deleteBtn)); // Wait until the item is removed from cart
    }
    
    

}

