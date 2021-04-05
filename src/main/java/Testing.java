import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class Testing {
    public WebDriver driver;
    public ChromeOptions options;
    //Wait wait;





    @BeforeClass
    void before(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://demo.opencart.com/index.php?route=common/home");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//h4/a[text()='MacBook']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[text() = 'Add to Cart']")).click();
    }


    @Test(priority=1)
    void test1(){
        driver.findElement(By.xpath("//ul/li/a[text() = 'Apple']")).click();
        WebElement title = driver.findElement(By.xpath("//div[@id='content']/h2[text()='Apple']"));
        boolean actual = title.isDisplayed();
        Assert.assertTrue(actual, "Oops! Something went wrong");
    }

    @Test(priority=2)
    void test2(){
        driver.findElement(By.xpath("//div[@class='caption' and .//h4[.='MacBook']]//following-sibling::div//span[.='Add to Cart']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       /*
        Wait wait = new WebDriverWait(driver, 2);
       wait.until(driver -> {
           JavascriptExecutor jse = (JavascriptExecutor)driver;
           String active = jse.executeScript("return window.jQuery.active").toString();
           return active.equals("0");
        });
        */
        WebElement message = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        Wait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
        boolean condition1 = message.isDisplayed();
        Assert.assertTrue(condition1, "Oops! Something went wrong");
        WebElement require = driver.findElement(By.xpath("//div[@class = 'alert alert-success alert-dismissible']"));
        boolean actually = require.getText().contains("Success: You have added MacBook to your shopping cart!");
        Assert.assertTrue(actually, "Oops! Something went wrong");
        WebElement cart = driver.findElement(By.xpath("//span[@id = 'cart-total'] "));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id = 'cart-total'] ")));
        boolean condition2 = cart.getText().contains("2 item(s)");
        Assert.assertTrue(condition2, "Oops! Something went wrong");


    }


    @Test(priority=3)
    void test3(){
        driver.findElement(By.xpath("//div[@id = 'cart']/button")).click();
        Wait wait2 = new WebDriverWait(driver, 30);
        wait2.until(driver -> {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            String active = jse.executeScript("return window.jQuery.active").toString();
            return active.equals("0");
        });
        WebElement name = driver.findElement(By.xpath("//ul[@class = 'dropdown-menu pull-right']/li/table/tbody/tr"));
        boolean actual1 = name.getText().contains("MacBook");
        Assert.assertTrue(actual1, "Oops! Something went wrong");
        boolean actual2 = name.getText().contains("x 2");
        Assert.assertTrue(actual2, "Oops! Something went wrong");
    }

    @Test(priority=4)
    void test4(){
        driver.findElement(By.xpath("//p[@class = 'text-right']/a/strong")).click();
        WebElement page = driver.findElement(By.xpath("//div[@id = 'content']/h1"));
        boolean actual3 = page.getText().contains("Shopping Cart");
        Assert.assertTrue(actual3, "Oops! Something went wrong");
        WebElement amount = driver.findElement(By.xpath("//input[@class = 'form-control']"));
        boolean actual4 = amount.getAttribute("value").contains("2");
        Assert.assertTrue(actual4, "Oops! Something went wrong");

    }

    @Test(priority=5)
    void test5(){
       WebElement input =  driver.findElement(By.xpath("//input[@class = 'form-control']"));
       input.click();
       input.sendKeys(Keys.BACK_SPACE);
       input.sendKeys("1");
       driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();
        Wait wait1 = new WebDriverWait(driver, 30);
        wait1.until(driver -> {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            String active = jse.executeScript("return window.jQuery.active").toString();
            return active.equals("0");
        });
        WebElement cart1 = driver.findElement(By.xpath("//span[@id = 'cart-total'] "));
        boolean actual3 = cart1.getText().contains("1 item(s)");
        Assert.assertTrue(actual3, "Oops! Something went wrong");






    }


    @AfterClass
    void end(){
        driver.findElement(By.xpath("//a[text()='Your Store']")).click();
    }







//WebElement amount = driver.findElement(By.xpath("//ul[@class = 'dropdown-menu pull-right']/li/table/tbody/tr/td[@class = 'text-right']"));
//        boolean actual1 = amount.getText().contains("x 2");

  /*
    @BeforeClass
    void before(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://demo.opencart.com/index.php?route=common/home");
        driver.findElement(By.xpath("//h4/a[text() = 'MacBook']")).click();
        driver.findElement(By.xpath("//button[@id = 'button-cart']")).click();
      //  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=' Success: You have added ']")));
    }
    @Test(priority = 1)
    void test1(){
        driver.findElement(By.xpath("//ul/li/a[text() ='Apple'] ")).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text() ='Apple']")));
        WebElement title = driver.findElement(By.xpath("//h2[text() ='Apple']"));
        boolean actual = title.isDisplayed();
        Assert.assertTrue(actual, "Something went wrong");




        // //h2[text() ='Apple']

    }

   */





}
