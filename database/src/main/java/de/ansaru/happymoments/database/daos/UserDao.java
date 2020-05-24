package de.ansaru.happymoments.database.daos;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "user.findAll", query = "SELECT u FROM UserDao u"),
        @NamedQuery(name = "user.findById", query = "SELECT u FROM UserDao u WHERE u.id = :id"),
        @NamedQuery(name = "user.findByEmail", query = "SELECT u FROM UserDao u WHERE u.email = :email"),
        @NamedQuery(name = "user.findPasswordByEmail", query = "SELECT u.password FROM UserDao u WHERE u.email = :email")
})
public class UserDao implements IDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String name;

    private String email;

    private boolean isAdmin;

    private boolean isActive;

    private LocalDate accountCreationDate;

    public UserDao() { }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

}
