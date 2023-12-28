package pages.transferencia;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helpers.Bi_helper;
import io.appium.java_client.AppiumBy;
import pages.General_page;

public class Tpropias_belapp_page extends General_page {

    private String TPROPIAS_BELLAPP = Bi_helper.rutaJson("tpropias_belapp_elements.json");

    private By btnCancelarGPS = AppiumBy.id(Bi_helper.obtenerDato("btnCancelarGPS", "id", TPROPIAS_BELLAPP));
    private By btnSkip = AppiumBy.id(Bi_helper.obtenerDato("btnSkipTutorial", "id", TPROPIAS_BELLAPP));
    private By btnFlags = AppiumBy.id(Bi_helper.obtenerDato("btnFlags", "id", TPROPIAS_BELLAPP));
    private By txtEntregable = AppiumBy.id(Bi_helper.obtenerDato("txtEntregable", "id", TPROPIAS_BELLAPP));
    private By btnReiniciar = AppiumBy.id(Bi_helper.obtenerDato("btnReiniciar", "id", TPROPIAS_BELLAPP));
    private By btnDespliegueCampos = AppiumBy.id(Bi_helper.obtenerDato("btnDespliegueCampos", "id", TPROPIAS_BELLAPP));
    private By txtCodigo = AppiumBy.id(Bi_helper.obtenerDato("txtCodigo", "id", TPROPIAS_BELLAPP));
    private By txtUsuario = AppiumBy.id(Bi_helper.obtenerDato("txtUsuario", "id", TPROPIAS_BELLAPP));
    private By txtContrasena = AppiumBy.id(Bi_helper.obtenerDato("txtContrasena", "id", TPROPIAS_BELLAPP));
    private By btnLogin = AppiumBy.id(Bi_helper.obtenerDato("btnLogin", "id", TPROPIAS_BELLAPP));
    private By btnAceptar = AppiumBy.id(Bi_helper.obtenerDato("btnAceptar", "id", TPROPIAS_BELLAPP));
    private By btnCancelar = AppiumBy.id(Bi_helper.obtenerDato("btnCancelar", "id", TPROPIAS_BELLAPP));
    private By txtToken = AppiumBy.id(Bi_helper.obtenerDato("txtToken", "id", TPROPIAS_BELLAPP));
    private By btnToken = AppiumBy.id(Bi_helper.obtenerDato("btnToken", "id", TPROPIAS_BELLAPP));
    private By btnCancelarFID = AppiumBy.id(Bi_helper.obtenerDato("btnCancelarFID", "id", TPROPIAS_BELLAPP));
    private By btnFav = AppiumBy.accessibilityId(Bi_helper.obtenerDato("btnFav", "accessibilityId", TPROPIAS_BELLAPP));
    private By btnHamb = AppiumBy.accessibilityId(Bi_helper.obtenerDato("btnHamb", "accessibilityId", TPROPIAS_BELLAPP));
    private By btnTransferencias = AppiumBy.id(Bi_helper.obtenerDato("btnTransferencias", "id", TPROPIAS_BELLAPP));
    private By lstCuentaDebitar = AppiumBy.id(Bi_helper.obtenerDato("lstCuentaDebitar", "id", TPROPIAS_BELLAPP));
    private By contCuentas = AppiumBy.id(Bi_helper.obtenerDato("contCuentas", "id", TPROPIAS_BELLAPP));
    private By lstCuentas = AppiumBy.className(Bi_helper.obtenerDato("lstCuentas", "className", TPROPIAS_BELLAPP));
    private By lstCuentaAcreditar = AppiumBy.id(Bi_helper.obtenerDato("lstCuentaAcreditar", "id", TPROPIAS_BELLAPP));
    private By lcontCuentas = AppiumBy.id(Bi_helper.obtenerDato("lcontCuentas", "id", TPROPIAS_BELLAPP));
    private By llstCuentas = AppiumBy.className(Bi_helper.obtenerDato("llstCuentas", "className", TPROPIAS_BELLAPP));
    private By lstCuentas2 = AppiumBy.className(Bi_helper.obtenerDato("lstCuentas2", "className", TPROPIAS_BELLAPP));
    private By txtMonto = AppiumBy.id(Bi_helper.obtenerDato("txtMonto", "id", TPROPIAS_BELLAPP));
    private By txtComentario = AppiumBy.id(Bi_helper.obtenerDato("txtComentario", "id", TPROPIAS_BELLAPP));
    private By btnTransferir = AppiumBy.id(Bi_helper.obtenerDato("btnTransferir", "id", TPROPIAS_BELLAPP));
    private By btnConfirmar = AppiumBy.id(Bi_helper.obtenerDato("btnConfirmar", "id", TPROPIAS_BELLAPP));
    private By btnCerrarComprobante = AppiumBy.id(Bi_helper.obtenerDato("btnCerrarComprobante", "id", TPROPIAS_BELLAPP));

    public Tpropias_belapp_page() {
        super();
    }

    public void clickbtnCancelarGPS() {
        esperaExplicitaAndroid(btnCancelarGPS, Duration.ofSeconds(30));
        clickAndroid(btnCancelarGPS);
    }

    public void clickbtnSkip() {
        esperaExplicitaAndroid(btnSkip, Duration.ofSeconds(30));
        clickAndroid(btnSkip);
    }

    public void clickbtnFlags() {
        esperaExplicitaAndroid(btnFlags, Duration.ofSeconds(30));
        clickAndroid(btnFlags);
    }

    public void escribirtxtEntregable() {
        esperaExplicitaAndroid(txtEntregable, Duration.ofSeconds(30));
        escribirAndroid(txtEntregable, "qa01");
    }

    public void clickbtnReiniciar() {
        esperaExplicitaAndroid(btnReiniciar, Duration.ofSeconds(30));
        clickAndroid(btnReiniciar);
    }

    public void clickbtnDespliegueCampos() {
        esperaExplicitaAndroid(btnDespliegueCampos, Duration.ofSeconds(30));
        clickAndroid(btnDespliegueCampos);
    }

    public void escribirtxtCodigo() {
        escribirAndroid(txtCodigo, "9054");
    }

    public void escribirtxtUsuario() {
        escribirAndroid(txtUsuario, "IDELGADO");
    }

    public void escribirtxtContrasena() {
        escribirAndroid(txtContrasena, "123456");
    }

    public void clickbtnLogin() {
        esperaExplicitaAndroid(btnLogin, Duration.ofSeconds(30));
        clickAndroid(btnLogin);
    }

    public void clickbtnAceptar() {
        esperaExplicitaAndroid(btnAceptar, Duration.ofSeconds(30));
        clickAndroid(btnAceptar);
    }

    public void clickbtnCancelar() {
        esperaExplicitaAndroid(btnCancelar, Duration.ofSeconds(30));
        clickAndroid(btnCancelar);
    }

    public void escribirtxtToken() {
        esperaExplicitaAndroid(txtToken, Duration.ofSeconds(30));
        escribirAndroid(txtToken, "123456");
    }

    public void clickbtnToken() {
        clickAndroid(btnToken);
    }

    public void clickbtnCancelarFID() {
        esperaExplicitaAndroid(btnCancelarFID, Duration.ofSeconds(30));
        clickAndroid(btnCancelarFID);
    }

    public void clickbtnFav() {
        esperaExplicitaAndroid(btnFav, Duration.ofSeconds(30));
        clickAndroid(btnFav);
    }

    public void clickbtnHamb() {
        esperaExplicitaAndroid(btnHamb, Duration.ofSeconds(30));
        clickAndroid(btnHamb);
    }

    public void clickbtnTransferencias() {
        esperaExplicitaAndroid(btnTransferencias, Duration.ofSeconds(30));
        clickAndroid(btnTransferencias);
    }

    public void clicklstCuentaDebitar() {
        esperaExplicitaAndroid(lstCuentaDebitar, Duration.ofSeconds(30));
        clickAndroid(lstCuentaDebitar);
    }

    public void clicklstCuentas() {
        buscarElementos(esperaExplicitaAndroid(contCuentas, Duration.ofSeconds(30)), lstCuentas).get(0).click();
    }

    public void clicklstCuentaAcreditar() {
        esperaExplicitaAndroid(lstCuentaAcreditar, Duration.ofSeconds(30));
        clickAndroid(lstCuentaAcreditar);
    }

    public void clicklstCuentas2() {
        buscarElementos(buscarElementos(esperaExplicitaAndroid(lcontCuentas, Duration.ofSeconds(30)), llstCuentas).get(0), lstCuentas2).get(0).click();        
    }

    public void escribirtxtMonto() {
        esperaExplicitaAndroid(txtMonto, Duration.ofSeconds(30));
        escribirAndroid(txtMonto, "1.50");
    }

    public void escribirtxtComentario() {
        escribirAndroid(txtComentario, "Prueba Appium BS Transferencias Propias");
    }

    public void clickbtnTransferir() {
        clickAndroid(btnTransferir);
    }

    public void clickbtnConfirmar() {
        esperaExplicitaAndroid(btnConfirmar, Duration.ofSeconds(30));
        clickAndroid(btnConfirmar);
    }

    public void clickbtnCerrarComprobante() {
        esperaExplicitaAndroid(btnCerrarComprobante, Duration.ofSeconds(30));
        clickAndroid(btnCerrarComprobante);
    }

    public WebDriver enviarDriver() {
        return obtenerDriver();
    }

    public void cerrarDriver() {
        cerrarNavegador();
    }

}
