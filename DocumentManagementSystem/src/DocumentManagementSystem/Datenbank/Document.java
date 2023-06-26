package DocumentManagementSystem.Datenbank;

import DocumentManagementSystem.DocumentAuthorization.DocumentManagementInterface;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Document implements DocumentManagementInterface {
    private File file;
    private String name;

    public Document(File file) {
        this.file = file;
        name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean uploadDocument(String pfad) {
        return false;
    }

    @Override
    public boolean saveUploadDocument(String pfad, String name) {
        try {

            // Erstellt ein neues File-Objekt an dem gewünschten Pfad
            File outputFile = new File("DocumentManagementSystem/Gespeicherte Dateien/" + name);

            // Inputfile ist die Datei die wir speichern möchten
            File inputFile = new File(pfad);

            // Wenn die Datei bereits existiert löschen wir sie
            if(outputFile.exists()){
                outputFile.delete();
            }

            // Wenn das Verzeichnis nicht existiert erstellen wir es
            outputFile.getParentFile().mkdirs();

            // Kopiert die Datei
            Files.copy(inputFile.toPath(), outputFile.toPath());

            JOptionPane.showMessageDialog(null, "Die Datei wurde erfolgreich hochgeladen: " + outputFile.getAbsolutePath());
            return true;

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Das hochladen der Datei ist fehlgeschlagen!");
        return false;
    }

    @Override
    public boolean docTypeCheck(String pfad) {
        return false;
    }

    @Override
    public String searchThroughDocument() {
        return null;
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
}
