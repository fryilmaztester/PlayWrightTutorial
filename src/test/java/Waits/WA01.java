package Waits;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

public class WA01 {

    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;
    static String URL = "https://seleniumatfingertips.wordpress.com/";
    static Locator locator;


    @Test
    public void test01() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
                        .setSlowMo(10).
                        setTimeout(20)
        );


    }
}
