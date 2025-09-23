package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.BaseClass;

public class LoginPage extends BaseClass {

	// Locators
	private Locator usernameInput;
	private Locator passwordInput;
	private Locator loginButton;

	public LoginPage(Page page) {
		usernameInput = page.locator("input[type='text']");
		passwordInput = page.locator("#password");
		loginButton = page.locator("#login-button");
	}

	// Actions
	public void login() {
		usernameInput.fill("standard_user");
		passwordInput.fill("secret_sauce");
		loginButton.click();
	}

	public String getPageTitle() {
		return page.title();
	}
}
