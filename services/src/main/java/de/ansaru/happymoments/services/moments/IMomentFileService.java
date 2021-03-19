package de.ansaru.happymoments.services.moments;

import de.ansaru.happymoments.model.moments.Moment;
import de.ansaru.happymoments.model.moments.MomentFile;
import de.ansaru.happymoments.model.moments.utils.FileType;
import de.ansaru.happymoments.services.moments.enums.UpdateMomentFileResult;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMomentFileService {

    Optional<MomentFile> createFile(
            long momentId,
            String description,
            LocalDate date,
            File file,
            FileType fileType);

    Optional<MomentFile> getFile(long id);

    List<MomentFile> getFilesByMoment(long momentId);

    UpdateMomentFileResult updateFile(
            long id,
            long requestedUserId,
            Moment moment,
            String description,
            LocalDate date);

}
