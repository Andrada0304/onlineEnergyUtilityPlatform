package ro.tuc.ds2020.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles userRole;

    public Account() {
    }

    public Account(String username, String password, Roles userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public Account(String username, Roles userRole) {
        this.username = username;
        this.userRole = userRole;
    }

    public Account(String username, String encode) {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return this.userRole;
    }

    public void setRole(Roles userRole) {
        this.userRole = userRole;
    }
}
