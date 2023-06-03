import java.util.ArrayList;

public interface DocumentManagementInterface {
    public boolean saveUploadDocument(String pfad);
    public String searchThroughDocument();
    public ArrayList<Document> createSearchList();
    public Document showSearchList();
    public String requestViewingAuthorization();
    public Document viewSelectedDocument();
    public String viewMetatagsDocument();
}
