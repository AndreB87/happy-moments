package de.ansaru.happymoments.model.user;

import de.ansaru.happymoments.model.IModel;

import java.time.LocalDate;

public class User implements IModel {

    private long id;

    private String name;

    private String email;

    private boolean isAdmin;

    private LocalDate accountCreationDate;

    private User() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public static class Builder {

        private final User user;

        public Builder() {
            user = new User();
        }

        public Builder withId(long id) {
            user.setId(id);
            return this;
        }

        public Builder withName(String name) {
            user.setName(name);
            return this;
        }

        public Builder withEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder withAccountCreationDate(LocalDate date) {
            user.setAccountCreationDate(date);
            return this;
        }

        public Builder asAdmin(boolean isAdmin) {
            user.setAdmin(isAdmin);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
