package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.BrowserFactory;

public class NavigationTests {

    public static void main(String[] args) throws Exception {
        chromeTest();
      //firefoxTest();
        //safariTest();
    }
    public static void chromeTest() throws Exception{
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://google.com");
// 3. Save the title in a string variable
        String title=driver.getTitle();

// 4. Go to https://etsy.com
        driver.navigate().to("https://etsy.com");

// 5. Save the title in a string variable
        String title2=driver.getTitle();

// 6. Navigate back to previous page
        driver.navigate().back();

// 7. Verify that title is same is in step
        if (driver.getTitle().equals(title)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }

// . Navigate forward to previous page
        driver.navigate().forward();

// 9. Verify that title is same is in step 5
        if (driver.getTitle().equals(title2)){
            System.out.println("true");
        }else {
            System.out.println("false");
        }

        driver.quit();
    }
    public static void firefoxTest() throws Exception{
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver=new FirefoxDriver();
        driver = BrowserFactory.getDriver("firefox");
        Thread.sleep(2000);
        driver.get("http://google.com");
        Thread.sleep(3000);
        String title = driver.getTitle();
        driver.navigate().to("https://etsy.com");
        Thread.sleep(2000);
        String title2 = driver.getTitle();
        driver.navigate().back();
        title = driver.getTitle();
        Thread.sleep(2000);
        driver.navigate().to("https://etsy.com");
        title2 = driver.getTitle();
        driver.quit();
    }
    public static void safariTest() throws Exception {
        WebDriver driver = BrowserFactory.getDriver("safari");
        Thread.sleep(2000);
        driver.get("http://google.com");
        Thread.sleep(3000);
        String title = driver.getTitle();
        driver.navigate().to("https://etsy.com");
        Thread.sleep(2000);
        String title2 = driver.getTitle();
        driver.navigate().back();
        title = driver.getTitle();
        Thread.sleep(2000);
        driver.navigate().to("https://etsy.com");
        title2 = driver.getTitle();
        driver.quit();
    }

}