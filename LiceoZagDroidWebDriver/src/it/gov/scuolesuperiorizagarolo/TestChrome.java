package it.gov.scuolesuperiorizagarolo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class TestChrome {
    public static void main(String[] args) {
        WebDriver driver;
        Map<String, Object> vars;
        JavascriptExecutor js;
        System.setProperty("webdriver.chrome.driver", "/Users/stefano/DATA-EXT/seleniumdriver/chromedriver");

        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

        driver.get("https://www.sissiweb.it/SwStart.aspx?Customer_ID=93015960581");

    }
}
