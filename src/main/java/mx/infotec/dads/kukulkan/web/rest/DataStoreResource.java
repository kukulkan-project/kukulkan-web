package mx.infotec.dads.kukulkan.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.service.DataStoreService;
import mx.infotec.dads.kukulkan.web.rest.util.HeaderUtil;
import mx.infotec.dads.kukulkan.web.rest.util.PaginationUtil;

/**
 * REST controller for managing DataStore.
 */
@RestController
@RequestMapping("/api")
public class DataStoreResource {

    private final Logger log = LoggerFactory.getLogger(DataStoreResource.class);

    private static final String ENTITY_NAME = "dataStore";

    private final DataStoreService dataStoreService;

    public DataStoreResource(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }

    /**
     * POST /data-stores : Create a new dataStore.
     *
     * @param dataStore
     *            the dataStore to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     *         new dataStore, or with status 400 (Bad Request) if the dataStore
     *         has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/data-stores")
    @Timed
    public ResponseEntity<DataStore> createDataStore(@Valid @RequestBody DataStore dataStore)
            throws URISyntaxException {
        log.debug("REST request to save DataStore : {}", dataStore);
        if (dataStore.getId() != null) {
            return ResponseEntity.badRequest().headers(
                    HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dataStore cannot already have an ID"))
                    .body(null);
        }
        DataStore result = dataStoreService.save(dataStore);
        return ResponseEntity.created(new URI("/api/data-stores/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * PUT /data-stores : Updates an existing dataStore.
     *
     * @param dataStore
     *            the dataStore to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         dataStore, or with status 400 (Bad Request) if the dataStore is
     *         not valid, or with status 500 (Internal Server Error) if the
     *         dataStore couldnt be updated
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PutMapping("/data-stores")
    @Timed
    public ResponseEntity<DataStore> updateDataStore(@Valid @RequestBody DataStore dataStore)
            throws URISyntaxException {
        log.debug("REST request to update DataStore : {}", dataStore);
        if (dataStore.getId() == null) {
            return createDataStore(dataStore);
        }
        DataStore result = dataStoreService.save(dataStore);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dataStore.getId().toString())).body(result);
    }

    /**
     * GET /data-stores : get all the dataStores.
     *
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of
     *         dataStores in body
     */
    @GetMapping("/data-stores")
    @Timed
    public ResponseEntity<List<DataStore>> getAllDataStores(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DataStores");
        Page<DataStore> page = dataStoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/data-stores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /data-stores/:id : get the "id" dataStore.
     *
     * @param id
     *            the id of the dataStore to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     *         dataStore, or with status 404 (Not Found)
     */
    @GetMapping("/data-stores/{id}")
    @Timed
    public ResponseEntity<DataStore> getDataStore(@PathVariable String id) {
        log.debug("REST request to get DataStore : {}", id);
        DataStore dataStore = dataStoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataStore));
    }

    /**
     * DELETE /data-stores/:id : delete the "id" dataStore.
     *
     * @param id
     *            the id of the dataStore to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/data-stores/{id}")
    @Timed
    public ResponseEntity<Void> deleteDataStore(@PathVariable String id) {
        log.debug("REST request to delete DataStore : {}", id);
        dataStoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * POST /data-stores : Create a new dataStore.
     *
     * @param dataStore
     *            the dataStore to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     *         new dataStore, or with status 400 (Bad Request) if the dataStore
     *         has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/data-stores/test")
    @Timed
    public ResponseEntity<DataStore> testConnection(@Valid @RequestBody DataStore dataStore) throws URISyntaxException {
        log.info("REST connection DataStore : {}", dataStore);
        if (dataStoreService.testConnection(dataStore)) {
            return ResponseEntity.ok().headers(HeaderUtil.createSuccessDataStoreStatus(ENTITY_NAME, "ok"))
                    .body(dataStore);
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureDataStoreStatus(ENTITY_NAME, "failure"))
                    .body(dataStore);
        }
    }

    /**
     * POST /run-script : Execute a Script.
     *
     * @param dataStore
     *            the dataStore to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     *         new dataStore, or with status 400 (Bad Request) if the dataStore
     *         has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/data-stores/run-script")
    @Timed
    public ResponseEntity<DataStore> runScript(@Valid @RequestBody DataStore dataStore) throws URISyntaxException {
        log.info("REST run-script : {}", dataStore);
        if (dataStoreService.runScript(dataStore)) {
            return ResponseEntity.ok().headers(HeaderUtil.createSuccessDataStoreStatus(ENTITY_NAME, "ok"))
                    .body(dataStore);
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureDataStoreStatus(ENTITY_NAME, "failure"))
                    .body(dataStore);
        }
    }

    /**
     * POST /run-script : Execute a Script.
     *
     * @param dataStore
     *            the dataStore to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     *         new dataStore, or with status 400 (Bad Request) if the dataStore
     *         has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/data-stores/create-schema")
    @Timed
    public ResponseEntity<DataStore> createSchema(@Valid @RequestBody DataStore dataStore) throws URISyntaxException {
        log.info("REST run-script : {}", dataStore);
        if (dataStoreService.createSchema(dataStore)) {
            return ResponseEntity.ok().headers(HeaderUtil.createSchemaSuccessStatus(ENTITY_NAME, "ok")).body(dataStore);
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.createSchemaFailureStatus(ENTITY_NAME, "failure"))
                    .body(dataStore);
        }
    }

    /**
     * POST /run-script : Execute a Script.
     *
     * @param dataStore
     *            the dataStore to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     *         new dataStore, or with status 400 (Bad Request) if the dataStore
     *         has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/data-stores/drop-schema")
    @Timed
    public ResponseEntity<DataStore> dropSchema(@Valid @RequestBody DataStore dataStore) throws URISyntaxException {
        log.info("REST run-script : {}", dataStore);
        if (dataStoreService.dropSchema(dataStore)) {
            return ResponseEntity.ok().headers(HeaderUtil.dropSchemaSuccessStatus(ENTITY_NAME, "ok")).body(dataStore);
        } else {
            return ResponseEntity.badRequest().headers(HeaderUtil.dropSchemaFailureStatus(ENTITY_NAME, "failure"))
                    .body(dataStore);
        }
    }
}
