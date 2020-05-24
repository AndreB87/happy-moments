package de.ansaru.happymoments.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Moment implements IModel {

    private long id;

    private String name;

    private String description;

    private LocalDate date;

    private LocalDate creationDate;

    private long ownerId;

    private List<Long> userIds;

    private List<Long> fileIds;

    @Deprecated
    private Moment() {
        userIds = new ArrayList<>();
        fileIds = new ArrayList<>();
        creationDate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public static class Builder {

        private Moment moment;

        public Builder() {
            moment = new Moment();
        }

        public Builder withId(long id) {
            moment.setId(id);
            return this;
        }

        public Builder withName(String name) {
            moment.setName(name);
            return this;
        }

        public Builder withDescription(String description) {
            moment.setDescription(description);
            return this;
        }

        public Builder withDate(LocalDate date) {
            moment.setDate(date);
            return this;
        }

        public Builder withCreationDate(LocalDate creationDate) {
            moment.setCreationDate(creationDate);
            return this;
        }

        public Builder withOwner(long ownerId) {
            moment.setOwnerId(ownerId);
            return this;
        }

        public Builder withUserIds(List<Long> userIds) {
            moment.setUserIds(userIds);
            return this;
        }

        public Builder withUserId(long userId) {
            moment.getUserIds().add(userId);
            return this;
        }

        public Moment build() {
            return moment;
        }
    }
}
