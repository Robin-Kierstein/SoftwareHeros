package DocumentManagementSystem.Datenbank;

import DocumentManagementSystem.DocumentAuthorization.Authorization;
import DocumentManagementSystem.GUI.UserInterface;

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
    public void showMenu() {

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
    public String docTypeCheckMessage() {
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
