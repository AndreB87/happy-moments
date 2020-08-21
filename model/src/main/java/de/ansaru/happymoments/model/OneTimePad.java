package de.ansaru.happymoments.model;

public class OneTimePad implements IModel {

    private long id;

    private String email;

    private String otp;

    private boolean isActive;

    private OneTimePad() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static class Builder {

        private OneTimePad otp;

        public Builder() {
            otp = new OneTimePad();
        }

        public Builder withId(long id) {
            otp.setId(id);
            return this;
        }

        public Builder withEmail(String email) {
            otp.setEmail(email);
            return this;
        }

        public Builder withOtp(String otp) {
            this.otp.setOtp(otp);
            return this;
        }

        public Builder asActive(boolean isActive) {
            otp.setActive(isActive);
            return this;
        }

        public OneTimePad build() {
            return otp;
        }
    }
}
