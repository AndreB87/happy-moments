package de.ansaru.happymoments.services;

import de.ansaru.happymoments.database.DatabaseServiceFactory;
import de.ansaru.happymoments.database.MomentFileDatabaseService;
import de.ansaru.happymoments.database.utils.EntityType;
import de.ansaru.happymoments.model.Moment;
import de.ansaru.happymoments.model.MomentFile;
import de.ansaru.happymoments.model.utils.FileType;
import de.ansaru.happymoments.services.enums.UpdateMomentFileResult;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MomentFileService {

    private final MomentFileDatabaseService db;

    public MomentFileService() {
        db = (MomentFileDatabaseService) DatabaseServiceFactory.getService(EntityType.MOMENT_FILE);
    }

    public Optional<MomentFile> createFile(long momentId, String description, LocalDate date, File file, FileType fileType) {
        MomentFile momentFile = new MomentFile.Builder()
                .withMomentId(momentId)
                .withDescription(description)
                .withDate(date)
                .withFile(file)
                .withFileType(fileType)
                .build();

        return db.create(momentFile);
    }

    public Optional<MomentFile> getFile(long id) {
        return db.get(id);
    }

    public List<MomentFile> getFilesByMoment(long momentId) {
        return db.getFilesByMoment(momentId);
    }

    public UpdateMomentFileResult updateFile(long id, long requestedUserId, Moment moment , String description, LocalDate date) {
        Optional<MomentFile> optionalFile = db.get(id);

        if (optionalFile.isPresent() && optionalFile.get().getMomentId() == moment.getId()) {
            MomentFile file = optionalFile.get();

            if (file.getUserId() == requestedUserId || moment.getOwner() == requestedUserId) {
                file.setDescription(description);
                file.setDate(date);

                if (update(file)) {
                    return UpdateMomentFileResult.SUCCESS;
                }
            } else {
                return UpdateMomentFileResult.NOT_OWNER;
            }
        }
        return UpdateMomentFileResult.UNKNOWN_ERROR;
    }

    private boolean update(MomentFile moment) {
        Optional<MomentFile> updated = db.update(moment);
        return updated.isPresent() &&
                updated.get().getDescription().equals(moment.getDescription()) &&
                updated.get().getDate().equals(moment.getDate()) &&
                updated.get().getUserId() == moment.getUserId();
    }

}
