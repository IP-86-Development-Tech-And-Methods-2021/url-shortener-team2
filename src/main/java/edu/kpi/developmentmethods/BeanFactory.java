package edu.kpi.developmentmethods;

import edu.kpi.developmentmethods.logic.Logic;
import edu.kpi.developmentmethods.storage.InMemoryStorageImpl;
import edu.kpi.developmentmethods.storage.Storage;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

/**
 * Factory for all injectable classes in our application.
 *
 * This class provides bean implementations for different
 * modules of our application
 * as well as provides us some useful constants
 */
@Factory
public class BeanFactory {
    private final String baseUrl = "http://localhost:8080";

    @Singleton
    Storage createStorage() {
        return new InMemoryStorageImpl();
    }

    @Singleton
    Logic createLogic(Storage storage) {
        return new Logic(storage);
    }
}
