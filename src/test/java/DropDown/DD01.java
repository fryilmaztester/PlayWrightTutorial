package DropDown;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DD01 {

    /*
    go to url : https://opensource-demo.orangehrmlive.com/
    login with username : Admin
    login with password : admin123
    click login button
    Click on PIM
    Click on Employee List
        Employee Name -> use faker class
        Id -> use faker class
        Employment Status -> select by index :2
        Include ->selectByVisibleText :Current and Past Employees
        Supervisor Name -> use faker class
        Job Title ->selectByValue :IT Manager
        Sub Unit ->selectByValue :3
    click search button
    verify text visible : "No Records Found"
    */

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://opensource-demo.orangehrmlive.com/";
    static Faker faker;


    @BeforeEach
    public void setUp(){
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().
                setHeadless(headless).setSlowMo(slowMotionTime));

        context = browser.newContext();
        page = context.newPage();

        faker = new Faker();

        page.navigate(URL);

        locator = page.locator("input[placeholder='Username']");
        locator.fill("Admin");

        locator = page.locator("input[placeholder='Password']");
        locator.fill("admin123");

        locator = page.locator("button[type='submit']");
        locator.click(new Locator.ClickOptions().setButton(MouseButton.LEFT));

        locator = page.locator(".oxd-main-menu-item.active");
        locator.click();

        locator = page.locator(".oxd-topbar-body-nav-tab.--visited");
        locator.click();

        locator = page.locator("input[placeholder='Type for hints...']").first();
        locator.fill(faker.name().firstName() + " " + faker.name().lastName());
        locator.press("Enter");


        locator = page.locator("input[class='oxd-input oxd-input--active']").last();
        locator.fill(faker.number().digits(9));

        Locator locatorDropDown = page.locator(".oxd-select-text-input");
        locatorDropDown.nth(0).click();
    }

    @Test
    public void test01() throws InterruptedException {

        Thread.sleep(2000);
        locator = page.locator("div[role='option']");
        System.out.println(locator.count());
        for (int i= 0; i<locator.count(); i++){
            System.out.println(locator.nth(i).textContent());
        }
        locator.nth(2).click();
        //locator.selectOption(new SelectOption().setIndex(1));


    }

    @Test
    public void test02() throws InterruptedException {

        Thread.sleep(2000);
        locator = page.locator("div[role='option']");
        System.out.println(locator.count());
        for (int i= 0; i<locator.count(); i++){
            System.out.println(locator.nth(i).textContent());
        }

        locator.selectOption(new SelectOption().setLabel("Part-Time Internship"));


    }

    @Test
    public void test03() throws InterruptedException {

        Thread.sleep(2000);
        locator = page.locator("div[role='option']");
        System.out.println(locator.count());
        for (int i= 0; i<locator.count(); i++){
            System.out.println(locator.nth(i).textContent());
        }

        locator.selectOption(new SelectOption().setValue("2"));

    }

    @AfterEach
    public void tearDown(){
        page.close();
    }

}
