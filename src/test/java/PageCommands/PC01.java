package PageCommands;

import com.microsoft.playwright.*;

public class PC01 {


    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        //Page Create etme
        Page page = browser.newPage();

        //Page create etme with options

    /*
        Page page1 = browser.newPage(
                new Browser.NewPageOptions().setUserAgent("Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3765.0 Mobile Safari/537.36")
                .setViewportSize(411, 731)
                .setDeviceScaleFactor(5)
                //.setIsMobile(true) //Mobil true
                //.setHasTouch(true) //Dokunmatik true
                .setLocale("en-US")
                .setGeolocation(41.889938, 12.492507) // Konum set edilir
                .setPermissions(asList("geolocation"))); // Konum izin verilir

        page1.navigate("https://www.openstreetmap.org/");

        Object dimensions = page1.evaluate("() => {\n" +
                "  return {\n" +
                "      width: document.documentElement.clientWidth,\n" +
                "      height: document.documentElement.clientHeight,\n" +
                "      deviceScaleFactor: window.devicePixelRatio\n" +
                "  }\n" +
                "}");
        System.out.println(dimensions);

     */
        //Page navigate etme
        page.navigate("https://www.google.com");

        //page title yazdırma
        System.out.println(page.title());

        //page in contentini yazdırma
      //  System.out.println(page.content());

        page.navigate("https://www.amazon.com");
        System.out.println(page.title());
        page.goBack(); // Bir önceki sayfaya gitme command i

        System.out.println(page.title());

        page.goForward(); //Bir sonraki sayfaya gitme command i

        System.out.println(page.title());

        System.out.println(page.url()); //Sayfa URL i


    }
}
