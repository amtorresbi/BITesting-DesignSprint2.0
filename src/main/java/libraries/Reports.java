package libraries;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import config.Preferencias;

public class Reports {

    private static String RUTA_REPORTE = Preferencias.getInstance().obtenerAtributo("rutaReporte");
    private static String RUTA_RESOURCES = Preferencias.getInstance().obtenerAtributo("rutaResources");

    public Reports() throws SecurityException, IllegalArgumentException {

    }

    private static final ExtentReports extent;

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter(RUTA_REPORTE);
        //spark.config().setCss(".r-img { width: 100%; }");
        spark.config().setEncoding("UTF-8");
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
        test.assignCategory(category);
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
        //////////////////////////
        try {
            // Lee el archivo HTML
            System.out.println("Leyendo el archivo HTML...");
            File input = new File(RUTA_REPORTE);
            Document doc = Jsoup.parse(input, "UTF-8");

            // Busca los elementos con la clase "logo" y el estilo especificado
            Elements logoElements = doc.select("div.logo[style*=background-image: url('https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@b00a2d0486596e73dd7326beacf352c639623a0e/commons/img/logo.png')]");

            // Itera sobre los elementos encontrados y realiza el reemplazo
            for (Element logoElement : logoElements) {
                // Reemplaza el atributo style con el nuevo valor
                logoElement.attr("style", "background-image: url('Resourses/icono.png')");
            }
            Elements iconElements = doc.select("link[rel=shortcut icon]");
            for (Element iconElement : iconElements) {
                // Reemplaza la URL con la nueva ruta local
                iconElement.attr("href", "Resourses/icono.png");
            }
            // Guarda los cambios en un nuevo archivo o sobrescribe el original
            // Si deseas sobrescribir el archivo original, simplemente usa el mismo nombre en el constructor de FileWriter
            System.out.println("Reemplazando el archivo HTML...");
            String modifiedHtml = doc.outerHtml();
            // Escribe el HTML modificado en un nuevo archivo o sobrescribe el original
            Path outputPath = Path.of(RUTA_REPORTE);
            Files.writeString(outputPath, modifiedHtml, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            File plantilla = new File(RUTA_RESOURCES + File.separator + "icono.png");
            Files.copy(Objects.requireNonNull(Preferencias.class.getResourceAsStream("/img/" + "icono.png")),
            plantilla.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ////////////////////
    }
}