package Exercises;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class TwitterRegister {

    //twitter a faker class kullanarak mail ile kayit olmaya calisiniz

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://www.twitter.com";
    static Keyboard keyboard;
    static Faker faker;
    static JSHandle jsHandle;


    @BeforeAll
    public static void setUp() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
                        .setSlowMo(slowMotionTime)
        );


        context = browser.newContext(new Browser.NewContextOptions().
                setPermissions(
                        asList("notifications")

                )
                .setLocale("en-US")
                .setGeolocation(41.889938, 12.492507));

        context.clearCookies(); //Cookie delete

        page = context.newPage();
        keyboard = page.keyboard();
        faker = new Faker();

        System.out.println(browser);
        page.navigate(URL);
    }

    @Test
    public void test01() {
        jsHandle = page.evaluateHandle("() => " +
                "window.scrollBy(0,600)");

        //    locator = page.locator("xpath=//a[@data-testid='signup']");
        //    locator.click();

        locator = page.locator("xpath=//span[.=\"Sign up with phone or email\"]");
        locator.nth(1).click();

        locator = page.locator("text=Name");
        locator.fill(faker.name().username());

        locator = page.locator("text=Phone").first();
        locator.fill(faker.phoneNumber().cellPhone());

        jsHandle = locator.evaluateHandle("() => " +
                "window.scrollBy(0,200)");

        locator = page.locator("#SELECTOR_1");
        locator.selectOption(new SelectOption()
                .setValue("2"));

        locator = page.locator("#SELECTOR_2");
        locator.selectOption(new SelectOption()
                .setIndex(2));

        locator = page.locator("#SELECTOR_3");
        locator.selectOption(new SelectOption()
                .setLabel("2004"));

        locator = page.locator("text=Next");
        locator.click();

    }

    @AfterAll
    public static void tearDown() {
        page.close();
    }

}
