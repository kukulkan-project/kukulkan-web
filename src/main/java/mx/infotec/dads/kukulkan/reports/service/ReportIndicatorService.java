package mx.infotec.dads.kukulkan.reports.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.reports.domain.ReportIndicator;

/**
 * Service Interface for managing ReportIndicator.
 */
public interface ReportIndicatorService {

    /**
     * Save a reportIndicator.
     *
     * @param reportIndicator the entity to save
     * @return the persisted entity
     */
    ReportIndicator save(ReportIndicator reportIndicator);

    /**
     *  Get all the reportIndicators.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ReportIndicator> findAll(Pageable pageable);

    /**
     *  Get the "id" reportIndicator.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ReportIndicator findOne(String id);

    /**
     *  Delete the "id" reportIndicator.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
