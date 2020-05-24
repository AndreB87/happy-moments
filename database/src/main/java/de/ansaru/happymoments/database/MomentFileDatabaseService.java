package de.ansaru.happymoments.database;

import de.ansaru.happymoments.model.MomentFile;

import java.util.Optional;

public class MomentFileDatabaseService extends AbstractDatabaseService<MomentFile> {

    @Override
    public Optional<MomentFile> get(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<MomentFile> create(MomentFile entity) {
        return Optional.empty();
    }

    @Override
    public Optional<MomentFile> update(MomentFile entity) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
