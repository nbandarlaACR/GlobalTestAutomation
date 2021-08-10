package utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Steps {

    private static ExtentTest testcase;

    public static void init(ExtentTest inTestCase) {
        testcase = inTestCase;
    }

    public static void log(String message) {
        testcase.info(message);
    }

    public static void logJson(String content){
        testcase.info(MarkupHelper.createCodeBlock(content, CodeLanguage.JSON));
    }

    public static void imgLog(String message) {
        String screenshot = ((TakesScreenshot)PageActions.driver).getScreenshotAs(OutputType.BASE64);
        testcase.addScreenCaptureFromBase64String(screenshot, message);
    }

    public static void imgStep(String message) {
        String sc = DriverUtil.screenshot();
        testcase.info("Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(sc).build());
    }
}





