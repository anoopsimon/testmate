package io.github.anoopsimon.provider;

import io.github.anoopsimon.model.FrameworkProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import javax.inject.Provider;
import java.util.Optional;

public class WebDriverProvider implements Provider<WebDriver> {

    @Override
    public WebDriver get() {
        WebDriver driver;
        String browser = ConfigFactory.create(FrameworkProperties.class).browser();
        switch (browser.toLowerCase()) {
            case "ie":
            case "internet explorer":
                driver = new InternetExplorerDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver= WebDriverManager.edgedriver().create();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "opera":
                driver = new OperaDriver();
                break;
            case "phantomjs":
            case "headless":
                driver = new PhantomJSDriver();
                break;
            case "remote":
                driver = new RemoteWebDriver(new DesiredCapabilities());
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        return driver;
    }
}

