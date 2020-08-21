package de.ansaru.happymoments.database.daos;

import javax.persistence.*;

@Entity
@Table(name = "otp", indexes = @Index(columnList = ("otp"), name = "otp_index", unique = true))
@NamedQueries({
    @NamedQuery(name = "otp.findAll", query = "SELECT o FROM OneTimePadDao o"),
    @NamedQuery(name = "otp.findById", query = "SELECT o FROM OneTimePadDao o WHERE o.id = :id"),
    @NamedQuery(name = "otp.findByOtp", query = "SELECT o FROM OneTimePadDao o WHERE o.otp = :otp")
})
public class OneTimePadDao implements IDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    private String otp;

    private boolean isActive;

    public OneTimePadDao() { }

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
}
