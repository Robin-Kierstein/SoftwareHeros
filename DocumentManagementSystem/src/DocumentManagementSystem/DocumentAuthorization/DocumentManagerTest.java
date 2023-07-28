package DocumentManagementSystem.DocumentAuthorization;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class DocumentManagerTest {

    DocumentManager documentManager = new DocumentManager();

    @Test
    public void searchDocumentPdfLowerCase(){
        assertEquals(documentManager.searchDocumentTest("pdf"),".pdf\n" + "cdew.pdf\n" + "test2009.pdf\n");
    }

    @Test
    public void searchDocumentPdfUpperCase(){
        assertEquals(documentManager.searchDocumentTest("PDF"),"");
    }

    @Test
    public void searchDocumentExactName(){
        assertEquals(documentManager.searchDocumentTest("cdew.pdf"),"cdew.pdf\n");
    }
    @Test
    public void searchDocumentEmptyInput(){
        assertEquals(documentManager.searchDocumentTest(""),".pdf\n" +
                "bildNummer2003030.png\n" +
                "cdew.pdf\n" +
                "null\n" +
                "test2009\n" +
                "test2009.pdf\n");
    }

    @Test
    public void searchDocumentNonExistingName(){
        assertEquals(documentManager.searchDocumentTest("A"),"");
    }



}
