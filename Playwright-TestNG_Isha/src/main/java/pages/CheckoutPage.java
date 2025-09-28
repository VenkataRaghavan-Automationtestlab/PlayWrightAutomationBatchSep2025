package pages;

import com.microsoft.playwright.Page;

import base.BasePage;
import utils.AllLoctors;

public class CheckoutPage extends BasePage {

	public CheckoutPage(Page page, String testName) {
		super(page, testName);
	}

	// Actions
	public CheckoutPage EnterFirstname() {
		actions.fill(AllLoctors.firstname, "Venkat");
		return this;
	}
	
	public CheckoutPage EnterLastname() {
		actions.fill(AllLoctors.lastname, "Raghavan");
		return this;
	}
	
	public CheckoutPage EnterZipCode() {
		actions.fill(AllLoctors.zipcode, "600032");
		return this;
	}

	public CheckoutPage ClickContinue() {
		actions.click(AllLoctors.ContinueBttn);
		return this;
	}
}
