package mx.infotec.dads.kukulkan.reports.service.impl;

import mx.infotec.dads.kukulkan.reports.domain.ReportElement;
import mx.infotec.dads.kukulkan.reports.repository.ReportElementRepository;
import mx.infotec.dads.kukulkan.reports.service.ReportElementService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing ReportElement.
 */
@Service
public class ReportElementServiceImpl implements ReportElementService{

    private final Logger log = LoggerFactory.getLogger(ReportElementServiceImpl.class);

    private final ReportElementRepository reportElementRepository;

    public ReportElementServiceImpl(ReportElementRepository reportElementRepository) {
        this.reportElementRepository = reportElementRepository;
    }

    /**
     * Save a reportElement.
     *
     * @param reportElement the entity to save
     * @return the persisted entity
     */
    @Override
    public ReportElement save(ReportElement reportElement) {
        log.debug("Request to save ReportElement : {}", reportElement);
        return reportElementRepository.save(reportElement);
    }

    /**
     *  Get all the reportElements.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<ReportElement> findAll(Pageable pageable) {
        log.debug("Request to get all ReportElements");
        return reportElementRepository.findAll(pageable);
    }

    /**
     *  Get one reportElement by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ReportElement findOne(String id) {
        log.debug("Request to get ReportElement : {}", id);
        return reportElementRepository.findOne(id);
    }

    /**
     *  Delete the  reportElement by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ReportElement : {}", id);
        reportElementRepository.delete(id);
    }
}
