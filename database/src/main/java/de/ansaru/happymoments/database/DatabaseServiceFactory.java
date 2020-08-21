package de.ansaru.happymoments.database;

import de.ansaru.happymoments.database.utils.EntityType;
import de.ansaru.happymoments.model.IModel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DatabaseServiceFactory {

    public static AbstractDatabaseService<? extends IModel> getService(EntityType entityType) {
        switch (entityType) {
            case AUTHENTICATION:
                return new AuthenticationDatabaseService();
            case MOMENT:
                return new MomentDatabaseService();
            case MOMENT_FILE:
                return new MomentFileDatabaseService();
            case OTP:
                return new OneTimePadDatabaseService();
            case USER:
                return new UserDatabaseService();
            default:
                throw new NotImplementedException();
        }
    }

}
