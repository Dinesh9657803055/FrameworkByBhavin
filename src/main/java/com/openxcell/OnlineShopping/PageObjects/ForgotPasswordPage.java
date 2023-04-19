package com.openxcell.OnlineShopping.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openxcell.OnlineShopping.AbstractComponents.AbstractComponents;

public class ForgotPasswordPage extends AbstractComponents {

	WebDriver driver;

	@FindBy(xpath = "//input[@placeholder='Enter your email address']")
	public WebElement txtEmail;
	
	@FindBy(css = "#userPassword")
	public WebElement txtPassword;
	
	@FindBy(css = "confirmPassword")
	public WebElement txtConfirmPassword;
	
	@FindBy(css = "button[type='submit']")
	public WebElement btnSubmit;
		
	public ForgotPasswordPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void resetUserPassword(String email, String password, String confirmPassword) {
		txtEmail.sendKeys(email);
		txtPassword.sendKeys(password);
		txtConfirmPassword.sendKeys(confirmPassword);
		btnSubmit.click();
	}
	
	public void setEmailAddress(String email) {
		txtEmail.sendKeys(email);
		System.out.println("Email address entered");
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
		System.out.println("New Password entered");
	}
	
	
	public void setConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
		System.out.println("Confirm Passwod entered");
	}
	
	public void clickSubmitButton() {
		btnSubmit.click();
		System.out.println("Submit button clicked");
	}
}
