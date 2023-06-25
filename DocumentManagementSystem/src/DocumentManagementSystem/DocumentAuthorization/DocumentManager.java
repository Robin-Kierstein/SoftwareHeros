package DocumentManagementSystem.DocumentAuthorization;

import DocumentManagementSystem.Datenbank.Document;
import DocumentManagementSystem.Datenbank.DocumentDatabase;
import DocumentManagementSystem.Datenbank.DocumentDatabaseInterface;

import javax.swing.*;
import java.util.ArrayList;

public class DocumentManager implements DocumentManagementInterface {

    private DocumentDatabaseInterface docdatabase = new DocumentDatabase();

        @Override
        public boolean uploadDocument(String pfad) {
            return false;
        }


    @Override
    public boolean saveUploadDocument(String pfad) {

        if (pfad == null) {
            return false;
        }
        return docdatabase.uploadDocument(pfad);
    }

    @Override
    public boolean docTypeCheck(){ //Checked ob Datei Typ zulässig

        String pfad = String.valueOf(saveUploadDocument(""));
        boolean typecheck;


            if (pfad.contains("pdf") ){
                typecheck = true;
            }else if (pfad.contains("jpg")){
                typecheck = true;
            }else if (pfad.contains("png")){
                typecheck = true;
            }else{
                typecheck = false;
            }

        return typecheck;
    }


    @Override
    public String searchThroughDocument() {
        return null;
    }

    @Override
    public ArrayList<Document> createSearchList() {
        return null;
    }

    @Override
    public Document showSearchList() {
        return null;
    }

    @Override
    public String requestViewingAuthorization() {
        return null;
    }

    @Override
    public Document viewSelectedDocument() {
        return null;
    }

    @Override
    public String viewMetatagsDocument() {
        return null;
    }
}
