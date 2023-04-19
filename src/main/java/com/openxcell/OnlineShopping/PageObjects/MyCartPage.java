package com.openxcell.OnlineShopping.PageObjects;

import java.util.List;

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
	
	public CheckoutPage GoToCheckoutPage() {
		waitForElementToGetInteractable(btnCheckout);
		btnCheckout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
