package Locators;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LC01 {

    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;
    static String URL = "https://www.linkedin.com/";
    static Locator locator;

    @BeforeAll
    public static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(10));

        context = browser.newContext();
        page = context.newPage();


    }


    @AfterAll
    public static void tearDown() {
        page.close();
    }


    @Test
    public void test01() {
        page.navigate(URL);
        page.locator("text=Discover").click();
        //text Locator
        // assertThat(page.locator("text=Discover")).isVisible();
    }

    @Test
    public void test02() {
        page.navigate("https://www.amazon.com");
        //Css Locator
        page.locator("#nav-logo-sprites").click();
    }

    @Test
    public void test03() {
        page.navigate("https://www.amazon.com");
        page.locator("[aria-label=\"Search\"]").type("MacBook");
    }

    @Test
    public void test04() {
        page.navigate(URL);
        //sıralama ile nth()
        page.locator("text=Sign in").nth(1).click();
    }

    @Test
    public void test05() {
        page.navigate(URL);
        //sıralama ile nth()
        locator = page.locator("text=Sign in").nth(1);
        System.out.println(locator.getAttribute("type")); //type attribute unun valuesu alınır
    }

    @Test
    public void test06() {
        page.navigate("https://www.amazon.com");
        locator = page.locator("#nav-xshop >> text=Sell");
        System.out.println(locator.textContent()); //Text metnini alma işlemi
    }

    @Test
    public void test07() {
        page.navigate(URL);
        locator = page.locator("input").nth(0);
        System.out.println(page.locator("input").count()); //kaç tane input olduğunu bizlere söyler
    }

    @Test
    public void test08() {
        page.navigate(URL);
        locator = page.locator("button", new Page.LocatorOptions().setHasText("Sign in"));
        System.out.println(locator.nth(0).textContent());
        System.out.println(locator.count());
        locator.nth(1).click();
    }

    @Test
    public void test09() {
        page.navigate("https://www.foreks.com");
        locator = page.locator("#searchInput");
        System.out.println(locator.inputValue().isEmpty());

    }

    @Test
    public void test10(){
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        locator = page.locator("input[type=\"checkbox\"]").first();
        locator.check();

    }


}
