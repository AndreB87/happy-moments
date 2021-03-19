package de.ansaru.happymoments.services.moments.converter;

import de.ansaru.happymoments.database.moments.entities.MomentFileEntity;
import de.ansaru.happymoments.model.moments.MomentFile;
import de.ansaru.happymoments.model.moments.utils.FileType;

public class MomentFileConverter implements IMomentFileConverter {

    @Override
    public MomentFile fromEntity(MomentFileEntity dao) {
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
    public MomentFileEntity toEntity(MomentFile model) {
        if (model == null) {
            return null;
        }

        MomentFileEntity dao = new MomentFileEntity();
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
