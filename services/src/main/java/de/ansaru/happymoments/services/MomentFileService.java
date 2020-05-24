package de.ansaru.happymoments.services;

import de.ansaru.happymoments.database.DatabaseServiceFactory;
import de.ansaru.happymoments.database.MomentFileDatabaseService;
import de.ansaru.happymoments.database.utils.EntityType;
import de.ansaru.happymoments.model.MomentFile;

import java.util.Optional;

public class MomentFileService {

    private MomentFileDatabaseService db;

    public MomentFileService() {
        db = (MomentFileDatabaseService) DatabaseServiceFactory.getService(EntityType.MOMENT_FILE);
    }

    public MomentFile getFile(int id) {
        return db.get(id).orElse(null);
    }

    public MomentFile updateFile(int id, String description) {
        Optional<MomentFile> optionalFile = db.get(id);
        MomentFile defaultFile = new MomentFile.Builder()
                .withDescription(description)
                .build();

        if (optionalFile.isPresent()) {
            MomentFile file = optionalFile.get();
            file.setDescription(description);
            return db.update(file).orElse(defaultFile);
        } else {
            return defaultFile;
        }
    }

}
