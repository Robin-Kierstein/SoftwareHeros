/*package DocumentManagementSystem.Datenbank;

import DocumentManagementSystem.DocumentAuthorization.DocumentManager;
import DocumentManagementSystem.GUI.GUI;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentTest {

    @Test
    void testSaveUploadDocumentWhenFileExists() {
        DocumentManager docmanger = new DocumentManager();
        GUI ui = new GUI(docmanger);

        // Erstellt eine Kopie von abc.txt am Zielpfad, bevor der Test ausgeführt wird
        File existingFile = new File("DocumentManagementSystem/SaveFile");
        if(!existingFile.exists()){
            ui.saveUploadDocument();
        }
        // Testet, ob die Methode erfolgreich eine vorhandene Datei ersetzen kann
        assertTrue(ui.saveUploadDocument());
    }

    @Test
    void testSaveUploadDocumentWhenFileDoesNotExist() {
        DocumentManager docmanger = new DocumentManager();
        GUI ui = new GUI(docmanger);

        // Stellt sicher, dass die Datei vor dem Test nicht existiert
        File file = new File("DocumentManagementSystem/SaveFile");
        if(file.exists()){
            file.delete();
        }
        // Testet, ob die Methode erfolgreich eine neue Datei erstellen kann
        assertTrue(ui.saveUploadDocument("C:\\Users\\Furka\\Desktop"));
        assertTrue(Files.exists(Paths.get("C:\\Users\\Furka\\Desktop\\kopie_abc.txt")));
    }

    @Test
    void testSaveUploadDocument() {
        DocumentManager docmanger = new DocumentManager();
        GUI ui = new GUI(docmanger);

        assertTrue(ui.saveUploadDocument("C:\\Users\\Furka\\Desktop"));
        assertTrue(Files.exists(Paths.get("DocumentManagementSystem/SaveFile")));
    }
}
*/