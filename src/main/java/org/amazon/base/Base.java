package org.amazon.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Entry point. Base class for
 * 1) Reading property file
 * 2) Initialize driver
 */
public class Base {

    public WebDriver driver;
    public Properties prop;
    public static WebDriverWait wait;

    /**
     * Intialize driver
     */
    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/config/config.properties");
        prop.load(fis);
        String browser = prop.getProperty("browser");
        try {
            if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/drivers/chromedriver_win32/chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/resources/drivers/geckodriver-v0.29.0-win64/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
        return driver;
    }
}
