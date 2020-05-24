package de.ansaru.happymoments.database.daos;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "moments")
@NamedQueries({
    @NamedQuery(name = "moments.findAll", query = "SELECT m FROM MomentDao m"),
    @NamedQuery(name = "moments.findMomentById", query = "SELECT m FROM MomentDao m WHERE m.momentId = :id"),
    @NamedQuery(name = "moments.findMomentsByOwner", query = "SELECT m FROM MomentDao m WHERE m.ownerId = :ownerId"),
    @NamedQuery(name = "moments.findMomentsByUser", query = "SELECT m FROM MomentDao m JOIN m.users u WHERE u = :userId")
})
public class MomentDao implements IDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long momentId;

    private String name;

    private String description;

    private LocalDate date;

    private LocalDate creationDate;

    private long ownerId;

    @ElementCollection
    private List<Long> users;

    public MomentDao() { }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public long getOwner() {
        return ownerId;
    }

    public void setOwner(long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Long> getUserIds() {
        return users;
    }

    public void setUserIds(List<Long> users) {
        this.users = users;
    }

}
