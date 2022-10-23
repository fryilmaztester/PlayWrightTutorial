package BrowserCommands;

import com.microsoft.playwright.*;

public class BC01 {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        //Browser olşturma
       // Browser browser = playwright.chromium().launch();  //Şu an amazona gider fakat bizlere göremeyiz.

        //Görebilmek için setHeaddless ı false yapmamız gerekmektedir.
       /* Browser browser = playwright.chromium().launch(new BrowserType.
                LaunchOptions().setHeadless(false));


        */

        //Browser ı yavaşlatmak için bizler setSlowMo() metodunu kullanıyoruz.
        /*Browser browser = playwright.chromium().launch(new BrowserType.
                LaunchOptions().setSlowMo(10));

         */

        //Browser da  inspector ı açmak için bizler setDevtools() metodunu kullanıyoruz.
        /*Browser browser = playwright.chromium().launch(new BrowserType.
                LaunchOptions().setDevtools(true));

         */



        /*
        Browser browser = playwright.firefox().launch(new BrowserType.
                LaunchOptions().setHeadless(false));

        Browser browser = playwright.webkit().launch();.launch(new BrowserType.
                   LaunchOptions().setHeadless(false));


         */


        Browser browser = playwright.firefox().launch(new BrowserType.
                LaunchOptions().setHeadless(false));

        /* Browser ın adını yazdırma
        String name = playwright.chromium().name();
        System.out.println("name: " + name);
         */

        System.out.println("Browser version: " +browser.version()); // Browser verison yazdırma
        boolean isConnected = browser.isConnected(); // Browser a bağlı olup olmadığını gösteren fonk.
        System.out.println("Browser Type: " + browser.browserType().name()); // Browser Type ın ne olduğunu bizlere verir.


        //Page Oluşturma
        Page page = browser.newPage();

        //page navigate etme
        page.navigate("https://www.amazon.com");

    }
}
