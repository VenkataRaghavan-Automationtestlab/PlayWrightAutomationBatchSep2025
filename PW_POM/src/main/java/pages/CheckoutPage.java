package pages;

import com.microsoft.playwright.*;

public class CheckoutPage {
	@SuppressWarnings("unused")
	private final Page page;
	// Locators
	private Locator firstname;
	private Locator lastname;
	private Locator zipcode;
	private Locator ContinueBttn;

	public CheckoutPage(Page page) {
		this.page = page;
		firstname = page.locator("#first-name");
		lastname = page.locator("#last-name");
		zipcode = page.locator("#postal-code");
		ContinueBttn = page.locator("[type='submit']");
	}

	public void EnterDetails() {
		firstname.fill("Venkat");
		lastname.fill("Raghavan");
		zipcode.fill("600032");
	}

	public void ClickContinue() {
		ContinueBttn.click();
	}
}
