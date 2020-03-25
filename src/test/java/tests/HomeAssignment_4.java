package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class HomeAssignment_4 {
    private WebDriver driver;
    //1. go to http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox
    //2. Randomly select a checkbox. As soon as you check any day, print the name of the day
    //and uncheck immediately.
    //After you check and uncheck Friday for the third time, exit the program.
    //NOTE: Remember some checkboxes are not selectable. You need to find a way to ignore them
    //when they are randomly selected. It has to be dynamic. Do not hard code Saturday and Sunday.
    //Use values of certain attributes.
@Test
    public void Days() throws Exception{
    driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
Select select =new Select(driver.findElement(By.cssSelector("input[type='checkbox']:nth-of-type(1)")));
select.selectByIndex(2);
Thread.sleep(5);
// List <WebElement>options= driver.findElements(By.cssSelector("input[type='checkbox']"));
//
//    Random random=new Random();
//    int index=random.nextInt(5);
//   while(index==0){
//      continue;
//   }
//    options.get(index).click();
  // int csize=options.size();
//  String cvalue[]=new String[csize];
//    for (int i = 0; i <csize ; i++) {
//        String value=options.get(i).getAttribute("name");
//        System.out.println("value = " + value);
//
//    }

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

    }

    @AfterMethod
    public void teardown(){
//        driver.quit();
    }
}


