package de.ansaru.happymoments.services.moments;

import de.ansaru.happymoments.model.moments.Moment;
import de.ansaru.happymoments.services.moments.enums.UpdateMomentResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMomentService {

    Optional<Moment> getMoment(long id);

    List<Moment> getMyMoments(long ownerId);

    List<Moment> getSharedMoments(long userId);

    Optional<Moment> createMoment(String name, String description, LocalDate date, long ownerId);

    UpdateMomentResult changeOwner(long momentId, long oldOwnerId, long newOwnerId);

    UpdateMomentResult addUser(long momentId, long requestingUser, long userId);

    UpdateMomentResult removeUser(long momentId, long requestingUserId, long userId);

    UpdateMomentResult deleteMoment(long momentId, long requestingUserId);

}
