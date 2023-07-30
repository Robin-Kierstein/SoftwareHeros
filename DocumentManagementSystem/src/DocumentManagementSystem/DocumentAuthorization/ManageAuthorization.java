package DocumentManagementSystem.DocumentAuthorization;

import DocumentManagementSystem.Datenbank.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Die Klasse ManageAuthorization ist für die Verwaltung der Berechtigungen eines Benutzers zuständig.
 * Sie erweitert die Klasse Authorization und implementiert das ManageAuthorizationInterface.
 */
public class ManageAuthorization extends Authorization implements ManageAuthorizationInterface {
    private User user;
    private boolean authorizationStatus = false;
    private Map<String, Boolean> userAuthorizationStatus = new HashMap<>();
    private Map<String, String> authorizationMessage = new HashMap<>();

    //Konstruktor der Klasse ManageAuthorization.
    public ManageAuthorization(User user) {
        this.user = user;
    }

    //Methode zum Anfordern von Anzeigerechten
    @Override
    public String requestViewingRights() {
        this.setType("View");
        authorizationStatus = false;
        user.getAuthorization().add(this);
        String request = "View rights requested for user " + user.getBenutzername();
        writeRequestToFile(request);
        return request;
    }

    //Methode zum Anzeigen des Anforderungsergebnisses
    @Override
    public boolean showRequestResult() {
        return userAuthorizationStatus.getOrDefault(user.getBenutzername(), false);
    }

    //Methode zum Hinzufügen einer Berechtigung.
    @Override
    public boolean addAuthorization() {
        // Autorisierung für den Benutzer hinzufügen
        userAuthorizationStatus.put(user.getBenutzername(), true);
        return true;
    }

    @Override
    public boolean removeAuthorization() {
        // Autorisierung für den Benutzer entfernen
        userAuthorizationStatus.put(user.getBenutzername(), false);
        return false;
    }



    //Methode zum Abrufen der Meldung basierend auf dem Berechtigungsstatus.
    @Override
    public String resultAuthorizationMessage() {
        // gibt eine Meldung basierend auf dem Berechtigungsstatus zurück
        String message = userAuthorizationStatus.get(user.getBenutzername()) ? "Authorization granted for " : "Authorization denied for ";
        message += this.getType() + " user " + user.getBenutzername();
        authorizationMessage.put(user.getBenutzername(), message);
        return message;
    }


    //Methode zum Schreiben einer Anforderung in eine Datei.
    //     * Die Anforderung wird mit Datum und Uhrzeit versehen.
    public void writeRequestToFile(String request) {
        try {
            FileWriter fileWriter = new FileWriter("requests.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);

            String requestWithDateTime = "[" + formattedDateTime + "] " + request;
            printWriter.println(requestWithDateTime);

            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Methode zum Anfordern von Upload-Rechten. Die Anforderung wird in eine Datei geschrieben.
    @Override
    public String requestUploadRights() {
        this.setType("Upload");
        authorizationStatus = false;
        user.getAuthorization().add(this);
        String request = "Upload rights requested for user " + user.getBenutzername();
        writeRequestToFile(request); // Schreiben Sie die Anforderung in die Datei
        return request;
    }
}
