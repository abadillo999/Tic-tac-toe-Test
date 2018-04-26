package es.codeurjc.ais.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class CucumberSystemTestAddings {
	private WebDriver driver1;
	private WebDriver driver2;

	@Given("^a WebApp$")
	public void a_WebApp() throws Throwable {
	    WebApp.start();
		ChromeDriverManager.getInstance().setup();
		Thread.sleep(10000);

	
       HealthCheck.start();
        
	}
	


	@Given("^two registered browser players$")
	public void two_registered_browser_players() throws Throwable {
		
        driver1= new ChromeDriver();
        driver2= new ChromeDriver();
        
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");

		driver1.findElement(By.id("nickname")).sendKeys("player1");
		driver2.findElement(By.id("nickname")).sendKeys("player2");
		
		driver1.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("startBtn")).click();
	}

	@When("^the combination of movements is$")
	public void the_combination_of_movements_is(List<Integer> args) throws Throwable {
		for (int n= 0; n < args.size(); n++)  {
			String cell = "cell-" + args.get(n);
			if(n%2==0) {
				driver1.findElement(By.id(cell)).click();
			}else {
				driver2.findElement(By.id(cell)).click();}
		}	
	}
	
	@Then("^I should receive the following message, in both browsers:\"([^\"]*)\"$")
	public void i_should_receive_the_following_message_in_both_browsers(String arg1) throws Throwable {

		String msg1 = driver1.switchTo().alert().getText();
		String msg2 = driver2.switchTo().alert().getText();

		assertThat(msg1).isEqualTo(arg1);
		assertThat(msg2).isEqualTo(arg1);	
		
		if (driver1 != null) {
			driver1.quit();
		}	
		if (driver2 != null) {
			driver2.quit();
		}
		
		}
	
	
}