package DocumentManagementSystem.DocumentAuthorization;

import DocumentManagementSystem.Datenbank.Document;
import DocumentManagementSystem.Datenbank.MetaDate;

import java.util.ArrayList;

public interface DocumentManagementInterface {
    boolean uploadDocument(String pfad);
    public boolean saveUploadDocument(String pfad, String name);
    boolean docTypeCheck(String pfad);
    public String searchThroughDocument();
    public ArrayList<Document> createSearchList();
    public Document showSearchList();
    public String requestViewingAuthorization();
    public Document viewSelectedDocument();
    public String viewMetatagsDocument();
}
