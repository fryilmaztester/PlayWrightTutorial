package CheckBox;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CB01 {

 /*
    Go to https://the-internet.herokuapp.com/checkboxes
    Locate the elements of checkboxes
    Then click on checkbox1 if box is not selected
    Then click on checkbox2 if box is not selected
    Then verify that checkbox1 is checked.

     */

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://the-internet.herokuapp.com/checkboxes";
    static Keyboard keyboard;

    @BeforeAll
    public static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
                        .setSlowMo(slowMotionTime)
        );

        context = browser.newContext();
        page = context.newPage();

        keyboard = page.keyboard();

        page.navigate(URL);

    }

    @Test
    public void testCheck() {
        locator = page.locator("input[type=\"checkbox\"]");
        System.out.println("Check Sayısı: " + locator.count());

        if (!locator.first().isChecked()) {
            locator.first().check();
        }

        boolean flag = locator.last().isChecked();
        System.out.println("Flag: " + flag);
        if (flag){
            locator.last().uncheck(); //check i kaldırmak için
        }

        assertThat(locator.first()).isChecked();
        assertThat(locator.last()).isChecked(new LocatorAssertions.
                IsCheckedOptions().setChecked(false));
    }

    @AfterAll
    public static void tearDown(){
        page.close();
    }
}
