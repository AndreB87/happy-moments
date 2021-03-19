package de.ansaru.happymoments.database.moments;

import de.ansaru.happymoments.database.IDatabaseService;
import de.ansaru.happymoments.database.moments.entities.MomentEntity;

import java.util.List;

public interface IMomentDatabaseService extends IDatabaseService<MomentEntity> {

    List<MomentEntity> getMomentsByOwner(long ownerId);

    public List<MomentEntity> getMomentsByUser(long userId);
}
