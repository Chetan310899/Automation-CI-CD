package chetan.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productname="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("chetan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Chet@n99");
		driver.findElement(By.id("login")).click();
		Actions a = new Actions(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-child")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating for disappearing the text
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		WebElement element = driver.findElement(By.cssSelector("[routerlink*='cart']"));
		a.moveToElement(element).click().perform();
		
		 List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		 
			
			  Boolean match = cartProducts.stream().anyMatch(cartProduct->
			  cartProduct.getText().equalsIgnoreCase(productname));
			  Assert.assertTrue(match);
			 
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		a.sendKeys(driver.findElement(By.className("form-group")), "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmmsg = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the Order."));
		
	}

}
