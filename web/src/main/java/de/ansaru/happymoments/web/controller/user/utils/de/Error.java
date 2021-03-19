package de.ansaru.happymoments.web.controller.user.utils.de;

public enum Error {

    CONF_FAILED("Die angegebenen Passwörter stimmen nicht überein."),
    EMAIL_USED("Die E-Mail Adresse ist bereits registriert."),
    MISSING_NAME("Der Name muss angegeben werden."),
    UNKNOWN_EMAIL("Unbekannte E-Mail Adresse."),
    UNKNOWN_ERROR("Ein unbekannter Fehler ist aufgetreten."),
    USER_NOT_FOUND("Der Benutzer wurde nicht gefunden."),
    WRONG_PASSWORD("Falsches Passwort.");

    private final String text;

    Error(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
