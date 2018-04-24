package es.codeurjc.ais.tictactoe;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		 plugin = {"pretty"}, 
		 features = { "classpath:es/codeurjc/ais/tictactoe/calc.feature" },
		 glue = {"es.codeurjc.ais.tictactoe" })

public class CucumberSystemTest {

}
