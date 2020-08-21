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
                .withOwner(dao.getOwner())
                .withDescription(dao.getDescription())
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
        dao.setName(model.getName());
        dao.setOwner(model.getOwner());
        dao.setDescription(model.getDescription());
        dao.setDate(model.getDate());
        dao.setCreationDate(model.getCreationDate());
        dao.setUserIds(model.getUserIds());

        return dao;
    }
}
