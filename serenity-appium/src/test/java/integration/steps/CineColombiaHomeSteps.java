package integration.steps;

import integration.pages.CineColombiaHomePage;

import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class CineColombiaHomeSteps extends ScenarioSteps{
    CineColombiaHomePage loginPage;

    @Step
    public void loginPageInvalidDataInput(){
        loginPage.gotoHomePage();
        loginPage.getPlays();
    }

    @Step
    public void enterLoginData(){
        loginPage.enterInvalidEmailIdWPLoginPage();
    }

    @Step
    public void checkErrorMessage(){
        assertThat(loginPage.isErrorMessageShownWPLoginPage()).isTrue();
    }

}