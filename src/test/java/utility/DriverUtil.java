package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtil {
   // ===== FIELDS ======= //
   private static WebDriver driver;
   private static String CHOSEN_BROWSER = "";

   
   // ===== METHODS ======= //
   public static void openBrowser() {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
      driver.manage().deleteAllCookies();
      driver.manage().window().maximize();
      CHOSEN_BROWSER = BrowserType.CHROME;
   }

   public static void openBrowser(String browserType) {
      if (browserType.equalsIgnoreCase(BrowserType.CHROME)) {
         // WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         CHOSEN_BROWSER = BrowserType.CHROME;
      } else if (browserType.equalsIgnoreCase(BrowserType.EDGE)) {
         // WebDriverManager.edgedriver().setup();
         driver = new EdgeDriver();
         CHOSEN_BROWSER = BrowserType.EDGE;
      } else if (browserType.equalsIgnoreCase(BrowserType.FIREFOX)) {
         //  WebDriverManager.firefoxdriver().setup();
         driver = new FirefoxDriver();
         CHOSEN_BROWSER = BrowserType.FIREFOX;
      }
   }

   public static void closeBrowser() {
      if (CHOSEN_BROWSER.equals(BrowserType.FIREFOX)) {
         if (driver != null) {
            driver.quit();
            return;
         }
         return;
      }
      if (driver != null) {
         driver.close();
         driver.quit();
      }
   }

   /**
    * Returns the WebDriver object that is used for testing 
    */
   public static WebDriver getDriver() {
      if (driver == null) {
         throw new NullPointerException("Cannot retrieve the driver object, it is NULL");
      }
      return driver;
   }

   /**
    * Returns the screenshot base64 String if called
    */
   public static String screenshot() {
      WebDriver driver = DriverUtil.getDriver();
      TakesScreenshot newScreen = (TakesScreenshot) driver;
      String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
      return "data:image/jpg;base64, " + scnShot;
   }
}
