package de.ansaru.happymoments.database.daos;

import javax.persistence.*;

@Entity
@Table(name = "otp")
public class OneTimePadDao implements IDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private UserDao user;

    private String otp;

    private boolean isActive;

    public OneTimePadDao() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
