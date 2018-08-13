package integration.steps;

import integration.pages.CineColombiaHomePage;

import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class CineColombiaHomeSteps extends ScenarioSteps{
    CineColombiaHomePage homePage;

    @Step
    public void gotoHomePage(){
        homePage.gotoHomePage();
    }

    @Step
    public void browsePlays(){
        homePage.browsePlays();
    }

    @Step
    public void saveResult(){
        homePage.saveResult();
    }
}