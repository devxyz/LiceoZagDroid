package it.gov.scuolesuperiorizagarolo;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

public class TestWebDriverFirefox {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;
        Map<String, Object> vars;
        JavascriptExecutor js;

        System.setProperty("webdriver.gecko.driver", "/Users/stefano/DATA-EXT/lib-java/selenium/geckodriver");

        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        //xxxx(driver);

        // Test name: prova
        // Step # | name | target | value | comment
        // 1 | open | https://re23.axioscloud.it/Secret/REStart.aspx?Customer_ID=93015960581 |  |
        driver.get("https://re23.axioscloud.it/Secret/REStart.aspx?Customer_ID=93015960581");
        Thread.sleep(500);
        // 3 | type | id=txtUser | 6285 |
        driver.findElement(By.id("txtUser")).sendKeys("6285");
        Thread.sleep(500);
        // 4 | type | id=txtPassword | MyosvY2018 |
        driver.findElement(By.id("txtPassword")).sendKeys("MyosvY2018");
        Thread.sleep(500);
        // 5 | click | id=btnLogin |  |
        driver.findElement(By.id("btnLogin")).click();
        Thread.sleep(500);
        // 6 | click | id=ContentPlaceHolderMenu_ddlClassi |  |
        driver.findElement(By.id("ContentPlaceHolderMenu_ddlClassi")).click();
        Thread.sleep(500);
        // 7 | select | id=ContentPlaceHolderMenu_ddlClassi | label=1^A SCIENTIFICO (RELIGIONE) |
        {// Test name: prova
            {
                WebElement dropdown = driver.findElement(By.id("ContentPlaceHolderMenu_ddlClassi"));
                dropdown.findElement(By.xpath("//option[. = '1^A SCIENTIFICO (RELIGIONE)']")).click();
                Thread.sleep(500);
            }
            // 8 | click | css=optgroup > option:nth-child(1) |  |
            driver.findElement(By.cssSelector("optgroup > option:nth-child(1)")).click();
            // 9 | mouseDownAt | css=#ContentPlaceHolderMenu_ddlFT > option:nth-child(1) | -1060,-92.10000610351562 |
            {
                WebElement element = driver.findElement(By.cssSelector("#ContentPlaceHolderMenu_ddlFT > option:nth-child(1)"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).clickAndHold().perform();
                Thread.sleep(500);
            }
            // 10 | mouseMoveAt | css=#ContentPlaceHolderMenu_ddlFT > option:nth-child(1) | -1060,-92.10000610351562 |
            {
                WebElement element = driver.findElement(By.cssSelector("#ContentPlaceHolderMenu_ddlFT > option:nth-child(1)"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
                Thread.sleep(500);
            }
            // 11 | mouseUpAt | css=#ContentPlaceHolderMenu_ddlFT > option:nth-child(1) | -1060,-92.10000610351562 |
            {
                WebElement element = driver.findElement(By.cssSelector("#ContentPlaceHolderMenu_ddlFT > option:nth-child(1)"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).release().perform();
                Thread.sleep(500);
            }
            // 12 | select | id=ContentPlaceHolderMenu_ddlFT | label=TRIMESTRE |
            {
                WebElement dropdown = driver.findElement(By.id("ContentPlaceHolderMenu_ddlFT"));
                dropdown.findElement(By.xpath("//option[. = 'TRIMESTRE']")).click();
                Thread.sleep(500);
            }
            // 13 | click | css=#ContentPlaceHolderMenu_ddlFT > option:nth-child(1) |  |
            driver.findElement(By.cssSelector("#ContentPlaceHolderMenu_ddlFT > option:nth-child(1)")).click();
            Thread.sleep(500);
            // 14 | click | id=ContentPlaceHolderBody_Button_RE_Scrutini_TAB |  |
            driver.findElement(By.id("ContentPlaceHolderBody_Button_RE_Scrutini_TAB")).click();
            Thread.sleep(500);
            // 15 | click | css=tr:nth-child(3) > .azioni |  |
            driver.findElement(By.cssSelector("tr:nth-child(3) > .azioni")).click();
            Thread.sleep(500);
            // 16 | selectFrame | index=2 |  |
            driver.switchTo().frame(2);
            Thread.sleep(500);
            // 17 | click | id=Button2 |  |
            driver.findElement(By.id("Button2")).click();
            Thread.sleep(500);
            // 18 | selectFrame | relative=parent |  |
            driver.switchTo().defaultContent();
            Thread.sleep(500);
            // 19 | mouseOver | css=tr:nth-child(7) > .azioni |  |
            {
                WebElement element = driver.findElement(By.cssSelector("tr:nth-child(7) > .azioni"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
                Thread.sleep(500);
            }
            // 20 | mouseOut | css=tr:nth-child(7) > .azioni |  |
            {
                WebElement element = driver.findElement(By.tagName("body"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element, 0, 0).perform();
                Thread.sleep(500);
            }
            // 21 | click | css=tr:nth-child(15) > .azioni |  |
            driver.findElement(By.cssSelector("tr:nth-child(15) > .azioni")).click();
            // 22 | selectFrame | index=2 |  |
            driver.switchTo().frame(2);
            // 23 | runScript | window.scrollTo(0,30) |  |
            //js.executeScript("window.scrollTo(0,30)");
            // 24 | click | id=Button2 |  |
            driver.findElement(By.id("Button2")).click();
            Thread.sleep(500);
            // 25 | selectFrame | relative=parent |  |
            driver.switchTo().defaultContent();
            // 26 | click | id=btnBack |  |
            driver.findElement(By.id("btnBack")).click();
            Thread.sleep(500);
            // 27 | click | id=btnExit |  |
            driver.findElement(By.id("btnExit")).click();
            Thread.sleep(500);
            WebElement dropdown = driver.findElement(By.id("ContentPlaceHolderMenu_ddlClassi"));
            dropdown.findElement(By.xpath("//option[. = '1^A SCIENTIFICO (RELIGIONE)']")).click();
        }
        // 8 | click | css=optgroup > option:nth-child(1) |  |
        driver.findElement(By.cssSelector("optgroup > option:nth-child(1)")).click();
        // 9 | mouseDownAt | css=#ContentPlaceHolderMenu_ddlFT > option:nth-child(1) | -1060,-92.10000610351562 |
        {
            WebElement element = driver.findElement(By.cssSelector("#ContentPlaceHolderMenu_ddlFT > option:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        // 10 | mouseMoveAt | css=#ContentPlaceHolderMenu_ddlFT > option:nth-child(1) | -1060,-92.10000610351562 |
        {
            WebElement element = driver.findElement(By.cssSelector("#ContentPlaceHolderMenu_ddlFT > option:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        // 11 | mouseUpAt | css=#ContentPlaceHolderMenu_ddlFT > option:nth-child(1) | -1060,-92.10000610351562 |
        {
            WebElement element = driver.findElement(By.cssSelector("#ContentPlaceHolderMenu_ddlFT > option:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        // 12 | select | id=ContentPlaceHolderMenu_ddlFT | label=TRIMESTRE |
        {
            WebElement dropdown = driver.findElement(By.id("ContentPlaceHolderMenu_ddlFT"));
            dropdown.findElement(By.xpath("//option[. = 'TRIMESTRE']")).click();
        }
        // 13 | click | css=#ContentPlaceHolderMenu_ddlFT > option:nth-child(1) |  |
        driver.findElement(By.cssSelector("#ContentPlaceHolderMenu_ddlFT > option:nth-child(1)")).click();
        // 14 | click | id=ContentPlaceHolderBody_Button_RE_Scrutini_TAB |  |
        driver.findElement(By.id("ContentPlaceHolderBody_Button_RE_Scrutini_TAB")).click();
        // 15 | click | css=tr:nth-child(3) > .azioni |  |
        driver.findElement(By.cssSelector("tr:nth-child(3) > .azioni")).click();
        // 16 | selectFrame | index=2 |  |
        driver.switchTo().frame(2);
        // 17 | click | id=Button2 |  |
        driver.findElement(By.id("Button2")).click();
        // 18 | selectFrame | relative=parent |  |
        driver.switchTo().defaultContent();
        // 19 | mouseOver | css=tr:nth-child(7) > .azioni |  |
        {
            WebElement element = driver.findElement(By.cssSelector("tr:nth-child(7) > .azioni"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        // 20 | mouseOut | css=tr:nth-child(7) > .azioni |  |
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        // 21 | click | css=tr:nth-child(15) > .azioni |  |
        driver.findElement(By.cssSelector("tr:nth-child(15) > .azioni")).click();
        // 22 | selectFrame | index=2 |  |
        driver.switchTo().frame(2);
        // 23 | runScript | window.scrollTo(0,30) |  |
        js.executeScript("window.scrollTo(0,30)");
        // 24 | click | id=Button2 |  |
        driver.findElement(By.id("Button2")).click();
        // 25 | selectFrame | relative=parent |  |
        driver.switchTo().defaultContent();
        // 26 | click | id=btnBack |  |
        driver.findElement(By.id("btnBack")).click();
        // 27 | click | id=btnExit |  |
        driver.findElement(By.id("btnExit")).click();
    }

    private static void xxxx(WebDriver driver) throws InterruptedException {
        driver.get("https://re23.axioscloud.it/Secret/REStart.aspx?Customer_ID=93015960581");
        Thread.sleep(1000);
        driver.findElement(By.id("txtUser")).sendKeys("6285");
        Thread.sleep(500);
        driver.findElement(By.id("txtPassword")).sendKeys("MyosvY2018");
        Thread.sleep(500);
        driver.findElement(By.id("btnLogin")).click();
        Thread.sleep(500);
        driver.findElement(By.id("ContentPlaceHolderMenu_ddlClassi")).click();
        Thread.sleep(500);
        {
            WebElement dropdown = driver.findElement(By.id("ContentPlaceHolderMenu_ddlClassi"));
            dropdown.findElement(By.xpath("//option[. = '1^A SCIENTIFICO (ITALIANO)']")).click();
            Thread.sleep(500);
        }
        driver.findElement(By.cssSelector("optgroup > option:nth-child(2)")).click();
        Thread.sleep(500);
        driver.findElement(By.id("ContentPlaceHolderBody_Button_RE_Scrutini_TAB")).click();


        Thread.sleep(10000);

        driver.findElement(By.id("btnExit")).click();
    }
}
