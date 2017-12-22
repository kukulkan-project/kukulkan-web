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
import mx.infotec.dads.kukulkan.assets.service.PhaseService;
import mx.infotec.dads.kukulkan.assets.service.dto.PhaseDTO;
import mx.infotec.dads.kukulkan.web.rest.util.HeaderUtil;
import mx.infotec.dads.kukulkan.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Phase.
 */
@RestController
@RequestMapping("/api")
public class PhaseResource {

    private final Logger log = LoggerFactory.getLogger(PhaseResource.class);

    private static final String ENTITY_NAME = "phase";

    private final PhaseService phaseService;

    public PhaseResource(PhaseService phaseService) {
        this.phaseService = phaseService;
    }

    /**
     * POST  /phases : Create a new phase.
     *
     * @param phaseDTO the phaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new phaseDTO, or with status 400 (Bad Request) if the phase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/phases")
    @Timed
    public ResponseEntity<PhaseDTO> createPhase(@Valid @RequestBody PhaseDTO phaseDTO) throws URISyntaxException {
        log.debug("REST request to save Phase : {}", phaseDTO);
        if (phaseDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new phase cannot already have an ID")).body(null);
        }
        PhaseDTO result = phaseService.save(phaseDTO);
        return ResponseEntity.created(new URI("/api/phases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /phases : Updates an existing phase.
     *
     * @param phaseDTO the phaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated phaseDTO,
     * or with status 400 (Bad Request) if the phaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the phaseDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/phases")
    @Timed
    public ResponseEntity<PhaseDTO> updatePhase(@Valid @RequestBody PhaseDTO phaseDTO) throws URISyntaxException {
        log.debug("REST request to update Phase : {}", phaseDTO);
        if (phaseDTO.getId() == null) {
            return createPhase(phaseDTO);
        }
        PhaseDTO result = phaseService.save(phaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, phaseDTO.getId()))
            .body(result);
    }

    /**
     * GET  /phases : get all the phases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of phases in body
     */
    @GetMapping("/phases")
    @Timed
    public ResponseEntity<List<PhaseDTO>> getAllPhases(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Phases");
        Page<PhaseDTO> page = phaseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/phases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /phases/:id : get the "id" phase.
     *
     * @param id the id of the phaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the phaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/phases/{id}")
    @Timed
    public ResponseEntity<PhaseDTO> getPhase(@PathVariable String id) {
        log.debug("REST request to get Phase : {}", id);
        PhaseDTO phaseDTO = phaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(phaseDTO));
    }

    /**
     * DELETE  /phases/:id : delete the "id" phase.
     *
     * @param id the id of the phaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/phases/{id}")
    @Timed
    public ResponseEntity<Void> deletePhase(@PathVariable String id) {
        log.debug("REST request to delete Phase : {}", id);
        phaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
