package utility.aie;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RicercaAIE {
    public static void main(String[] args) throws IOException {
        WebDriver driver;
        Map<String, Object> vars;
        JavascriptExecutor js;
        System.setProperty("webdriver.chrome.driver", "/Users/stefano/DATA-EXT/seleniumdriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();


        String ii = "9788805070237\n" +
                "9788823434295\n" +
                "9788839522979\n" +
                "9788839522962\n" +
                "9788808737441\n" +
                "9788808152879\n" +
                "9788839521927\n" +
                "9788808537812\n" +
                "9788808320742\n" +
                "9788808721204\n" +
                "9788808201638\n" +
                "9788863644449\n" +
                "9788808276414\n" +
                "9788863084023\n" +
                "9788857790466\n" +
                "9788808201638\n" +
                "9788863644449\n" +
                "9788808276414\n" +
                "9788863084023\n" +
                "9788857790466\n";
        double somma = 0;
        for (String isbn : ii.split("\n")) {
            ArrayList<String> elementi = getStrings(driver, isbn);
            //System.out.println("==========================");
            String prezzo = elementi.get(elementi.size() - 1).replace(",", ".");

            somma += Double.parseDouble(prezzo);
            System.out.println(elementi.get(0) + "\t" + elementi.get(3) + "\t" + prezzo);
            /*for (String s : elementi) {
                System.out.println(s);
            }*/

        }
        System.out.println();
        System.out.println(somma);
        driver.close();


    }

    public static ArrayList<String> getStrings(WebDriver driver, String isbn) {
        driver.get("https://www.adozioniaie.it/RicercaOpere.cgi?FormName=ISBN_Search&FormAction=search&CODVOL=" + isbn);
        String pageSource = driver.getPageSource();
        String[] split = pageSource.split("\n");
        ArrayList<String> elementi = new ArrayList<>();
        for (String line : split) {
            String str = "class=\"ODF\">";
            int i = line.indexOf(str);
            if (i >= 0) {
                String tot = line.substring(i + str.length());
                int j = tot.indexOf("</");
                String value = tot.substring(0, j);
                elementi.add(value.replaceAll("&nbsp;", " ").trim());
            }
        }
        return elementi;
    }
}
