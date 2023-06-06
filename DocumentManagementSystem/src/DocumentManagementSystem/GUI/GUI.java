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
    public String showMenu() {
        int choice = 0;

        boolean status = false;
        boolean status2 = false;

        do {

            try {

                do {
                    String input = JOptionPane.showInputDialog("Bitte auswählen: \n 1. Registrieren \n 2. Anmelden\n 3. Upload \n");
                    choice = Integer.parseInt(input);

                    switch (choice) {
                        case 1:
                            JOptionPane.showMessageDialog(null, "Das ist der Fall registrieren");
                            status = true;
                            status2 = true;
                            break;

                        case 2:
                            JOptionPane.showMessageDialog(null, "Das ist der Fall Anmelden");
                            status = true;
                            status2 = true;
                            break;

                        case 3:
                            JOptionPane.showMessageDialog(null, "Das ist der Fall Upload");
                            //TODO: Pfad einfügen
                            docmanager.saveUploadDocument("Hier einen Pfad einfügen");
                            status = true;
                            status2 = true;
                            break;
                    }
                } while (status != true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte nur Zahlen zwischen 1 und 3 eingeben");
            }
        } while (status2 != true);
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
