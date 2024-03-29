package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;
import DocumentManagementSystem.Datenbank.MetaDate;
import DocumentManagementSystem.Datenbank.User;
import DocumentManagementSystem.Datenbank.UserDatabase;
import DocumentManagementSystem.DocumentAuthorization.DocumentManagementInterface;
import DocumentManagementSystem.DocumentAuthorization.DocumentManager;
import DocumentManagementSystem.DocumentAuthorization.ManageAuthorization;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class GUI implements UserInterface, DocumentManagementInterface {

    private DocumentManagementInterface docmanager;

    public GUI(DocumentManagementInterface docmanager) {
        this.docmanager = docmanager;
    }


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
                    String input = JOptionPane.showInputDialog(null, "Bitte auswählen: \n 1. Registrieren \n 2. Anmelden\n 3. Upload \n 4. View \n " +
                            "5. Request Upload Rights \n 6. Dokumente suchen \n 7. Request Viewing Rights \n 8. Menü verlassen", "Menü", JOptionPane.OK_CANCEL_OPTION);
                    //Cancel Option
                    if (input == null) {
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
                            if (!UserDatabase.isLoggedIn()) {
                                JOptionPane.showMessageDialog(null, "Bitte erst einloggen!");
                            } else {
                                String authorizationResult = ui.requestUploadAuthorization();
                                if (authorizationResult.equals("Erfolg")) {
                                    pfad = JOptionPane.showInputDialog("Bitte Pfad einfügen");

                                    String name = "";


                                    ui.docTypeCheckMessage(pfad);
                                    if (!docTypeCheck(pfad)) {
                                        break;
                                    }

                                    name = ui.chooseUploadDocument(pfad);
                                    ui.saveUploadDocument(pfad, name );

                                } else {
                                    JOptionPane.showMessageDialog(null, authorizationResult);  // Informiert den Benutzer, dass er keine Berechtigung hat
                                }
                            }

                            inputIsNumber = true;
                            break;

                        case 4:
                            if (!UserDatabase.isLoggedIn()) {
                                JOptionPane.showMessageDialog(null, "Bitte erst einloggen!");
                            } else {
                                String authorizationResult = ui.requestViewingAuthorization();
                                if (authorizationResult.equals("Erfolg")) {
                                    String selecteddocument = JOptionPane.showInputDialog("Bitte Dokumentenname eingeben");
                                    viewSelecteddDocument(selecteddocument);


                                } else {
                                    JOptionPane.showMessageDialog(null, authorizationResult);  // Informiert den Benutzer, dass er keine Berechtigung hat
                                }
                            }

                            inputIsNumber = true;
                            break;

                        case 5:
                            // Überprüfen, ob Benutzer eingeloggt ist
                            if (!UserDatabase.isLoggedIn()) {
                                JOptionPane.showMessageDialog(null, "Bitte erst einloggen!");
                            } else {
                                // Eingabe einer Notiz für den Admin abfragen
                                String note = JOptionPane.showInputDialog("Bitte Notiz für den Admin eingeben");

                                // Den aktuellen eingeloggten Benutzer abrufen
                                User loggedInUser = UserDatabase.getUserByUsername(UserDatabase.getLoggedInUser());

                                // Neue Instanz von ManageAuthorization mit dem eingeloggten Benutzer erstellen
                                ManageAuthorization authorizationManager = new ManageAuthorization(loggedInUser);

                                // Anforderung von Upload-Rechten für den Benutzer stellen
                                String requestMessage = authorizationManager.requestUploadRights();

                                // Anforderung in die Datei schreiben und Notiz hinzufügen
                                authorizationManager.writeRequestToFile(requestMessage + ". Notiz: " + note);

                                // Benutzer über erfolgreiche Anforderung informieren
                                JOptionPane.showMessageDialog(null, "Berechtigungsanforderung gesendet. Bitte warten Sie auf die Genehmigung des Admins.");
                            }

                            inputIsNumber = true;
                            break;

                        case 6:
                            String keyword = JOptionPane.showInputDialog(null,"Wonach soll gesucht werden?","Suche",JOptionPane.OK_CANCEL_OPTION);
                            //Cancel Option
                            if (keyword == null) {
                                break;
                            }
                            docmanager.searchDocument(keyword);
                            break;

                        case 7:
                            if (!UserDatabase.isLoggedIn()) {
                                JOptionPane.showMessageDialog(null, "Bitte erst einloggen!");
                            } else {
                                // Eingabe einer Notiz für den Admin abfragen
                                String note = JOptionPane.showInputDialog("Bitte Notiz für den Admin eingeben");

                                // Den aktuellen eingeloggten Benutzer abrufen
                                User loggedInUser = UserDatabase.getUserByUsername(UserDatabase.getLoggedInUser());

                                // Neue Instanz von ManageAuthorization mit dem eingeloggten Benutzer erstellen
                                ManageAuthorization authorizationManager = new ManageAuthorization(loggedInUser);

                                // Anforderung von Upload-Rechten für den Benutzer stellen
                                String requestMessage = authorizationManager.requestViewingRights();

                                // Anforderung in die Datei schreiben und Notiz hinzufügen
                                authorizationManager.writeRequestToFile(requestMessage + ". Notiz: " + note);

                                // Benutzer über erfolgreiche Anforderung informieren
                                JOptionPane.showMessageDialog(null, "Berechtigungsanforderung gesendet. Bitte warten Sie auf die Genehmigung des Admins.");
                            }
                            break;

                        case 8:
                            inputIsValidNumber = true;
                            inputIsNumber = true;
                            return;

                        default:
                            JOptionPane.showMessageDialog(null, "Bitte nur Zahlen zwischen 1 und 7 eingeben");
                    }
                } while (!inputIsValidNumber);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte nur Zahlen zwischen 1 und 7 eingeben");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!inputIsNumber);
    }


    @Override
    public String requestUploadAuthorization() {
        if (UserDatabase.hasUploadRights(UserDatabase.getLoggedInUser())) {
            return "Erfolg";
        } else {
            return "Fehler: Sie haben keine Berechtigung zum Hochladen von Dokumenten.";
        }
    }



    @Override
    public String chooseUploadDocument(String pfad) {
        String fileName = JOptionPane.showInputDialog(null, "Geben Sie den Dokumentnamen ein", JOptionPane.OK_CANCEL_OPTION);
        String answer = JOptionPane.showInputDialog(null, "Möchten Sie dieses Dokument wirklich hochladen? (ja Nein):", JOptionPane.OK_CANCEL_OPTION);

        // If the user says yes, save the name.
        if (answer.equals("ja")) {
            System.out.println("Der Dokumentname lautet " + fileName);
            return fileName;
        } else {
            // If the user says no, give the option to go back to step one.
            String choice = JOptionPane.showInputDialog(null, "Möchten Sie zu Schritt eins zurückkehren und einen anderen Dateinamen hochladen? (ja Nein):", JOptionPane.OK_CANCEL_OPTION);
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
    public boolean docTypeCheck(String pfad) { //Checked ob Datei Typ zulässig

        boolean typecheck;


        if (pfad.contains("pdf")) {
            typecheck = true;
        } else if (pfad.contains("jpg")) {
            typecheck = true;
        } else if (pfad.contains("png")) {
            typecheck = true;
        } else {
            typecheck = false;
        }

        return typecheck;
    }

    @Override
    public String docTypeCheckMessage(String pfad) { //Message an User wenn Datei Typ falsch

        if (docTypeCheck(pfad) == false) {
            JOptionPane.showMessageDialog(null, "Unzulässiger Datei Typ");
        } else {
            JOptionPane.showMessageDialog(null, "Zulässiger Datei Typ");
        }
        return null;
    }

    @Override
    public String resultMessage(String pfad) { //Message an User ob Upload erfolgreich

        if (docTypeCheck(pfad) != false) {
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
    public Document viewSelectedDocument(String selecteddocument) throws IOException {
        return null;
    }

    @Override
    public boolean uploadDocument(String pfad) {
        return false;
    }

    @Override
    public boolean saveUploadDocument(String pfad, String name  ) {
        try {

            // Erstellt ein neues File-Objekt an dem gewünschten Pfad
            File outputFile = new File("DocumentManagementSystem/Gespeicherte Dateien/" + name);

            // Inputfile ist die Datei die wir speichern möchten
            File inputFile = new File(pfad);

            // Wenn die Datei bereits existiert löschen wir sie
            if (outputFile.exists()) {
                outputFile.delete();
            }

            // Wenn das Verzeichnis nicht existiert erstellen wir es
            outputFile.getParentFile().mkdirs();

            // Kopiert die Datei
            Files.copy(inputFile.toPath(), outputFile.toPath());

            JOptionPane.showMessageDialog(null, "Die Datei wurde erfolgreich hochgeladen: " + outputFile.getAbsolutePath());
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Das hochladen der Datei ist fehlgeschlagen!");
        return false;
    }

    @Override
    public Document viewSelecteddDocument(String selecteddocument) throws IOException {


        File outputFile = new File("DocumentManagementSystem/Gespeicherte Dateien/" + selecteddocument);
        if (outputFile.exists()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(outputFile);
        } else if (!outputFile.exists()) {
            JOptionPane.showMessageDialog(null, "Ein Dokument unter diesem Namen existiert nicht");
        }


        return null;
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
        if (UserDatabase.hasUploadRights(UserDatabase.getLoggedInUser())) {
            return "Erfolg";
        } else {
            return "Fehler: Sie haben keine Berechtigung zum Hochladen von Dokumenten.";
        }
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

    public  MetaDate fillMetadata() {
        String text = JOptionPane.showInputDialog("Geben Sie den Metadatentext ein:");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie die Metadaten-ID ein:"));
        String doctorName = JOptionPane.showInputDialog("Geben Sie den Namen des Spieler ein:");
        String date = JOptionPane.showInputDialog("Geben Sie das Datum ein:");
        String prescribedMedications = JOptionPane.showInputDialog("Geben Sie die verschriebenen Plan ein:");

        boolean allFieldsFilled = true;

        if (text == null || text.isEmpty()) {
            allFieldsFilled = false;
            JOptionPane.showMessageDialog(null, "Metadatentext muss ausgefüllt werden.");
        }

        if (id == 0) {
            allFieldsFilled = false;
            JOptionPane.showMessageDialog(null, "Metadaten-ID muss ausgefüllt werden.");
        }

        if (doctorName == null || doctorName.isEmpty()) {
            allFieldsFilled = false;
            JOptionPane.showMessageDialog(null, "Name des Spielers muss ausgefüllt werden.");
        }

        if (date == null || date.isEmpty()) {
            allFieldsFilled = false;
            JOptionPane.showMessageDialog(null, "Datum muss ausgefüllt werden.");
        }

        if (prescribedMedications == null || prescribedMedications.isEmpty()) {
            allFieldsFilled = false;
            JOptionPane.showMessageDialog(null, "Verschriebene Medikamente müssen ausgefüllt werden.");
        }

        if (allFieldsFilled) {
            return new MetaDate(text, id, doctorName, date, prescribedMedications);
        } else {
            return null;
        }
    }
}
