package DocumentManagementSystem.Datenbank;

import DocumentManagementSystem.DocumentAuthorization.DocumentManager;
import DocumentManagementSystem.GUI.GUI;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentTest {


    @Test
    void testSaveUploadDocument() {
        DocumentManager docmanger = new DocumentManager();
        GUI ui = new GUI(docmanger);
        String pfad = JOptionPane.showInputDialog("Bitte Pfad einfügen");

        //prüft die Methode in der GUI
        assertTrue(ui.saveUploadDocument(pfad));
        //prüft, ob die gespeicherte Datei nun wirklich existiert
        assertTrue(Files.exists(Paths.get("DocumentManagementSystem/SaveFile")));
    }
}
