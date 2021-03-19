package de.ansaru.happymoments.database.moments.entities;

import de.ansaru.happymoments.database.entities.IEntity;
import de.ansaru.happymoments.database.moments.constants.MomentFileQueries;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;

@Entity
@Table(name = "files")
@NamedQueries({
    @NamedQuery(
        name = MomentFileQueries.FIND_ALL,
        query = "SELECT f FROM MomentFileEntity f"
    ),
    @NamedQuery(
        name = MomentFileQueries.FIND_BY_ID,
        query = "SELECT f FROM MomentFileEntity f WHERE f.fileId = :id"
    ),
    @NamedQuery(
        name = MomentFileQueries.FIND_BY_MOMENT,
        query = "SELECT f FROM MomentFileEntity f WHERE f.momentId = :momentId"
    )
})
public class MomentFileEntity implements IEntity {

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

    public MomentFileEntity() { }

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
