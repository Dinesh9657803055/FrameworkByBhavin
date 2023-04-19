package com.openxcell.OnlineShopping.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.openxcell.OnlineShopping.AbstractComponents.AbstractComponents;

public class SignupPage extends AbstractComponents {

	WebDriver driver;

	@FindBy(css = "#firstName")
	public WebElement txtFirstName;

	@FindBy(css = "#lastName")
	public WebElement txtLastName;

	@FindBy(css = "#userEmail")
	public WebElement txtEmail;

	@FindBy(css = "#userMobile")
	public WebElement txtMobile;

	@FindBy(xpath = "(//div[@class='form-group col-md-6'] //select[@class='custom-select ng-untouched ng-pristine ng-valid'])")
	public WebElement drpOccuption;

	@FindBy(id = "userPassword")
	public WebElement txtNewPassword;

	@FindBy(id = "confirmPassword")
	public WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@type='checkbox']")
	public WebElement chkTermCondition;

	@FindBy(xpath = "//input[@id='login']")
	public WebElement btnSignup;

	@FindBy(css = ".headcolor")
	public WebElement signUpSuccessMessage;

	public SignupPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void SignupApplication(String firstName, String lastName, String email, String mobile, String occupation, String password, String confirmPassword, String gender) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmailAddress(email);
		setMobileNumber(mobile);
		selectOccupation(occupation);
		setNewPassword(password);
		setConfirmPassword(confirmPassword);
		setGender(gender);
		checkTermAndCondition();
		ClickSingupButton();
	}

	public void setFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}

	public void setEmailAddress(String email) {
		txtEmail.sendKeys(email);
	}

	public void setMobileNumber(String mobile) {
		txtMobile.sendKeys(mobile);
	}

	public void selectOccupation(String occupation) {
		Select drpOccupation = new Select(drpOccuption);
		drpOccupation.selectByVisibleText(occupation);
	}

	public void setNewPassword(String password) {
		txtNewPassword.sendKeys(password);
	}

	public void setConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}

	public void setGender(String gender) {
		WebElement gndr = driver.findElement(By.cssSelector("input[value='" + gender + "']"));
		gndr.click();
	}
	
	public void checkTermAndCondition() {
		chkTermCondition.click();
	}
	
	public void ClickSingupButton() {
		btnSignup.click();
	}

	public String getSignUpSuccessMessage() {
		return signUpSuccessMessage.getText();
	}
}