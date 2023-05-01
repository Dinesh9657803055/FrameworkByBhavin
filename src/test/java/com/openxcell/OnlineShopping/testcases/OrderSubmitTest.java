package com.openxcell.OnlineShopping.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.openxcell.OnlineShopping.Base.BaseTest;
import com.openxcell.OnlineShopping.PageObjects.CheckoutPage;
import com.openxcell.OnlineShopping.PageObjects.ForgotPasswordPage;
import com.openxcell.OnlineShopping.PageObjects.MyCartPage;
import com.openxcell.OnlineShopping.PageObjects.MyOrderPage;
import com.openxcell.OnlineShopping.PageObjects.ProductCataloguePage;
import com.openxcell.OnlineShopping.PageObjects.SignupPage;

public class OrderSubmitTest extends BaseTest {

	public WebDriver driver;

	public String productName = "ADIDAS ORIGINAL";
	public String countryName = "Singapore";

	public SignupPage signupPage;
	public ForgotPasswordPage forgotPasswordPage;
	public ProductCataloguePage productCatalogue;
	public MyCartPage myCartPage;
	public CheckoutPage checkoutPage;

	public void objectInitilization() {
		signupPage = new SignupPage(driver);
		forgotPasswordPage = new ForgotPasswordPage(driver);
	}

	@Test(dataProvider = "getData", groups = "smoke", enabled = true)
	// public void SubmitOrder(String email, String password, String productName,
	// String countryName) throws InterruptedException {
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException {
		productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		WebElement toastMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Assert.assertTrue(toastMessage.isDisplayed(), "Login successfully!");

		productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("productName"));
		 
		productCatalogue.clickAddToCartButton(input.get("productName"));
		System.out.println("Item added in Cart");
		productCatalogue.waitForLoadingToDisappear();
		productCatalogue.waitForToastMessageToAppear();
		
		myCartPage = landingPage.GoToCart(); Boolean match =
		myCartPage.VerifyProuctToDisplay(input.get("productName"));
		Assert.assertTrue(match); checkoutPage = myCartPage.GoToCheckoutPage();
		checkoutPage.doCheckout("544", "Denish Knight", input.get("countryName"));
		String ActualMessage = checkoutPage.getThankYouMessage();
		Assert.assertTrue(ActualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Order Submitted successfully!"); Thread.sleep(2000);
		MyOrderPage orderPage = landingPage.GoToMyOrders();
		Assert.assertTrue(orderPage.VerifyOrderToDisplay(input.get("productName")));
		
		landingPage.ClickSignOut();
	}

	@Test
	public void VeriLoginWithInvalidCredential() throws IOException, InterruptedException {
		landingPage.loginApplication("bhavin.dholakiya@openxcell.com", "Test@123");
		Assert.assertEquals(landingPage.GetErrorMessage(), "Incorrect email or password.");
	}

	@Test(dependsOnMethods = "VeriLoginWithInvalidCredential")
	public void VerifyAndSubmitLogin() {
		// landingPage.goTo();
		productCatalogue = landingPage.loginApplication("denish.knight@gmail.com", "Test@321");
		WebElement toastMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Assert.assertTrue(toastMessage.isDisplayed(), "Login successfully!");
	}

	@Test(dependsOnMethods = "VerifyAndSubmitLogin")
	public void GetProducesAndAddToCart() throws InterruptedException {
		productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);

		productCatalogue.clickAddToCartButton(productName);
		System.out.println("Item added in Cart");

		productCatalogue.waitForLoadingToDisappear();
		productCatalogue.waitForToastMessageToAppear();

		myCartPage = landingPage.GoToCart();
	}

	@Test(dependsOnMethods = "GetProducesAndAddToCart")
	public void VerifyCart() {
		Boolean match = myCartPage.VerifyProuctToDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = myCartPage.GoToCheckoutPage();
	}

	@Test(dependsOnMethods = "VerifyCart")
	public void DoCheckout() throws InterruptedException {

		checkoutPage.doCheckout("544", "Denish Knight", countryName);

		/*
		 * Select drpMonth = new Select( driver.findElement(By.
		 * xpath("//form/div/div[2]/div/select[@class='input ddl'][1]")));
		 * drpMonth.selectByVisibleText("3");
		 * System.out.println("Month of Expiry date selected"); Select drpYear = new
		 * Select(driver.findElement(By.
		 * xpath("//form/div/div[2]/div//select[@class='input ddl'][2]")));
		 * drpYear.selectByVisibleText("26");
		 * System.out.println("Year of Expiry date selected");
		 */

		String ActualMessage = checkoutPage.getThankYouMessage();
		Assert.assertTrue(ActualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Order Submitted successfully!");
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = "DoCheckout")
	public void OrderHistoryTest() {
		// productCatalogue = landingPage.loginApplication("denish.knight@gmail.com", "Test@321");
		MyOrderPage orderPage = landingPage.GoToMyOrders();
		Assert.assertTrue(orderPage.VerifyOrderToDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = fileReader.getJsonDataToMap();

		return new Object[][] { { data.get(0) }, { data.get(1) } };
		/*
		 * HashMap<Object,Object> map = new HashMap<Object,Object>(); map.put("email",
		 * "denish.knight@gmail.com"); map.put("password", "Test@321");
		 * map.put("productName", "ADIDAS ORIGINAL"); map.put("countryName",
		 * "Singapore");
		 * 
		 * HashMap<Object,Object> map1 = new HashMap<Object,Object>(); map1.put("email",
		 * "jems.bond@gmail.com"); map1.put("password", "Test@123");
		 * map1.put("productName", "ZARA COAT 3"); map1.put("countryName", "India");
		 * 
		 * return new Object[][] { {"denish.knight@gmail.com", "Test@321",
		 * "ADIDAS ORIGINAL", "Singapore"}, {"jems.bond@gmail.com", "Test@123",
		 * "ZARA COAT 3", "India"}, };
		 */
	}
}
