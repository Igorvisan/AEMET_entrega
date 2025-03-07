package org.example;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnect {

    public static String GLOBAL_VARIABLE = "https://www.aemet.es/xml/municipios/localidad_20076.xml";

    public Document getXmlDocument() {

        Document doc = null;
        try {
            URL oURL = new URL(GLOBAL_VARIABLE);
            URLConnection oConnection = oURL.openConnection();
            InputStream inputStream = oConnection.getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(inputStream);

            inputStream.close(); // Cierra el stream después de parsear
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}

