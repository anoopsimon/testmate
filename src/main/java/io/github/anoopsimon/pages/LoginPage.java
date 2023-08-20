package io.github.anoopsimon.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

public class LoginPage
{
    @Inject
    private WebDriver driver;

    public LoginPage(WebDriver webDriver) {
        this.driver =webDriver;
    }

    public void login(){
    }
}
