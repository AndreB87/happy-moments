package de.ansaru.happymoments.web.controller.user.utils.de;

public enum Error {

    EMAIL_USED("Die E-Mail Adresse ist bereits registriert"),
    UNKNOWN_EMAIL("Unbekannte E-Mail Adresse"),
    UNKNOWN_ERROR("Ein unbekannter Fehler ist aufgetreten"),
    WRONG_PASSWORD("Falsches Passwort");

    private String text;

    Error(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
