package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseClass {
	
	public static Playwright playwright;
	public static Browser browser;
	public static Page page;
	public static BrowserContext context;

	public Page startapp() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		context = browser.newContext();
		return context.newPage();
	}
}
