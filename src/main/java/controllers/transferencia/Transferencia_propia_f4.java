package controllers.transferencia;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class Transferencia_propia_f4 implements ITestListener{
    private static String nameTest = "";
    private static int executedTests = 0;

    @Override
    public void onTestStart(ITestResult result) {
        // Este método se ejecuta al inicio de cada test
        nameTest = "Test ejecutandose: " + result.getName();
        System.out.println("Iniciando test: " + nameTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Este método se ejecuta al finalizar exitosamente cada test
        executedTests++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Este método se ejecuta al finalizar cada test con fallo
        executedTests++;
    }

    public static float getExecutedTests() {
        return executedTests;
    }

    public static String getNameTest() {
        return nameTest;
    }

    public static void resetValues() {
        // Método para reiniciar los valores de las variables
        executedTests = 0;
        nameTest = "No hay ejecución en proceso";
    }

    public static void main(String[] args) {
        TestNG testng = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("My Suite");
        XmlTest test = new XmlTest(suite);
        test.setName("My Test");
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("tests.Login_test"));
        classes.add(new XmlClass("tests.transferencia.Transferencia_propia_test_f4"));
        test.setXmlClasses(classes);
        testng.setXmlSuites(List.of(suite));
        // Agregar el listener
        testng.addListener(new Transferencia_propia_f4());
        
        testng.run();
    }
}