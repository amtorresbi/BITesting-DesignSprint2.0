package controllers;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class Login {

    public static void main(String[] args) {
        TestNG testng = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Prueba Login");

        XmlTest test = new XmlTest(suite);
        test.setName("Prueba Login");

        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("tests.Login_test"));
        test.setXmlClasses(classes);

        testng.setXmlSuites(List.of(suite));
        testng.run();
    }
}
