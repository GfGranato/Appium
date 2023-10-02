import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class WebViewDemoPage {

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
    @DisplayName("Validate WebView Alert pop up")
    public void scrollList(){

        driver.findElement(MobileBy.AccessibilityId("Webview Demo")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("urlInput")));

        driver.findElement(MobileBy.AccessibilityId("urlInput")).sendKeys("Test Url");
        driver.findElement(MobileBy.xpath("//android.view.ViewGroup[@content-desc='navigateBtn']")).click();

        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.id("android:id/alertTitle"))).isDisplayed());
        Assert.assertEquals("Sorry, you are not allowed to visit that url",
                driver.findElement(MobileBy.id("android:id/message")).getText());
        driver.findElement(MobileBy.id("android:id/alertTitle")).click();
    }
}
