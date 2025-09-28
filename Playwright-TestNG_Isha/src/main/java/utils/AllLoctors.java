package utils;

import com.microsoft.playwright.Page;

import base.BasePage;

public class AllLoctors extends BasePage {

	public AllLoctors(Page page, String testName) {
		super(page, testName);
	}

	// Login Page Locators
	public final static String usernameInput = "#user-name";
	public final static String passwordInput = "#password";
	public final static String loginButton = "#login-button";
	public final static String errorMsg = "#error-message";

	// Cart Page Locators
	public final static String SauceLabsbagname = "text=Sauce Labs Backpack";
	public final static String CHECKOUTBttn = "text=CHECKOUT";

	public final static String SauceLabsElementname = "(//*[@class='inventory_item_name'])[1]";
	public final static String AddtoCartBttn = "(//*[@class='btn_primary btn_inventory'])[1]";
	public final static String shoppingcart = "[data-icon='shopping-cart']";
	
	//Checkout Page Locators
	public final static String firstname = "#first-name";
	public final static String lastname = "#last-name";
	public final static String zipcode = "#postal-code";
	public final static String ContinueBttn = "[type='submit']";
	
	//Overview Page Locators
	public final static String shippingdetails = "(//*[@class='summary_value_label'])[2]";
	public final static String finishBttn = "[class='btn_action cart_button']";
	public final static String ThankyouMsg = "//*[@id='checkout_complete_container']/h2";
}
