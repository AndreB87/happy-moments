package de.ansaru.happymoments.services;

import de.ansaru.happymoments.database.DatabaseServiceFactory;
import de.ansaru.happymoments.database.MomentDatabaseService;
import de.ansaru.happymoments.database.utils.EntityType;
import de.ansaru.happymoments.model.Moment;
import de.ansaru.happymoments.services.enums.UpdateMomentResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MomentService {

    private final MomentDatabaseService db;

    public MomentService() {
        db = (MomentDatabaseService) DatabaseServiceFactory.getService(EntityType.MOMENT);
    }

    public Optional<Moment> getMoment(long id) {
        return db.get(id);
    }

    public List<Moment> getMyMoments(long ownerId) {
        return db.getMomentsByOwner(ownerId);
    }

    public List<Moment> getSharedMoments(long userId) {
        return db.getMomentsByUser(userId);
    }

    public Optional<Moment> createMoment(String name, String description, LocalDate date, long ownerId) {
        Moment moment = new Moment.Builder()
                .withName(name)
                .withDescription(description)
                .withDate(date)
                .withCreationDate(LocalDate.now())
                .withOwner(ownerId)
                .build();

        return db.create(moment);
    }

    public UpdateMomentResult changeOwner(Moment moment, long oldOwnerId, long newOwnerId) {
        if (moment.getOwner() == oldOwnerId) {
            moment.setOwnerId(newOwnerId);
            Optional<Moment> updated = updateMoment(moment);

            if (updated.isPresent() && updated.get().getOwner() == newOwnerId) {
                return UpdateMomentResult.SUCCESS;
            } else {
                return UpdateMomentResult.UNKNOWN_ERROR;
            }
        } else {
            return UpdateMomentResult.NOT_OWNER;
        }
    }

    public UpdateMomentResult addUser(Moment moment, long requestingUser, long userId) {
        if (moment.getOwner() == requestingUser) {
            moment.getUserIds().add(userId);
            Optional<Moment> updated = updateMoment(moment);

            if (updated.isPresent() && updated.get().getUserIds().contains(userId)) {
                return UpdateMomentResult.SUCCESS;
            } else {
                return UpdateMomentResult.UNKNOWN_ERROR;
            }
        } else {
            return UpdateMomentResult.NOT_OWNER;
        }
    }

    public UpdateMomentResult removeUser(Moment moment, long requestingUserId, long userId) {
        if (moment.getOwner() == requestingUserId) {
            moment.getUserIds().remove(userId);
            Optional<Moment> updated = updateMoment(moment);

            if (updated.isPresent() && !updated.get().getUserIds().contains(userId)) {
                return UpdateMomentResult.SUCCESS;
            } else {
                return  UpdateMomentResult.UNKNOWN_ERROR;
            }
        } else {
            return UpdateMomentResult.NOT_OWNER;
        }
    }

    public UpdateMomentResult deleteMoment(Moment moment, long requestingUserId) {
        if (moment.getOwner() == requestingUserId) {
            if (db.delete(moment.getId())) {
                return UpdateMomentResult.SUCCESS;
            } else {
                return UpdateMomentResult.UNKNOWN_ERROR;
            }
        }
        return UpdateMomentResult.NOT_OWNER;
    }

    private Optional<Moment> updateMoment(Moment moment) {
        return db.update(moment);
    }
}
