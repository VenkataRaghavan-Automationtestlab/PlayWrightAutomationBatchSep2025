package pages;

import com.microsoft.playwright.Page;
import base.BasePage;
import utils.AllLoctors;

public class CartPage extends BasePage {

	public CartPage(Page page, String testName) {
		super(page, testName);
	}

		// Actions
		public CartPage ValidateCartpageElementText() {
			actions.assertVisible(AllLoctors.SauceLabsbagname);
			actions.assertHasText(AllLoctors.SauceLabsbagname, "Sauce Labs Backpack");
			return this;
		}

		public CartPage ClickCheckout() {
			actions.click(AllLoctors.CHECKOUTBttn);
			return this;
		}
}
