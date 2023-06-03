public interface UserInterface {

    public String showMenu();
    public String requestUploadAuthorization();
    public String chooseUploadDocument(String pfad);
    public boolean confirmUpload();
    public String resultMessage();
    public String getSearchInput();
    public Document selectSavedDocument();

}
