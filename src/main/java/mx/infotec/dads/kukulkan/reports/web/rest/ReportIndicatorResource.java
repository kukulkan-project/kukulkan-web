package mx.infotec.dads.kukulkan.reports.web.rest;

import com.codahale.metrics.annotation.Timed;

import mx.infotec.dads.kukulkan.reports.domain.ReportIndicator;
import mx.infotec.dads.kukulkan.reports.service.ReportIndicatorService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ReportIndicator.
 */
@RestController
@RequestMapping("/api")
public class ReportIndicatorResource {

    private final Logger log = LoggerFactory.getLogger(ReportIndicatorResource.class);

    private static final String ENTITY_NAME = "reportIndicator";

    private final ReportIndicatorService reportIndicatorService;

    public ReportIndicatorResource(ReportIndicatorService reportIndicatorService) {
        this.reportIndicatorService = reportIndicatorService;
    }

    /**
     * POST  /report-indicators : Create a new reportIndicator.
     *
     * @param reportIndicator the reportIndicator to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reportIndicator, or with status 400 (Bad Request) if the reportIndicator has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/report-indicators")
    @Timed
    public ResponseEntity<ReportIndicator> createReportIndicator(@Valid @RequestBody ReportIndicator reportIndicator) throws URISyntaxException {
        log.debug("REST request to save ReportIndicator : {}", reportIndicator);
        if (reportIndicator.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new reportIndicator cannot already have an ID")).body(null);
        }
        ReportIndicator result = reportIndicatorService.save(reportIndicator);
        return ResponseEntity.created(new URI("/api/report-indicators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /report-indicators : Updates an existing reportIndicator.
     *
     * @param reportIndicator the reportIndicator to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reportIndicator,
     * or with status 400 (Bad Request) if the reportIndicator is not valid,
     * or with status 500 (Internal Server Error) if the reportIndicator couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/report-indicators")
    @Timed
    public ResponseEntity<ReportIndicator> updateReportIndicator(@Valid @RequestBody ReportIndicator reportIndicator) throws URISyntaxException {
        log.debug("REST request to update ReportIndicator : {}", reportIndicator);
        if (reportIndicator.getId() == null) {
            return createReportIndicator(reportIndicator);
        }
        ReportIndicator result = reportIndicatorService.save(reportIndicator);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reportIndicator.getId().toString()))
            .body(result);
    }

    /**
     * GET  /report-indicators : get all the reportIndicators.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of reportIndicators in body
     */
    @GetMapping("/report-indicators")
    @Timed
    public ResponseEntity<List<ReportIndicator>> getAllReportIndicators(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ReportIndicators");
        Page<ReportIndicator> page = reportIndicatorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/report-indicators");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /report-indicators/:id : get the "id" reportIndicator.
     *
     * @param id the id of the reportIndicator to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reportIndicator, or with status 404 (Not Found)
     */
    @GetMapping("/report-indicators/{id}")
    @Timed
    public ResponseEntity<ReportIndicator> getReportIndicator(@PathVariable String id) {
        log.debug("REST request to get ReportIndicator : {}", id);
        ReportIndicator reportIndicator = reportIndicatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(reportIndicator));
    }

    /**
     * DELETE  /report-indicators/:id : delete the "id" reportIndicator.
     *
     * @param id the id of the reportIndicator to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/report-indicators/{id}")
    @Timed
    public ResponseEntity<Void> deleteReportIndicator(@PathVariable String id) {
        log.debug("REST request to delete ReportIndicator : {}", id);
        reportIndicatorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
