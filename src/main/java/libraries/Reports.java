package libraries;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.Preferencias;

public class Reports {

    private static String RUTA_REPORTE = Preferencias.getInstance().obtenerAtributo("rutaReporte");

    public Reports() throws SecurityException, IllegalArgumentException {

    }

    private static final ExtentReports extent;

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter(RUTA_REPORTE);
        extent = new ExtentReports();

        extent.attachReporter(spark);
    }

    public static ExtentReports getExtentInstance() {
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }

    public static void logInfo(ExtentTest test, String message) {
        test.log(Status.INFO, message);
    }

    public static void logPass(ExtentTest test, String message) {
        test.log(Status.PASS, message);
    }

    public static void logFail(ExtentTest test, String message) {
        test.log(Status.FAIL, message);
    }

    public static void logCaptura(ExtentTest test, String message, String ruta, Boolean testPass) {
        if (testPass) {
            test.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(ruta).build());
        } else {
            test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(ruta).build());
        }
    }

    public static void logWarning(ExtentTest test, String message) {
        test.log(Status.WARNING, message);
    }

    public static void assignAuthor(ExtentTest test, String autor) {
        test.assignAuthor(autor);
    }

    public static void assignCategory(ExtentTest test, String category) {
        test.assignAuthor(category);
    }

    public static void assign(ExtentTest test, String autor, String category, String device) {
        test.assignAuthor(autor).assignCategory(category).assignDevice(device);
    }

    public static void captura(ExtentTest test) {
        test.fail("error al iniciar sesión",
                MediaEntityBuilder.createScreenCaptureFromPath(RUTA_REPORTE + "/Inicio Incorrecto.png").build());
    }

    public static void cerrarTest() {
        extent.flush();
    }
}