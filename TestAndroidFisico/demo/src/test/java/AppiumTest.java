import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class AppiumTest {
    static AppiumDriver<MobileElement> driver;

    public static void main(String[] args) {
        try {
            openApp();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void openApp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appium:noReset", true); // Configura noReset en true
        cap.setCapability("appium:deviceName", "Galaxy A34 5G");
        cap.setCapability("appium:udid", "RFCW4273XKK");
        cap.setCapability("platformName", "Android");
        cap.setCapability("appium:platformVersion", "13");
        cap.setCapability("appium:appPackage", "gt.com.bi.bienlinea.dev");
        cap.setCapability("appium:appActivity", "bienlinea.bi.com.gt.auth.LoginActivity");
        cap.setCapability("appium:automationName", "UiAutomator2");

        URL url = new URL("http://127.0.0.1:4723/");

        driver = new AppiumDriver<MobileElement>(url, cap);

        System.out.println("Application started...");

        String qa = "qa01";
        String codigoBi = "1782131";
        String usuarioBi = "url03";
        String passBi = "123456";
        // escoger la qa
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/btn_feature_flags")).click();

        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"Entregable\"))"));
        // setear la qa
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/url_env")).sendKeys(qa);
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/snackbar_action")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By biLogo = By.id("gt.com.bi.bienlinea.dev:id/text_version_name");
        wait.until(ExpectedConditions.visibilityOfElementLocated(biLogo));
        //
        MobileElement ingresarConDatosButton = driver
                .findElement(By.id("gt.com.bi.bienlinea.dev:id/iv_entry_data_arrow"));
        ingresarConDatosButton.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // hacer login
        // insertar valores
        MobileElement codigoInputLogin = driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/edit_code"));
        codigoInputLogin.sendKeys(codigoBi);
        MobileElement userInputLogin = driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/edit_user"));
        userInputLogin.sendKeys(usuarioBi);
        MobileElement passInputLogin = driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/edit_password"));
        passInputLogin.sendKeys(passBi);
        // click en el boton de login
        MobileElement loginButton = driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/btn_login"));
        loginButton.click();

        By loaderLocator = By.id("gt.com.bi.bienlinea.dev:id/lottie_loader");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        // espera
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        System.out.println("Login done...");
        // aceptar el token de inicio
        // if (isElementPresentById(driver,
        // "android:id/button1")) {
        // // click en el boton de aceptar
        // driver.findElement(By.id("android:id/button1")).click();

        // // ingreso de token
        // driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/edit_token")).sendKeys("123456");
        // // click en el boton de aceptar
        // driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/btn_token_in")).click();
        // }
        // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // // pesta;a de permisos
        // if (isElementPresentById(driver,
        // "gt.com.bi.bienlinea.dev:id/card_fav")) {
        // // click en el boton de aceptar
        // driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/text_no_thanks")).click();
        // }

        // if (isElementPresentById(driver,
        // "gt.com.bi.bienlinea.dev:id/tv_permissions_title")) {
        // // click en el boton de aceptar
        // driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/btn_accept_permission")).click();
        // driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();

        // }

        // // pesta;a de permisos
        // if (isElementPresentById(driver,
        // "gt.com.bi.bienlinea.dev:id/parentPanel")) {
        // // click en el boton de aceptar
        // driver.findElement(By.id("android:id/button1")).click();
        // }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        driver.findElement(By.id("android:id/button2")).click();
        // entrar a transferencias
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();
        // clieck en transferencias
        driver.findElement(By.xpath(
                "//android.widget.CheckedTextView[@resource-id=\"gt.com.bi.bienlinea.dev:id/design_menu_item_text\" and @text=\"Transferir\"]"))
                .click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        // click en el boton de transferir
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/text_account_transfer_name")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));

        driver.findElement(By.xpath(
                "//android.widget.TextView[@resource-id=\"gt.com.bi.bienlinea.dev:id/text_account_description\" and @text=\"CUENTA DE AHORRO BI GTQ-0005075\"]"))
                .click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/text_account_accredit_alias")).click();
        driver.findElement(By.xpath(
                "//android.widget.TextView[@resource-id=\"gt.com.bi.bienlinea.dev:id/text_account_description\" and @text=\"CUENTA MONETARIA BI US$-0000021022\"]"))
                .click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/edit_amount_transfer")).sendKeys("1.00");
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/edit_comment_transfer")).sendKeys("Comentario de prueba");
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/btn_transfer")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/btn_accept_transfer")).click();
        // guardar comprobante
        driver.findElement(By.id("gt.com.bi.bienlinea.dev:id/btn_save")).click();
        // terminar el test

        driver.quit();
    }

    public static boolean isElementPresentById(AppiumDriver<MobileElement> driver, String elementId) {
        try {
            driver.findElement(By.id(elementId));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
