package ReusableCommand;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class DriverUtils {

    static String getBrowserNameFromEnv() {
        String browserName = System.getenv("BROWSER");
        if (browserName == null) {
            browserName = ConfiReader.getProperty("browserName");
        }
        return browserName;
    }

    static BrowserType getBrowserTypeFromEnv(Playwright playwright) {
        String browserName = getBrowserNameFromEnv();
        switch (browserName) {
            case "webkit":
                return playwright.webkit();
            case "firefox":
                return playwright.firefox();
            case "chromium":
                return playwright.chromium();
            default:
                throw new IllegalArgumentException("Unknown browser: " + browserName);
        }
    }
}
