import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstTestCase {
    WebDriver driver;
    @BeforeSuite
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/alerts");

    }
    /*
    @Test(dataProvider = "loginData")
    public void loginTest(String userid, String passwd,String expected_url){
        WebElement userid_fld = driver.findElement(By.name("uid"));
        WebElement passwd_fld = driver.findElement(By.name("password"));
        WebElement log_btn = driver.findElement(By.name("btnLogin"));
        userid_fld.sendKeys(userid);
        passwd_fld.sendKeys(passwd);
        log_btn.click();
        Assert.assertEquals(driver.getCurrentUrl(), expected_url);

    }

     */
    @Test(priority = 0)
    public void click_me() throws InterruptedException {
        WebElement click_btn = driver.findElement(By.xpath("//*[@id=\"alertButton\"]"));
        //click_btn.click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        boolean hasAlert = ExpectedConditions.alertIsPresent().apply(driver) !=null;
        System.out.println(hasAlert);


    }
    @Test(priority = 1)
    public void click_conf(){
        WebElement confirm_btn = driver.findElement(By.xpath("//*[@id=\"confirmButton\"]"));
        confirm_btn.click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        WebElement ok = driver.findElement(By.id("confirmResult"));
        System.out.println(ok.getText().contains("Ok"));
        confirm_btn.click();
        driver.switchTo().alert().dismiss();
        WebElement cancel = driver.findElement(By.id("confirmResult"));
        System.out.println(cancel.getText().contains("Cancel"));

    }
    @Test(priority = 2)
    public void prompt(){
        WebElement prompt_btn = driver.findElement(By.id("promtButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", prompt_btn);
        prompt_btn.click();
        driver.switchTo().alert().sendKeys("Alyara");
        driver.switchTo().alert().accept();
        WebElement text = driver.findElement(By.id("promptResult"));
        System.out.println(text.getText().contains("Alyara"));
        prompt_btn.click();
        driver.switchTo().alert().dismiss();
        try{
            WebElement conf = driver.findElement(By.id("promptResult"));
        }
        catch(NoSuchElementException e){
            System.out.println("No such alert");
        }

    }
    @Test(priority = 3)
    public void time_alert(){
        try {
            WebElement time_alert = driver.findElement(By.id("timerAlertButton"));
            time_alert.click();
            int myMinutes = 5;
            Duration durationInMinutes = Duration.ofMinutes(myMinutes);
            WebDriverWait wait = new WebDriverWait(driver, durationInMinutes);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert simpleAlert = driver.switchTo().alert();
            simpleAlert.accept();
            System.out.println("Unexpected alert accepted");
        } catch (Exception e) {
            System.out.println("unexpected alert not present");
        }

    }

    @AfterSuite
    public void shutDown(){
        driver.close();
    }
    /*
    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object[][]{
                {"mngr481324", "ybAhYzY","https://demo.guru99.com/v4/manager/Managerhomepage.php"}
        };
    }

*/

}
//mngr481324
//ybAhYzY
