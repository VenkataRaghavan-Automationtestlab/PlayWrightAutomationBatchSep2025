package pages;

import com.microsoft.playwright.Page;
import base.BasePage;
import utils.AllLoctors;

public class ProductsPage extends BasePage {

	public ProductsPage(Page page, String testName) {
		super(page, testName);
	}

	public ProductsPage ValidateElementandText() {
		actions.assertVisible(AllLoctors.SauceLabsElementname);
		actions.assertHasText(AllLoctors.SauceLabsElementname, "Sauce Labs Backpack");
		return this;
	}

	public ProductsPage addtocart() {
		actions.click(AllLoctors.AddtoCartBttn);
		return this;
	}

	public ProductsPage clickonshoppingcart() {
		actions.click(AllLoctors.shoppingcart);
		return this;
	}
}
