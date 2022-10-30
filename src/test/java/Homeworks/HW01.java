package Homeworks;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

public class HW01 {

     /*
    Launch the browser.
    Open "https://demoqa.com/select-menu".
    Select the Old Style Select Menu using the element id.
    Print all the options of the dropdown.
    Select 'Purple' using the index.
    After that, select 'Magenta' using visible text.
    Select an option using value.
    Close the browser

    Tarayıcıyı başlatın.
    "https://demoqa.com/select-menu" sayfasını açın .
    Öğe kimliğini kullanarak Eski Stil Seçim Menüsünü seçin.
    Açılır listedeki tüm seçenekleri yazdırın.
    Dizini kullanarak 'Mor'u seçin.
    Bundan sonra, görünür metni kullanarak 'Macenta'yı seçin.
    Değeri kullanarak bir seçenek belirleyin.
    tarayıcıyı kapat
     */


    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://demoqa.com/select-menu";
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

    @Test
    public void test01() throws InterruptedException {
        locator = page.locator("#oldSelectMenu");
        System.out.println("Old Style Count: " + locator.count() + "\n");

        //System.out.println(locator.allInnerTexts()); // List olarak Döner
        System.out.println(locator.innerText()); //Text şeklinde yazdırır.
        //System.out.println(locator.textContent()); //Yan yana bütün seçenekleri yazar
        //System.out.println(locator.innerHTML()); //HTML ile birlikte bütün text metinleri yazdırır

        locator.selectOption(new SelectOption()
                .setLabel("Purple"));

        Thread.sleep(2000);

        locator.selectOption(new SelectOption()
                .setValue("9"));

      //  locator = page.locator("select[xpath=\"1\"]");
        System.out.println("Seçili Olan: " + locator.textContent());



    }
}
