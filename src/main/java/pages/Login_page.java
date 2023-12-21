package pages;

import helpers.Bi_helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_page extends General_page {

    private String DATA_JSON = Bi_helper.rutaJson("login_elements.json");

    private By codigo = By.id(Bi_helper.obtenerDato("formCodigo", "id", DATA_JSON));
    private By usuario = By.id(Bi_helper.obtenerDato("formUsuario", "id", DATA_JSON));
    private By contrasenia = By.id(Bi_helper.obtenerDato("formContrasenia", "id", DATA_JSON));
    private By inicioSesion = By.id(Bi_helper.obtenerDato("btnInisioSesion", "id", DATA_JSON));
    private By errorCodigo = By.xpath(Bi_helper.obtenerDato("lblErrorCodigo", "xpath", DATA_JSON));
    private By errorUsuario = By.xpath(Bi_helper.obtenerDato("lblErrorUsuario", "xpath", DATA_JSON));
    private By errorContrasenia = By.xpath(Bi_helper.obtenerDato("lblErrorContrasenia", "xpath", DATA_JSON));
    private By mensajeError = By.cssSelector(Bi_helper.obtenerDato("lblMensajeError", "clase", DATA_JSON));

    public Login_page() {
        super();
    }

    public void ingresarCodigo(String texto) {
        escribir(codigo, texto);
    }

    public void ingresarUsuario(String texto) {
        escribir(usuario, texto);
    }

    public void ingresarContrasenia(String texto) {
        escribir(contrasenia, texto);
    }

    public void btnInicio() {
        click(inicioSesion);
    }

    public String obtenerErrorCodigo() {
        return obtenerTexto(errorCodigo);
    }

    public Boolean existeErrorCodigo() {
        return esVisible(errorCodigo);
    }

    public String obtenerErrorUsuario() {
        return obtenerTexto(errorUsuario);
    }

    public Boolean existeErrorUsuario() {
        return esVisible(errorUsuario);
    }

    public String obtenerErrorContrasenia() {
        return obtenerTexto(errorContrasenia);
    }

    public Boolean existeErrorContrasenia() {
        return esVisible(errorContrasenia);
    }

    public String obtenerMensajeError() {
        return obtenerTexto(mensajeError);
    }

    public Boolean existeMensajeError() {
        return esVisible(mensajeError);
    }

    public WebDriver enviarDriver() {
        return obtenerDriver();
    }

    public void cerrarDriver() {
        cerrarNavegador();
    }
}
