package de.ansaru.happymoments.database.user.entities;

import de.ansaru.happymoments.database.entities.IEntity;
import de.ansaru.happymoments.database.user.constants.UserQueries;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
            name = UserQueries.FIND_ALL,
            query = "SELECT u FROM UserEntity u"
        ),
        @NamedQuery(
            name = UserQueries.FIND_BY_ID,
            query = "SELECT u FROM UserEntity u WHERE u.id = :id"
        ),
        @NamedQuery(
            name = UserQueries.FIND_BY_EMAIL,
            query = "SELECT u FROM UserEntity u WHERE u.email = :email"
        ),
        @NamedQuery(
            name = UserQueries.FIND_ID_BY_EMAIL,
            query = "SELECT u.userId FROM UserEntity u WHERE u.email = :email"
        )
})
public class UserEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String name;

    private String email;

    private boolean isAdmin;

    private LocalDate accountCreationDate;

    private String password;

    public UserEntity() { }

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

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


}
