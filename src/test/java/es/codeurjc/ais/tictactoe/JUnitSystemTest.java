package es.codeurjc.ais.tictactoe;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class JUnitSystemTest {


	static WebDriver driver1;
	static WebDriver driver2;
	
	static void cellIterator(int[] iterable) {
		for(int i = 0 ; i < iterable.length ; i++ ) {
			if(i%2 == 0) {
			driver1.findElement(By.id("cell-" + iterable[i])).click();
			}else {
			driver2.findElement(By.id("cell-" + iterable[i])).click();}
		}
		
	}

	@BeforeClass
	public static void setupClass() throws Throwable {
		ChromeDriverManager.getInstance().setup();
		WebApp.start();
		
		//This setup is needed to exercise the SUT before launching the tests  
        
		HealthCheck.start();

	}
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}

	@Before
	public void setupTest() throws MalformedURLException {
        driver1= new ChromeDriver();
        driver2= new ChromeDriver();
		
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");

		String player1 = "player1";
		String player2 = "player2";

		driver1.findElement(By.id("nickname")).sendKeys(player1);
		driver2.findElement(By.id("nickname")).sendKeys(player2);

		driver1.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("startBtn")).click();
	}

	@After
	public void teardown() {
		if (driver1 != null) {
			driver1.quit();
		}	
		if (driver2 != null) {
			driver2.quit();
		}
	}

	@Test
	public void winPlayer1(){

		int iterable []  =  {0,1,3,2,6};
		cellIterator(iterable);
		
		String msg1 = driver1.switchTo().alert().getText();
		String msg2 = driver2.switchTo().alert().getText();

		assertThat(msg1).isEqualTo("player1 wins! player2 looses.");
		assertThat(msg2).isEqualTo("player1 wins! player2 looses.");
	}
	
	@Test
	public void winPlayer2(){
		
		int iterable []  =  {0,1,3,4,5,7};
		cellIterator(iterable);

		String msg1 = driver1.switchTo().alert().getText();
		String msg2 = driver2.switchTo().alert().getText();

		assertThat(msg1).isEqualTo("player2 wins! player1 looses.");
		assertThat(msg2).isEqualTo("player2 wins! player1 looses.");
	}
	

	@Test
	public void Draw(){
		
		int iterable []  =  {1,0,3,2,5,4,6,7,8};
		cellIterator(iterable);


		String msg1 = driver1.switchTo().alert().getText();
		String msg2 = driver2.switchTo().alert().getText();

		assertThat(msg1).isEqualTo("Draw!");
		assertThat(msg2).isEqualTo("Draw!");
	}
}
