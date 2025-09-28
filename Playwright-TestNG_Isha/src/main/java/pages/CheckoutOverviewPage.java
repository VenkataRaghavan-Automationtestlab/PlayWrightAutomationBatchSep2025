package pages;

import com.microsoft.playwright.Page;

import base.BasePage;
import utils.AllLoctors;

public class CheckoutOverviewPage extends BasePage {

	public CheckoutOverviewPage(Page page, String testName) {
		super(page, testName);
	}

	// Actions
	public CheckoutOverviewPage ValidateDescription() {
		actions.assertVisible(AllLoctors.SauceLabsbagname);
		actions.assertHasText(AllLoctors.SauceLabsbagname, "Sauce Labs Backpack");
		return this;
	}

	public CheckoutOverviewPage ValidateShippingInfo() {
		actions.assertVisible(AllLoctors.shippingdetails);
		actions.assertHasText(AllLoctors.shippingdetails, "FREE PONY EXPRESS DELIVERY!");
		return this;
	}

	public CheckoutOverviewPage ClickFinishBttn() {
		actions.click(AllLoctors.finishBttn);
		return this;
	}
}
