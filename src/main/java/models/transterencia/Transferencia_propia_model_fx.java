package models.transterencia;

import com.aventstack.extentreports.ExtentTest;
import config.Preferencias;
import helpers.Bi_helper;
import libraries.ReadExcelFile;
import libraries.Reports;
import libraries.WriteExcelFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import pages.General_page;
import pages.Login_page;
import pages.Principal_page;
import pages.Principal_page_fx;
import pages.informacion.Informacion_page;
import pages.transferencia.Transferencia_propia_page;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Transferencia_propia_model_fx {

    Login_page loginPage = new Login_page();
    Principal_page principalPage = new Principal_page();
    Principal_page_fx principalPage_f2 = new Principal_page_fx();
    Transferencia_propia_page transferenciaPropiaPage = new Transferencia_propia_page();
    Informacion_page informacionPage = new Informacion_page();
    ReadExcelFile readFile = new ReadExcelFile();
    WriteExcelFile writeFile = new WriteExcelFile();

    private String RUTA_EXCEL = Preferencias.getInstance().obtenerAtributo("rutaExcel");
    private String RUTA_BITACORA = Preferencias.getInstance().obtenerAtributo("bitacora");
    private String DATOS_CONFIG = Preferencias.getInstance().obtenerAtributo("rutaJsonConfig");

    public Transferencia_propia_model_fx() throws SecurityException,
            IllegalArgumentException {
        super();
    }

    public void transferenciasFx(ExtentTest test, String cuentaDebitar, String cuentaAcreditar, String monto, int linea) {

        try {
            String errorTransferencia = "", tipoCambio = "", tipoCuentaDebitar = "",
                    tipoCuentaAcreditar = "";

            boolean monedaCruzada = false;

            tipoCuentaDebitar = Bi_helper.obtenerTipoCuenta(cuentaDebitar);
            tipoCuentaAcreditar = Bi_helper.obtenerTipoCuenta(cuentaAcreditar);
            CuentaDebitar(cuentaDebitar, test);
            CuentaAcreditar(cuentaAcreditar, test);
            
            tipoCambio = validarMonedaCruzada(test, monto);

            if (Objects.equals(tipoCambio, "N/A")) {
                // nada
            } else {
                monedaCruzada = true;
            }

            typeComentario(test);
            clickContinuar(test);
 
            if (clickConfirmar(test)) {
                terminaTransaccionMonedaCruzada(test, monedaCruzada, cuentaDebitar, cuentaAcreditar, monto,
                        tipoCuentaDebitar, tipoCuentaAcreditar, tipoCambio);
            }

            errorTransferencia = terminaTransaccion(test, cuentaDebitar, cuentaAcreditar);

            writeLog(test, linea, errorTransferencia);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new SkipException(e.getMessage());
            //Reports.logFail(test, "Error en el inicio de sesion");
        }

    }

    public void accederTransferenciasPropiasF2(ExtentTest test,String cuentaDebitar) {
        try {
            System.out.println("Estoy aca dentro del acceder transferencias propias");
            System.out.println(cuentaDebitar);
             String tipoCuentaDebitar;
             tipoCuentaDebitar = Bi_helper.obtenerTipoCuenta(cuentaDebitar);
             System.out.println(tipoCuentaDebitar);
             principalPage_f2.clicMenuInformacionDeCuentas();
             Thread.sleep(5000);
             if (tipoCuentaDebitar.equals("Monetaria")){
                System.out.println("Estoy aca dentro del if de acceder transferencias propias");
                informacionPage.clickinformacionMonetarias();
             }else if (tipoCuentaDebitar.equals("Ahorro")){
                System.out.println("Estoy aca dentro del else if de acceder transferencias propias");
                informacionPage.clickinformacionAhorros();
             }
             Reports.logPass(test, "Acceso a transferencias propias correcto");
         } catch (Exception e) {
             Reports.logFail(test, "Acceso a transferencias propias incorrecto");
         }
    }
    public void accederTransferenciasPropiasF3(ExtentTest test,String cuentaDebitar) {
        try {
             principalPage_f2.clicMenuInformacionDeCuentas();
             Thread.sleep(1000);
             informacionPage.clickinformacionTodasLasCuentas();
             Reports.logPass(test, "Acceso a transferencias propias correcto");

         } catch (Exception e) {
             Reports.logFail(test, "Acceso a transferencias propias incorrecto");
         }
    }
    
    public void CuentaDebitarf3(String cuenta, ExtentTest test) {
        try {
            transferenciaPropiaPage.clickBtnContinuar();
            transferenciaPropiaPage.clickseleccionarListaDebitar();
            if (transferenciaPropiaPage.seleccionarListaDebitar(cuenta)){
                Reports.logPass(test, "Cuenta a debitar No." + cuenta + " seleccionada exitosamente");
            }
            else{
                Reports.logFail(test, "Cuenta a debitar No." + cuenta + " no encontrada");
            }
        } catch (Exception e) {
            Reports.logFail(test, "Fallo al seleccionar la cuenta a debitar No." + cuenta);
            System.out.println("Error cuenta debitar: " + e.getMessage());
        }
    }
    public void CuentaDebitar(String cuenta, ExtentTest test) {
        // try {
        //     transferenciaPropiaPage.clickseleccionarListaDebitar();
        //     if (transferenciaPropiaPage.seleccionarListaDebitar(cuenta))
        //         Reports.logPass(test, "Cuenta a debitar No." + cuenta + " seleccionada exitosamente");
        //     else
        //         Reports.logFail(test, "Cuenta a debitar No." + cuenta + " no encontrada");
        // } catch (Exception e) {
        //     Reports.logFail(test, "Fallo al seleccionar la cuenta a debitar No." + cuenta);
        // }

        try {
            if (SeleccionarTabla(cuenta, "PROPIAS", 1, 5))
                Reports.logPass(test, "Cuenta a debitar No." + cuenta + " seleccionada exitosamente");
            else
                Reports.logFail(test, "Cuenta a debitar No." + cuenta + " no encontrada");
        } catch (Exception e) {
            Reports.logFail(test, "Fallo al seleccionar la cuenta a debitar No." + cuenta);
        }
    }



    public boolean SeleccionarTabla(String nocuenta,String opcion,Integer columnIndex,Integer columnAction) throws InterruptedException{
        WebDriver driver=General_page.obtenerDriver();
        List<WebElement> lstTablasCuentas = driver.findElements(By.tagName("table"));
        for (WebElement tblCuenta : lstTablasCuentas) {
            List<WebElement> lstFilasTabla = tblCuenta.findElements(By.xpath(".//tbody/tr")); // Filas.
            for (WebElement fila : lstFilasTabla) {
                if (fila.findElement(By.xpath(".//td[" + Integer.toString(columnIndex) + "]")).getText().equals(nocuenta)) {
                    fila.findElement(By.xpath(".//td[" + Integer.toString(columnAction) + "]")).findElement(By.id("drop"))
                            .click();
                    Thread.sleep(1000); // wait(2) de uft
                    fila.findElement(By.xpath(".//td[" + Integer.toString(columnAction) + "]")).findElement(By.tagName("ul"))
                            .findElement(By.linkText(opcion)).click();
                    return true;
                } else {
                    System.out.println("no se encontro");
                }
            }
        }
        return false;
    }












    public void CuentaAcreditar(String cuenta, ExtentTest test) {
        try {
            transferenciaPropiaPage.clickseleccionarListaAcreditar();
            if (transferenciaPropiaPage.seleccionarListaAcreditar(cuenta))
                Reports.logPass(test, "Cuenta a acreditar No." + cuenta + " seleccionada exitosamente");
            else
                Reports.logFail(test, "Cuenta a acreditar No." + cuenta + " no encontrada");
        } catch (Exception e) {
            Reports.logFail(test, "Fallo al seleccionar la cuenta a acreditar No." + cuenta);
        }
    }

    public String validarMonedaCruzada(ExtentTest test, String monto) {
        try {
            if (transferenciaPropiaPage.esVisibleLblTasaDeCambioVenta()) {
                transferenciaPropiaPage.typeFormMontoDebitoQV(monto);
                Reports.logPass(test, "Transaccion de Q a $");
                return transferenciaPropiaPage.obtenerAtributoTxtTasaVenta();
                // $ a Q
            } else if (transferenciaPropiaPage.esVisibleLblTasaDeCambioCompra()) {
                transferenciaPropiaPage.typeFormMontoDebito$C(monto);
                Reports.logPass(test, "Transaccion de $ a Q");
                return transferenciaPropiaPage.obtenerAtributoTxtTasaCompra();
            } else {
                Reports.logPass(test, "Transaccion de la misma moneda");
                transferenciaPropiaPage.typeFormMontoDebito(monto);
                return "N/A";
            }
        } catch (Exception e) {
            Reports.logFail(test, "Fallo la validacion de moneda cruzada");
        }
        return null;
    }

    public Boolean typeComentario(ExtentTest test) throws IOException, InterruptedException {
        String comentario = Bi_helper.obtenerDato("comentarioTransferencias", "comentario", DATOS_CONFIG);
        try {
            transferenciaPropiaPage.typeFormComentario(comentario);
            Reports.logPass(test, "Ingreso correctamente el comentario: " + comentario);
            return true;
        } catch (Exception e) {
            Reports.logFail(test, "Fallo el ingreso el comentario: " + comentario);
            return false;
        }
    }

    public Boolean clickContinuar(ExtentTest test) {
        try {
            transferenciaPropiaPage.clickBtnContinuar();
            Reports.logPass(test, "Clic en boton continuar correcto");
            return true;
        } catch (Exception e) {
            Reports.logFail(test, "Fallo el clic en boton continuar");
            return false;
        }
    }

    public Boolean clickConfirmar(ExtentTest test) {
        try {
            if (transferenciaPropiaPage.esVisibleBtnConfirmar()) {
                transferenciaPropiaPage.clickBtnConfirmar();
                Reports.logPass(test, "Clic en boton confirmar correcto");
                return true;
            } else
                Reports.logFail(test, "Fallo el clic en boton confirmar");
        } catch (Exception e) {
            Reports.logFail(test, "Fallo el clic en boton confirmar");
            return false;
        }
        return false;
    }

    public void terminaTransaccionMonedaCruzada(ExtentTest test, Boolean monedaCruzada, String cuentaDebito,
                                                String CuentaCredito, String monto, String tipoCuentaDebitar, String tipoCuentaAcreditar,
                                                String tipoCambio) {
        try {
            String montoCredito = "";
            if (transferenciaPropiaPage.esVisibleLblAutorizacion()) {
                Thread.sleep(2000);
                if (monedaCruzada) {
                    montoCredito = transferenciaPropiaPage.obtenerTextolblMontoAcreditado$();
                } else
                    montoCredito = monto;
                writeFile.writeBitacora2(RUTA_BITACORA, ReadExcelFile.search(RUTA_BITACORA) + 2,
                        transferenciaPropiaPage.obtenerTextoLblAutorizacion(),
                        transferenciaPropiaPage.obtenerTextoLblCuentaDebitada(), tipoCuentaDebitar, cuentaDebito, monto,
                        transferenciaPropiaPage.obtenerTextoLblCuentaAcreditada(), tipoCuentaAcreditar,
                        CuentaCredito, montoCredito, tipoCambio);
                Reports.logPass(test, "Transaccion terminada con exito");
            }
        } catch (Exception e) {
            Reports.logFail(test, "Fallo al terminar la transaccion");
        }
    }

    public String terminaTransaccion(ExtentTest test, String cuentaDebito, String cuentaCredito) {
        String errorTransferencia = "";
        try {
            if (transferenciaPropiaPage.esVisibleLblAutorizacion()) {
                errorTransferencia = transferenciaPropiaPage.obtenerTextoLblTransferenciaExitosa();
                Reports.logCaptura(test, errorTransferencia, Bi_helper.screenShot(cuentaDebito, cuentaCredito,
                        "Transferencias Propias", transferenciaPropiaPage.enviarDriver(), true), true);
            } else if (transferenciaPropiaPage.isDisplayLblTransferenciaErronea()) {
                errorTransferencia = transferenciaPropiaPage.obtenerTextoLblTransferenciaErronea();
                Reports.logCaptura(test, errorTransferencia,
                        Bi_helper.screenShot(cuentaDebito, cuentaCredito, "Transferencias Propias",
                                transferenciaPropiaPage.enviarDriver(), false),
                        false);
            } else {
                errorTransferencia = "Error en transferencia";
                Reports.logCaptura(test, errorTransferencia, Bi_helper.screenShot(cuentaDebito, cuentaCredito,
                        "Transferencias Propias", transferenciaPropiaPage.enviarDriver(), false), false);
            }
            Thread.sleep(1000);
            return errorTransferencia;

        } catch (Exception e) {
            Reports.logFail(test, "Error al terminar la transaccion");
            System.out.println(e.getMessage());
            return errorTransferencia;
        }
    }

    public void writeLog(ExtentTest test, int linea, String errorTransferencia) {

        try {
            writeFile.writeCellValue(RUTA_EXCEL, "TransferenciasPropias", linea, 3, errorTransferencia);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            Bi_helper.setErrores(6, Map.of("{ruta}", RUTA_EXCEL));
            throw new SkipException(e.getMessage());
        }
    }

    public void returnHome() {
        try {
            principalPage.clickHome();
        } catch (Exception e) {

        }
    }

    public void cerrarNavegador() {
        transferenciaPropiaPage.cerrarDriver();
    }

    public void validarArchivosCerrados() {
        FileInputStream bitacora = null;
        FileInputStream datos = null;

        try {
            bitacora = new FileInputStream(RUTA_BITACORA);
            datos = new FileInputStream(RUTA_EXCEL);

            Workbook testBitacora = WorkbookFactory.create(bitacora);
            Workbook testDatos = WorkbookFactory.create(datos);

            testBitacora.close();
            testDatos.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Bi_helper.setErrores(6, Map.of("{ruta}", RUTA_EXCEL));
            Bi_helper.setErrores(6, Map.of("{ruta}", RUTA_BITACORA));
            throw new SkipException("Debe cerrar los archivos excel");
        } finally {
            try {
                if (bitacora != null) {
                    bitacora.close();
                }

                if (datos != null) {
                    datos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
