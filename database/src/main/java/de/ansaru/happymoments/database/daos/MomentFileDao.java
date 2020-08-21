package de.ansaru.happymoments.database.daos;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;

@Entity
@Table(name = "files")
@NamedQueries({
    @NamedQuery(name = "momentFile.findAll", query = "SELECT f FROM MomentFileDao f"),
    @NamedQuery(name = "momentFile.findById", query = "SELECT f FROM MomentFileDao f WHERE f.fileId = :id"),
    @NamedQuery(name = "momentFile.findByMoment", query = "SELECT f FROM MomentFileDao f WHERE f.momentId = :momentId")
})
public class MomentFileDao implements IDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fileId;

    private String description;

    private String fileType;

    private File file;

    private LocalDate date;

    private LocalDate creationDate;

    private long userId;

    private long momentId;

    public MomentFileDao() { }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }
}
