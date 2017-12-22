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
import mx.infotec.dads.kukulkan.assets.service.ProblemDomainService;
import mx.infotec.dads.kukulkan.assets.service.dto.ProblemDomainDTO;
import mx.infotec.dads.kukulkan.web.rest.util.HeaderUtil;
import mx.infotec.dads.kukulkan.web.rest.util.PaginationUtil;

/**
 * REST controller for managing ProblemDomain.
 */
@RestController
@RequestMapping("/api")
public class ProblemDomainResource {

    private final Logger log = LoggerFactory.getLogger(ProblemDomainResource.class);

    private static final String ENTITY_NAME = "problemDomain";

    private final ProblemDomainService problemDomainService;

    public ProblemDomainResource(ProblemDomainService problemDomainService) {
        this.problemDomainService = problemDomainService;
    }

    /**
     * POST  /problem-domains : Create a new problemDomain.
     *
     * @param problemDomainDTO the problemDomainDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new problemDomainDTO, or with status 400 (Bad Request) if the problemDomain has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/problem-domains")
    @Timed
    public ResponseEntity<ProblemDomainDTO> createProblemDomain(@Valid @RequestBody ProblemDomainDTO problemDomainDTO) throws URISyntaxException {
        log.debug("REST request to save ProblemDomain : {}", problemDomainDTO);
        if (problemDomainDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new problemDomain cannot already have an ID")).body(null);
        }
        ProblemDomainDTO result = problemDomainService.save(problemDomainDTO);
        return ResponseEntity.created(new URI("/api/problem-domains/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * PUT  /problem-domains : Updates an existing problemDomain.
     *
     * @param problemDomainDTO the problemDomainDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated problemDomainDTO,
     * or with status 400 (Bad Request) if the problemDomainDTO is not valid,
     * or with status 500 (Internal Server Error) if the problemDomainDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/problem-domains")
    @Timed
    public ResponseEntity<ProblemDomainDTO> updateProblemDomain(@Valid @RequestBody ProblemDomainDTO problemDomainDTO) throws URISyntaxException {
        log.debug("REST request to update ProblemDomain : {}", problemDomainDTO);
        if (problemDomainDTO.getId() == null) {
            return createProblemDomain(problemDomainDTO);
        }
        ProblemDomainDTO result = problemDomainService.save(problemDomainDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, problemDomainDTO.getId()))
            .body(result);
    }

    /**
     * GET  /problem-domains : get all the problemDomains.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of problemDomains in body
     */
    @GetMapping("/problem-domains")
    @Timed
    public ResponseEntity<List<ProblemDomainDTO>> getAllProblemDomains(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ProblemDomains");
        Page<ProblemDomainDTO> page = problemDomainService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/problem-domains");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /problem-domains/:id : get the "id" problemDomain.
     *
     * @param id the id of the problemDomainDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the problemDomainDTO, or with status 404 (Not Found)
     */
    @GetMapping("/problem-domains/{id}")
    @Timed
    public ResponseEntity<ProblemDomainDTO> getProblemDomain(@PathVariable String id) {
        log.debug("REST request to get ProblemDomain : {}", id);
        ProblemDomainDTO problemDomainDTO = problemDomainService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(problemDomainDTO));
    }

    /**
     * DELETE  /problem-domains/:id : delete the "id" problemDomain.
     *
     * @param id the id of the problemDomainDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/problem-domains/{id}")
    @Timed
    public ResponseEntity<Void> deleteProblemDomain(@PathVariable String id) {
        log.debug("REST request to delete ProblemDomain : {}", id);
        problemDomainService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
