package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGHomeAssignment2 {
    private WebDriver driver;
    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().version("79").setup();
        //INTERVIEW QUESTION: HOW TO HANDLE SSL ISSUES IN SELENIUM?
        //ChromeOptions - use to customize browser for tests
        ChromeOptions chromeOptions = new ChromeOptions();
        //to ignore "Your connection is not private issue"
        chromeOptions.setAcceptInsecureCerts(true);
        //provide chromeOptions object into chromedriver constructor
        driver = new ChromeDriver(chromeOptions);
        //Step 1. Go to "http://practice.cybertekschool.com"
        driver.get("http://practice.cybertekschool.com");
        driver.findElement(By.linkText("Status Codes")).click();
    }
    @Test
    public void test9() {
        //Step 2. And click on "Status Codes".
//        driver.findElement(By.linkText("Status Codes")).click();
        //Step 3. Then click on "200".
        driver.findElement(By.linkText("200")).click();
        //Step 4. Verify that following message is displayed:
        //"This page returned a 200 status code"
        WebElement msg = driver.findElement(By.tagName("p"));
        Assert.assertTrue(msg.getText().contains("This page returned a 200 status code"));
    }
    @Test
    public void test10() {

//Step 3. Then click on “301”.
        driver.findElement(By.linkText("301")).click();
//Step 4. Verify that following message is displayed:
        WebElement element = driver.findElement(By.xpath("//div//p[text()][1]"));
        Assert.assertTrue(element.isDisplayed());

//“This page returned a 301 status code”


    }
    @Test
    public void test11() {
        //Step 4. Then click on “404”.
        driver.findElement(By.linkText("404")).click();
        //Step 5. Verify that following message is displayed:
        WebElement element = driver.findElement(By.xpath("//div//p[text()][1]"));
        Assert.assertTrue(element.isDisplayed());
        //“This page returned a 404 status code”
    }
    @Test
    public void test12() {
//Step 4. Then click on “500”.
        driver.findElement(By.linkText("500")).click();
//Step 5. Verify that following message is displayed:
        WebElement element = driver.findElement(By.xpath("//div//p[text()][1]"));
        Assert.assertTrue(element.isDisplayed());
//“This page returned a 500 status code
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}