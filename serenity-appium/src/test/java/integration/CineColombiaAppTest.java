package integration;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;
import integration.steps.CineColombiaHomeSteps;

//@RunWith(SerenityRunner.class)
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/java/integration/resources/features/search_movies.feature" , plugin = {"json:target/cucumber_json/cucumber.json"} )
public class CineColombiaAppTest {


}
