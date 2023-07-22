package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;

import java.io.IOException;

public interface UserInterface {


    void showMenu();
    String requestUploadAuthorization();
    String chooseUploadDocument(String pfad);
    boolean confirmUpload();
    String resultMessage();

    String docTypeCheckMessage(String pfad);

    String resultMessage(String pfad);

    String getSearchInput();
    Document selectSavedDocument();

    Document viewSelectedDocument(String selecteddocument) throws IOException;

    Document viewSelecteddDocument(String selecteddocument) throws IOException;
}
