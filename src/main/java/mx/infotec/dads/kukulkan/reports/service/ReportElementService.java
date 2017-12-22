package mx.infotec.dads.kukulkan.reports.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.reports.domain.ReportElement;

/**
 * Service Interface for managing ReportElement.
 */
public interface ReportElementService {

    /**
     * Save a reportElement.
     *
     * @param reportElement the entity to save
     * @return the persisted entity
     */
    ReportElement save(ReportElement reportElement);

    /**
     *  Get all the reportElements.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ReportElement> findAll(Pageable pageable);

    /**
     *  Get the "id" reportElement.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ReportElement findOne(String id);

    /**
     *  Delete the "id" reportElement.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
