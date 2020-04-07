package tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HomeAssignment_4 {
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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void days() {
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        List<WebElement> l1 = driver.findElements(By.cssSelector(".gwt-CheckBox>label"));
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".gwt-CheckBox>input"));
        Random r = new Random();
        int count = 0;
        while (count < 3) {
            // this method will return any value between 0 and 7
            int index = r.nextInt(l1.size());
            if (checkboxes.get(index).isEnabled()) {
                l1.get(index).click();
                if (l1.get(index).getText().equals("Friday")) {
                    count++;
                }
                System.out.println(l1.get(index).getText());
                l1.get(index).click();
            }
        }
    }

    @Test
    public void todays_date() {
//1. go to http://practice.cybertekschool.com/dropdown
        driver.get("http://practice.cybertekschool.com/dropdown");
        WebElement year = driver.findElement(By.id("year"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        Select y = new Select(year);
        Select m = new Select(month);
        Select d = new Select(day);
        String year_value = y.getFirstSelectedOption().getText();
        String month_value = y.getFirstSelectedOption().getText();
        String day_value = y.getFirstSelectedOption().getText();
//2. verify that dropdowns under Select your date of birth display current year, month, day

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMMMdd");
        Assert.assertEquals(year_value + month_value + day, sf.format(new Date()));
    }

    @Test
    public void years_months_days(){
        driver.get("http://practice.cybertekschool.com/dropdown");
        WebElement year = driver.findElement(By.id("year"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        Select y = new Select(year);
        Select m = new Select(month);
        Select d = new Select(day);
        Random r = new Random();
        int index = r.nextInt(y.getOptions().size());
        y.selectByIndex(index);
        List<String> months31 = new ArrayList<>(Arrays.asList(new String[]{"January", "March", "May", "July", "August", "October", "December"}));
        int febDays;
        int yearValue = Integer.parseInt(y.getFirstSelectedOption().getText());
        if(yearValue %400==0 || (yearValue%4 ==0 && yearValue %100!=0)){
            febDays=29;
        }else{
            febDays=28;
        }
        for(int i =0; i<12; i++){
            m.selectByIndex(i);
            if(months31.contains(m.getFirstSelectedOption().getText())){
                Assert.assertEquals(d.getOptions().size(), 31);
            }else if(m.getFirstSelectedOption().getText().equals("February")){
                Assert.assertEquals(d.getOptions().size(), febDays);
            }else{
                Assert.assertEquals(d.getOptions().size(), 30);
            }
        }
    }
    @Test
    public void department_sort() {
        //DEPARTMENTS SORT
        //1. go to  https://www.amazon.com
        driver.get("https://www.amazon.com");
        //2. verify that default value of the All departments dropdown is All
        Assert.assertEquals(driver.findElement(By.cssSelector("span[class='nav-search-label']")).getText(), "All");
//        //3. verify  that options in the All departments dropdown are not sorted alphabetically
        List<WebElement> l1 = new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        boolean notAlphabetOrder = false;
        for (int i = 0; i < l1.size() - 1; i++) {
            if (l1.get(i).getText().compareTo(l1.get(i + 1).getText()) > 0) {
                notAlphabetOrder = true;
                break;
            }
//        driver.get("https://www.amazon.com");
//        Assert.assertEquals(driver.findElement(By.cssSelector("nav-search-label")).getText(),"All");
//        List<WebElement> l1=new Select(driver.findElement(By.id("searchDropdownBox"));
        }
        Assert.assertTrue(notAlphabetOrder);
    }
   @Test
    public void main_departments(){
// go to https://www.amazon.com/gp/site-directory
       driver.get("https://www.amazon.com/gp/site-directory");
//2. verify that every main department name (indicated by blue rectangles in the picture) is
//present in the All departments dropdown (indicated by green rectangle in the picture)
       List<WebElement> mainDep = driver.findElements(By.tagName("h2"));
       List<WebElement> allDep = new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();

       Set<String> mainDepS = new HashSet<>();
       Set<String> allDepS = new HashSet<>();
       for( WebElement each : mainDep){
           mainDepS.add(each.getText());
       }
       for( WebElement each : allDep){
           allDepS.add(each.getText());
       }
       for(String each : mainDepS){
           if(!allDepS.contains(each)){
               System.out.println(each);
               System.out.println("This main dep is not in All depattments list");
           }
       }
       Assert.assertTrue(allDep.containsAll(mainDepS));

   }
   @Test
   public void links(){
        //1. go to https://www.w3schools.com/
       //2. find all the elements in the page with the tag a
       //3. for each of those elements, if it is displayed on the page, print the text and the href of that
       //element.
       driver.get("https://www.w3schools.com/");
      List<WebElement> l1=driver.findElements(By.tagName("a"));
       for (WebElement each:l1) {
         if (each.isDisplayed()){
             System.out.println(each.getText());
             System.out.println(each.getAttribute("href"));
         }
       }
   }
   @Test
    public void validLink(){
        //VALID LINKS
       //1. go to https://www.selenium.dev/documentation/en/
       driver.get("https://www.selenium.dev/documentation/en/");
       //2. find all the elements in the page with the tag a
       List<WebElement> l1=driver.findElements(By.tagName("a"));
       //3. verify that all the links are valid
       for (int i = 0; i <l1.size() ; i++) {
           String href=l1.get(i).getAttribute("href");

           try {
               URL url=new URL(href);
               HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
httpURLConnection.setConnectTimeout(3000);
httpURLConnection.connect();

Assert.assertEquals(httpURLConnection.getResponseCode(),200);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
   @Test
    public void cart() {
//go to https://amazon.com
       driver.get("https://amazon.com");
//2. search for "wooden spoon"
       driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
       driver.findElement(By.xpath("//span[@id='nav-search-submit-text']/following-sibling::input")).click();
       List<WebElement> price = driver.findElements(By.xpath("//span[@class='a-price']/span[@class='a-offscreen']"));
       int x = new Random().nextInt(price.size());
       x = x==0?1:x;

       String originName = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])["+x+"]")).getText();

       String originPrice = "$" +
               driver.findElement(By.xpath("(//span[@class='a-price']/span[2]/span[2])["+x+"]")).getText() +"."+
               driver.findElement(By.xpath("(//span[@class='a-price']/span[2]/span[3])["+x+"]")).getText();

       driver.findElement(By.xpath("(//span[@class='a-price-fraction'])["+x+"]")).click();
       Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Qty:']/following-sibling::span")).getText(), "1");
       Assert.assertEquals(driver.findElement(By.id("productTitle")).getText(), originName);
       Assert.assertEquals(driver.findElement(By.id("price_inside_buybox")).getText(), originPrice);
       Assert.assertTrue(driver.findElement(By.id("add-to-cart-button")).isDisplayed());

    }

//5. click on that random result
//6. verify default quantity of items is 1
//7. verify that the name and the price is the same as the one from step 5
//8. verify button "Add to Cart" is visible

    @Test
    public void prime(){
        //1. go to https://amazon.com
        driver.get("https://amazon.com");
        //2. search for "wooden spoon"
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        //3. click search
        driver.findElement(By.xpath("//span[@id='nav-search-submit-text']/following-sibling::input")).click();
        //4. remember name first result that has prime label
        WebElement firstPrimeName=driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2)[1]"));
        String firstName=firstPrimeName.getText();
        //5. select Prime checkbox on the left
        driver.findElement(By.xpath("(//i[@aria-label='Prime Eligible'])")).click();
        //6. verify that name first result that has prime label is same as step 4
        String primeName=driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2)[1]")).getText();
        //7. check the last checkbox under Brand on the left
        Assert.assertEquals(firstName,primeName);
        //8. verify that name first result that has prime label is different
    }
    @Test
    public void moreSpoons(){
        //1. go to https://amazon.com
        driver.get("https://amazon.com");
        //2. search for "wooden spoon"
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.xpath("//span[@id='nav-search-submit-text']/following-sibling::input")).click();
        //3. remember all Brand names on the left///
        List<WebElement> l1 = driver.findElements(By.xpath("//div[@id='brandsRefinements']//ul/li/span/a/span"));
        List<String > s1 = new ArrayList<>();
        for(WebElement each : l1){
            s1.add(each.getText());
        }
        //4. select Prime checkbox on the left
        driver.findElement(By.xpath("//i[@class='a-icon a-icon-prime a-icon-medium']/../div/label/i")).click();
        List<WebElement> l2 = driver.findElements(By.xpath("//div[@id='brandsRefinements']//ul/li/span/a/span"));
        List<String > s2 = new ArrayList<>();
        for(WebElement each : l2){
            s2.add(each.getText());
        }
        Assert.assertEquals(s1,s2);

        //5. verify that same Brand names are still displayed
    }
     }