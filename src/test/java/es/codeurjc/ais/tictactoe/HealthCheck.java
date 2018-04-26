package es.codeurjc.ais.tictactoe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HealthCheck {
	static void start() throws Throwable {
		ChromeDriver driver1 = new ChromeDriver();
        ChromeDriver driver2 = new ChromeDriver();

        driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");

		String player1 = "player1";
		String player2 = "player2";

		driver1.findElement(By.id("nickname")).sendKeys(player1);
		driver2.findElement(By.id("nickname")).sendKeys(player2);

		driver1.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("startBtn")).click();

		driver1.quit();
		driver2.quit();
		for(int i = 0;i <2 ; i ++) {
	        driver1= new ChromeDriver();
	        driver2= new ChromeDriver();
		
	        driver1.get("http://localhost:8080/");
			driver2.get("http://localhost:8080/");

			driver1.findElement(By.id("nickname")).sendKeys(player1);
			driver2.findElement(By.id("nickname")).sendKeys(player2);

			driver1.findElement(By.id("startBtn")).click();
			driver2.findElement(By.id("startBtn")).click();
			int iterable []  =  {0,1,3,2,6};
			for(int n = 0 ; n < iterable.length ; n++ ) {
				if(n%2 == 0) {
				driver1.findElement(By.id("cell-" + iterable[n])).click();
				}else {
				driver2.findElement(By.id("cell-" + iterable[n])).click();}
			}

			driver1.quit();
			driver2.quit();
			}
	}
	


}
