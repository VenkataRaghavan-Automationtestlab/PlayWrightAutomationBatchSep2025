package pages;

import com.microsoft.playwright.Page;
import base.BasePage;
import utils.AllLoctors;

public class LoginPage extends BasePage {

    public LoginPage(Page page, String testName) {
        super(page, testName);
    }

    // Actions
    public LoginPage openUrl(String url) {
        actions.navigate(url);
		return this;
    }

    public LoginPage enterusername(String username) {
        actions.fill(AllLoctors.usernameInput, username);
		return this;
    }
    
    public LoginPage enterpassword(String password) {
        actions.fill(AllLoctors.passwordInput, password);
		return this;
    }
    
    public LoginPage ClickloginBttn() {
        actions.click(AllLoctors.loginButton);
		return this;
    }

    public String getErrorMessage() {
        return actions.getText(AllLoctors.errorMsg);
    }
}
