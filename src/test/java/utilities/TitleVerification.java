package utilities;

import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.*;
public class TitleVerification {
    public static void main(String[] args) throws Exception {
        List<String> urls = Arrays.asList("http://practice.cybertekschool.com/",
                "http://practice.cybertekschool.com/dropdown", "http://practice.cybertekschool.com/login");

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://google.com");
        for (String each:urls
             ) {
int count=0;
if (each.startsWith("http://practice.cybertekschool.com")){
    driver.navigate().to(each);
    Thread.sleep(3);
    System.out.println("URL starts with http://practice.cybertekschool.com");
    System.out.println("Test passed "+driver.getTitle());
}else{
    System.out.println("Actual URL "+each);
    System.out.println("This URL does not start with http://practice.cybertekschool.com");

}
String title1=driver.getTitle();
            for (String each2:urls
                 ) {
                count++;
                int a=each2.indexOf(driver.getTitle());
                String title2 =driver.getTitle();
                if (count==1);
                if (title1.equals(title2)){
                    System.out.println("Titels equal");
                    System.out.println("Test passed");
                }else{
                    System.out.println("Test failed");
                }
                }

            }
        driver.quit();
    }

    }

