package mx.infotec.dads.kukulkan.web.rest;

import mx.infotec.dads.kukulkan.KukulkancraftsmanApp;

import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.service.DataStoreService;
import mx.infotec.dads.kukulkan.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import mx.infotec.dads.kukulkan.domain.enumeration.TableTypes;
/**
 * Test class for the DataStoreResource REST controller.
 *
 * @see DataStoreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KukulkancraftsmanApp.class)
public class DataStoreResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_DRIVER_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_DRIVER_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final TableTypes DEFAULT_TABLE_TYPES = TableTypes.TABLE;
    private static final TableTypes UPDATED_TABLE_TYPES = TableTypes.TABLE_VIEW;

    @Autowired
    private DataStoreRepository dataStoreRepository;

    @Autowired
    private DataStoreService dataStoreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDataStoreMockMvc;

    private DataStore dataStore;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DataStoreResource dataStoreResource = new DataStoreResource(dataStoreService);
        this.restDataStoreMockMvc = MockMvcBuilders.standaloneSetup(dataStoreResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataStore createEntity() {
        DataStore dataStore = new DataStore()
            .name(DEFAULT_NAME)
            .url(DEFAULT_URL)
            .driverClass(DEFAULT_DRIVER_CLASS)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .tableTypes(DEFAULT_TABLE_TYPES);
        return dataStore;
    }

    @Before
    public void initTest() {
        dataStoreRepository.deleteAll();
        dataStore = createEntity();
    }

    @Test
    public void createDataStore() throws Exception {
        int databaseSizeBeforeCreate = dataStoreRepository.findAll().size();

        // Create the DataStore
        restDataStoreMockMvc.perform(post("/api/data-stores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataStore)))
            .andExpect(status().isCreated());

        // Validate the DataStore in the database
        List<DataStore> dataStoreList = dataStoreRepository.findAll();
        assertThat(dataStoreList).hasSize(databaseSizeBeforeCreate + 1);
        DataStore testDataStore = dataStoreList.get(dataStoreList.size() - 1);
        assertThat(testDataStore.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDataStore.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testDataStore.getDriverClass()).isEqualTo(DEFAULT_DRIVER_CLASS);
        assertThat(testDataStore.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testDataStore.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testDataStore.getTableTypes()).isEqualTo(DEFAULT_TABLE_TYPES);
    }

    @Test
    public void createDataStoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataStoreRepository.findAll().size();

        // Create the DataStore with an existing ID
        dataStore.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataStoreMockMvc.perform(post("/api/data-stores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataStore)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DataStore> dataStoreList = dataStoreRepository.findAll();
        assertThat(dataStoreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkDriverClassIsRequired() throws Exception {
        int databaseSizeBeforeTest = dataStoreRepository.findAll().size();
        // set the field null
        dataStore.setDriverClass(null);

        // Create the DataStore, which fails.

        restDataStoreMockMvc.perform(post("/api/data-stores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataStore)))
            .andExpect(status().isBadRequest());

        List<DataStore> dataStoreList = dataStoreRepository.findAll();
        assertThat(dataStoreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDataStores() throws Exception {
        // Initialize the database
        dataStoreRepository.save(dataStore);

        // Get all the dataStoreList
        restDataStoreMockMvc.perform(get("/api/data-stores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataStore.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].driverClass").value(hasItem(DEFAULT_DRIVER_CLASS.toString())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].tableTypes").value(hasItem(DEFAULT_TABLE_TYPES.toString())));
    }

    @Test
    public void getDataStore() throws Exception {
        // Initialize the database
        dataStoreRepository.save(dataStore);

        // Get the dataStore
        restDataStoreMockMvc.perform(get("/api/data-stores/{id}", dataStore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dataStore.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.driverClass").value(DEFAULT_DRIVER_CLASS.toString()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.tableTypes").value(DEFAULT_TABLE_TYPES.toString()));
    }

    @Test
    public void getNonExistingDataStore() throws Exception {
        // Get the dataStore
        restDataStoreMockMvc.perform(get("/api/data-stores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDataStore() throws Exception {
        // Initialize the database
        dataStoreService.save(dataStore);

        int databaseSizeBeforeUpdate = dataStoreRepository.findAll().size();

        // Update the dataStore
        DataStore updatedDataStore = dataStoreRepository.findOne(dataStore.getId());
        updatedDataStore
            .name(UPDATED_NAME)
            .url(UPDATED_URL)
            .driverClass(UPDATED_DRIVER_CLASS)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .tableTypes(UPDATED_TABLE_TYPES);

        restDataStoreMockMvc.perform(put("/api/data-stores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDataStore)))
            .andExpect(status().isOk());

        // Validate the DataStore in the database
        List<DataStore> dataStoreList = dataStoreRepository.findAll();
        assertThat(dataStoreList).hasSize(databaseSizeBeforeUpdate);
        DataStore testDataStore = dataStoreList.get(dataStoreList.size() - 1);
        assertThat(testDataStore.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDataStore.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testDataStore.getDriverClass()).isEqualTo(UPDATED_DRIVER_CLASS);
        assertThat(testDataStore.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testDataStore.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testDataStore.getTableTypes()).isEqualTo(UPDATED_TABLE_TYPES);
    }

    @Test
    public void updateNonExistingDataStore() throws Exception {
        int databaseSizeBeforeUpdate = dataStoreRepository.findAll().size();

        // Create the DataStore

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDataStoreMockMvc.perform(put("/api/data-stores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dataStore)))
            .andExpect(status().isCreated());

        // Validate the DataStore in the database
        List<DataStore> dataStoreList = dataStoreRepository.findAll();
        assertThat(dataStoreList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDataStore() throws Exception {
        // Initialize the database
        dataStoreService.save(dataStore);

        int databaseSizeBeforeDelete = dataStoreRepository.findAll().size();

        // Get the dataStore
        restDataStoreMockMvc.perform(delete("/api/data-stores/{id}", dataStore.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DataStore> dataStoreList = dataStoreRepository.findAll();
        assertThat(dataStoreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataStore.class);
        DataStore dataStore1 = new DataStore();
        dataStore1.setId("id1");
        DataStore dataStore2 = new DataStore();
        dataStore2.setId(dataStore1.getId());
        assertThat(dataStore1).isEqualTo(dataStore2);
        dataStore2.setId("id2");
        assertThat(dataStore1).isNotEqualTo(dataStore2);
        dataStore1.setId(null);
        assertThat(dataStore1).isNotEqualTo(dataStore2);
    }
}
