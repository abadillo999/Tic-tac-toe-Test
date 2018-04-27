package es.codeurjc.ais.tictactoe;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.ChromeDriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(
		 plugin = {"pretty"}, 
		 features = { "classpath:es/codeurjc/ais/tictactoe/WebApp.feature" },
		 glue = {"es.codeurjc.ais.tictactoe" })

public class CucumberSystemTest {
	
	@BeforeClass
	public static void a_WebApp() throws Throwable {
	    WebApp.start();
		ChromeDriverManager.getInstance().setup();
		//Thread.sleep(10000);
	
		HealthCheck.start();
	}
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
}
