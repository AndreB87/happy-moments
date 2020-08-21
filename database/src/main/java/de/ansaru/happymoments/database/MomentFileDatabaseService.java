package de.ansaru.happymoments.database;

import de.ansaru.happymoments.database.daos.MomentFileDao;
import de.ansaru.happymoments.database.utils.MomentFileDaoModelConverter;
import de.ansaru.happymoments.model.MomentFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class MomentFileDatabaseService extends AbstractDatabaseService<MomentFile> {

    private final MomentFileDaoModelConverter converter = new MomentFileDaoModelConverter();

    public List<MomentFile> getFilesByMoment(long momentId) {
        List<MomentFile> resultList = new ArrayList<>();

        return entityManager.createNamedQuery("momentFile.findByMoment", MomentFileDao.class)
                .setParameter("momentId", momentId)
                .getResultList()
                .stream()
                .map(converter::fromEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

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
