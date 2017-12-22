package mx.infotec.dads.kukulkan.reports.web.rest;

import com.codahale.metrics.annotation.Timed;

import mx.infotec.dads.kukulkan.reports.domain.ReportElement;
import mx.infotec.dads.kukulkan.reports.service.ReportElementService;
import mx.infotec.dads.kukulkan.web.rest.util.HeaderUtil;
import mx.infotec.dads.kukulkan.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ReportElement.
 */
@RestController
@RequestMapping("/api")
public class ReportElementResource {

    private final Logger log = LoggerFactory.getLogger(ReportElementResource.class);

    private static final String ENTITY_NAME = "reportElement";

    private final ReportElementService reportElementService;

    public ReportElementResource(ReportElementService reportElementService) {
        this.reportElementService = reportElementService;
    }

    /**
     * POST  /report-elements : Create a new reportElement.
     *
     * @param reportElement the reportElement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reportElement, or with status 400 (Bad Request) if the reportElement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/report-elements")
    @Timed
    public ResponseEntity<ReportElement> createReportElement(@RequestBody ReportElement reportElement) throws URISyntaxException {
        log.debug("REST request to save ReportElement : {}", reportElement);
        if (reportElement.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new reportElement cannot already have an ID")).body(null);
        }
        ReportElement result = reportElementService.save(reportElement);
        return ResponseEntity.created(new URI("/api/report-elements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /report-elements : Updates an existing reportElement.
     *
     * @param reportElement the reportElement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reportElement,
     * or with status 400 (Bad Request) if the reportElement is not valid,
     * or with status 500 (Internal Server Error) if the reportElement couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/report-elements")
    @Timed
    public ResponseEntity<ReportElement> updateReportElement(@RequestBody ReportElement reportElement) throws URISyntaxException {
        log.debug("REST request to update ReportElement : {}", reportElement);
        if (reportElement.getId() == null) {
            return createReportElement(reportElement);
        }
        ReportElement result = reportElementService.save(reportElement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reportElement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /report-elements : get all the reportElements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of reportElements in body
     */
    @GetMapping("/report-elements")
    @Timed
    public ResponseEntity<List<ReportElement>> getAllReportElements(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ReportElements");
        Page<ReportElement> page = reportElementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/report-elements");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /report-elements/:id : get the "id" reportElement.
     *
     * @param id the id of the reportElement to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reportElement, or with status 404 (Not Found)
     */
    @GetMapping("/report-elements/{id}")
    @Timed
    public ResponseEntity<ReportElement> getReportElement(@PathVariable String id) {
        log.debug("REST request to get ReportElement : {}", id);
        ReportElement reportElement = reportElementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(reportElement));
    }

    /**
     * DELETE  /report-elements/:id : delete the "id" reportElement.
     *
     * @param id the id of the reportElement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/report-elements/{id}")
    @Timed
    public ResponseEntity<Void> deleteReportElement(@PathVariable String id) {
        log.debug("REST request to delete ReportElement : {}", id);
        reportElementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
