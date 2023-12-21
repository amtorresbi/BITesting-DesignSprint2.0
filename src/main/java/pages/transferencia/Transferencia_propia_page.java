package pages.transferencia;

import helpers.Bi_helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.General_page;

import java.time.Duration;

public class Transferencia_propia_page extends General_page {

    private String TRANSFERENCIA_MENU_JSON = Bi_helper.rutaJson("transferencia_elements.json");
    private String DATA_JSON = Bi_helper.rutaJson("transferencia_propia_elements.json");

    public By transferenciaMonetariasYAhorros = By
            .xpath(Bi_helper.obtenerDato("transferenciaMonetariasYAhorros", "xpath", TRANSFERENCIA_MENU_JSON));
    public By transferenciaBiCreditOnline = By
            .xpath(Bi_helper.obtenerDato("transferenciaBiCreditOnline", "xpath", TRANSFERENCIA_MENU_JSON));
    public By transferenciaTarjetaPrepago = By
            .xpath(Bi_helper.obtenerDato("transferenciaTarjetaPrepago", "xpath", TRANSFERENCIA_MENU_JSON));
    public By transferenciaHistorial = By
            .xpath(Bi_helper.obtenerDato("transferenciaHistorial", "xpath", TRANSFERENCIA_MENU_JSON));
    public By seleccionarListaDebitar = By.xpath(Bi_helper.obtenerDato("selectCuentaDebitar", "xpath", DATA_JSON));
    public By seleccionarListaAcreditar = By.xpath(Bi_helper.obtenerDato("selectCuentaAcreditar", "xpath", DATA_JSON));
    public By formMontoDebito = By.id(Bi_helper.obtenerDato("formMontoDebito", "id", DATA_JSON));
    public By formComentario = By.id(Bi_helper.obtenerDato("formComentario", "id", DATA_JSON));
    public By btnContinuar = By.id(Bi_helper.obtenerDato("btnContinuar", "id", DATA_JSON));
    public By btnConfirmar = By.id(Bi_helper.obtenerDato("btnConfirmar", "id", DATA_JSON));
    public By lblTransferenciaExitosa = By.xpath(Bi_helper.obtenerDato("lblTransferenciaExitosa", "xpath", DATA_JSON));
    public By lblAutorizacion = By.xpath(Bi_helper.obtenerDato("lblAutorizacion", "xpath", DATA_JSON));
    public By lblCuentaDebitada = By.xpath(Bi_helper.obtenerDato("lblCuentaDebitada", "xpath", DATA_JSON));
    public By lblCuentaAcreditada = By.xpath(Bi_helper.obtenerDato("lblCuentaAcreditada", "xpath", DATA_JSON));
    public By lblTasaDeCambioVenta = By.id(Bi_helper.obtenerDato("lblTasaDeCambioVenta", "id", DATA_JSON));
    public By formMontoDebitoQV = By.xpath(Bi_helper.obtenerDato("formMontoDebitoQV", "xpath", DATA_JSON));
    public By formMontoDebito$V = By.xpath(Bi_helper.obtenerDato("formMontoDebito$V", "xpath", DATA_JSON));
    public By lblTasaDeCambioCompra = By.id(Bi_helper.obtenerDato("lblTasaDeCambioCompra", "id", DATA_JSON));
    public By formMontoDebito$C = By.xpath(Bi_helper.obtenerDato("formMontoDebito$C", "xpath", DATA_JSON));
    public By lblTransferenciaErronea = By.xpath(Bi_helper.obtenerDato("lblTransferenciaErronea", "xpath", DATA_JSON));
    public By lblMontoAcreditado$ = By.xpath(Bi_helper.obtenerDato("lblMontoAcreditado$", "xpath", DATA_JSON));
    public By lblTxtTasaVenta = By.id(Bi_helper.obtenerDato("lblTxtTasaVenta", "id", DATA_JSON));
    public By lblTxtTasaCompra = By.id(Bi_helper.obtenerDato("lblTxtTasaCompra", "id", DATA_JSON));

    public Transferencia_propia_page() {
        super();
    }

    public void clicktransferenciaMonetariasYAhorros() {
        esperaExplicita(transferenciaMonetariasYAhorros, Duration.ofSeconds(10));
        click(transferenciaMonetariasYAhorros);
    }

    public void clickseleccionarListaDebitar() {
        esperaExplicita(seleccionarListaDebitar, Duration.ofSeconds(10));
        click(seleccionarListaDebitar);
    }

    public boolean seleccionarListaDebitar(String cuenta) {
        return seleccionarLista(seleccionarListaDebitar, cuenta);
    }

    public void clickseleccionarListaAcreditar() {
        esperaExplicita(seleccionarListaAcreditar, Duration.ofSeconds(10));
        click(seleccionarListaAcreditar);
    }

    public boolean seleccionarListaAcreditar(String cuenta) {
        return seleccionarLista(seleccionarListaAcreditar, cuenta);
    }

    public Boolean esVisibleLblTasaDeCambioVenta() {
        return esVisible(lblTasaDeCambioVenta);
    }

    public void typeFormMontoDebitoQV(String monto) {
        escribir(formMontoDebitoQV, monto);
    }

    public Boolean esVisibleLblTasaDeCambioCompra() {
        return esVisible(lblTasaDeCambioCompra);
    }

    public void typeFormMontoDebito$C(String monto) {
        escribir(formMontoDebito$C, monto);
    }

    public void typeFormMontoDebito(String monto) {
        escribir(formMontoDebito, monto);
    }

    public void typeFormComentario(String monto) {
        escribir(formComentario, monto);
    }

    public void clickBtnContinuar() {
        click(btnContinuar);
    }

    public Boolean esVisibleBtnConfirmar() {
        return esVisible(btnConfirmar);
    }

    public void clickBtnConfirmar() {
        moverseA(btnConfirmar);
        esperaExplicita(btnConfirmar, Duration.ofSeconds(10));
        click(btnConfirmar);
    }

    public Boolean esVisibleLblAutorizacion() {
        return esVisible(lblAutorizacion);
    }

    public String obtenerTextolblMontoAcreditado$() {
        return obtenerTexto(lblMontoAcreditado$);
    }

    public String obtenerTextoLblAutorizacion() {
        return obtenerTexto(lblAutorizacion);
    }

    public String obtenerTextoLblCuentaDebitada() {
        return obtenerTexto(lblCuentaDebitada);
    }

    public String obtenerTextoLblCuentaAcreditada() {
        return obtenerTexto(lblCuentaAcreditada);
    }

    public String obtenerTextoLblTransferenciaExitosa() {
        return obtenerTexto(lblTransferenciaExitosa);
    }

    public Boolean isDisplayLblTransferenciaErronea() {
        return esVisible(lblTransferenciaErronea);
    }

    public String obtenerTextoLblTransferenciaErronea() {
        return obtenerTexto(lblTransferenciaErronea);
    }

    public String obtenerAtributoTxtTasaVenta() {
        return obtenerAtributo(lblTxtTasaVenta, "value");
    }

    public String obtenerAtributoTxtTasaCompra() {
        return obtenerAtributo(lblTxtTasaCompra, "value");
    }

    public WebDriver enviarDriver() {
        return obtenerDriver();
    }

    public void cerrarDriver() {
        cerrarNavegador();
    }
}
