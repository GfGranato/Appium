import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class EchoBoxPage {

    static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.apk";
    static final String APPIUM = "http://localhost:4723/wd/hub";
    private WebDriverWait wait;
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion","9");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("app", APP);
        driver = new AndroidDriver(new URL(APPIUM), caps);
        wait = new WebDriverWait(driver , 7);
    }

    @After
    public void tearDown() {
        if (driver != null){
            driver.quit();
        }
    }

    @BeforeEach
    public void beforeEach() {

        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login Scren")));
        driver.findElement(MobileBy.AccessibilityId("Login Scren"));
    }

    @Test
    @DisplayName("Validate send key in echo box page")
    public void echoBoxSendKey(){
        /*
            write a text that clicks on echo box
            and type some text
            after that verify if the text appears as a label.
         */
        String textToSend = "Hello World!";
        By lblText = MobileBy.AccessibilityId(textToSend);
        By inputMessage = MobileBy.AccessibilityId("messageInput");

        driver.findElement(MobileBy.AccessibilityId("Echo Box")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(inputMessage));
        driver.findElement(inputMessage).sendKeys(textToSend);
        driver.findElement(MobileBy
                .xpath("//android.view.ViewGroup[@content-desc='messageSaveBtn']/android.widget.TextView"))
                .click();
        wait.until(ExpectedConditions.presenceOfElementLocated(lblText));
        Assert.assertEquals(textToSend, driver.findElement(lblText).getText());

    }
}
