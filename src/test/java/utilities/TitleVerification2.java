package utilities;

import org.openqa.selenium.WebDriver;

import java.util.*;

public class TitleVerification2 {
    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        List<String> urls = Arrays.asList("https://lulugandgeorgia.com", "https://wayfair.com/", "https://walmart.com",
               "https://westelm.com/");
        //Visit all websites one by one. 4.Verify that URLof the website contains the title of the website.
        // Ignore spacesand casein comparison. For example,title of in the first websitetitle is Lulu and Giorgiaand
        // the URLis https://lulugandgeorgia.com.
        // So,this test case must pass.5.Close the browser at theendof the test.
//        String titlLulu="Lulu and Georgia";
//        String titlWayfair="Wayfair";
//        String titleWalmart="walmart";
//        String titleWestelm="Westelm";
        for (String each : urls ) {
            driver.get(each);
            String title = driver.getTitle();
            title = title.replace(" ", "").toLowerCase();
            System.out.println(each + " contains: " + title + " ? " + each.contains(title));
        }
        driver.quit();
    }
}


