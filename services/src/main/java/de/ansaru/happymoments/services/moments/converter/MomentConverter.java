package de.ansaru.happymoments.services.moments.converter;

import de.ansaru.happymoments.database.moments.entities.MomentEntity;
import de.ansaru.happymoments.model.moments.Moment;

public class MomentConverter implements IMomentConverter {

    @Override
    public Moment fromEntity(MomentEntity dao) {
        return dao != null ?
            new Moment.Builder()
                .withId(dao.getMomentId())
                .withName(dao.getName())
                .withOwner(dao.getOwner())
                .withDescription(dao.getDescription())
                .withDate(dao.getDate())
                .withCreationDate(dao.getCreationDate())
                .withUserIds(dao.getUserIds())
                .build() :
            null;
    }

    @Override
    public MomentEntity toEntity(Moment model) {
        if (model == null) {
            return null;
        }

        MomentEntity dao = new MomentEntity();
        dao.setMomentId(model.getId());
        dao.setName(model.getName());
        dao.setOwner(model.getOwner());
        dao.setDescription(model.getDescription());
        dao.setDate(model.getDate());
        dao.setCreationDate(model.getCreationDate());
        dao.setUserIds(model.getUserIds());

        return dao;
    }
}
