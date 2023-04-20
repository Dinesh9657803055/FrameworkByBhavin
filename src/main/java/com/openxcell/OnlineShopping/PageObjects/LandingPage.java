package com.openxcell.OnlineShopping.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openxcell.OnlineShopping.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;
	
	@FindBy(xpath = "//form/div/input[@id='userEmail']")
	public WebElement txtEmail;
	
	@FindBy(xpath = "//form/div/input[@id='userPassword']")
	public WebElement txtPassword;
	
	@FindBy(xpath = "//form/input[@id='login']")
	public WebElement btnLogin;
	
	@FindBy(css = ".text-reset")
	public WebElement linkSignup;
	
	@FindBy(css = "[class*='ng-trigger-flyInOut']")
	public WebElement errorMessage;
	
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ProductCataloguePage loginApplication(String email, String password) {
		setLoginEmail(email);
		setLoginPassword(password);
		return clickLoginButton();
	}
	
	public void setLoginEmail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
		System.out.println("Email address entered");
	}
	
	public void setLoginPassword(String password) {
		txtPassword.clear();
		txtPassword.sendKeys(password);
		System.out.println("Password entered");
	}
	
	public ProductCataloguePage clickLoginButton() {
		btnLogin.click();
		waitForToastMessageToAppear();
		System.out.println("Login button clicked");
		ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);
		return productCatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		System.out.println("Redirected on Login form");
		waitForElementToGetInteractable(txtEmail);
	}
	
	public void GoToSignupForm() {
		linkSignup.click();
		System.out.println("Register link clicked");
	}
	
	public String GetErrorMessage() {
		waitForToastMessageToAppear();
		return errorMessage.getText();
	}
}
