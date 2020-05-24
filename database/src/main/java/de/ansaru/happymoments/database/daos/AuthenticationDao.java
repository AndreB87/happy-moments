package de.ansaru.happymoments.database.daos;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "authentication")
@NamedQueries({
        @NamedQuery(name = "authentication.findAll", query = "SELECT a FROM AuthenticationDao a"),
        @NamedQuery(name = "authentication.findByEmail", query = "SELECT a FROM AuthenticationDao a WHERE a.username = :email")
})
public class AuthenticationDao implements IDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String username;

    private String password;

    @ElementCollection
    private List<String> authorities;

    private boolean accountExpired;

    private boolean accountLocked;

    private boolean credentialsExpired;

    private boolean enabled;

    public AuthenticationDao() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAccountExpired(boolean isAccountExpired) {
        this.accountExpired = isAccountExpired;
    }

    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }


}
