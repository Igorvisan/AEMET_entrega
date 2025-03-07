package org.example;

import com.google.api.services.drive.Drive;
import org.w3c.dom.Document;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        // Descargar el XML y obtener un Document
        UrlConnect urlConnect = new UrlConnect();
        Document doc = urlConnect.getXmlDocument();

        if (doc != null) {
            // Aplicar XPath
            String xpathQuery = "//prediccion/dia/estado_cielo | //prediccion/dia/viento | //prediccion/dia/temperatura | //prediccion/dia/sens_termica | //prediccion/dia/humedad_relativa";
            Document documentoXml = XPathTransform.applyXPath(doc, xpathQuery);

            if (documentoXml != null) {
                // Convertir el Document a InputStream
                InputStream inputStream = ConvertToInputStream.convertDocument(documentoXml);

                if (inputStream != null) {
                    try {
                        // Crear el servicio de Drive (asegúrate de tener configurada la autenticación correctamente)
                        DriveService driveService = new DriveService(); // Crear el objeto DriveService
                        Drive drive = driveService.getDriveService();

                        // Subir el archivo a Google Drive
                        UploadToDrive.subirDocumento(inputStream, drive);
                    } catch (Exception e) {
                        System.out.println("Error al subir el archivo a Google Drive: " + e.getMessage());
                    }
                } else {
                    System.out.println("Error al convertir el documento XML a InputStream.");
                }
            } else {
                System.out.println("Error al aplicar XPath.");
            }
        } else {
            System.out.println("Error al obtener el XML.");
        }
    }
}
