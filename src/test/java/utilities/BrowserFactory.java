package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver getDriver (String browserName ) {
        String str  = System.getProperty("os.name").toLowerCase();
        switch (browserName) {
            case "edge":
                if (str.contains("ac")){
                    return null;
                } else {
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver();
                }
            case "safari":
                if (str.contains("windows")){
                    return null;
                }else {

                    System.out.println("I don't have safari method in WebDriverManager class");
                }
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
        }
        System.out.println("only safari and edge available");
        return null;
    }
}
