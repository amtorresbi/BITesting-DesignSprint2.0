package tests.transferencia;

import com.aventstack.extentreports.ExtentTest;
import config.Preferencias;
import libraries.Reports;
import libraries.data_provider.Dtransferencia_propia;
import models.transterencia.Transferencia_propia_model;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Transferencia_propia_test {

    Transferencia_propia_model transferenciaModel = null;

    @BeforeClass
    public void principio() {
        try {
            transferenciaModel = new Transferencia_propia_model();
            transferenciaModel.validarArchivosCerrados();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            transferenciaModel.cerrarNavegador();
            Reports.cerrarTest();
        }
    }

    @Test(dataProvider = "Operacion", dataProviderClass = Dtransferencia_propia.class)
    public void TransferenciaPropiaTest(String cuentaDebitar, String cuentaAcreditar, String monto, int linea) throws SecurityException, IllegalArgumentException, InterruptedException {

        try {
            ExtentTest test = Reports.createTest("Transferencia Propia de cuenta " + cuentaDebitar + " a " + cuentaAcreditar);
            Reports.assign(test, "Equipo Demo QA", "Transferencias", Preferencias.getInstance().obtenerAtributo("navegadorNombre"));

            transferenciaModel.transferencias(test, cuentaDebitar, cuentaAcreditar, monto, linea);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            transferenciaModel.returnHome();
            transferenciaModel.cerrarNavegador();
            Reports.cerrarTest();
        }
    }

    @AfterClass
    public void afterClass() {
        transferenciaModel.returnHome();
        transferenciaModel.cerrarNavegador();
        Reports.cerrarTest();
    }

}
