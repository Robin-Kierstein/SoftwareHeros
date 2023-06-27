package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;

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

}
