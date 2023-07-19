package DocumentManagementSystem.DocumentAuthorization;

public interface ManageAuthorizationInterface {
    String requestViewingRights();
    boolean showRequestResult();
    boolean addAuthorization();
    boolean removeAuthorization();
    String resultAuthorizationMessage();
}