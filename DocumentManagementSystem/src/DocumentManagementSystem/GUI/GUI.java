package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;
import DocumentManagementSystem.Datenbank.UserDatabase;
import DocumentManagementSystem.DocumentAuthorization.DocumentManagementInterface;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class GUI implements UserInterface, DocumentManagementInterface {

    private DocumentManagementInterface docmanager;

    public GUI(DocumentManagementInterface docmanager) {
        this.docmanager = docmanager;
    }

    @Override
    //TODO: Weitere Menü Auswahlmöglichkeiten hinzufügen
    public void showMenu() {
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
                                //docmanager.saveUploadDocument("Hier einen Pfad einfügen");
                                docTypeCheckMessage();
                                resultMessage();
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Are you sure you want to upload '" + pfad + "'? (yes/no): ");
        String confirmation = scanner.nextLine();

        scanner.close();

        String chosenPfad;  // Declare the chosenPfad variable outside the if block

        if (confirmation.equalsIgnoreCase("yes")) {
            chosenPfad = pfad;  // Save the chosen pfad to the variable
        } else {
            chosenPfad = null;  // Reset the chosen pfad
        }

        return chosenPfad;  // Return the chosenPfad variable
    }


    @Override
    public boolean confirmUpload() {
        return false;
    }

    @Override
    public String docTypeCheckMessage(){ //Message an User wenn Datei Typ falsch

        if (docmanager.docTypeCheck() == false){
            JOptionPane.showMessageDialog(null, "Unzulässiger Datei Typ");
        }else{
            JOptionPane.showMessageDialog(null, "Zulässiger Datei Typ");
        }
        return null;
    }

    @Override
    public String resultMessage() { //Message an User ob Upload erfolgreich

        if (docmanager.saveUploadDocument("") != false) {
            JOptionPane.showMessageDialog(null, "Upload erfolgreich");
        } else if (docmanager.saveUploadDocument("") == false) {
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
    public boolean saveUploadDocument(String pfad) {
        try {

            // Erstellt ein neues File-Objekt an dem gewünschten Pfad
            File outputFile = new File("DocumentManagementSystem/SaveFile"); // Hier geben wir den Namen der neuen Datei an

            // Für den Test erstellen wir eine temporäre "inputFile"
            File inputFile = new File(pfad); //Geben den genauen Pfad zu unserer Testdatei an

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
    public boolean docTypeCheck() {
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
