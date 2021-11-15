import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BaseTest extends Locators
{
    @Test
    public void test() throws IOException, InterruptedException
    {
        System.setProperty("webdriver.chrome.driver", "D:\\Docs\\Drivers\\chromedriver.exe");
        DesiredCapabilities chDCap = DesiredCapabilities.chrome();
        chDCap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);                                             //Certification
        chDCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        WebDriver hbDriver = new ChromeDriver();
        WebDriverWait expWait = new WebDriverWait(hbDriver, 30);                                             //Explicit Wait
        Actions actHB = new Actions(hbDriver);
        JavascriptExecutor js = (JavascriptExecutor) hbDriver;                                                             //For scroll down

        Logger logHB = Logger.getLogger("lcwLog");                                                                         //Creating Log file
        FileHandler fileH;
        fileH = new FileHandler("C:\\Users\\Hidalgo\\Desktop\\signUpLog.log");
        logHB.addHandler(fileH);
        SimpleFormatter smpFormatter = new SimpleFormatter();

        hbDriver.get(url);                                                                                                //Open main page and check it's a main page.
        hbDriver.manage().window().maximize();
        Assert.assertEquals(url, hbDriver.getCurrentUrl());
        logHB.info("Main page opened.");

        actHB.moveToElement(hbDriver.findElement(By.xpath(mouseHover))).build().perform();                                 //Mouse hover
        expWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(openMenu)));
        WebElement crsMove = hbDriver.findElement(By.xpath(mouseHover));
        actHB.moveToElement(hbDriver.findElement(By.xpath(login))).click().build().perform();                              //Click 'Giriş Yap'

        expWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(mailTbox)));                                     //Login
        hbDriver.findElement(By.id(mailTbox)).sendKeys("y.ozdemir71@gmail.com");
        expWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(passTbox)));
        hbDriver.findElement(By.id(passTbox)).sendKeys("123Test321");
        hbDriver.findElement(By.id(loginBTN)).click();
        logHB.info("Successfully login.");

        /*IMPORTANT! Hepsiburada have a two different login screen
        First page: have only mail textbox and next button, open new page and enter a pass and login
        Second page: The page both have a mail & pass textboxes.
        My login test case applied for second method. Successfully login.
        */

        expWait.until(ExpectedConditions.urlToBe("https://www.hepsiburada.com/"));
        hbDriver.findElement(By.className(searchbox)).sendKeys("samsung");                                    //Search word 'samsung'
        hbDriver.findElement(By.xpath(searchClick)).click();

        expWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(phoneClick)));                                               //Left menu
        hbDriver.findElement(By.xpath(phoneClick)).click();
        expWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mobileClick)));
        hbDriver.findElement(By.xpath(mobileClick)).click();
        expWait.until(ExpectedConditions.urlToBe(urlTobe));
        Assert.assertEquals(urlTobe, hbDriver.getCurrentUrl());
        logHB.info("Samsung cellphones selected.");

        /*
        Test case 6 wanted 'Click page 2' but page don't have multiple page.
        For this step I'll scroll down.
        */
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, 1500)");                                                                   //Select mobile phone
        //expWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(zFlip)));
        Thread.sleep(2000);
        hbDriver.findElement(By.xpath(galaxyA72)).click();
        logHB.info("Mobile phone selected.");

        expWait.until(ExpectedConditions.urlToBe(urlTobePhone));                                                        //Like phone
        js.executeScript("window.scrollTo(0, 1500)");
        hbDriver.findElement(By.xpath(likeMobile)).click();
        logHB.info("Phone liked.");
        Thread.sleep(1000);
        String alT = hbDriver.switchTo().alert().getText();
        Assert.assertEquals("Ürün listenize eklendi.",alT);
        //expWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(likePop)));
        logHB.info("Pop up successfully visible.");

        js.executeScript("window.scrollTo(0, 0)");









    }
}
