package utilities;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class TitleVerification3 {
    public static void main(String[] args) throws Exception{

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
            WebDriver driver = BrowserFactory.getDriver("chrome");
            driver.get(each);
            String title = driver.getTitle();
            if (each.contains(title.substring(0,title.indexOf(" ")).toLowerCase())){
                System.out.println("The title contains the part of the url");
                System.out.println(each.contains(title.substring(0,title.indexOf(" ")).toLowerCase()));
                System.out.println();
            } else {
                System.out.println("The title is NOT the part of the url");
            }
            Thread.sleep(2000);
            driver.quit();
        }
    }
}