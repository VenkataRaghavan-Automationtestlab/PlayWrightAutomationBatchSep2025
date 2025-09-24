package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.*;

public class CartPage {
	@SuppressWarnings("unused")
	private final Page page;
	// Locators
	private Locator SauceLabsbagname;
	private Locator CHECKOUTBttn;

	public CartPage(Page page) {
		this.page = page;
		SauceLabsbagname = page.locator("text=Sauce Labs Backpack");
		CHECKOUTBttn = page.locator("text=CHECKOUT");
	}

	// Actions
	public void ValidateCartpageElementText() {
		assertThat(SauceLabsbagname).isVisible();
		assertThat(SauceLabsbagname).containsText("Sauce Labs Backpack");
	}

	public void ClickCheckout() {
		CHECKOUTBttn.click();
	}
}
