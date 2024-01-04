package tests;

import com.aventstack.extentreports.ExtentTest;
import config.Preferencias;
import libraries.Reports;
import models.Login_model;
import org.testng.annotations.Test;

public class Login_test {
    @Test
    public void LoginTest() {
        System.out.println("Entro");
        ExtentTest test = Reports.createTest("Login");
        Login_model loginModel = new Login_model();

        System.out.println(loginModel);

        try {
            Reports.assign(test, "Equipo Demo QA", "Login", Preferencias.getInstance().obtenerAtributo("navegadorNombre"));
            loginModel.procesar(test);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Reports.logFail(test, "Error en el inicio de sesion");
            Reports.cerrarTest();
            loginModel.cerrarNavegador();
        }
    }
}
