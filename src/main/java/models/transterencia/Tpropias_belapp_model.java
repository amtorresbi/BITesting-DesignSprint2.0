package models.transterencia;

import java.net.MalformedURLException;

import com.aventstack.extentreports.ExtentTest;

import libraries.Reports;
import pages.transferencia.Tpropias_belapp_page;

public class Tpropias_belapp_model {

    private Tpropias_belapp_page tpropias_belapp_page = new Tpropias_belapp_page();
    private ExtentTest test; 

    public Tpropias_belapp_model() {
        super();
    }

    public void tpropias_belappBS_transferencia() throws MalformedURLException {
        tpropias_belapp_page.iniciarNavegadorAndroid();
        tpropias_belapp_page.clickbtnCancelarGPS();
        tpropias_belapp_page.clickbtnSkip();
        tpropias_belapp_page.clickbtnFlags();
        tpropias_belapp_page.scrollEntregable();
        tpropias_belapp_page.escribirtxtEntregable();
        tpropias_belapp_page.clickbtnReiniciar();
        tpropias_belapp_page.clickbtnCancelarGPS();
        tpropias_belapp_page.clickbtnDespliegueCampos();
        tpropias_belapp_page.escribirtxtCodigo();
        tpropias_belapp_page.escribirtxtUsuario();
        tpropias_belapp_page.escribirtxtContrasena();
        tpropias_belapp_page.clickbtnLogin();
        tpropias_belapp_page.clickbtnAceptar();
        tpropias_belapp_page.escribirtxtToken();
        tpropias_belapp_page.clickbtnToken();
        test = Reports.createTest("Ingreso de Token Dispositivo Android Browserstack");
        Reports.assign(test, "LAU", "TEST", "Samsung Galaxy S23 ULTRA");
        Reports.logPass(test, "Ingreso de token exitoso!");
        tpropias_belapp_page.clickbtnCancelarFID();
        tpropias_belapp_page.clickbtnCancelarGPS();
        tpropias_belapp_page.clickbtnFav();
        tpropias_belapp_page.clickbtnHamb();
        tpropias_belapp_page.clickbtnAceptar();
        tpropias_belapp_page.clickbtnAceptar();
        tpropias_belapp_page.clickbtnCancelar();
        test = Reports.createTest("Login Dispositivo Android Browserstack");
        Reports.assign(test, "LAU", "TEST", "Samsung Galaxy S23 ULTRA");
        Reports.logPass(test, "Ingreso de código exitoso! Código 9054 ");
        Reports.logPass(test, "Ingreso de usuario exitoso! Usuario IDELGADO ");
        Reports.logPass(test, "Ingreso de contraseña exitoso! Contraseña 123456 ");
        Reports.logPass(test, "Login exitoso!");
        tpropias_belapp_page.clickbtnHamb();
        tpropias_belapp_page.clickbtnTransferencias();
        tpropias_belapp_page.clicklstCuentaDebitar();
        tpropias_belapp_page.clicklstCuentas();
        tpropias_belapp_page.clicklstCuentaAcreditar();
        tpropias_belapp_page.clicklstCuentas2();
        tpropias_belapp_page.escribirtxtMonto();
        tpropias_belapp_page.escribirtxtComentario();
        tpropias_belapp_page.clickbtnTransferir();
        tpropias_belapp_page.clickbtnConfirmar();
        tpropias_belapp_page.clickbtnCerrarComprobante();
         test = Reports.createTest("Transferencia Dispositivo Android Browserstack");
        Reports.assign(test, "LAU", "TEST", "Samsung Galaxy S23 ULTRA");
        Reports.logPass(test, "Cuenta a debitar en Quetzales - Monetaria - 0004464319");
        Reports.logPass(test, "Cuenta a acreditar en Dolares - Monetaria - 0000219758");
        Reports.logPass(test, "Monto a acreditar de 1.50");
        Reports.logPass(test, "Ingreso de comentario: Prueba Appium BS Transferencias Propias ");
        Reports.logPass(test, "Transferencia exitosa!");
    }
    
}
