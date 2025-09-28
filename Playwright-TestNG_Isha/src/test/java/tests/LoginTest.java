package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import utils.ConfigManager;
import utils.ExcelUtils;
import utils.PageActions;

import java.util.List;

public class LoginTest extends BaseTest {
	
	public String testcasename = "Login Test";
	
	public LoginTest(Page page, String testName) {
        this.page = page;
        this.actions = new PageActions(page, testName);
    }
    
	
	@DataProvider(name = "loginData")
	public Object[][] loginDataProvider() {
		List<String[]> dataList = ExcelUtils.readSheet("src/test/resources/testdata.xlsx", "Sheet1");
		// Convert List<String[]> to Object[][]
		Object[][] data = new Object[dataList.size()][];
		for (int i = 0; i < dataList.size(); i++) {
			data[i] = dataList.get(i);
		}
		return data;
	}

	@Test(dataProvider = "loginData", retryAnalyzer = utils.TestRetryAnalyzer.class)
	public void validLoginTest(String username, String password) {
		
		// Page object
		LoginPage login = getPage(LoginPage.class);
		ProductsPage PP = getPage(ProductsPage.class);
		CartPage CP = getPage(CartPage.class);
		CheckoutPage COP = getPage(CheckoutPage.class);
		CheckoutOverviewPage COO = getPage(CheckoutOverviewPage.class);
		CheckoutCompletePage CCP = getPage(CheckoutCompletePage.class);	
		
		login.openUrl(ConfigManager.get("base.url")).enterusername(username).enterpassword(password).ClickloginBttn();
		PP.ValidateElementandText().addtocart().clickonshoppingcart();
		CP.ValidateCartpageElementText().ClickCheckout();
		COP.EnterFirstname().EnterLastname().EnterZipCode().ClickContinue();
		COO.ValidateDescription().ValidateShippingInfo().ClickFinishBttn();
		CCP.ValidateThanksMsg();
	}
}
