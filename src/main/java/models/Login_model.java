package models;

import com.aventstack.extentreports.ExtentTest;
import config.Preferencias;
import helpers.Bi_helper;
import libraries.ReadExcelFile;
import libraries.Reports;
import libraries.WriteExcelFile;
import org.testng.SkipException;
import pages.Login_page;
import pages.Principal_page;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Login_model {
    Login_page loginPage = new Login_page();
    ReadExcelFile readFile = new ReadExcelFile();
    WriteExcelFile writeFile = new WriteExcelFile();

    private String RUTA_EXCEL = Preferencias.getInstance().obtenerAtributo("rutaExcel");
    private String PAGINA_WEB = Preferencias.getInstance().obtenerAtributo("paginaWeb");
    private String NIVEL = Preferencias.getInstance().obtenerAtributo("nivelTest");

    public Login_model() throws SecurityException, IllegalArgumentException {
        super();
    }

    public void procesar(ExtentTest test) {
        try {
            iniciar(test);
            writeCodigo(test);
            writeUsuario(test);
            writeContrasenia(test);
            clickIngreso(test);

            if (NIVEL.equals("2")) {
                String log = validarInicio(test);
                loginLog(log);

                if (!Objects.equals(log, "Inicio de sesion correcto")) {
                    Bi_helper.setErrores(5, null);
                    throw new SkipException("Error en el inicio de sesion");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Reports.logFail(test, "Error en el inicio de sesion");
            throw new SkipException("Error en el inicio de sesion");
        }
    }

    public void iniciar(ExtentTest test) {
        try {
            loginPage.iniciarNavegador();
            loginPage.navegar(PAGINA_WEB);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Bi_helper.setErrores(4, Map.of("{mensaje}", e.getMessage()));
        }
    }

    public void writeCodigo(ExtentTest test) throws IOException {
        String codigo = readFile.getCellValue(RUTA_EXCEL, "Login", 2, 1);
        try {
            loginPage.ingresarCodigo(codigo);
            Reports.logPass(test, "Se ingreso correctamente el codigo: " + codigo);
        } catch (Exception e) {
            Reports.logFail(test, "Fallo el ingreso del codigo: " + codigo);
        }
    }

    public void writeUsuario(ExtentTest test) throws IOException {
        String usuario = readFile.getCellValue(RUTA_EXCEL, "Login", 3, 1);
        try {
            loginPage.ingresarUsuario(usuario);
            Reports.logPass(test, "Ingreso correcto del usuario: " + usuario);
        } catch (Exception e) {
            Reports.logFail(test, "Fallo el ingreso del usuario: " + usuario);
        }
    }

    public void writeContrasenia(ExtentTest test) {
        try {
            loginPage.ingresarContrasenia(readFile.getCellValue(RUTA_EXCEL, "Login", 4, 1));
            Reports.logPass(test, "Ingreso correcto de la Contraseña: " + "Prueba123..");
        } catch (Exception e) {
            Reports.logFail(test, "Fallo al ingresar la Contraseña: " + "Prueba123..");
        }
    }

    public void clickIngreso(ExtentTest test) {
        try {
            loginPage.btnInicio();
            Reports.logPass(test, "Clic en boton para iniciar sesion correcto");
        } catch (Exception e) {
            Reports.logFail(test, "Fallo el clic en boton para iniciar sesion");
        }
    }

    public String validarInicio(ExtentTest test) {
        try {
            Principal_page principal = new Principal_page();
            if (principal.existeCerrarSesion()) {
                Reports.logPass(test, "Inicio de sesion correcto");
                return "Inicio de sesion correcto";
            } else if (loginPage.existeErrorCodigo()) {
                Reports.logCaptura(test, loginPage.obtenerErrorCodigo(),
                        Bi_helper.screenShot("", "", "Login", loginPage.enviarDriver(), false), false);
                return loginPage.obtenerErrorCodigo();
            } else if (loginPage.existeErrorUsuario()) {
                Reports.logCaptura(test, loginPage.obtenerErrorUsuario(),
                        Bi_helper.screenShot("", "", "Login", loginPage.enviarDriver(), false), false);
                return loginPage.obtenerErrorUsuario();
            } else if (loginPage.existeErrorContrasenia()) {
                Reports.logCaptura(test, loginPage.existeMensajeError().toString(),
                        Bi_helper.screenShot("", "", "Login", loginPage.enviarDriver(), false), false);
                return loginPage.obtenerErrorContrasenia();
            } else if (loginPage.existeMensajeError()) {
                Reports.logCaptura(test, loginPage.obtenerMensajeError(),
                        Bi_helper.screenShot("", "", "Login", loginPage.enviarDriver(), false), false);
                return loginPage.obtenerMensajeError();
            } else {
                Reports.logCaptura(test, "Error inicio de sesion",
                        Bi_helper.screenShot("", "", "Login", loginPage.enviarDriver(), false), false);
                return "Error inicio de sesion";
            }

        } catch (Exception e) {
            return null;
        }
    }

    public void loginLog(String log) {
        try {
            writeFile.writeCellValue(RUTA_EXCEL, "Login", 4, 1, log);
        } catch (IOException ignored) {
        }
    }

    public void cerrarNavegador() {
        loginPage.cerrarDriver();
    }
}
