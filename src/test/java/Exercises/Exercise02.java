package Exercises;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Exercise02 {

  /*
    https://id.heroku.com/login sayfasına gidiniz
    Bir mail adersi giriniz
    Bir password giriniz
    Login butonuna tiklayiniz
    "There was a problem with your login." texti görünür ise "kayit yapilamadi" yazdiriniz
    eğer yazı görünür değilse "kayıt yapıldı" yazdırınız
    Tüm sayfaları kapatınız

     */

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://id.heroku.com/login";
    static Keyboard keyboard;
    static Faker faker;

    @BeforeAll
    public static void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
                        .setSlowMo(slowMotionTime)
        );

        context = browser.newContext();
        page = context.newPage();

        keyboard = page.keyboard();

        page.navigate(URL);

        faker = new Faker();

    }

    @Test
    public void test02(){
        locator = page.locator("#email");
        locator.fill(faker.name().firstName() + "@clarusway.com");

        locator = page.locator("#password");
        locator.fill(faker.number().digits(10));

        locator = page.locator("button", new Page.LocatorOptions().setHasText("Log In"));
        locator.click();

        locator = page.locator("div[role=\"alert\"]");

        assertThat(locator).hasText("There was a problem with your login.");
        String actualText = locator.textContent();
        System.out.println(actualText);

        if (locator.isVisible()){
            System.out.println("kayit yapilamadi");
        }else{
            System.out.println("kayit yapildi");
        }
    }

    @AfterAll
    public static void tearDown(){
        page.close();
    }
}
