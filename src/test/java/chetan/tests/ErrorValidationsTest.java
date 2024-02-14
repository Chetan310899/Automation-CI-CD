package chetan.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import chetan.TestComponents.BaseTest;
import chetan.pageobjects.CartPage;
import chetan.pageobjects.CheckoutPage;
import chetan.pageobjects.ConfirmationPage;
import chetan.pageobjects.LandingPage;
import chetan.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = "ErrorHandling")
	public void submitOrder() throws IOException {
		landingPage.loginApp("rahulshetty@gmail.com", "Iaking@000");
		
		Assert.assertEquals("Incorrect email or password.",landingPage.errorMessage());
	}
	
	@Test
	public void productError() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApp("chetan@gmail.com", "Chet@n99");
		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();

		Boolean match = cartPage.verifyProductDisplay("ADIDASORIGINAL");
		Assert.assertFalse(match);
		
	}


}
