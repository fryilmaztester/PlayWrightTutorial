package DropDown;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/*
    go to url : https://the-internet.herokuapp.com/dropdown
    Drapdown da var olan bütün seçenekleri yazdırınız.
    Option 1 i --> value ile seçiniz
    Option 2 yi --> index ile seçiniz
    Option 1 i --> label ile seçiniz
 */

public class DD02 {


    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://the-internet.herokuapp.com/dropdown";


    @BeforeEach
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().
                setHeadless(headless).setSlowMo(slowMotionTime));

        context = browser.newContext();
        page = context.newPage();

        page.navigate(URL);
    }

    @Test
    public void test01() throws InterruptedException {
        locator = page.locator("#dropdown");
        System.out.println("Count: " + locator.count());
        locator.selectOption(new SelectOption()
                .setValue("1"));

        locator = page.locator("option[value='1']");

        // Thread.sleep(2000);

        System.out.println(locator.getAttribute("selected"));
        assertThat(locator).hasAttribute("selected", "selected");
        System.out.println("Test 1: " + locator.innerText());
    }

    @Test
    public void test02() throws InterruptedException {
        locator = page.locator("#dropdown");

        locator.selectOption(new SelectOption()
                .setIndex(2));
        // Thread.sleep(2000);

        locator = page.locator("option[value='2']");
        System.out.println(locator.getAttribute("selected"));
        assertThat(locator).hasAttribute("selected", "selected");
        System.out.println("Test 2: " + locator.innerText());

    }

    @Test
    public void test03() throws InterruptedException {
        locator = page.locator("#dropdown");

        locator.selectOption(new SelectOption().
                setLabel("Option 1"));

        // Thread.sleep(2000);

        locator = page.locator("option[value='1']");
        System.out.println(locator.getAttribute("selected"));
        assertThat(locator).hasAttribute("selected", "selected");
        System.out.println("Test 1: " + locator.innerText());

    }

    @AfterEach
    public void tearDown() {
        System.out.println("AFTER: " + new Page.CloseOptions().runBeforeUnload);
        page.close(new Page.CloseOptions()
                .setRunBeforeUnload(false));
    }
}
