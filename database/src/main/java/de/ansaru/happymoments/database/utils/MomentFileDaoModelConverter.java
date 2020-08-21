package de.ansaru.happymoments.database.utils;

import de.ansaru.happymoments.database.daos.MomentFileDao;
import de.ansaru.happymoments.model.MomentFile;
import de.ansaru.happymoments.model.utils.FileType;

public class MomentFileDaoModelConverter implements IDaoModelConverter<MomentFileDao, MomentFile> {

    @Override
    public MomentFile fromEntity(MomentFileDao dao) {
        return dao != null ?
            new MomentFile.Builder()
                .withId(dao.getFileId())
                .withMomentId(dao.getMomentId())
                .withUserId(dao.getUserId())
                .withFile(dao.getFile())
                .withFileType(FileType.valueOf(dao.getFileType()))
                .withDescription(dao.getDescription())
                .withCreationDate(dao.getCreationDate())
                .withDate(dao.getDate())
                .build() :
           null;
    }

    @Override
    public MomentFileDao toEntity(MomentFile model) {
        if (model == null) {
            return null;
        }

        MomentFileDao dao = new MomentFileDao();
        dao.setFileId(model.getId());
        dao.setMomentId(model.getMomentId());
        dao.setUserId(model.getUserId());
        dao.setFile(model.getFile());
        dao.setFileType(model.getFileType().toString());
        dao.setDescription(model.getDescription());
        dao.setCreationDate(model.getCreationDate());
        dao.setDate(model.getDate());

        return dao;
    }

}
