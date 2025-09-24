package stepDefinitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SD_Login {

	private static Playwright playwright;
	private static Browser browser;
	private Page page;

	@Before
	public void preCondition() {
		// Create Playwright instance
		playwright = Playwright.create();

		// Launch Chrome (via Chromium with "chrome" channel)
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false) // change
																														// to
																														// true
																														// for
																														// headless
																														// mode
		);
		page = browser.newPage();
		page.navigate("https://www.saucedemo.com/");
	}

	@Given("load the application URL")
	public void load_the_application_url() {
		page.navigate("https://www.saucedemo.com/");
		System.out.println("load the application URL");
	}

	@When("enter the valid username {string}")
	public void enter_the_valid_username(String username) {
		page.fill("#user-name", username);
		System.out.println("enter the valid username");
	}

	@And("enter the valid password {string}")
	public void enter_the_valid_password(String password) {
		page.fill("#password", password);
		System.out.println("enter the valid password");
	}

	@And("Click on login button")
	public void click_on_login_button() {
		page.click("#login-button");
		System.out.println("Click on login button");
	}

	@Then("Page navigated to Homepage and verify title")
	public void page_navigated_to_homepage_and_verify_title() {
		String title = page.textContent(".title");
		if (title.equalsIgnoreCase("Products")) {
			System.out.println("Products matched");
		} else {
			System.out.println("Products did not match");
		}
		System.out.println("Page navigated to Homepage and verify title");
	}

	@But("Error Message will be displayed")
	public void error_message_will_be_displayed() {
		String ErrorMsg = page.textContent("[data-test='error']");
		if (ErrorMsg.equalsIgnoreCase("Epic sadface: Sorry, this user has been locked out.")) {
			System.out.println("Error Msg matched");
		} else {
			System.out.println("Error Msg did not match");
		}
	}

	@After
	public void postCondition() {
		// Clean up properly
		if (page != null)
			page.close();
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();
	}

}
