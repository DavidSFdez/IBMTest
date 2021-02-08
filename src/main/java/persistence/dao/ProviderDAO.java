package main.java.persistence.dao;

import main.java.persistence.exception.IBMTestPersistenceException;
import main.java.persistence.model.Provider;

import java.util.List;

public interface ProviderDAO {

    /**
     * Gets the providers by the clientId
     * 
     * @param clientId
     * @return list of providers
     */
    List<Provider> getByClientId(Integer clientId) throws IBMTestPersistenceException;
}
