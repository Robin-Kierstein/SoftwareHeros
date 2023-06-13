package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;

public interface UserInterface {

    String showMenu();
    String requestUploadAuthorization();
    String chooseUploadDocument(String pfad);
    boolean confirmUpload();
    String resultMessage();
    String getSearchInput();
    Document selectSavedDocument();

}
