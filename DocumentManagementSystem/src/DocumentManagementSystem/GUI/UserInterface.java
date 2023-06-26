package DocumentManagementSystem.GUI;

import DocumentManagementSystem.Datenbank.Document;

public interface UserInterface {

    /**
     * @Author Robin Kierstein
     * This method opens a menu in a window.
     * The user can select options by typing in a certain number.
     * The menu runs until the user wants to leave it or cancels it.
     */
    void showMenu();
    String requestUploadAuthorization();
    String chooseUploadDocument(String pfad);
    boolean confirmUpload();
    String resultMessage();

    String docTypeCheckMessage(String pfad);

    String getSearchInput();
    Document selectSavedDocument();

}
