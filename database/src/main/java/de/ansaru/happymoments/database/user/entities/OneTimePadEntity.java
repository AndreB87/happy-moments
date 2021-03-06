package de.ansaru.happymoments.database.user.entities;

import de.ansaru.happymoments.database.entities.IEntity;
import de.ansaru.happymoments.database.user.constants.OneTimePadQueries;

import javax.persistence.*;

@Entity
@Table(name = "otp", indexes = @Index(columnList = ("otp"), name = "otp_index", unique = true))
@NamedQueries({
    @NamedQuery(
        name = OneTimePadQueries.FIND_ALL,
        query = "SELECT o FROM OneTimePadEntity o"
    ),
    @NamedQuery(
        name = OneTimePadQueries.FIND_BY_ID,
        query = "SELECT o FROM OneTimePadEntity o WHERE o.id = :id"
    ),
    @NamedQuery(
        name = OneTimePadQueries.FIND_BY_OTP,
        query = "SELECT o FROM OneTimePadEntity o WHERE o.otp = :otp"
    )
})
public class OneTimePadEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    private String otp;

    private boolean isActive;

    public OneTimePadEntity() { }

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
