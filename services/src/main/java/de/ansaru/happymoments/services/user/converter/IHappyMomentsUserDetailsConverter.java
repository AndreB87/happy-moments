package de.ansaru.happymoments.services.user.converter;

import de.ansaru.happymoments.database.user.entities.UserEntity;
import de.ansaru.happymoments.model.user.HappyMomentsUserDetails;
import de.ansaru.happymoments.services.converter.EntityModelConverter;

public interface IHappyMomentsUserDetailsConverter extends EntityModelConverter<UserEntity, HappyMomentsUserDetails> { }
