package de.ansaru.happymoments.web.controller.moments.utils.de;

public enum Messages {

    NO_PERMISSION("Du hast keinen Zugriff auf diesen Moment!"),
    NOT_AVAILABLE("Der Moment ist nicht verfügbar."),
    NOT_FOUND("Der Moment konnte nicht gefunden werden");

    private final String text;

    Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
