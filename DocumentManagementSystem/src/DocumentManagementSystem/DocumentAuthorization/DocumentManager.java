package DocumentManagementSystem.DocumentAuthorization;

import DocumentManagementSystem.Datenbank.Document;
import DocumentManagementSystem.Datenbank.DocumentDatabase;
import DocumentManagementSystem.Datenbank.DocumentDatabaseInterface;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class DocumentManager implements DocumentManagementInterface {

    private DocumentDatabaseInterface docdatabase = new DocumentDatabase();

        @Override
        public boolean uploadDocument(String pfad) {
            return false;
        }


    @Override
    public boolean saveUploadDocument(String pfad, String name) {

        if (pfad == null) {
            return false;
        }
        return docdatabase.uploadDocument(pfad);
    }

    @Override
    public boolean docTypeCheck(String pfad) {
        return false;
    }

    @Override
    public void searchDocument(String input) {
        File folder = new File("DocumentManagementSystem/Gespeicherte Dateien");

        //Ordner zu einer Liste machen, um damit zu arbeiten
        String[] list = folder.list(new FilenameFilter() {
            //accept vom FilenameFilter überschreiben, um auf den input zu prüfen
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(input);
            }
        });
        //Dateinamen aus der Liste Reihenweise in den Output schreiben und diesen ausgeben
        String output = "";

        for (String name : list) {
            output += name +"\n";
        }
        JOptionPane.showMessageDialog(null,output);
    }

    /**
     * This method is only for Junit Tests. The logic is identical to the searchDocument method but the output has been
     * changed to String to make testing easier.
     * @param input The keyword that the documents name should contain
     */
    public String searchDocumentTest(String input){
        File folder = new File("DocumentManagementSystem/Gespeicherte Dateien");

        //Ordner zu einer Liste machen, um damit zu arbeiten
        String[] list = folder.list(new FilenameFilter() {
            //accept vom FilenameFilter überschreiben, um auf den input zu prüfen
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(input);
            }
        });
        //Dateinamen aus der Liste Reihenweise in den Output schreiben und diesen ausgeben
        String output = "";

        for (String name : list) {
            output += name +"\n";
        }
        return output;
    }

    @Override
    public ArrayList<Document> createSearchList() {
        return null;
    }

    @Override
    public Document showSearchList() {
        return null;
    }

    @Override
    public String requestViewingAuthorization() {
        return null;
    }

    @Override
    public Document viewSelectedDocument() {
        return null;
    }

    @Override
    public String viewMetatagsDocument() {
        return null;
    }

    @Override
    public String requestViewingRights() {
        return null;
    }
}
