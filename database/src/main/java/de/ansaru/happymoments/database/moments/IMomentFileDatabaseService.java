package de.ansaru.happymoments.database.moments;

import de.ansaru.happymoments.database.IDatabaseService;
import de.ansaru.happymoments.database.moments.entities.MomentFileEntity;

import java.util.List;

public interface IMomentFileDatabaseService extends IDatabaseService<MomentFileEntity> {

    List<MomentFileEntity> getFilesByMoment(long momentId);

}
