package de.ansaru.happymoments.services.moments;

import de.ansaru.happymoments.database.moments.IMomentDatabaseService;
import de.ansaru.happymoments.database.moments.entities.MomentEntity;
import de.ansaru.happymoments.model.moments.Moment;
import de.ansaru.happymoments.services.moments.converter.IMomentConverter;
import de.ansaru.happymoments.services.moments.enums.UpdateMomentResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MomentService implements IMomentService {

    @Autowired
    private IMomentDatabaseService dbService;

    @Autowired
    private IMomentConverter converter;

    public Optional<Moment> getMoment(long id) {
        return Optional.ofNullable(converter.fromEntity(dbService.get(id)));
    }

    public List<Moment> getMyMoments(long ownerId) {
        List<Moment> resultList = new ArrayList<>();
        dbService.getMomentsByOwner(ownerId).forEach(m -> resultList.add(converter.fromEntity(m)));
        return resultList;
    }

    public List<Moment> getSharedMoments(long userId) {
        List<Moment> resultList = new ArrayList<>();
        dbService.getMomentsByUser(userId).forEach(m -> resultList.add(converter.fromEntity(m)));
        return resultList;
    }

    public Optional<Moment> createMoment(String name, String description, LocalDate date, long ownerId) {
        Moment moment = new Moment.Builder()
            .withName(name)
            .withDescription(description)
            .withDate(date)
            .withCreationDate(LocalDate.now())
            .withOwner(ownerId)
            .build();

        return Optional.ofNullable(
            converter.fromEntity(
                dbService.create(converter.toEntity(moment))
            )
        );
    }

    public UpdateMomentResult changeOwner(long momentId, long oldOwnerId, long newOwnerId) {
        MomentEntity moment = dbService.get(momentId);
        if (moment.getOwner() == oldOwnerId) {
            moment.setOwner(newOwnerId);
            MomentEntity updated = dbService.update(moment);
            if (updated != null && updated.getOwner() == newOwnerId) {
                return UpdateMomentResult.SUCCESS;
            } else {
                return UpdateMomentResult.UNKNOWN_ERROR;
            }
        } else {
            return UpdateMomentResult.NOT_OWNER;
        }
    }

    public UpdateMomentResult addUser(long momentId, long requestingUser, long userId) {
        MomentEntity moment = dbService.get(momentId);
        if (moment.getOwner() == requestingUser) {
            moment.getUserIds().add(userId);
            MomentEntity updated = dbService.update(moment);

            if (updated != null && updated.getUserIds().contains(userId)) {
                return UpdateMomentResult.SUCCESS;
            } else {
                return UpdateMomentResult.UNKNOWN_ERROR;
            }
        } else {
            return UpdateMomentResult.NOT_OWNER;
        }
    }

    public UpdateMomentResult removeUser(long momentId, long requestingUserId, long userId) {
        MomentEntity moment = dbService.get(momentId);
        if (moment.getOwner() == requestingUserId) {
            moment.getUserIds().remove(userId);
            MomentEntity updated = dbService.update(moment);

            if (updated != null && !updated.getUserIds().contains(userId)) {
                return UpdateMomentResult.SUCCESS;
            } else {
                return  UpdateMomentResult.UNKNOWN_ERROR;
            }
        } else {
            return UpdateMomentResult.NOT_OWNER;
        }
    }

    public UpdateMomentResult deleteMoment(long momentId, long requestingUserId) {
        MomentEntity moment = dbService.get(momentId);
        if (moment.getOwner() == requestingUserId) {
            if (dbService.delete(moment)) {
                return UpdateMomentResult.SUCCESS;
            } else {
                return UpdateMomentResult.UNKNOWN_ERROR;
            }
        }
        return UpdateMomentResult.NOT_OWNER;
    }

    public void setDbService(IMomentDatabaseService dbService) {
        this.dbService = dbService;
    }
}
