package de.ansaru.happymoments.database.utils;

import de.ansaru.happymoments.database.daos.OneTimePadDao;
import de.ansaru.happymoments.model.OneTimePad;

public class OneTimePadDaoModelConverter implements IDaoModelConverter<OneTimePadDao, OneTimePad> {

    @Override
    public OneTimePad fromEntity(OneTimePadDao dao) {
        return dao != null ?
            new OneTimePad.Builder()
                .withId(dao.getId())
                .withEmail(dao.getEmail())
                .withOtp(dao.getOtp())
                .asActive(dao.isActive())
                .build() :
            null;
    }

    @Override
    public OneTimePadDao toEntity(OneTimePad model) {
        if (model == null) {
            return null;
        }

        OneTimePadDao dao = new OneTimePadDao();
        dao.setEmail(model.getEmail());
        dao.setOtp(model.getOtp());
        dao.setActive(model.isActive());

        return dao;
    }

}
