package mx.infotec.dads.kukulkan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataContextContainer;

/**
 * Service Interface for managing DataStore.
 */
public interface DataStoreService {

    /**
     * Save a dataStore.
     *
     * @param dataStore
     *            the entity to save
     * @return the persisted entity
     */
    DataStore save(DataStore dataStore);

    /**
     * Get all the dataStores.
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    Page<DataStore> findAll(Pageable pageable);

    /**
     * Get the "id" dataStore.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    DataStore findOne(String id);

    /**
     * Delete the "id" dataStore.
     *
     * @param id
     *            the id of the entity
     */
    void delete(String id);

    /**
     * Create DataContext from a DataStore
     * 
     * @param dataStore
     * @return DataContext
     */
    public DataContextContainer<?> createDataContext(DataStore dataStore);

    /**
     * Get a DataStore
     * 
     * @param id
     *            of the data store
     * @return DataContext
     */
    public DataStore getDataStore(String id);

    /**
     * regresa una lista con todos los elementos DataStore
     * 
     * @return List<DataStore>
     */
    List<DataStore> findAll();

    /**
     * regresa una lista de elementos DataStore por página
     * 
     * @param pagable
     * @return
     */
    Page<DataStore> findAllByPage(Pageable pagable);

    /**
     * Consulta un DataStore por su llave primaria
     * 
     * @param id
     * @return DataStore
     */
    DataStore findById(String id);

    /**
     * Regresa true o false si existe un DataStore almacenado
     * 
     * @param id
     * @return boolean
     */
    boolean exists(String id);

    /**
     * Borrar todos los elementos DataStore almacenados
     * 
     * @param id
     */
    void deleteAll();

    /**
     * Test a Connection
     * 
     * @param dataStore
     * @return boolean
     */
    boolean testConnection(DataStore dataStore);

    /**
     * Run a Script
     * 
     * @param dataStore
     * @return boolean
     */
    boolean runScript(DataStore dataStore);

    /**
     * Create a schema into the database;
     * 
     * @param dataStore
     * @return
     */
    boolean createSchema(DataStore dataStore);

    /**
     * Drop schema
     * 
     * @param dataStore
     * @return
     */
    boolean dropSchema(DataStore dataStore);
}
