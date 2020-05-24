package de.ansaru.happymoments.database.utils;

import de.ansaru.happymoments.database.daos.IDao;
import de.ansaru.happymoments.model.IModel;

public interface IDaoModelConverter<T extends IDao, U extends IModel> {

    U fromEntity(T dao);

    T toEntity(U model);

}
