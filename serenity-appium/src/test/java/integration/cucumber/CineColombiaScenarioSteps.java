package integration.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import integration.steps.CineColombiaHomeSteps;

public class CineColombiaScenarioSteps {

    @Steps
    CineColombiaHomeSteps cineColombiaSteps;

    @Given("I am on the home page")
    public void gotoLoginPage(){
        cineColombiaSteps.gotoHomePage();
    }

    @When("I browse for all the option")
    public void enterInvalidData(){
        cineColombiaSteps.browsePlays();
    }

    @Then("I save the results in a excel file")
    public void checkErrorMessage(){
        cineColombiaSteps.saveResult();
    }
}
