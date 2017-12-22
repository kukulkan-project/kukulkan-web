/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.KukulkanConfigurationProperties;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataContextContainer;
import mx.infotec.dads.kukulkan.engine.domain.core.DataContextType;
import mx.infotec.dads.kukulkan.engine.grammar.KukulkanVisitor;
import mx.infotec.dads.kukulkan.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.service.DataStoreService;
import mx.infotec.dads.kukulkan.util.DataStoreConstants;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * DataStoreServiceImpl
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service
public class DataStoreServiceImpl implements DataStoreService {

    private final Logger log = LoggerFactory.getLogger(DataStoreServiceImpl.class);

    @Autowired
    private KukulkanConfigurationProperties prop;

    private static final String DATA_STORE_TYPE = "type";
    private static final String DATA_STORE_URL = "url";
    private static final String DATA_STORE_DRIVER_CLASS = "driver-class";
    private static final String DATA_STORE_USERNAME = "username";
    private static final String DATA_STORE_PASSWORD = "password";

    private final DataStoreRepository dataStoreRepository;

    public DataStoreServiceImpl(DataStoreRepository dataStoreRepository) {
        this.dataStoreRepository = dataStoreRepository;
    }

    /**
     * Save a dataStore.
     *
     * @param dataStore
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public DataStore save(DataStore dataStore) {
        log.debug("Request to save DataStore : {}", dataStore);
        return dataStoreRepository.save(dataStore);
    }

    /**
     * Get all the dataStores.
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    public Page<DataStore> findAll(Pageable pageable) {
        log.debug("Request to get all DataStores");
        return dataStoreRepository.findAll(pageable);
    }

    /**
     * Get one dataStore by id.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    @Override
    public DataStore findOne(String id) {
        log.debug("Request to get DataStore : {}", id);
        return dataStoreRepository.findOne(id);
    }

    /**
     * Delete the dataStore by id.
     *
     * @param id
     *            the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DataStore : {}", id);
        dataStoreRepository.delete(id);
    }

    @Override
    public DataContextContainer<?> createDataContext(DataStore dataStore) {
        if (dataStore.getDataStoreType().getName().equals(DataStoreConstants.DATA_STORE_TYPE_JDBC)) {
            DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
            properties.put(DATA_STORE_TYPE, dataStore.getDataStoreType().getName());
            properties.put(DATA_STORE_URL, dataStore.getUrl() + "/" + dataStore.getSchema());
            properties.put(DATA_STORE_DRIVER_CLASS, dataStore.getDriverClass());
            properties.put(DATA_STORE_USERNAME, dataStore.getUsername());
            properties.put(DATA_STORE_PASSWORD, dataStore.getPassword());
            return new DataContextContainer<>(DataContextType.RELATIONAL_DATA_BASE,
                    DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties));
        } else if (dataStore.getDataStoreType().getName().equals(DataStoreConstants.DATA_STORE_TYPE_CSV)) {
            DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
            properties.put(DATA_STORE_TYPE, dataStore.getDataStoreType().getName());
            properties.put(DATA_STORE_URL, dataStore.getUrl());
            return new DataContextContainer<>(DataContextType.RELATIONAL_DATA_BASE,
                    DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties));
        } else if (dataStore.getDataStoreType().getName().equals(DataStoreConstants.DATA_STORE_TYPE_GRAMMAR)) {
            return new DataContextContainer<>(DataContextType.KUKULKAN_GRAMMAR,
                    new KukulkanVisitor());
        } else {
            return null;
        }
    }

    @Override
    public DataStore getDataStore(String id) {
        return dataStoreRepository.findOne(id);
    }

    @Override
    public List<DataStore> findAll() {
        return dataStoreRepository.findAll();
    }

    @Override
    public Page<DataStore> findAllByPage(Pageable pagable) {
        return dataStoreRepository.findAll(pagable);
    }

    @Override
    public DataStore findById(String id) {
        return dataStoreRepository.findOne(id);
    }

    @Override
    public boolean exists(String id) {
        return dataStoreRepository.exists(id);
    }

    @Override
    public void deleteAll() {
        dataStoreRepository.deleteAll();
    }

    @Override
    public boolean testConnection(DataStore dataStore) {
        Properties connectionProps = new Properties();
        connectionProps.put("user", dataStore.getUsername());
        connectionProps.put("password", dataStore.getPassword() == null ? "" : dataStore.getPassword());
        try (Connection connection = DriverManager.getConnection(dataStore.getUrl() + "/" + dataStore.getSchema(),
                connectionProps)) {
            return true;
        } catch (SQLException e) {
            throw new ApplicationException("testConnection Error");
        }
    }

    @Override
    public boolean runScript(DataStore dataStore) {
        log.info("dataStore.user: " + dataStore.getUsername());
        log.info("dataStore.password: " + dataStore.getPassword());
        log.info("dataStore.script: " + dataStore.getScript());
        Properties connectionProps = new Properties();
        connectionProps.put("user", dataStore.getUsername());
        connectionProps.put("password", dataStore.getPassword() == null ? "" : dataStore.getPassword());
        try (Connection connection = DriverManager.getConnection(dataStore.getUrl() + "/" + dataStore.getSchema(),
                connectionProps)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(dataStore.getScript());
            return true;
        } catch (SQLException e) {
            throw new ApplicationException("Run Script Error", e);
        }
    }

    @Override
    public boolean createSchema(DataStore dataStore) {
        log.info("dataStore.user: " + dataStore.getUsername());
        log.info("dataStore.password: " + dataStore.getPassword());
        Properties connectionProps = new Properties();
        connectionProps.put("user", dataStore.getUsername());
        connectionProps.put("password", dataStore.getPassword() == null ? "" : dataStore.getPassword());
        try (Connection connection = DriverManager.getConnection(dataStore.getUrl(), connectionProps)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + dataStore.getSchema() + ";");
            return true;
        } catch (SQLException e) {
            log.debug("createSchema", e);
            return false;
        }
    }

    @Override
    public boolean dropSchema(DataStore dataStore) {
        log.info("dataStore.user: " + dataStore.getUsername());
        log.info("dataStore.password: " + dataStore.getPassword());
        Properties connectionProps = new Properties();
        connectionProps.put("user", dataStore.getUsername());
        connectionProps.put("password", dataStore.getPassword() == null ? "" : dataStore.getPassword());
        try (Connection connection = DriverManager.getConnection(dataStore.getUrl(), connectionProps)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE " + dataStore.getSchema() + ";");
            return true;
        } catch (SQLException e) {
            log.error("dropSchema", e);
            return false;
        }
    }
}
