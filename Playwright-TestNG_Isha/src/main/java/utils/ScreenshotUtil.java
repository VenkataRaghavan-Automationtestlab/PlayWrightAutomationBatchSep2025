package utils;

import com.microsoft.playwright.Page;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(Page page, String stepName) {
    	try {
            String safeStepName = stepName.replaceAll("[^a-zA-Z0-9-_]", "_");
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
            String fileName = safeStepName + "_" + timestamp + ".png";

            String screenshotDir = ConfigManager.getReportDir() + "/screenshots";
            Path screenshotPath = Path.of(screenshotDir, fileName);
            Files.createDirectories(screenshotPath.getParent());

            page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true));

            // Return path with forward slashes for ExtentReports
            return screenshotPath.toAbsolutePath().toString().replace("\\", "/");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
