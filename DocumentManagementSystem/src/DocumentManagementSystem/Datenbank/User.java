package DocumentManagementSystem.Datenbank;

import DocumentManagementSystem.Datenbank.Document;
import DocumentManagementSystem.DocumentAuthorization.Authorization;
import DocumentManagementSystem.GUI.UserInterface;

import javax.swing.*;
import java.util.List;

public class User implements UserInterface {
    private String name;
    private int id;
    private List<Authorization> authorization;

    public User(String name, int id, List<Authorization> authorization) {
        this.name = name;
        this.id = id;
        this.authorization = authorization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static void menu(){
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
                            status = true;
                            status2 = true;
                            break;
                    }
                } while (status != true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte nur Zahlen zwischen 1 und 3 eingeben");
            }
        } while (status2 != true);
    }


    @Override
    public String showMenu() {
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
}
