package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Roles;

public class AccountDTO {

    private String username;
    private String password;
    private Roles userRole;

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, Roles userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return userRole;
    }

    public void setRole(Roles userRole) {
        this.userRole = userRole;
    }
}
