import com.microsoft.playwright.*;


public class PlaywrightBasics {


    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setSlowMo(50).setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.amazon.com");

        System.out.println("Title: " + page.title());

        String name = System.getProperty("os.name");
        System.out.println("name: " + name);


    }
}
