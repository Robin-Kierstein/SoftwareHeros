package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;

public interface UserInterface {

    void showMenu();
    String requestUploadAuthorization();
    String chooseUploadDocument(String pfad);
    boolean confirmUpload();
    String resultMessage();

    String docTypeCheckMessage();

    String getSearchInput();
    Document selectSavedDocument();

}
