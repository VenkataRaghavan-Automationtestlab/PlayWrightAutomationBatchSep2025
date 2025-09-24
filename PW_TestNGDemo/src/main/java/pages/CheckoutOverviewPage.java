package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.*;

public class CheckoutOverviewPage {
	@SuppressWarnings("unused")
	private final Page page;
	// Locators
	private Locator SauceLabsbagname;
	private Locator shippingdetails;
	private Locator finishBttn;
	private Locator ThankyouMsg;

	public CheckoutOverviewPage(Page page) {
		this.page = page;
		SauceLabsbagname = page.locator("text=Sauce Labs Backpack");
		shippingdetails = page.locator("(//*[@class='summary_value_label'])[2]");
		finishBttn = page.locator("[class='btn_action cart_button']");
		ThankyouMsg = page.locator("//*[@id='checkout_complete_container']/h2");
	}

	// Actions
	public void ValidateDescription() {
		assertThat(SauceLabsbagname).isVisible();
		assertThat(SauceLabsbagname).containsText("Sauce Labs Backpack");
	}

	public void ValidateShippingInfo() {
		assertThat(shippingdetails).isVisible();
		assertThat(shippingdetails).containsText("FREE PONY EXPRESS DELIVERY!");
	}

	public void ClickFinishBttn() {
		finishBttn.click();
	}

	public void ValidateThanksMsg() {
		assertThat(ThankyouMsg).isVisible();
		assertThat(ThankyouMsg).containsText("THANK YOU FOR YOUR ORDER");
	}
}
