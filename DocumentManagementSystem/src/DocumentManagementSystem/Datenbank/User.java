package DocumentManagementSystem.Datenbank;

import DocumentManagementSystem.DocumentAuthorization.Authorization;
import DocumentManagementSystem.DocumentAuthorization.DocumentManager;
import DocumentManagementSystem.GUI.GUI;
import DocumentManagementSystem.GUI.UserInterface;

import javax.swing.*;
import java.util.List;

public class User implements UserInterface {
    private String benutzername;
    private String passwort;
    private int id;
    private List<Authorization> authorization;

    public User(String name, String passwort) {
        this.benutzername = name;
        this.passwort = passwort;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Authorization> getAuthorization() {
        return authorization;
    }

    public void setAuthorization(List<Authorization> authorization) {
        this.authorization = authorization;
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
                                //docmanager.saveUploadDocument("Hier einen Pfad einfügen");
                                //docTypeCheckMessage();
                                ui.chooseUploadDocument(pfad);
                                ui.saveUploadDocument(pfad, "");
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
    public String docTypeCheckMessage(String pfad) {
        return null;
    }

    @Override
    public String resultMessage(String pfad) {
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
}
