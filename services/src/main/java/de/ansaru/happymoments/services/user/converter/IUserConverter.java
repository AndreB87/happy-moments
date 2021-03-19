package de.ansaru.happymoments.services.user.converter;

import de.ansaru.happymoments.database.user.entities.UserEntity;
import de.ansaru.happymoments.model.user.User;
import de.ansaru.happymoments.services.converter.EntityModelConverter;

public interface IUserConverter extends EntityModelConverter<UserEntity, User> { }
