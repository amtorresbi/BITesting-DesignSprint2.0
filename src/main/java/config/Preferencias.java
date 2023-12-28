package config;

import helpers.Bi_helper;
import org.testng.SkipException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Preferencias {

    private static Preferencias instancia;
    private static Map<String, String> atributos;
    private static final String entorno = "production";

    private Preferencias() {
        String home = System.getProperty("user.home");
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String fechaActual = fecha.format(formato);
        atributos = new HashMap<>();
        atributos.put("home", home);
        atributos.put("baseUrl", System.getProperty("user.dir"));
        atributos.put("rutaExcel", home + "\\Documents\\Automatizacion BEL Web\\Datos.xlsx");
        atributos.put("paginaWeb", "https://pruebas.bi.com.gt/InicioSesion/Inicio/Autenticar#!");
        atributos.put("bitacora", home + "\\Documents\\Automatizacion BEL Web\\Operacional.xlsx");
        atributos.put("rutaJson", home + "\\Documents\\Automatizacion BEL Web\\Resourses\\ElementsPage.json");
        atributos.put("rutaJsonAmbiente", home + "\\Documents\\Automatizacion BEL Web\\Resourses\\ambientes.json");
        atributos.put("rutaJsonConfig", home + "\\Documents\\Automatizacion BEL Web\\Resourses\\Config.json");
        atributos.put("rutaReporte", home + "\\Documents\\Automatizacion BEL Web\\Reporte " + fechaActual + ".html");
        atributos.put("nivelTest", "1");
        atributos.put("navegadorTipo", "1");
        atributos.put("navegadorNombre", "Chrome");
    }

    public static synchronized Preferencias getInstance() {
        if (instancia == null) {
            instancia = new Preferencias();
        }

        return instancia;
    }

    /**
     * @param nombre Nombre del atributo
     * @return Valor del atriburo
     */
    public synchronized String obtenerAtributo(String nombre) {
        if (atributos.containsKey(nombre)) {
            System.out.println("Obteniendo atributo " + nombre + ", valor: " + atributos.get(nombre));
            return atributos.get(nombre);
        } else {
            Bi_helper.setErrores(7, Map.of("{elemento}", nombre));
            throw new SkipException("Nombre de atributo no válido: " + nombre);
        }
    }

    /**
     * @param nombre Nombre del atriburo
     * @param valor  Nuevo valor que se le asigna al atributo
     */
    public synchronized void valorAtributo(String nombre, String valor) {
        System.out.println("Asignando valor " + valor + " al atributo " + nombre);
        atributos.put(nombre, valor);
    }

    /**
     * <p>Entorno de la aplicación</p>
     *
     * <p>Se pueden utilizar las siguientes opciones: </p><br>
     *
     * <code>development</code><br>
     * <code>testing</code><br>
     * <code>production</code><br>
     *
     * @return String entorno
     */
    public String obtenerEntorno() {
        return entorno;
    }

    public void generarPlantillas() {
        String rutaDestino = Preferencias.getInstance().obtenerAtributo("home") + "/Documents/Automatizacion BEL Web";
        File carpeta = new File(rutaDestino + "/Resourses");

        if (!carpeta.exists()) {
            carpeta.mkdirs();

            copiarPlantilla(rutaDestino, "Datos.xlsx");
            copiarPlantilla(rutaDestino, "Operacional.xlsx");
            copiarPlantilla(rutaDestino + "/Resourses", "ambientes.json");
            copiarPlantilla(rutaDestino + "/Resourses", "Config.json");
        } else {
            System.out.println("Las plantillas ya fueron generadas");
        }
    }

    private void copiarPlantilla(String destino, String archivo) {
        try {
            File plantilla = new File(destino + File.separator + archivo);
            Files.copy(Objects.requireNonNull(Preferencias.class.getResourceAsStream("/plantillas/" + archivo)),
                    plantilla.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado: " + plantilla.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new SkipException("Copia de plantilla no completada: " + archivo);
        }
    }
}
