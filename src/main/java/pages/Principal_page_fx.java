package pages;

import helpers.Bi_helper;
import org.openqa.selenium.By;

import java.time.Duration;

public class Principal_page_fx extends General_page {
    private String DATA_JSON = Bi_helper.rutaJson("principal_elements.json");

    public By menuinformacionDeCuentas = By
            .xpath(Bi_helper.obtenerDato("menuInformacionDeCuentas", "xpath", DATA_JSON));
    public By menuTransferencias = By.xpath(Bi_helper.obtenerDato("menuTransferencias", "xpath", DATA_JSON));
    public By menuPagosYAportes = By.xpath(Bi_helper.obtenerDato("menuPagosYAportes", "xpath", DATA_JSON));
    public By menuAgenciaEnLinea = By.xpath(Bi_helper.obtenerDato("menuAgenciaEnLinea", "xpath", DATA_JSON));
    public By menuHistorial = By.xpath(Bi_helper.obtenerDato("menuHistorial", "xpath", DATA_JSON));
    public By menuOtrosSitios = By.xpath(Bi_helper.obtenerDato("menuOtrosSitios", "xpath", DATA_JSON));
    public By cerrarSesion = By.xpath(Bi_helper.obtenerDato("btnCerrarSesion", "xpath", DATA_JSON));
    public By home = By.xpath(Bi_helper.obtenerDato("home", "xpath", DATA_JSON));

    public Principal_page_fx() {
        super();
    }

    public void clickMenuTransferencias() {
        click(menuTransferencias);
    }

    public Boolean existeCerrarSesion() {
        return esVisible(cerrarSesion);
    }

    public void cerrarSesion() {
        esperaExplicita(cerrarSesion, Duration.ofSeconds(10));
        click(cerrarSesion);

    }

    public void clickHome() {
        esperaImplicita(Duration.ofMillis(200));
        click(home);
    }

    /***********************/
     public void clicMenuInformacionDeCuentas() {
        click(menuinformacionDeCuentas);
    }
}
