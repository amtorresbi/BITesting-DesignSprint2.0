package tests.transferencia;

import models.transterencia.Tpropias_belapp_model;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import libraries.Reports;

public class Tpropias_belapp_test {

    @Test
    public void Tpropias_belappTest() throws MalformedURLException {
        Tpropias_belapp_model tpropias_belapp_model = new Tpropias_belapp_model();
        tpropias_belapp_model.tpropias_belappBS_transferencia();
        Reports.cerrarTest();      
    }
    
}
