package DocumentManagementSystem.DocumentAuthorization;

import DocumentManagementSystem.Datenbank.Document;

import java.util.ArrayList;

public interface DocumentManagementInterface {
    boolean uploadDocument(String pfad);
    boolean saveUploadDocument(String pfad, String name);
    boolean docTypeCheck(String pfad);

    /***
     * Looks for documents by comparing all uploaded documents names with user input and shows all matching results.
     * @param input The keyword that the documents name should contain
     */
    void searchDocument(String input);
    ArrayList<Document> createSearchList();
    Document showSearchList();
    String requestViewingAuthorization();
    Document viewSelectedDocument();
    String viewMetatagsDocument();
}
