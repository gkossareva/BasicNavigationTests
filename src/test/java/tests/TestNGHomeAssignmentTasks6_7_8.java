package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;

public class TestNGHomeAssignmentTasks6_7_8 {
    private WebDriver driver;
    @Test
    public void Test6()throws Exception{
    //Test case #6

        //Step 1. Go to "https://www.tempmailaddress.com/"
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
        Thread.sleep(3);
        String  email=driver.findElement(By.id("email")).getText();
        //Step 2. Copy and save email as a string.
        //Step 3. Then go to “https://practicecybertekschool.herokuapp.com”
        driver.navigate().to("http://practice.cybertekschool.com/");
        //Step 4. And click on “Sign Up For Mailing List".
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        //Step 5. Enter any valid name.
        driver.findElement(By.cssSelector("input[name='full_name']")).sendKeys("Maggie");
        //Step 6. Enter email from the Step 2.
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        //Step 7. Click Sign Up
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        //Step 8. Verify that following message is displayed:
        String actual=driver.findElement(By.cssSelector("h3[class='subheader']")).getText();
        String expected="Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actual,expected);
        //“Thank you for signing up. Click the button below to
        //return to the home page.”
        //Step 9. Navigate back to the “https://
        //www.tempmailaddress.com/”
        //driver.navigate().to("https://www.tempmailaddress.com/");
        driver.navigate().back();
       Thread.sleep(1);//
        driver.navigate().back();
        Thread.sleep(1);
        driver.navigate().back();
        Thread.sleep(1);
        driver.navigate().refresh();
        Thread.sleep(1);
        //Step 10. Verify that you’ve received an email from
        //“do-not-reply@practice.cybertekschool.com”
        driver.findElement(By.cssSelector("a[class='navbar-brand']")).click();
        Thread.sleep(5);
        //Step 11. Click on that email to open it.
        Actions actions = new Actions(driver);
        WebElement elementLocator = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]"));
        actions.doubleClick(elementLocator).perform();
        //Step 12. Verify that email is from: “do-notreply@practice.cybertekschool.com”
        String expectedEmail="do-not-reply@practice.cybertekschool.com";
        String actualEmail = driver.findElement(By.id("odesilatel")).getText();
        Assert.assertEquals(actualEmail, expectedEmail);

        //Step 13. Verify that subject is: “Thanks for
        String expected2 = "Thanks for subscribing to practice.cybertekschool.com!";
        String actual2 = driver.findElement(By.id("predmet")).getText();
        Assert.assertEquals(actual2, expected2);
  }
  @Test
  public void Test7() throws Exception{
      driver.get("http://practice.cybertekschool.com/");
      driver.manage().window().maximize();
      driver.findElement(By.linkText("File Upload")).click();
      WebElement upload = driver.findElement(By.id("file-upload"));
      upload.sendKeys("/Users/galinakossareva/Downloads/FileToUpload.txt");
      Thread.sleep(3);
      driver.findElement(By.id("file-submit")).click();
      Thread.sleep(3);
      WebElement element = driver.findElement(By.xpath("//h3[text()=\"File Uploaded!\"]"));
      Assert.assertTrue(element.isDisplayed());

    }

    @Test
    public  void Test8()throws Exception{
        //Step 1. Go to “https://practicecybertekschool.herokuapp.com”
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
        //Step 2. And click on “Autocomplete”.
        driver.findElement(By.linkText("Autocomplete")).click();
        //Step 3. Enter “United States of America” into
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        //country input box.
        //Step 4. Verify that following message is displayed:
        driver.findElement(By.cssSelector("input[type='button']")).click();
        Thread.sleep(3);
        //“You selected: United States of America”
       String actualresult= driver.findElement(By.id("result")).getText();
       String expectedresult= "You selected: United States of America";

       Assert.assertEquals(actualresult,expectedresult);
        //Optional: If you want to to be a real selenium hero,
        //use @DataProvider for for tests cases from 9
        //through 12.
        //Please use following documentation: https://
        //testng.org/doc/documentationmain.html#parameters-dataproviders
    }
    @BeforeMethod
    public void setup()  {
        WebDriverManager.chromedriver().version("79").setup();
        //INTERVIEW QUESTION: HOW TO HANDLE SSL ISSUES IN SELENIUM?
        //ChromeOptions - use to customize browser for tests
        ChromeOptions chromeOptions = new ChromeOptions();
        //to ignore "Your connection is not private issue"
        chromeOptions.setAcceptInsecureCerts(true);
        //provide chromeOptions object into chromedriver constructor
        driver = new ChromeDriver(chromeOptions);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}