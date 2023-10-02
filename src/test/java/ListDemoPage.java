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

public class ListDemoPage {

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
    @DisplayName("Validate Scroll to last element in List")
    public void scrollList(){
        /*
            This test will execute a swipe on a list
            and then assert that the last element is visible.
            if the swipe is not executed, the test will fail
         */
        
        driver.findElement(MobileBy.AccessibilityId("List Demo")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Altostratus")));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Interaction moveToStart = finger.createPointerMove(
                Duration.ZERO, PointerInput.Origin.viewport(), 520, 1530);
        Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = finger.createPointerMove(
                Duration.ofMillis(1000), PointerInput.Origin.viewport(), 520, 490);
        Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(moveToStart)
                .addAction(pressDown)
                .addAction(moveToEnd)
                .addAction(pressUp);
        driver.perform(Arrays.asList(swipe));

        driver.findElement(MobileBy.AccessibilityId("Stratus"));
        Assert.assertTrue(driver.findElement(MobileBy.AccessibilityId("Stratus")).isDisplayed());
    }
}
