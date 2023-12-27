package controllers.transferencia;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class Transferencia_propia_f2 {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("My Suite");
        XmlTest test = new XmlTest(suite);
        test.setName("My Test");
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("tests.Login_test"));
        classes.add(new XmlClass("tests.transferencia.Transferencia_propia_test_f2"));
        test.setXmlClasses(classes);
        testng.setXmlSuites(List.of(suite));
        testng.run();
    }
}