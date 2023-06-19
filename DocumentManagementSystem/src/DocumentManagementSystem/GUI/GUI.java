package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;
import DocumentManagementSystem.DocumentAuthorization.DocumentManagementInterface;

import javax.swing.*;
import java.util.ArrayList;

public class GUI implements UserInterface, DocumentManagementInterface {

    private DocumentManagementInterface docmanager;

    public GUI(DocumentManagementInterface docmanager) {
        this.docmanager = docmanager;
    }

    @Override
    //TODO: Weitere Menü Auswahlmöglichkeiten hinzufügen
    public String showMenu() {
        int auswahl = 0;

        boolean inputKontrolle = false;
        boolean inputKontrolle2 = false;

        do {

            try {

                do {
                    String input = JOptionPane.showInputDialog("Bitte auswählen: \n 1. Registrieren \n 2. Anmelden\n 3. Upload \n 4. Menü verlassen");
                    auswahl = Integer.parseInt(input);

                    switch (auswahl) {
                        case 1:
                            JOptionPane.showMessageDialog(null, "Das ist der Fall registrieren");
                            //TODO: Regestriervorgang programmieren
                            inputKontrolle = true;
                            inputKontrolle2 = true;
                            break;

                        case 2:
                            JOptionPane.showMessageDialog(null, "Das ist der Fall Anmelden");
                            //TODO: Anmeldung programmieren
                            inputKontrolle = true;
                            inputKontrolle2 = true;
                            break;

                        case 3:
                            JOptionPane.showMessageDialog(null, "Das ist der Fall Upload");
                            //TODO: Pfad einfügen
                            docmanager.saveUploadDocument("Hier einen Pfad einfügen");
                            inputKontrolle = true;
                            inputKontrolle2 = true;
                            break;

                        case 4:
                            inputKontrolle = true;
                            inputKontrolle2 = true;

                        default:
                            JOptionPane.showMessageDialog(null, "Bitte nur Zahlen zwischen 1 und 4 eingeben");
                    }
                } while (!inputKontrolle);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte nur Zahlen zwischen 1 und 4 eingeben");
            }
        } while (!inputKontrolle2);
    return null;
    }

    @Override
    public String requestUploadAuthorization() {
        return null;
    }

    @Override
    public String chooseUploadDocument(String pfad) {
        return null;
    }

    @Override
    public boolean confirmUpload() {
        return false;
    }

    @Override
    public String resultMessage() {
        return null;
    }

    @Override
    public String getSearchInput() {
        return null;
    }

    @Override
    public Document selectSavedDocument() {
        return null;
    }

    @Override
    public boolean uploadDocument(String pfad) {
        return false;
    }

    @Override
    public boolean saveUploadDocument(String pfad) {
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
