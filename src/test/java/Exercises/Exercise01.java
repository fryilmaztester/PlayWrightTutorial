package Exercises;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Exercise01 {

    // Navigate to website  https://testpages.herokuapp.com/styled/index.html
    // Under the  ORIGINAL CONTENTS
    // click on Alerts
    // print the URL
    // navigate back
    // print the URL
    // Click on Basic Ajax
    // print the URL
    // enter value-> 20 and Enter
    // and then verify Submitted Values is displayed open page
    // close driver

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://testpages.herokuapp.com/styled/index.html";
    static Keyboard keyboard;

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
    }

    @AfterAll
    public static void tearDown(){
        page.close();
    }

    @Test
    public void test01(){
        locator = page.locator("#alerts");

        locator.click();

        String afterClickedURL = page.url();
        System.out.println("Tıklandıktan sonra URL: " + afterClickedURL);

        page.goBack();

        String backedURL = page.url();
        System.out.println("Backed URL" + backedURL);

        locator = page.locator("#basicajax");
        locator.click();

        String basicAjaxURL = page.url();
        System.out.println("basicAjax URL" + basicAjaxURL);

        locator = page.locator("#lteq30");
        locator.fill("20");
        keyboard.press("Enter");

        locator = page.locator("text=Submitted Values");
        assertThat(locator).isVisible();


    }



}
