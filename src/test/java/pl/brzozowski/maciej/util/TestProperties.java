package pl.brzozowski.maciej.util;

import org.openqa.selenium.chrome.ChromeOptions;

public class TestProperties {
    private final String DRIVER_PACKAGE = "webdriver.chrome.driver";
    private final String LINUX = "linux";
    private final String DRIVER_PATH = "src/test/java/pl/brzozowski/maciej/drivers/";
    private String detectedSystem;
    private final String WINDOWS = "windows";
    private final String MAC = "mac";
    private ChromeOptions chromeOptions;
    private String userName = System.getProperty("userId", "");
    private String userPassword = System.getProperty("userPass", "");

    public TestProperties() {
        this.chromeOptions = new ChromeOptions();
    }

    public void prepareProperties() {
        detectedSystem = System.getProperty("os.name").toLowerCase();
        if (detectedSystem.contains(LINUX)) {
            System.setProperty(DRIVER_PACKAGE, DRIVER_PATH + LINUX + "/chromedriver");
        } else {
            if (detectedSystem.contains(WINDOWS)) {
                System.setProperty(DRIVER_PACKAGE, DRIVER_PATH + WINDOWS + "\\chromedriver.exe");
            } else {
                System.setProperty(DRIVER_PACKAGE, DRIVER_PATH + MAC + "/chromedriver");
            }
        }
    }

    public ChromeOptions getChromeOptions() {
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.addArguments("--lang=en-GB");
        return chromeOptions;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
