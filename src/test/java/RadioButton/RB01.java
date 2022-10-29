package RadioButton;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RB01 {

    /*
    Go to url :  https://www.facebook.com/
    Click on Create an Account button
    Then click on the radio buttons

     */

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://www.facebook.com/";


    @BeforeAll
    public static void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().
                setHeadless(headless).setSlowMo(slowMotionTime));

        context = browser.newContext();
        page = context.newPage();

        page.navigate(URL);
    }

    @Test
    public void radioButton() {
        locator = page.locator("div[class='_6ltg']").last();
        locator.click();

        locator = page.locator("input[type=\"radio\"]");
        System.out.println("Radio Button Count: " + locator.count());

        if (!locator.nth(1).isChecked()) {
            locator.nth(1).check();
        }

        assertThat(locator.nth(1)).isChecked();

        if (locator.nth(1).isChecked()) {
            locator.nth(1).uncheck();
        }
        assertThat(locator.nth(1)).isChecked(new LocatorAssertions.
                IsCheckedOptions().
                setChecked(false));
    }


    @AfterAll
    public static void tearDown() {
        page.close();
    }
}
