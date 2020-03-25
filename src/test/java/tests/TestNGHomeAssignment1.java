package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserFactory;

import java.util.List;

//Step 1. Go to “https://practicecybertekschool.herokuapp.com”
//Step 2. Click on “Registration Form”
//Step 3. Enter “wrong_dob” into date of birth input
//box.
//Step 4. Verify that warning message is displayed:
//“The date of birth is not valid”
public class TestNGHomeAssignment1 {
private String URL="https://practice-cybertekschool.herokuapp.com/";
private WebDriver driver;
@Test
public void test1() throws InterruptedException {
    driver.findElement(By.name("email")).sendKeys("wrong_dob", Keys.ENTER);
    String expectedMessage= "email address is not a valid";
            String actualMessage =driver.findElement(By.xpath("//small[contains(text(),'email address is not a valid')]")).getText();
    Assert.assertEquals(actualMessage,expectedMessage);

}
    @Test
public void test2() throws InterruptedException {
        //Step 3. Verify that following options for
        //programming languages are displayed: c++, java,
        //JavaScript
        List<WebElement> checkboxes=driver.findElements(By.tagName("form-check-input"));
        for (WebElement checkbox:checkboxes
             ) {
            Thread.sleep(5);
            if (checkbox.isDisplayed()){
                System.out.println("Test Passed");
            }else{
                System.out.println("Test Failed");
            }
        }


    }

@Test
public void test3() throws Exception {
//Test case #3
//Step 3. Enter only one alphabetic character into first
//name input box.
//Step 4. Verify that warning message is displayed:
//“first name must be more than 2 and less than 64
//characters long”
    driver.findElement(By.xpath("//input[@placeholder='first name']")).sendKeys("a", Keys.ENTER);
    String expected="first name must be more than 2 and less than 64 characters long";
    String actual=driver.findElement(By.xpath("//small[contains(text(),'first name must be more than 2 and less than 64 ch')]")).getText();
Assert.assertEquals(actual,expected);
}
    @Test
    public void test4()  {

        //Step 3. Enter only one alphabetic character into last
        //name input box.
        //Step 4. Verify that warning message is displayed:
        //“The last name must be more than 2 and less than
        //64 characters long
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("a", Keys.ENTER);
        String expected="The last name must be more than 2 and less than 64 characters long";
        String actual=driver.findElement(By.xpath("//small[contains(text(), 'The last name must be more than 2 and less than 64 characters long')]")).getText();
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void test5() throws Exception {
//Step 3. Enter any valid first name.
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("Monika");
//Step 4. Enter any valid last name.
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Belucci");
//Step 5. Enter any valid user name.
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("monika");
//Step 6. Enter any valid password.
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("verySecret");
        //email
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("monica@gmail.com");
//Step 7. Enter any valid phone number.
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("703-345-1111");
//Step 8. Select gender.
        driver.findElement(By.cssSelector("input[value='female']")).click();
//Step 9. Enter any valid date of birth.
        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("02/07/2000");
//Step 10. Select any department.
        Select select=new Select(driver.findElement(By.cssSelector("select[name='department']")));
        select.selectByVisibleText("Department of Engineering");
//Step 11. Enter any job title.
        Select select2=new Select(driver.findElement(By.cssSelector("select[name='job_title']")));
        select2.selectByVisibleText("Product Owner");
//Step 12. Select java as a programming language.
        driver.findElement(By.cssSelector("input[id='inlineCheckbox2']")).click();
//Step 13. Click Sign up.
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(5);
//Step 14. Verify that following success message is
//displayed: “You've successfully completed
//registration!”
String actual =driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/p")).getText();
String expected="You've successfully completed registration!";
Assert.assertEquals(actual,expected);
    }

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
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
        Thread.sleep(5);
        driver.findElement(By.linkText("Registration Form")).click();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
