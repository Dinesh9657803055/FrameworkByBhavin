package com.openxcell.OnlineShopping.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openxcell.OnlineShopping.AbstractComponents.AbstractComponents;

public class MyOrderPage extends AbstractComponents{
WebDriver driver;
	
	@FindBy(css = "tr td:nth-child(3)")
	public List<WebElement> productNames;
 	
	public MyOrderPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyOrderToDisplay(String productName) {
		Boolean match = productNames.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		System.out.println("Product found in my orders");
		return match;
	}
}
