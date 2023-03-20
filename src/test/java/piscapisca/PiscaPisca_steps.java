package piscapisca;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PiscaPisca_steps {
	
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_1110556364/chromedriver.exe");
		driver = new ChromeDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scrollBy(0,500)", "");
	}

	@After(order = 1)
	public void screenshot(Scenario scene) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("target\\screenshot\\" + scene.getName() + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	/*@After(order = 0) 
	public void closeWindow() { 
		 driver.quit();
		 System.out.println("Finished"); 
	}*/
	
	@Given("im access pisca pisca website")
	public void im_access_pisca_pisca_website(){

		driver.get("https://www.piscapisca.pt/");
		driver.manage().window().maximize();
		
	}
	
	@When("click to accept cookies")
	public void click_to_accept_cookies() {
		
		driver.findElement(By.id("didomi-notice-agree-button")).click();
	}
	
	@Then("the platform must show the main page")
	public void the_platform_must_show_the_main_page() {
		
		String initialPage = driver.findElement(By.cssSelector("h1[class='title']")).getText();
			assertEquals("Que carros procuras ?", initialPage);
	}

}
