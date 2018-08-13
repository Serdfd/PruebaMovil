package integration.pages;

import integration.Util.Constants;
import integration.Util.ExcelHelper;
import integration.models.Movie;
import org.openqa.selenium.By;
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

    private List<Movie> movies;

    public void gotoHomePage(){
        clickElement(continueButton);
    }

    public void browsePlays(){
        movies = new ArrayList<>();

        try {
            movies.addAll(getMoviesFromOption(operaOption));
            movies.addAll(getMoviesFromOption(balletOption));
            movies.addAll(getMoviesFromOption(teatroOption));
        }
        catch (InterruptedException ex) {
            System.out.println(":( Something goes wrong!");
        }
    }

    public void saveResult(){
        ExcelHelper.export(movies);
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
        TimeUnit.SECONDS.sleep(4);
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
}
