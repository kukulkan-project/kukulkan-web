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
import mx.infotec.dads.kukulkan.assets.service.GranularityService;
import mx.infotec.dads.kukulkan.assets.service.dto.GranularityDTO;
import mx.infotec.dads.kukulkan.web.rest.util.HeaderUtil;
import mx.infotec.dads.kukulkan.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Granularity.
 */
@RestController
@RequestMapping("/api")
public class GranularityResource {

    private final Logger log = LoggerFactory.getLogger(GranularityResource.class);

    private static final String ENTITY_NAME = "granularity";

    private final GranularityService granularityService;

    public GranularityResource(GranularityService granularityService) {
        this.granularityService = granularityService;
    }

    /**
     * POST  /granularities : Create a new granularity.
     *
     * @param granularityDTO the granularityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new granularityDTO, or with status 400 (Bad Request) if the granularity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/granularities")
    @Timed
    public ResponseEntity<GranularityDTO> createGranularity(@Valid @RequestBody GranularityDTO granularityDTO) throws URISyntaxException {
        log.debug("REST request to save Granularity : {}", granularityDTO);
        if (granularityDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new granularity cannot already have an ID")).body(null);
        }
        GranularityDTO result = granularityService.save(granularityDTO);
        return ResponseEntity.created(new URI("/api/granularities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /granularities : Updates an existing granularity.
     *
     * @param granularityDTO the granularityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated granularityDTO,
     * or with status 400 (Bad Request) if the granularityDTO is not valid,
     * or with status 500 (Internal Server Error) if the granularityDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/granularities")
    @Timed
    public ResponseEntity<GranularityDTO> updateGranularity(@Valid @RequestBody GranularityDTO granularityDTO) throws URISyntaxException {
        log.debug("REST request to update Granularity : {}", granularityDTO);
        if (granularityDTO.getId() == null) {
            return createGranularity(granularityDTO);
        }
        GranularityDTO result = granularityService.save(granularityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, granularityDTO.getId()))
            .body(result);
    }

    /**
     * GET  /granularities : get all the granularities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of granularities in body
     */
    @GetMapping("/granularities")
    @Timed
    public ResponseEntity<List<GranularityDTO>> getAllGranularities(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Granularities");
        Page<GranularityDTO> page = granularityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/granularities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /granularities/:id : get the "id" granularity.
     *
     * @param id the id of the granularityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the granularityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/granularities/{id}")
    @Timed
    public ResponseEntity<GranularityDTO> getGranularity(@PathVariable String id) {
        log.debug("REST request to get Granularity : {}", id);
        GranularityDTO granularityDTO = granularityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(granularityDTO));
    }

    /**
     * DELETE  /granularities/:id : delete the "id" granularity.
     *
     * @param id the id of the granularityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/granularities/{id}")
    @Timed
    public ResponseEntity<Void> deleteGranularity(@PathVariable String id) {
        log.debug("REST request to delete Granularity : {}", id);
        granularityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
