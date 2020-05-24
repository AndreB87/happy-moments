package de.ansaru.happymoments.services;

import de.ansaru.happymoments.database.DatabaseServiceFactory;
import de.ansaru.happymoments.database.MomentDatabaseService;
import de.ansaru.happymoments.database.utils.EntityType;
import de.ansaru.happymoments.model.Moment;
import de.ansaru.happymoments.model.MomentFile;
import de.ansaru.happymoments.model.User;

import java.time.LocalDate;
import java.util.Optional;

public class MomentService {

    private MomentDatabaseService db;

    public MomentService() {
        db = (MomentDatabaseService) DatabaseServiceFactory.getService(EntityType.MOMENT);
    }

    public Moment getMoment(int id) {
        return db.get(id).orElse(null);
    }

    public Moment createMoment(String name, String description, LocalDate date, long ownerId) {
        Moment moment = new Moment.Builder()
                .withName(name)
                .withDescription(description)
                .withDate(date)
                .withOwner(ownerId)
                .build();

        return db.create(moment).orElse(moment);
    }

    public Moment updateMoment(Moment moment) {
        return db.update(moment).orElse(null);
    }

    public Moment changeOwner(Moment moment, long newOwnerId) {
        moment.setOwnerId(newOwnerId);
        return db.update(moment).orElse(null);
    }

    public Moment addFile(Moment moment, long fileId) {
        moment.getFileIds().add(fileId);
        return db.update(moment).orElse(null);
    }

    public Moment removeFile(Moment moment, long userId, long fileId) {
        if (moment.getOwner() == userId) {
            moment.getFileIds().remove(fileId);
            return db.update(moment).orElse(null);
        }
        return moment;
    }

    public Moment addUser(Moment moment, long requestingUser, long userId) {
        if (moment.getOwner() == requestingUser) {
            moment.getUserIds().add(userId);
            return db.update(moment).orElse(null);
        } else {
            return moment;
        }
    }

    public Moment removeUser(Moment moment, long requestingUserId, long userId) {
        if (moment.getOwner() == requestingUserId) {
            moment.getUserIds().remove(userId);
            return db.update(moment).orElse(null);
        } else {
            return moment;
        }
    }

    public boolean deleteMoment(Moment moment, long requestingUserId) {
        if (moment.getOwner() == requestingUserId) {
            return db.delete(moment.getId());
        }
        return false;
    }

}
