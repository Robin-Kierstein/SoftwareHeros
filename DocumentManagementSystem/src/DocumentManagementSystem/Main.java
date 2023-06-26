package DocumentManagementSystem;

import DocumentManagementSystem.DocumentAuthorization.DocumentManager;
import DocumentManagementSystem.GUI.GUI;

public class Main {
    public static void main(String[] args) {
        DocumentManager docmanger = new DocumentManager();
        GUI ui = new GUI(docmanger);
        ui.showMenu();


    }
}