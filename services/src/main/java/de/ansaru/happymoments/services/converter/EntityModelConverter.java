package de.ansaru.happymoments.services.converter;

import de.ansaru.happymoments.database.entities.IEntity;
import de.ansaru.happymoments.model.IModel;

public interface EntityModelConverter<T extends IEntity, U extends IModel> {

    U fromEntity(T dao);

    T toEntity(U model);

}
