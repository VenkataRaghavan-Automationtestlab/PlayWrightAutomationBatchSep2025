package testCase;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginAndCheckout() {
        try {
            test.info("Navigating to login page");

            LoginPage loginPage = new LoginPage(page);
            loginPage.navigate();
            loginPage.login("standard_user", "secret_sauce");

            ProductsPage products = new ProductsPage(page);
            test.pass("Logged in successfully");
            test.info("Opened product details and returned");
            products.ValidateElementandText();
            products.addtocart();
            products.clickonshoppingcart();

            CartPage cartPage = new CartPage(page);
            cartPage.ValidateCartpageElementText();
            cartPage.ClickCheckout();
            test.info("Filling customer details");

            CheckoutPage checkout = new CheckoutPage(page);
            checkout.EnterDetails();
            checkout.ClickContinue();
            
            CheckoutOverviewPage overview = new CheckoutOverviewPage(page);
            overview.ValidateDescription();
            overview.ValidateShippingInfo();
            overview.ClickFinishBttn();
            overview.ValidateThanksMsg();
                   
            test.pass("Checkout completed successfully");

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }
}
