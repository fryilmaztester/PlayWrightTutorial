package Exercises;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

public class FacebookRegister {

    /*
   Senaryo
   1- https://www.facebook.com/ sitesine giriniz.
   2- Yeni hesap oluştura tıklayınız.
   3- Formdaki tüm bilgileri giriniz.
     */


    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://www.facebook.com/ ";
    static Keyboard keyboard;
    static Faker faker;


    @BeforeAll
    public static void setUp() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
                        .setSlowMo(slowMotionTime)
        );



        page = browser.newPage(
                new Browser.NewPageOptions().
                        setPermissions(
                                asList("notifications")
                        ));
        keyboard = page.keyboard();
        faker = new Faker();

        page.navigate(URL);
    }

    /*
    CSS de
    ^ -> ile başlayan
    $ -> ile biten
    * -> içinde geçen

    Xpath
    //a[starts-with(@id,'u_')] -> ile başlayan
    //a[ends-with(@id,'_f7')]    -> ile biten
    //a[contains(@id,'u_')]    -> içinde geçen
     */

    @Test
    public void test01(){
        //locator = page.locator("text=Create New Account");
        locator = page.locator("a[id^='u_']");
        locator.click();

        //xpath=//input[starts-with(id,'u_')]
        locator = page.locator("xpath=//input[starts-with(@id,'u_')]");
        locator.first().fill(faker.name().firstName());

        locator.nth(1).fill(faker.name().lastName());

        locator = page.locator("input[id*='g_']").first();
        locator.fill(faker.phoneNumber().cellPhone());

        locator = page.locator("#password_step_input");
        locator.fill(faker.internet().password());

        //Dropdwon ile setValue()
        locator = page.locator("#day");
        locator.selectOption(new SelectOption()
                .setValue("1"));

        locator = page.locator("#day");
        locator.selectOption(new SelectOption()
                .setValue("1"));

        locator = page.locator("#month");
        locator.selectOption(new SelectOption()
                .setIndex(2));

        locator = page.locator("#year");
        locator.selectOption(new SelectOption()
                .setLabel("2003"));

        locator = page.locator("input[class^='_']");
        locator.nth(1).check();

        locator = page.locator("button", new Page.
                LocatorOptions().setHasText("Sign Up"));

    }

    @AfterAll
    public static void tearDown(){
        page.close();
    }
}
