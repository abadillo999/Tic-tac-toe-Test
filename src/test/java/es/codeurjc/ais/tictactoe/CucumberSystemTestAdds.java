package es.codeurjc.ais.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class CucumberSystemTestAdds {
	private WebDriver driver1;
	private WebDriver driver2;


	@Given("^I have a WebApp and two registered browser players$")
	public void i_have_a_WebApp_and_two_registered_browser_players() throws Throwable {
		
		ChromeDriverManager.getInstance().setup();
        driver1= new ChromeDriver();
        driver2= new ChromeDriver();
        
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");

		driver1.findElement(By.id("nickname")).sendKeys("player1");
		driver2.findElement(By.id("nickname")).sendKeys("player2");
		
		driver1.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("startBtn")).click();
	}

	@When("^The combination of movements is$")
	public void the_combination_of_movements_is(List<Integer> args) throws Throwable {
		for (int n= 0; n < args.size(); n++)  {
			String cell = "cell-" + args.get(n);
			if(n%2==0) {
				driver1.findElement(By.id(cell)).click();
			}else {
				driver2.findElement(By.id(cell)).click();}
		}	
	}
	
	@Then("^I should receive a message with the winner, first player, in both browsers$")
	public void i_should_receive_a_message_with_the_winner_first_player_in_both_browsers() throws Throwable {
		String msg1 = driver1.switchTo().alert().getText();
		String msg2 = driver2.switchTo().alert().getText();

		assertThat(msg1).isEqualTo("player1 wins! player2 looses.");
		assertThat(msg2).isEqualTo("player1 wins! player2 looses.");	
		
		if (driver1 != null) {
			driver1.quit();
		}	
		if (driver2 != null) {
			driver2.quit();
		}
		
	}
	
	@Then("^I should receive a message with the winner, second player, in both browsers$")
	public void i_should_receive_a_message_with_the_winner_second_player_in_both_browsers() throws Throwable {
		
		String msg1 = driver1.switchTo().alert().getText();
		String msg2 = driver2.switchTo().alert().getText();

		assertThat(msg1).isEqualTo("player2 wins! player1 looses.");
		assertThat(msg2).isEqualTo("player2 wins! player1 looses.");
		
		if (driver1 != null) {
			driver1.quit();
		}	
		if (driver2 != null) {
			driver2.quit();
		}	}
	
	@Then("^I should receive a message with draw$")
	public void i_should_receive_a_message_with_draw() throws Throwable {
		String msg1 = driver1.switchTo().alert().getText();
		String msg2 = driver2.switchTo().alert().getText();

		assertThat(msg1).isEqualTo("Draw!");
		assertThat(msg2).isEqualTo("Draw!");
		
		if (driver1 != null) {
			driver1.quit();
		}	
		if (driver2 != null) {
			driver2.quit();
		}	}
}