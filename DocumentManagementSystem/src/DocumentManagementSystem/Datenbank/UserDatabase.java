package DocumentManagementSystem.Datenbank;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserDatabase {
    private static boolean isLoggedIn;

    public static void main(String[] args) {
        //register();
        //login();
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void login() {
        //Anmelden
        //Textfelder für User Input erstellen
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();

        Object[] felder = {
                "Benutzername", textField1,
                "Passwort", textField2
        };

        //boolean zum Abgleich von User Input und vorhandenen Usern
        boolean matched = false;
        do {

            //User nach Benutzername und Passwort fragen und in Textfeldern speichern
            int option =  JOptionPane.showConfirmDialog(null, felder, "Anmelden", JOptionPane.OK_CANCEL_OPTION);
            //Cancel Option
            if (option == JOptionPane.CANCEL_OPTION){
                return;
            }

            //Input aus Textfeldern in Variablen speichern
            String username = textField1.getText();
            String passwort = textField2.getText();

            try {
                FileReader fileReader = new FileReader("Login.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.equals(username + "\t" + passwort)) {
                        matched = true;
                        break;
                    }
                }
                fileReader.close();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, ioe.getMessage());
            }

            if (matched) {
                JOptionPane.showMessageDialog(null, "Anmeldung erfolgreich");
                isLoggedIn = true;
            } else {
                int auswahl = JOptionPane.showConfirmDialog(null, "Anmeldedaten falsch","",JOptionPane.OK_CANCEL_OPTION);
                if (auswahl == JOptionPane.CANCEL_OPTION){
                    return;
                }

            }
        } while (!matched);
    }

    public static boolean checkForExistingUsers(String benutzername) {
        boolean ausgabe = false;
        try {
            FileReader fileReader = new FileReader("Login.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //line aus benutzername und passort spliten um nur den benutzernamen zu prüfen
                String[] array = line.split("\t");
                if (array[0].equals(benutzername)) {
                    ausgabe = true;
                    break;
                }
            }
            fileReader.close();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe.getMessage());
        }
        return ausgabe;
    }

    public static void register() {
        //Textfelder für User Input erstellen
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();

        Object[] felder = {
                "Benutzername", textField1,
                "Passwort", textField2
        };

        boolean inputKontrolle = true;
        //Schleife für den Fall das der Benutzername bereits vorhanden ist
        do {

            //User nach Benutzername und Passwort fragen und in Textfeldern speichern
            int option = JOptionPane.showConfirmDialog(null, felder, "Registrieren", JOptionPane.OK_CANCEL_OPTION);
            //Cancel Option
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            }

            //Prüfen ob es den Benutzernamen bereits gibt
            boolean checkForExistingUsers = checkForExistingUsers(textField1.getText());
            if (checkForExistingUsers) {
                int auswahl = JOptionPane.showConfirmDialog(null, "Dieser Benutzername existiert bereits", "", JOptionPane.OK_CANCEL_OPTION);
                if (auswahl == JOptionPane.CANCEL_OPTION) {
                    return;
                }
            }
            if (!checkForExistingUsers){
                inputKontrolle = false;
            }

        } while (inputKontrolle);

        try {
            //eingegebenen Benutzername und Passwort in Login.txt speichern
            FileWriter filewriter = new FileWriter("Login.txt", true);
            filewriter.write(textField1.getText() + "\t" + textField2.getText() + "\n");
            filewriter.close();
            //Benutzerdaten nochmal und erfolgereiche Registrierung für den User anzeigen
            JOptionPane.showMessageDialog(null, "Registrierung erfolgreich! \n" + "Benutzername: " + textField1.getText() + "\n" + "Passwort: " + textField2.getText(), "Registrierung erfolgreich", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe.getMessage());
        }
    }

}
