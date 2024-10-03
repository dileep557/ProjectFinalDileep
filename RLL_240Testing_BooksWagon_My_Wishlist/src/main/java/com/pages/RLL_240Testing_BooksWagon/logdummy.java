package com.pages.RLL_240Testing_BooksWagon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class logdummy {
	
	WebDriver driver;
	//String url = "https://www.bookswagon.com/";
	
	By MyAccount=By.xpath("//span[contains(text(),\"My Account\")]");
	By loginButton = By.xpath("//h3[contains(text(),\"Log in\")]");
	By emailField = By.xpath("//input[@id=\"ctl00_phBody_SignIn_txtEmail\"]");
	By passwordField = By.xpath("//input[@id=\"ctl00_phBody_SignIn_txtPassword\"]");
	By submitButton = By.xpath("//a[@id=\"ctl00_phBody_SignIn_btnLogin\"]");
 
	By RequestBook=By.xpath("//a[contains(text(),\"Request a Book\")]");
 
 
	By ISBN13 = By.xpath("//input[@name=\"ctl00$phBody$RequestBook$txtISBN\"]");
	By Author = By.xpath("//input[@id=\"ctl00_phBody_RequestBook_txtAuthor\"]");
	By emailField1 = By.xpath("//input[@id=\"ctl00_phBody_RequestBook_txtEmailReq\"]");
	By verifyEmailButton = By.xpath("//input[@id=\"ctl00_phBody_RequestBook_btnVeiry\"]");
	//By otpField = By.xpath("ctl00_Footer_ValidateOTP_txtOTP");
	By BookTitle = By.xpath("//input[@id=\"ctl00_phBody_RequestBook_txtTitle\"]");
	By Quantity = By.xpath("//input[@id=\"ctl00_phBody_RequestBook_txtQty\"]");
	By phoneNumber1= By.xpath("//input[@name=\"ctl00$phBody$RequestBook$txtPhone\"]");
	By submitButton1 = By.xpath("//input[@id=\"ctl00_phBody_RequestBook_imgbtnSave\"]");
	By errorMessage = By.xpath("//*[@id=\"ctl00_phBody_RequestBook_lblsuccessmsg\"]");
	//By validotp = By.xpath("//input[@id=\"ctl00_Footer_ValidateOTP_btnSubmit\"]");
	
	
	public void init1(WebDriver driver) {
		this.driver = driver;
 
	}
	public void Lanuch_BooksWagon() {
 
		driver.get("https://www.bookswagon.com/");
	}
	 
	public void login(String email, String password) {
			driver.findElement(MyAccount).click();
			driver.findElement(loginButton).click();
			driver.findElement(emailField).sendKeys(email);
			driver.findElement(passwordField).sendKeys(password);
			driver.findElement(submitButton  ).click();
	}

}
