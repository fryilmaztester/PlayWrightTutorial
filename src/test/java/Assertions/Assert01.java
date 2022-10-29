package Assertions;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class Assert01 {

    /*
    go to amazon homepage
    Do the following tasks by creating 3 different test methods
    1- Test if the url contains amazon
    2- test if the title does not contain facebook
    3- test that the amazon logo appears in the upper left corner
    */

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static String URL = "https://www.amazon.com/";
    static Locator locator;

    @BeforeAll
    public static void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(10)
        );

        page = browser.newPage();

    }

    @AfterAll
    public static void tearDown(){
        page.close();
    }

    @Test
    public void test01(){
        page.navigate(URL);
        String expectedResult = "amazon";
        String actualResult = page.url();

        System.out.println("actualResult: " + actualResult);
       // Assertions.assertTrue(actualResult.contains(expectedResult)); //JUnit Assertion
        assertThat(page).hasURL(URL); //Playwright Assertions with hasURL()
    }

    @Test
    public void test02(){
        page.navigate("https://www.google.com");
        String expectedResult = "Google";
        String actualResult = page.title();
       // Assertions.assertEquals(actualResult,expectedResult);
        assertThat(page).hasTitle("Google");//Playwright Assertions with hasTitle()
    }

    @Test
    public void test03(){
        page.navigate(URL);
        assertThat(page.locator("id=nav-logo-sprites")).isVisible();
    }

    @Test
    public void test04(){
        page.navigate("https://www.linkedin.com/");
        locator = page.locator("text=Sign in").nth(1);
        assertThat(locator).containsText("Sign in");
    }

    @Test
    public void test05(){
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        locator = page.locator("input[type=\"checkbox\"]").last();
        assertThat(locator).isChecked();

    }

    @Test
    public void test06(){
        page.navigate("https://www.linkedin.com/");
        locator = page.locator("text=Sign in").nth(1);
        assertThat(locator).hasAttribute("type","submit");
    }


}
