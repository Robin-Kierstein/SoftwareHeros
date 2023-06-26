package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;
import DocumentManagementSystem.Datenbank.UserDatabase;
import DocumentManagementSystem.DocumentAuthorization.DocumentManagementInterface;
import DocumentManagementSystem.DocumentAuthorization.DocumentManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class GUI implements UserInterface, DocumentManagementInterface {

    private DocumentManagementInterface docmanager;

    public GUI(DocumentManagementInterface docmanager) {
        this.docmanager = docmanager;
    }

    /**
     * @Author Robin Kierstein
     * This method opens a menu in a window.
     * The user can select options by typing in a certain number.
     * The menu runs until the user wants to leave it or cancels it.
     */
    @Override
    //TODO: Weitere Menü Auswahlmöglichkeiten hinzufügen
    public void showMenu() {
        DocumentManager docmanger = new DocumentManager();
        GUI ui = new GUI(docmanger);
        String pfad = "";
        int choice = 0;

        boolean inputIsValidNumber = false;
        boolean inputIsNumber = false;

        do {

            try {

               do {
                    String input = JOptionPane.showInputDialog(null,"Bitte auswählen: \n 1. Registrieren \n 2. Anmelden\n 3. Upload \n 4. Menü verlassen","Menü",JOptionPane.OK_CANCEL_OPTION);
                        //Cancel Option
                        if (input == null){
                            return;
                        }
                    choice = Integer.parseInt(input);

                    switch (choice) {
                        case 1:
                            UserDatabase.register();
                            inputIsNumber = true;
                            break;

                        case 2:
                            UserDatabase.login();
                            inputIsNumber = true;
                            break;

                        case 3:
                            if (!UserDatabase.isLoggedIn()){
                                JOptionPane.showMessageDialog(null,"Bitte erst einloggen!");
                            }else {
                                //TODO: Upload ausprogrammieren
                                pfad = JOptionPane.showInputDialog("Bitte Pfad einfügen");

                                String name = "";


                                //docmanager.saveUploadDocument("Hier einen Pfad einfügen");
                                ui.docTypeCheckMessage(pfad);
                                if (!docTypeCheck(pfad)) {
                                    break;
                                }

                                name = ui.chooseUploadDocument(pfad);
                                ui.saveUploadDocument(pfad,name);
                                /*ui.resultMessage(pfad);*/
                                //saveUploadDocument(pfad);
                            }

                            inputIsNumber = true;
                            break;

                        case 4:
                            inputIsValidNumber = true;
                            inputIsNumber = true;
                            return;

                        default:
                            JOptionPane.showMessageDialog(null, "Bitte nur Zahlen zwischen 1 und 4 eingeben");
                    }
                } while (!inputIsValidNumber);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte nur Zahlen zwischen 1 und 4 eingeben");
            }
        } while (!inputIsNumber);
    }

    @Override
    public String requestUploadAuthorization() {
        return null;
    }

    @Override
    public String chooseUploadDocument(String pfad) {


        String fileName = JOptionPane.showInputDialog(null,"Geben Sie den Dokumentnamen ein",JOptionPane.OK_CANCEL_OPTION);
        String answer = JOptionPane.showInputDialog(null,"Möchten Sie dieses Dokument wirklich hochladen? (ja Nein):",JOptionPane.OK_CANCEL_OPTION);



        // If the user says yes, save the name.
        if (answer.equals("ja")) {
            System.out.println("Der Dokumentname lautet " + fileName);
            return fileName;
        } else {
            // If the user says no, give the option to go back to step one.
            String choice = JOptionPane.showInputDialog(null,"Möchten Sie zu Schritt eins zurückkehren und einen anderen Dateinamen hochladen? (ja Nein):",JOptionPane.OK_CANCEL_OPTION);
            if (choice.equals("ja")) {
                return null;
            } else {
                return fileName;
            }
        }
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
    public boolean docTypeCheck(String pfad){ //Checked ob Datei Typ zulässig

        boolean typecheck;


        if (pfad.contains("pdf") ){
            typecheck = true;
        }else if (pfad.contains("jpg")){
            typecheck = true;
        }else if (pfad.contains("png")){
            typecheck = true;
        }else{
            typecheck = false;
        }

        return typecheck;
    }

    @Override
    public String docTypeCheckMessage(String pfad){ //Message an User wenn Datei Typ falsch

        if (docTypeCheck(pfad) == false){
            JOptionPane.showMessageDialog(null, "Unzulässiger Datei Typ");
        }else{
            JOptionPane.showMessageDialog(null, "Zulässiger Datei Typ");
        }
        return null;
    }

    @Override
    public String resultMessage(String pfad) { //Message an User ob Upload erfolgreich

        if (docTypeCheck(pfad)!= false) {
            JOptionPane.showMessageDialog(null, "Upload erfolgreich");
        } else if (docTypeCheck(pfad) == false) {
            JOptionPane.showMessageDialog(null, "Upload nicht erfolgreich");
            return null;
        }

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

            System.out.println("File saved successfully: " + outputFile.getAbsolutePath());
            return true;

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File save failed");
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
