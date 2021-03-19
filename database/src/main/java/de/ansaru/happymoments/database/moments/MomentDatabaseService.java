package de.ansaru.happymoments.database.moments;

import de.ansaru.happymoments.database.AbstractDatabaseService;
import de.ansaru.happymoments.database.moments.constants.MomentQueries;
import de.ansaru.happymoments.database.moments.entities.MomentEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MomentDatabaseService extends AbstractDatabaseService<MomentEntity> implements IMomentDatabaseService {

    @Override
    public MomentEntity get(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getSingleResult(MomentQueries.FIND_BY_ID, MomentEntity.class, params);
    }

    @Override
    public List<MomentEntity> getMomentsByOwner(long ownerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("ownerId", ownerId);
        return getResultList(MomentQueries.FIND_BY_OWNER, params);
    }

    @Override
    public List<MomentEntity> getMomentsByUser(long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return getResultList(MomentQueries.FIND_BY_USER, params);
    }

    private List<MomentEntity> getResultList(String namedQuery, Map<String, Object> params) {
        return getResultList(namedQuery, MomentEntity.class, params);
    }
}
