package Project.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String address;
    private LocalDate date;

    @ManyToOne
    private Role_Account role_account;

    public Account() {
    }

    public Account(long id, String username, String password, String address, LocalDate date, Role_Account role_account) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.date = date;
        this.role_account = role_account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Role_Account getRole_account() {
        return role_account;
    }

    public void setRole_account(Role_Account role_account) {
        this.role_account = role_account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
