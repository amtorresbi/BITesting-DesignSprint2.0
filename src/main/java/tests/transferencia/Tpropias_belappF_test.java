package tests.transferencia;

import models.transterencia.Tpropias_belappF_model;
import java.net.MalformedURLException;

import org.testng.annotations.Test;

import libraries.Reports;

public class Tpropias_belappF_test {
    @Test
    public void Tpropias_belappFTest() throws MalformedURLException {
        Tpropias_belappF_model tpropias_belappF_model = new Tpropias_belappF_model();
        tpropias_belappF_model.tpropias_belappFisico_transferencias();
        Reports.cerrarTest();
    }
}
