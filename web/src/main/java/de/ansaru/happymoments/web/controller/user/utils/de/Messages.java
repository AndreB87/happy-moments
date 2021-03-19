package de.ansaru.happymoments.web.controller.user.utils.de;

public enum Messages {

    DIDNT_WORK("Das hat leider nicht geklappt."),
    PASSWORD_CHANGED("Das Passwort wurde erfolgreich geändert"),
    SIGNUP_COMPLETE("Dein Konto wurde bestätigt \nBitte melde dich erneut an.");

    private final String text;

    Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
