import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Test {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch();
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://www.example.com/");
        Object dimensions = page.evaluate("() => {\n" +
                "  return {\n" +
                "      width: document.documentElement.clientWidth,\n" +
                "      height: document.documentElement.clientHeight,\n" +
                "      deviceScaleFactor: window.devicePixelRatio\n" +
                "  }\n" +
                "}");
        System.out.println(dimensions);

        System.out.println(System.getenv("BROWSER_CHANNEL"));

        String headfulEnv = System.getenv("HEADFUL");
        System.out.println("headFul: " + headfulEnv);


        String browserName = System.getenv("BROWSER");


        System.out.println(browserName);
    }
    }

