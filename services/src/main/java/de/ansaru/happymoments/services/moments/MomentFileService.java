package de.ansaru.happymoments.services.moments;

import de.ansaru.happymoments.database.moments.IMomentFileDatabaseService;
import de.ansaru.happymoments.database.moments.entities.MomentFileEntity;
import de.ansaru.happymoments.model.moments.Moment;
import de.ansaru.happymoments.model.moments.MomentFile;
import de.ansaru.happymoments.model.moments.utils.FileType;
import de.ansaru.happymoments.services.moments.converter.IMomentFileConverter;
import de.ansaru.happymoments.services.moments.enums.UpdateMomentFileResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MomentFileService implements IMomentFileService {

    @Autowired
    private IMomentFileDatabaseService dbService;

    @Autowired
    private IMomentFileConverter converter;

    public Optional<MomentFile> createFile(
            long momentId,
            String description,
            LocalDate date,
            File file,
            FileType fileType)
    {
        MomentFile momentFile = new MomentFile.Builder()
            .withMomentId(momentId)
            .withDescription(description)
            .withDate(date)
            .withFile(file)
            .withFileType(fileType)
            .build();

        return Optional.ofNullable(
            converter.fromEntity(
                dbService.create(converter.toEntity(momentFile))
            )
        );
    }

    public Optional<MomentFile> getFile(long id)
    {
        return Optional.ofNullable(converter.fromEntity(dbService.get(id)));
    }

    public List<MomentFile> getFilesByMoment(long momentId)
    {
        List<MomentFile> resultList = new ArrayList<>();
        dbService.getFilesByMoment(momentId).forEach(m -> resultList.add(converter.fromEntity(m)));
        return resultList;
    }

    public UpdateMomentFileResult updateFile(
        long id,
        long requestedUserId,
        Moment moment,
        String description,
        LocalDate date)
    {
        MomentFileEntity entity = dbService.get(id);

        if (entity != null && entity.getMomentId() == moment.getId()) {
            if (entity.getUserId() == requestedUserId || moment.getOwner() == requestedUserId) {
                entity.setDescription(description);
                entity.setDate(date);
                MomentFileEntity updatedEntity = dbService.update(entity);
                if (updatedEntity != null) {
                    return UpdateMomentFileResult.SUCCESS;
                }
            } else {
                return UpdateMomentFileResult.NOT_OWNER;
            }
        }
        return UpdateMomentFileResult.UNKNOWN_ERROR;
    }

    public void setDbService(IMomentFileDatabaseService dbService) {
        this.dbService = dbService;
    }
}
