package DocumentManagementSystem.DocumentAuthorization;

import DocumentManagementSystem.DocumentAuthorization.Authorization;

public class ManageAuthorization extends Authorization {
    @Override
    public String requestViewingRights() {
        return null;
    }

    @Override
    public boolean showRequestResult() {
        return false;
    }

    @Override
    public boolean addAuthorization() {
        return false;
    }

    @Override
    public boolean removeAuthorization() {
        return false;
    }

    @Override
    public String resultAuthorizationMessage() {
        return null;
    }
}
