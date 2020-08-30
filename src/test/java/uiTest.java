import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class uiTest {
    private WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.get("https://www.cleartrip.com/");
    }
    @Test(enabled = false)
    public void initialLogin() throws InterruptedException {
        driver.findElement(By.id("FromTag")).sendKeys("Hyd");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//ul[@id='ui-id-1']/li/a[contains(text(),'Hyderabad, IN')]")).click();
        driver.findElement(By.id("ToTag")).sendKeys("Del");
        driver.findElement(By.xpath("//ul[@id='ui-id-" +
                "+z2']/li/a[contains(text(),'New Delhi, IN')]")).click();
        driver.findElement(By.xpath("//input[@id='DepartDate']")).click();
        driver.findElement(By.xpath("//td[@class=' ui-datepicker-days-cell-over undefined selected']")).click();
        driver.findElement(By.id("SearchBtn")).click();

    }
    @AfterClass
    public void closure(){
        driver.quit();
    }
}
