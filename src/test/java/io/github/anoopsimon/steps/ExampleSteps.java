package io.github.anoopsimon.steps;

import javax.inject.Inject;

import io.github.anoopsimon.pages.LoginPage;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;

import static org.junit.Assert.assertEquals;

public class ExampleSteps {

    @Inject
    private WebDriver webDriver;

    @Given("^I search for \"([^\"]*)\"$")
    public void i_search_for(String arg1) {
        new LoginPage(webDriver).login();
    }

    @Then("^I google should show me results!$")
    public void i_google_should_show_me_results() {
    }
}