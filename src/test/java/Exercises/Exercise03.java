package Exercises;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Exercise03 {

    // Navigate to  https://testpages.herokuapp.com/styled/index.html
    // Click on  Calculate under Micro Apps
    // Type any number in the first input
    // Type any number in the second input
    // Click on Calculate
    // Get the result
    // Print the result

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://testpages.herokuapp.com/styled/index.html";
    static JSHandle jsHandle;

    @BeforeEach
    public void setUp(){
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().
                setHeadless(headless).setSlowMo(slowMotionTime));

        context = browser.newContext();
        page = context.newPage();

        page.navigate(URL);
    }

    @Test
    public void test03() throws InterruptedException {
        locator = page.locator("#calculatetest");

        Thread.sleep(5000);
        jsHandle = page.evaluateHandle("() => window.scrollBy(0,300)");

        locator.click();

        locator = page.locator("#number1");
        locator.fill("20");

        locator = page.locator("#number2");
        locator.fill("30");

        locator = page.locator("#calculate");
        locator.click();

        String answer = page.locator("#answer").textContent();
        System.out.println("answer: " + answer);


    }

    @AfterEach
    public void tearDown(){
        page.close();
    }
}
