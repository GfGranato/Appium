import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;


public class MobileWebAutomation {

    static final String APPIUM = "http://localhost:4723/wd/hub";
    static final String SITE = "http://appiumpro.com/";
    private WebDriverWait wait;
    private AndroidDriver<AndroidElement> driver;


    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion","9");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName","uiautomator2");
        caps.setCapability("browserName", "Chrome");
        driver = new AndroidDriver<>(new URL(APPIUM), caps);
        wait = new WebDriverWait(driver , 7);
    }

    @After
    public void tearDown() {
        if (driver != null){
            driver.quit();
        }
    }



    @Test()
    @DisplayName("Validate Recaptcha Error Message")
    public void validateRecaptchaErrorMessage(){
        /*
            This test will open appiumPro website
            goes to contact us, fill the data and confirm ignoring the recaptcha
            validate the error message
         */
        List<AndroidElement> toogleMenu = driver.findElements(By.xpath("//img[@src='/icons/menu.svg']"));

        driver.get(SITE);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='/icons/menu.svg']")));
        toogleMenu.get(0).click();
        driver.findElement(By.linkText("Contact")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("contactEmail")
        )).sendKeys("name.surname@foo.com");
        driver.findElement(By.id("contactText")).sendKeys("A regular text");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("Contact_response__w0ER6")
        ));
        Assert.assertEquals("Your message could not be sent due to an error. " +
                "The error message was: You must fill out the Captcha box", errorMessage.getText()
                );
    }
}
