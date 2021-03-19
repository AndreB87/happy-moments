package de.ansaru.happymoments.database.moments;

import de.ansaru.happymoments.database.AbstractDatabaseService;
import de.ansaru.happymoments.database.moments.constants.MomentFileQueries;
import de.ansaru.happymoments.database.moments.entities.MomentFileEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MomentFileDatabaseService extends AbstractDatabaseService<MomentFileEntity> implements IMomentFileDatabaseService {

    public List<MomentFileEntity> getFilesByMoment(long momentId) {
        Map<String, Object> params = new HashMap<>();
        params.put("momentId", momentId);
        return getResultList(MomentFileQueries.FIND_BY_MOMENT, MomentFileEntity.class, params);
    }

    @Override
    public MomentFileEntity get(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getSingleResult(MomentFileQueries.FIND_BY_ID, MomentFileEntity.class, params);
    }
}
