package pages;

import com.microsoft.playwright.Page;

import base.BasePage;
import utils.AllLoctors;

public class CheckoutCompletePage extends BasePage {

	public CheckoutCompletePage(Page page, String testName) {
		super(page, testName);
	}

	public CheckoutCompletePage ValidateThanksMsg() {
		actions.assertVisible(AllLoctors.ThankyouMsg);
		actions.assertHasText(AllLoctors.ThankyouMsg, "THANK YOU FOR YOUR ORDER");
		return this;
	}
}
