package de.ansaru.happymoments.database.utils;

import de.ansaru.happymoments.database.daos.MomentDao;
import de.ansaru.happymoments.model.Moment;

public class MomentDaoModelConverter implements IDaoModelConverter<MomentDao, Moment> {

    @Override
    public Moment fromEntity(MomentDao dao) {
        return dao != null ?
            new Moment.Builder()
                .withId(dao.getMomentId())
                .withName(dao.getName())
                .withDescription(dao.getDescription())
                .withOwner(dao.getOwner())
                .withDate(dao.getDate())
                .withCreationDate(dao.getCreationDate())
                .withUserIds(dao.getUserIds())
                .build() :
            null;
    }

    @Override
    public MomentDao toEntity(Moment model) {
        if (model == null) {
            return null;
        }

        MomentDao dao = new MomentDao();
        dao.setMomentId(model.getId());
        dao.setOwner(model.getOwner());
        dao.setDescription(model.getDescription());
        dao.setOwner(model.getOwner());
        dao.setDate(model.getDate());
        dao.setCreationDate(model.getCreationDate());
        dao.setUserIds(model.getUserIds());

        return dao;
    }
}
