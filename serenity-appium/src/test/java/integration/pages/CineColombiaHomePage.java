package integration.pages;

import integration.Util.Constants;
import integration.models.Movie;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.pagefactory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CineColombiaHomePage extends MobilePageObject {

    public CineColombiaHomePage(WebDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id="com.cinepapaya.cinecolombia:id/btnContinue")
    private WebElement continueButton;

    @AndroidFindBy(className = "android.widget.ImageButton")
    private WebElement homeMenu;

    @AndroidFindBy(id = "com.cinepapaya.cinecolombia:id/tviOpera")
    private WebElement operaOption;

    @AndroidFindBy(id = "com.cinepapaya.cinecolombia:id/tviBallet")
    private WebElement balletOption;

    @AndroidFindBy(id = "com.cinepapaya.cinecolombia:id/tviTeatro")
    private WebElement teatroOption;

    @AndroidFindBy(id = "com.cinepapaya.cinecolombia:id/rviMovies")
    private WebElement resultMovies;

    @AndroidFindBy(xpath="//android.widget.EditText")
    @iOSFindBy(xpath="//XCUIElementTypeTextField[@name=\"Email address\"]")
    private WebElement WPEmailAddressField;

    @AndroidFindBy(id="org.wordpress.android:id/primary_button")
    @iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"Next Button\"]")
    private WebElement WPLogInPageNextButton;

    @AndroidFindBy(id="org.wordpress.android:id/textinput_error")
    @iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'not registered')]")
    private WebElement WPLogInPageInvalidEmailErrorMessage;

    @FindBy(id="com.flipkart.android:id/btn_mlogin")
    private WebElement existingUsersignIn;

    @FindBy(id="com.flipkart.android:id/mobileNo")
    private WebElement userId;

    @FindBy(id="com.flipkart.android:id/et_password")
    private WebElement password;

    @FindBy(id="com.flipkart.android:id/btn_mlogin")
    private WebElement login_Button;

    @FindBy(id="com.flipkart.android:id/pageLevelError")
    private WebElement error_text;


    public void gotoHomePage(){
        clickElement(continueButton);
    }

    public void getPlays(){
        List<Movie> movies = new ArrayList<>();

        try {
            movies.addAll(getMoviesFromOption(operaOption));
            movies.addAll(getMoviesFromOption(balletOption));
            movies.addAll(getMoviesFromOption(teatroOption));
        }
        catch (InterruptedException ex) {
            System.out.println(":( Something goes wrong!");
        }

    }

    private void openHomeMenu(){
        clickElement(homeMenu);
    }

    private void clickElement(WebElement webElement){
        element(webElement).click();
    }

    private List<Movie> getMoviesFromOption(WebElement option) throws InterruptedException {
        List<Movie> movies = new ArrayList<>();

        openHomeMenu();
        clickElement(option);
        //this.getDriver().wait(2000);
        TimeUnit.SECONDS.sleep(2);
        movies.addAll(getMovies(resultMovies));

        return  movies;
    }

    private List<Movie> getMovies(WebElement source){
        List<Movie> movies = new ArrayList<>();

        if(source != null) {
            List<WebElement> details = source.findElements(By.id(Constants.MOVIE_DETAIL_RESOURCE_ID));

            for (WebElement detail : details) {
                movies.add(getMovieFromDetail(detail));
            }
        }

        return movies;
    }

    private Movie getMovieFromDetail(WebElement detail){
        Movie movie = new Movie();
        movie.setMovieName(detail.findElement(By.id(Constants.MOVIE_NAME_RESOURCE_ID)).getText());
        movie.setReleaseDate(detail.findElement(By.id(Constants.MOVIE_RELEASE_DATE_RESOURCE_ID)).getText());
        movie.setGenres(detail.findElement(By.id(Constants.MOVIE_GENRE_RESOURCE_ID)).getText());
        movie.setDuration(detail.findElement(By.id(Constants.MOVIE_DURATION_RESOURCE_ID)).getText());

        return movie;
    }



    public void enterInvalidEmailIdWPLoginPage(){
        WPEmailAddressField.sendKeys("vikram@invalid.com");
    }

    public boolean isErrorMessageShownWPLoginPage(){
        WPLogInPageNextButton.click();
        return WPLogInPageInvalidEmailErrorMessage.getText().contains("is not registered on WordPress.com");
    }


    public void gotoLoginPage(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/btn_mlogin")) );
        element(existingUsersignIn).click();
    }

    public void enterInvalidCredentials(){
        element(userId).sendKeys("dummyName");
        element(password).sendKeys("invalidPwd");
        element(login_Button).click();
    }

    public boolean isErrorMessageShown(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/pageLevelError")) );
        return element(error_text).getText().contentEquals("Invalid login details");
    }

}
