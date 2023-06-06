package DocumentManagementSystem.DocumentAuthorization;

import DocumentManagementSystem.Datenbank.Document;

import java.util.ArrayList;

public interface DocumentManagementInterface {
    boolean uploadDocument(String pfad);

    public boolean saveUploadDocument(String pfad);
    public String searchThroughDocument();
    public ArrayList<Document> createSearchList();
    public Document showSearchList();
    public String requestViewingAuthorization();
    public Document viewSelectedDocument();
    public String viewMetatagsDocument();
}
