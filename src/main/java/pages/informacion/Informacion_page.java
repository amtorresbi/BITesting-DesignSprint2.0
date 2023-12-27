package pages.informacion;

import helpers.Bi_helper;
import org.openqa.selenium.By;
import pages.General_page;

import java.time.Duration;

public class Informacion_page extends General_page {

    private String INFORMACION_MENU_JSON = Bi_helper.rutaJson("informacion_elements.json");
    
    public By informacionMonetarias = By
            .xpath(Bi_helper.obtenerDato("informacionMonetarias", "xpath", INFORMACION_MENU_JSON));
    public By informacionAhorros = By
            .xpath(Bi_helper.obtenerDato("informacionAhorros", "xpath", INFORMACION_MENU_JSON));
    public By informacionTodasLasCuentas = By
            .xpath(Bi_helper.obtenerDato("informacionTodasLasCuentas", "xpath", INFORMACION_MENU_JSON));
    public Informacion_page() {
        super();
    }
    public void clickinformacionMonetarias() {
        esperaExplicita(informacionMonetarias, Duration.ofSeconds(10));
        click(informacionMonetarias);
    }
    public void clickinformacionAhorros() {
        esperaExplicita(informacionAhorros, Duration.ofSeconds(10));
        click(informacionAhorros);
    }
    public void clickinformacionTodasLasCuentas() {
        esperaExplicita(informacionTodasLasCuentas, Duration.ofSeconds(10));
        click(informacionTodasLasCuentas);
    }
    
}
