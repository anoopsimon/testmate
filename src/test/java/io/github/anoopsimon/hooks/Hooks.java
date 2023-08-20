package io.github.anoopsimon.hooks;

import javax.inject.Inject;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.anoopsimon.model.FrameworkProperties;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private final WebDriver driver;

    @Inject
    public Hooks(final WebDriver driver)
    {
        this.driver = driver;
    }

    @Before
    public void openWebSite()
    {
        String url =ConfigFactory.create(FrameworkProperties.class).webAppUrl();
        System.out.println("URL :" + url);
        driver.navigate().to(url);
    }

    @After
    public void closeSession() {
        driver.close();
    }
}
