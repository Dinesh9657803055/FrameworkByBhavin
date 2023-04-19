package com.openxcell.OnlineShopping.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openxcell.OnlineShopping.AbstractComponents.AbstractComponents;

public class MyCartPage extends AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css = ".cartSection h3")
	public List<WebElement> cartProducts;
 	
	@FindBy(css = "li[class='totalRow'] button[type='button']")
	public WebElement btnCheckout;
	
	@FindBy(xpath = "//form/div/div[2]/div/input")
	public WebElement txtCVV;
	
	@FindBy(xpath = "//form/div/div[3]/div/input")
	public WebElement txtNameOnCard;
	
	@FindBy(css = "input[placeholder='Select Country']")
	public WebElement drpCountry;
	
	@FindBy(css = ".action__submit")
	public WebElement btnPlaceOrder;
	
	@FindBy(css = ".hero-primary")
	public WebElement thankYouMessage;
	
	public MyCartPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProuctToDisplay(String productName) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		System.out.println("Product found in Cart");
		return match;
	}
	
	
	public void doCheckout(String cvvNumber, String nameOnCard, String countryName) {		
		clickCheckOutButton();
		setCVVNumber(cvvNumber);		
		setNameOnCard(nameOnCard);
		selectCountry(countryName);
		clickPaceOrderButton();
		getThankYouMessage();
	}
	
	public void clickCheckOutButton() {
		waitForElementToGetInteractable(btnCheckout);
		btnCheckout.click();
	}
	
	public void setCVVNumber(String cvvNumber) {
		txtCVV.sendKeys(cvvNumber);
	}
	
	public void setNameOnCard(String nameOnCard) {
		txtNameOnCard.sendKeys(nameOnCard);
	}
	
	public void selectCountry(String countryName) {
		drpCountry.sendKeys(countryName);
		System.out.println("Country name entered");
		driver.findElement(By.xpath("//span[text()=' " + countryName + "']")).click();
		System.out.println("Country selected");
	}
	
	public void clickPaceOrderButton() {
		btnPlaceOrder.click();
	}
	
	public String getThankYouMessage() {
		return thankYouMessage.getText();
	}
}
