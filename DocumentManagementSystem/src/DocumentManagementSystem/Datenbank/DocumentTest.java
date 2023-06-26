package DocumentManagementSystem.Datenbank;

import DocumentManagementSystem.DocumentAuthorization.DocumentManager;
import DocumentManagementSystem.GUI.GUI;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentTest {


    @Test
    void testSaveUploadDocument() {
        DocumentManager docmanger = new DocumentManager();
        GUI ui = new GUI(docmanger);

        assertTrue(ui.saveUploadDocument("C:\\Users\\Furka\\Desktop\\abc.txt"));
        assertTrue(Files.exists(Paths.get("DocumentManagementSystem/SaveFile")));
    }
}
