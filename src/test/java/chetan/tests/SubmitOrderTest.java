package chetan.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.Input;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import chetan.TestComponents.BaseTest;
import chetan.pageobjects.CartPage;
import chetan.pageobjects.CheckoutPage;
import chetan.pageobjects.ConfirmationPage;
import chetan.pageobjects.LandingPage;
import chetan.pageobjects.OrderPage;
import chetan.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData", groups="purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		
		ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"),input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmmsg = confirmationPage.getCoinfirmationMessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the Order."));

	}
	
	
	//To verify ADDIDAS is displayed in Order Page

	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApp("rahulshetty@gmail.com", "Iamking@000");
		OrderPage orderPage = productCatalogue.goToOrder();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//chetan//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
}



//@DataProvider
//public Object[][] getData() throws IOException {

//HashMap<Object,Object> map = new HashMap<Object,Object>();
//map.put( "email", "chetan@gmail.com");
//map.put("password", "Chet@n99");
//map.put("product", "ADIDAS ORIGINAL");
//
//HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//map1.put( "email", "rahulshetty@gmail.com");
//map1.put("password", "Iamking@000");
//map1.put("product", "ZARA COAT 3");


//return new Object[][] {{"chetan@gmail.com","Chet@n99","ADIDAS ORIGINAL"},{"rahulshetty@gmail.com","Iamking@000","ZARA COAT 3"}};

//}