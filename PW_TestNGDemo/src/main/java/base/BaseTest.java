package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class BaseTest implements AutoCloseable {
	protected Playwright playwright;
	protected Browser browser;
	protected Page page;
	protected ExtentReports extent;
	protected ExtentTest test;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "testName" })
	public void setUp(@Optional("Default Test") String testName) {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(200));
		page = browser.newPage();

		ExtentSparkReporter reporter = new ExtentSparkReporter("report.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		test = extent.createTest(testName);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		close();
	}

	public void flushReport() {
		if (extent != null)
			extent.flush();
	}

	@Override
	public void close() {
		if (page != null)
			page.close();
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();
		flushReport();
	}
}
