package EmulatingDevices;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ED01 {

    static Playwright playwright;
    static Browser browser;
    static Page page;
    static Locator locator;
    static BrowserContext context;
    static boolean headless = false;
    static double slowMotionTime = 10;
    static String URL = "https://www.wikipedia.org/";
    static Keyboard keyboard;
    static String[] devices = {"iPhone 12"};


    @Test
    public void ed01() throws InterruptedException {
        //System.out.println(Arrays.toString(devices));
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
                        .setSlowMo(slowMotionTime)
        );

        context = browser.newContext(new Browser.NewContextOptions().
                setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 12 like Mac OS X) AppleWebKit/605.1.15 " +
                        "(KHTML, like Gecko) Version/16.0 Mobile/15E148 Safari/604.1").
                //     setUserAgent(devices[0]).
                        setIsMobile(true)
                .setHasTouch(true)
                .setViewportSize(414, 715));
        page = context.newPage();

        keyboard = page.keyboard();

        page.navigate(URL);

        Thread.sleep(5000);
    }
}
