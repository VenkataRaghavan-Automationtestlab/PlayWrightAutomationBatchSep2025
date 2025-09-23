package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.BaseClass;

public class HomePage extends BaseClass {

	// Locators
	private Locator SauceLabsElementname;
	private Locator AddtoCartBttn;
	private Locator shoppingcart;

	public HomePage(Page page) {
		SauceLabsElementname = page.locator("(//*[@class='inventory_item_name'])[1]");
		AddtoCartBttn = page.locator("(//*[@class='btn_primary btn_inventory'])[1]");
		shoppingcart = page.locator("[data-icon='shopping-cart']");
	}
	
	// Actions
	public void ValidateElementandText() {
		assertThat(SauceLabsElementname).isVisible();
		assertThat(SauceLabsElementname).containsText("Sauce Labs Backpack");
	}

	public void addtocart() {
		AddtoCartBttn.click();
	}
	
	public void clickonshoppingcart() {
		shoppingcart.click();
	}
}
