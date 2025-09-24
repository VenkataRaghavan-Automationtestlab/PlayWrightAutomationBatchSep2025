package pages;

import com.microsoft.playwright.*;

public class LoginPage {
	private final Page page;
	// Locators
	private Locator usernameInput;
	private Locator passwordInput;
	private Locator loginButton;

	public LoginPage(Page page) {
		this.page = page;
		usernameInput = page.locator("input[type='text']");
		passwordInput = page.locator("#password");
		loginButton = page.locator("#login-button");
	}

	public void navigate() {
		page.navigate("https://www.saucedemo.com/v1/index.html");
		page.waitForLoadState();
	}

	public void login(String user, String pwd) {
		usernameInput.fill(user);
		passwordInput.fill(pwd);
		loginButton.click();
	}
	
	public String getPageTitle() {
		return page.title();
	}
	
}
