package pl.brzozowski.maciej;


import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.brzozowski.maciej.util.TestProperties;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.ENTER;
import static pl.brzozowski.maciej.util.Configuration.*;

public class FacebookTest {

    private WebDriver webDriver;
    private WebDriverWait wait;
    private TestProperties testProperties;

    @Before
    public void setUp() {
        testProperties = new TestProperties();
        testProperties.prepareProperties();
        webDriver = new ChromeDriver(testProperties.getChromeOptions());
        wait = new WebDriverWait(webDriver, 500);

    }

    @Test(timeout = 60000L)
    public void shouldLoginToFacebookAndCreateHelloWorldPost() {
        //given
        webDriver.navigate().to(URL);
        webDriver.manage().deleteAllCookies();
        loginUser(testProperties.getUserName(), testProperties.getUserPassword());
        //when
        preparePost();
        webDriver.findElement(By.xpath(POST_FRAME)).sendKeys(HELLO_WORLD);
        //then
        //TODO make assertion to check if post was created
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    private void preparePost() {
        webDriver.findElement(By.xpath(PROFILE_NAME)).click();
        wait.withTimeout(ofSeconds(5L))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(POST_FRAME)));
        WebElement post_window = webDriver.findElement(By.xpath(POST_FRAME));
        post_window.click();
        Assume.assumeTrue(post_window.isDisplayed());
    }

    private void loginUser(String email, String password) {
        webDriver.findElement(By.xpath(EMAIL)).sendKeys(email);
        webDriver.findElement(By.xpath(PASSWORD)).sendKeys(password + ENTER);
    }

}
