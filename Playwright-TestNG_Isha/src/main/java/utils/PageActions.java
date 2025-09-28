package utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.aventstack.extentreports.ExtentTest;
import java.text.Normalizer;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Playwright PageActions - robust wrapper around Playwright Page for common actions
 */
public class PageActions {
    private final Page page;
    private final String prefix;
    private final Logger logger;
    // new: per-instance counter to avoid collisions
    private final AtomicInteger screenshotCounter = new AtomicInteger(0);
    private final DateTimeFormatter tsFmt = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    public PageActions(Page page, String testName) {
        if (page == null) throw new IllegalArgumentException("Page must not be null");
        this.page = page;
        this.prefix = sanitizeFilename(testName == null ? "test" : testName);
        this.logger = Logger.getLogger(PageActions.class.getName());
    }

    public PageActions(Page page, String testName, ExtentTest extentTest, Logger logger) {
        if (page == null) throw new IllegalArgumentException("Page must not be null");
        this.page = page;
        this.prefix = sanitizeFilename(testName == null ? "test" : testName);
        this.logger = logger == null ? Logger.getLogger(PageActions.class.getName()) : logger;
    }

    private String sanitizeFilename(String s) {
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFKD);
        return normalized.replaceAll("[^\\p{Alnum}]+", "_").replaceAll("_+", "_").toLowerCase(Locale.ROOT);
    }

    public void setScreenshotsEnabled(boolean enabled) {
    }
    
    private void logEachAction(String desc) {
        try {
            String ss = ScreenshotUtil.takeScreenshot(page, prefix + "_" + desc.replaceAll("\\s+", "_"));
            ReportManager.stepPass(desc, ss);
        } catch (Exception e) {
            ReportManager.logInfo("Screenshot failed for: " + desc);
        }
    }

    // --- Navigation ---
    public PageActions navigate(String url) {
        page.navigate(url);
        logEachAction("Navigate → " + url);
        return this;
    }

    // --- Clicks ---
    public PageActions click(String selector) {
        page.locator(selector).click();
        logEachAction("Click → " + selector);
        return this;
    }

    public PageActions click(Locator locator) {
        locator.click();
        logEachAction("Click → locator");
        return this;
    }

    public PageActions safeClick(String selector, int retries, Duration retryDelay) {
        Locator loc = page.locator(selector);
        for (int i = 0; i < Math.max(1, retries); i++) {
            try {
                loc.click(new Locator.ClickOptions().setTimeout((int) retryDelay.toMillis()));
                logEachAction("SafeClick → " + selector + " (attempt " + (i + 1) + ")");
                return this;
            } catch (Exception e) {
                if (i == retries - 1) throw e;
                try { Thread.sleep(retryDelay.toMillis()); } catch (InterruptedException ignored) {}
            }
        }
        return this;
    }

    // --- Typing ---
    public PageActions fill(String selector, String value) {
        page.locator(selector).fill(value);
        logEachAction("Fill → " + selector + " = " + value);
        return this;
    }

    public PageActions fill(Locator locator, String value) {
        locator.fill(value);
        logEachAction("Fill → locator = " + value);
        return this;
    }

    // --- Get Text ---
    public String getText(String selector) {
        String text = page.locator(selector).innerText();
        logEachAction("GetText → " + selector + " = " + text);
        return text;
    }

    public String getText(Locator locator) {
        String text = locator.innerText();
        logEachAction("GetText → locator = " + text);
        return text;
    }

    // --- Waits ---
    public PageActions waitForVisible(String selector, Duration timeout) {
        page.locator(selector).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout((int) timeout.toMillis()));
        logEachAction("WaitForVisible → " + selector);
        return this;
    }

    public PageActions waitForHidden(String selector, Duration timeout) {
        page.locator(selector).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout((int) timeout.toMillis()));
        logEachAction("WaitForHidden → " + selector);
        return this;
    }

    // --- Dropdowns ---
    public PageActions selectByValue(String selector, String value) {
        page.locator(selector).selectOption(value);
        logEachAction("SelectByValue → " + selector + " = " + value);
        return this;
    }

    public PageActions selectByText(String selector, String text) {
        page.locator(selector).selectOption(new SelectOption().setLabel(text));
        logEachAction("SelectByText → " + selector + " = " + text);
        return this;
    }

    // --- Checkbox & Radio ---
    public PageActions check(String selector) {
        page.locator(selector).check();
        logEachAction("Check → " + selector);
        return this;
    }

    public PageActions uncheck(String selector) {
        page.locator(selector).uncheck();
        logEachAction("Uncheck → " + selector);
        return this;
    }

    public PageActions selectRadio(String selector) {
        page.locator(selector).check();
        logEachAction("SelectRadio → " + selector);
        return this;
    }

    // --- Multi-elements ---
    public List<String> getAllTexts(String selector) {
        List<String> texts = page.locator(selector).allInnerTexts();
        logEachAction("GetAllTexts → " + selector + " = " + texts);
        return texts;
    }

    // --- Assertions / State ---
    public boolean isVisible(String selector) {
        boolean v = page.locator(selector).isVisible();
        logEachAction("IsVisible → " + selector + " = " + v);
        return v;
    }

    public boolean isEnabled(String selector) {
        boolean v = page.locator(selector).isEnabled();
        logEachAction("IsEnabled → " + selector + " = " + v);
        return v;
    }

    public boolean isChecked(String selector) {
        boolean v = page.locator(selector).isChecked();
        logEachAction("IsChecked → " + selector + " = " + v);
        return v;
    }

    public PageActions assertVisible(String selector) {
        assertThat(page.locator(selector)).isVisible();
        logEachAction("AssertVisible → " + selector);
        return this;
    }

    public PageActions assertEnabled(String selector) {
        assertThat(page.locator(selector)).isEnabled();
        logEachAction("AssertEnabled → " + selector);
        return this;
    }

    public PageActions assertChecked(String selector) {
        assertThat(page.locator(selector)).isChecked();
        logEachAction("AssertChecked → " + selector);
        return this;
    }

    public PageActions assertHasText(String selector, String exactText) {
        assertThat(page.locator(selector)).hasText(exactText);
        logEachAction("AssertHasText → " + selector + " = '" + exactText + "'");
        return this;
    }

    public PageActions assertHasText(String selector, Pattern regex) {
        assertThat(page.locator(selector)).hasText(regex);
        logEachAction("AssertHasText(regex) → " + selector + " = '" + regex.pattern() + "'");
        return this;
    }

    // --- Alerts ---
    public PageActions registerAlertAccept() {
        page.onceDialog(Dialog::accept);
        logEachAction("Register: Alert Accept");
        return this;
    }

    public PageActions registerAlertDismiss() {
        page.onceDialog(Dialog::dismiss);
        logEachAction("Register: Alert Dismiss");
        return this;
    }

    public PageActions registerPromptHandler(String textToAccept) {
        page.onceDialog(dialog -> {
            if ("prompt".equals(dialog.type())) dialog.accept(textToAccept);
            else dialog.accept();
        });
        logEachAction("Register: Prompt Handler → " + textToAccept);
        return this;
    }
}
