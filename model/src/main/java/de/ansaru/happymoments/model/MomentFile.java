package de.ansaru.happymoments.model;

import de.ansaru.happymoments.model.utils.FileType;

import java.io.File;
import java.time.LocalDate;

public class MomentFile implements IModel {

    private long id;

    private String description;

    private File file;

    private FileType fileType;

    private LocalDate date;

    private LocalDate creationDate;

    private long userId;

    private long momentId;

    private MomentFile() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
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

    public static class Builder {

        private MomentFile file;

        public Builder() {
            file = new MomentFile();
        }

        public Builder withId(long id) {
            file.setId(id);
            return this;
        }

        public Builder withDescription(String description) {
            file.setDescription(description);
            return this;
        }

        public Builder withFile(File file) {
            this.file.setFile(file);
            return this;
        }

        public Builder withFileType(FileType fileType) {
            file.setFileType(fileType);
            return this;
        }

        public Builder withDate(LocalDate date) {
            file.setDate(date);
            return this;
        }

        public Builder withCreationDate(LocalDate creationDate) {
            file.setCreationDate(creationDate);
            return this;
        }

        public Builder withUserId(long userId) {
            file.setUserId(userId);
            return this;
        }

        public Builder withMomentId(long momentId) {
            file.setMomentId(momentId);
            return this;
        }

        public MomentFile build() {
            return file;
        }

    }
}
