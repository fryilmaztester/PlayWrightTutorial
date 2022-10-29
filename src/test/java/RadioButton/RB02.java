package RadioButton;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RB02 {

        /*
    Go to url :  https://demo.guru99.com/test/radio.html
    Eğer hiç biri checkli değilse ortadaki checkleyiniz
     */

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://demo.guru99.com/test/radio.html";


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
        locator = page.locator("input[type='radio']");

        for (int i =0; i<locator.count()-1; i++) {
            if (locator.nth(i).isChecked()){
                break;
            }
        }
        System.out.println(locator.count()/2);
        locator.nth((locator.count()/2)).check();

        assertThat(locator.nth((locator.count()/2))).isChecked();
    }


    @AfterAll
    public static void tearDown() {
        page.close();
    }
}
