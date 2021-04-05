import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.opencart.com/index.php?route=common/home");
        Wait wait = new WebDriverWait(driver, 2);
        wait.until(driver2 -> {
            JavascriptExecutor jse = (JavascriptExecutor)driver2;
            String active = jse.executeScript("return window.jQuery.active").toString();
            return active.equals("0");
        });
        driver.findElement(By.xpath("//h4/a[text()='MacBook']")).click();
        driver.findElement(By.xpath("//button[text() = 'Add to Cart']")).click();

    }
}
