package Exercises;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.util.Arrays.asList;

public class TeknosaCase {

    /*
      https://www.teknosa.com/ adresine gidiniz
    arama cubuguna oppo yazip enter deyiniz
    sonuc sayisini yazdiriniz
    cikan ilk urune tiklayiniz
    sepete ekleyiniz
    sepetime git e tiklayiniz
    consol da "Sipariş Özeti" webelementinin text ini yazidiriniz
    Alisverisi tamamlayiniz
    son alarak da "Teknosa'ya hoş geldiniz"  webelementinin text ini yazidiriniz
    driver i kapatiniz
     */

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://www.teknosa.com/";
    static Keyboard keyboard;
    static JSHandle jsHandle;

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

        page.navigate(URL);
    }

    @Test
    public void testTeknosa() {
        locator = page.locator("#search-input");
        locator.fill("Oppo");

        keyboard.press("Enter");

        locator = page.locator("text=Sonuç").nth(1);
        System.out.println("Arama Sonuçları: " + locator.textContent().
                trim().
                replace(" ",""));

        locator = page.locator("#product-item");
        System.out.println("İlk Sayfa Sonuç Ürünleri: " + locator.count());

        jsHandle = page.evaluateHandle("() => scrollBy(0,300)");

        String firstProductTitle = locator.nth(0).getAttribute("title");
        locator.nth(0).click();

        locator = page.locator("h1[class=\"pdp-title\"]");
        String actualAddedBasket = locator.textContent().trim();
        //Assertions.assertTrue(actualAddedBasket.contains(firstProductTitle));

        locator = page.locator("#addToCartButton").nth(0);
        locator.click();

        locator = page.locator("text=Sepetim'e Git");
        locator.click();

        locator = page.locator("text=Sipariş Özeti");
        System.out.println("Sipariş Özeti: " + locator.textContent());

        locator = page.locator("text=Alışverişi Tamamla").nth(0);
        locator.click();


        locator = page.locator("text=Teknosa’ya Hoş Geldiniz");
        System.out.println("Hoş Geldiniz..." + locator.textContent());


    }

    @AfterAll
    public static void tearDown(){
        page.close();
    }
}
