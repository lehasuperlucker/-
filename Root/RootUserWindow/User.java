package sample.Root.RootUserWindow;

public class User {
    private String idColumn;
    private String usernameColumn;
    private  String roleColumn;
    private String accessColumn;

    public String getAccessColumn() {
        return accessColumn;
    }

    public void setAccessColumn(String accessColumn) {
        this.accessColumn = accessColumn;
    }

    public User(String id, String username, String role, String access) {
        this.idColumn=id;
        this.usernameColumn=username;
        this.roleColumn=role;
        this.accessColumn=access;
    }

    public String getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(String idColumn) {
        this.idColumn = idColumn;
    }

    public String getUsernameColumn() {
        return usernameColumn;
    }

    public void setUsernameColumn(String usernameColumn) {
        this.usernameColumn = usernameColumn;
    }

    public String getRoleColumn() {
        return roleColumn;
    }

    public void setRoleColumn(String roleColumn) {
        this.roleColumn = roleColumn;
    }
}
