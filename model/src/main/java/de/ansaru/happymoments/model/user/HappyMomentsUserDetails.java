package de.ansaru.happymoments.model.user;

import de.ansaru.happymoments.model.IModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class HappyMomentsUserDetails implements UserDetails, IModel {

    private HappyMomentsUserDetails() { }

    private String emailAddress;

    private String password;

    private boolean accountExpired;

    private boolean accountLocked;

    private boolean credentialsExpired;

    private boolean enabled;

    private List<GrantedAuthority>  grantedAuthorities;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setAccountExpired(boolean expired) {
        this.accountExpired = expired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public static class Builder {

        private HappyMomentsUserDetails details;

        public Builder() {
            details = new HappyMomentsUserDetails();
        }

        public Builder withEmailAddress(String emailAddress) {
            details.setEmailAddress(emailAddress);
            return this;
        }

        public Builder withPassword(String password) {
            details.setPassword(password);
            return this;
        }

        public Builder withExpiredAccount(boolean accountExpired) {
            details.setAccountExpired(accountExpired);
            return this;
        }

        public Builder withLockedAccount(boolean accountLocked) {
            details.setAccountLocked(accountLocked);
            return this;
        }

        public Builder withExpiredCredentials(boolean credentialsExpired) {
            details.setCredentialsExpired(credentialsExpired);
            return this;
        }

        public Builder isEnabled(boolean enabled) {
            details.setEnabled(enabled);
            return this;
        }

        public Builder withGrantedAuthorities(List<GrantedAuthority> authorities) {
            details.setGrantedAuthorities(authorities);
            return this;
        }

        public HappyMomentsUserDetails build() {
            return details;
        }
    }
}
