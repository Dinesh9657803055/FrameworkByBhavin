package com.openxcell.OnlineShopping.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.openxcell.OnlineShopping.PageObjects.MyCartPage;
import com.openxcell.OnlineShopping.PageObjects.MyOrderPage;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(css = "[routerlink*='cart']")
	public WebElement linkCartMenu;
	
	@FindBy(css = "[routerlink*='myorders']")
	public WebElement linkMyOrdersMenu;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void waitForElementToAppear(By findBy) {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToGetInteractable(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitForLoadingToDisappear() {
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));	
	}
	
	public void waitForToastMessageToAppear() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));	
	}
	
	public MyCartPage GoToCart() throws InterruptedException {
		waitForElementToGetInteractable(linkCartMenu);
		Thread.sleep(1000);
		linkCartMenu.click();
		System.out.println("Cart menu clicked");
		MyCartPage myCartPage = new MyCartPage(driver);
		return myCartPage;
	}
	
	public MyOrderPage GoToMyOrders() {
		waitForElementToGetInteractable(linkMyOrdersMenu);
		linkMyOrdersMenu.click();
		System.out.println("My Order Menu link is clicked");
		MyOrderPage myOrderPage = new MyOrderPage(driver);
		return myOrderPage;
	}
}
