package mx.infotec.dads.kukulkan.assets.web.rest;

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
import mx.infotec.dads.kukulkan.assets.service.LevelOfImplementationService;
import mx.infotec.dads.kukulkan.assets.service.dto.LevelOfImplementationDTO;
import mx.infotec.dads.kukulkan.web.rest.util.HeaderUtil;
import mx.infotec.dads.kukulkan.web.rest.util.PaginationUtil;

/**
 * REST controller for managing LevelOfImplementation.
 */
@RestController
@RequestMapping("/api")
public class LevelOfImplementationResource {

    private final Logger log = LoggerFactory.getLogger(LevelOfImplementationResource.class);

    private static final String ENTITY_NAME = "levelOfImplementation";

    private final LevelOfImplementationService levelOfImplementationService;

    public LevelOfImplementationResource(LevelOfImplementationService levelOfImplementationService) {
        this.levelOfImplementationService = levelOfImplementationService;
    }

    /**
     * POST  /level-of-implementations : Create a new levelOfImplementation.
     *
     * @param levelOfImplementationDTO the levelOfImplementationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new levelOfImplementationDTO, or with status 400 (Bad Request) if the levelOfImplementation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/level-of-implementations")
    @Timed
    public ResponseEntity<LevelOfImplementationDTO> createLevelOfImplementation(@Valid @RequestBody LevelOfImplementationDTO levelOfImplementationDTO) throws URISyntaxException {
        log.debug("REST request to save LevelOfImplementation : {}", levelOfImplementationDTO);
        if (levelOfImplementationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new levelOfImplementation cannot already have an ID")).body(null);
        }
        LevelOfImplementationDTO result = levelOfImplementationService.save(levelOfImplementationDTO);
        return ResponseEntity.created(new URI("/api/level-of-implementations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /level-of-implementations : Updates an existing levelOfImplementation.
     *
     * @param levelOfImplementationDTO the levelOfImplementationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated levelOfImplementationDTO,
     * or with status 400 (Bad Request) if the levelOfImplementationDTO is not valid,
     * or with status 500 (Internal Server Error) if the levelOfImplementationDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/level-of-implementations")
    @Timed
    public ResponseEntity<LevelOfImplementationDTO> updateLevelOfImplementation(@Valid @RequestBody LevelOfImplementationDTO levelOfImplementationDTO) throws URISyntaxException {
        log.debug("REST request to update LevelOfImplementation : {}", levelOfImplementationDTO);
        if (levelOfImplementationDTO.getId() == null) {
            return createLevelOfImplementation(levelOfImplementationDTO);
        }
        LevelOfImplementationDTO result = levelOfImplementationService.save(levelOfImplementationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, levelOfImplementationDTO.getId()))
            .body(result);
    }

    /**
     * GET  /level-of-implementations : get all the levelOfImplementations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of levelOfImplementations in body
     */
    @GetMapping("/level-of-implementations")
    @Timed
    public ResponseEntity<List<LevelOfImplementationDTO>> getAllLevelOfImplementations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of LevelOfImplementations");
        Page<LevelOfImplementationDTO> page = levelOfImplementationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/level-of-implementations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /level-of-implementations/:id : get the "id" levelOfImplementation.
     *
     * @param id the id of the levelOfImplementationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the levelOfImplementationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/level-of-implementations/{id}")
    @Timed
    public ResponseEntity<LevelOfImplementationDTO> getLevelOfImplementation(@PathVariable String id) {
        log.debug("REST request to get LevelOfImplementation : {}", id);
        LevelOfImplementationDTO levelOfImplementationDTO = levelOfImplementationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(levelOfImplementationDTO));
    }

    /**
     * DELETE  /level-of-implementations/:id : delete the "id" levelOfImplementation.
     *
     * @param id the id of the levelOfImplementationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/level-of-implementations/{id}")
    @Timed
    public ResponseEntity<Void> deleteLevelOfImplementation(@PathVariable String id) {
        log.debug("REST request to delete LevelOfImplementation : {}", id);
        levelOfImplementationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
