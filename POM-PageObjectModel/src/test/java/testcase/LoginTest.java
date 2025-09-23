package testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import pages.CartPage;
import pages.HomePage;
import pages.InfoPage;
import pages.LoginPage;
import pages.OverviewPage;
import utils.BaseClass;

public class LoginTest extends BaseClass{

	private BaseClass playwright;
	private Page page;
	public LoginPage lp;
	public HomePage hp;
	public CartPage cp;
	public InfoPage ip;
	public OverviewPage Op;
	
	@BeforeMethod
	 public void setUp() {
	 playwright = new BaseClass();
	 page = playwright.startapp(); // utility method
	 page.navigate("https://www.saucedemo.com/v1/index.html");
	 assert page.url().contains("saucedemo");
	 lp = new LoginPage(page);
	 hp = new HomePage(page);
	 cp = new CartPage(page);
	 ip = new InfoPage(page);
	 Op = new OverviewPage(page);
	 }
	
	@Test
	 public void verifyLoginTest() {
	 lp = new LoginPage(page);
	 lp.login();
	 hp.ValidateElementandText();
	 hp.addtocart();
	 hp.clickonshoppingcart();
	 cp.ValidateCartpageElementText();
	 cp.ClickCheckout();
	 ip.EnterDetails();
	 ip.ClickContinue();
	 Op.ValidateDescription();
	 Op.ValidateShippingInfo();
	 Op.ClickFinishBttn();
	 Op.ValidateThanksMsg();
	 }
	
	@AfterMethod
	 public void tearDown() {
	 page.context().browser().close();
	 }
}
