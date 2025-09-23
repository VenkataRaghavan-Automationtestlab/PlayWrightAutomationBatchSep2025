package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import utils.BaseClass;

public class InfoPage extends BaseClass {

	// Locators
	private Locator firstname;
	private Locator lastname;
	private Locator zipcode;
	private Locator ContinueBttn;

	public InfoPage(Page page) {
		firstname = page.locator("#first-name");
		lastname = page.locator("#last-name");
		zipcode = page.locator("#postal-code");
		ContinueBttn = page.locator("[type='submit']");
	}
	
	// Actions
	public void EnterDetails() {
		firstname.fill("Venkat");
		lastname.fill("Raghavan");
		zipcode.fill("600032");
	}

	public void ClickContinue() {
		ContinueBttn.click();
	}
}
