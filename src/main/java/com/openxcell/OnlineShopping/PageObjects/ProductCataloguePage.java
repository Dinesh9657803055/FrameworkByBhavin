package com.openxcell.OnlineShopping.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.openxcell.OnlineShopping.AbstractComponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css=".mb-3")
	public List<WebElement> products; 

	By productList = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	public ProductCataloguePage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productList);
		System.out.println("Number of available products: " + products.size());
		return products;		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);
		return prod;		
	}
	
	public void clickAddToCartButton(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
	}

}
