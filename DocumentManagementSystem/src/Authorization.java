public abstract class Authorization implements ManageAuthorizationInterface {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
