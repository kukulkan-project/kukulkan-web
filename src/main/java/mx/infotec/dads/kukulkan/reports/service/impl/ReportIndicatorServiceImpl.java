package mx.infotec.dads.kukulkan.reports.service.impl;

import mx.infotec.dads.kukulkan.reports.domain.ReportIndicator;
import mx.infotec.dads.kukulkan.reports.repository.ReportIndicatorRepository;
import mx.infotec.dads.kukulkan.reports.service.ReportIndicatorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing ReportIndicator.
 */
@Service
public class ReportIndicatorServiceImpl implements ReportIndicatorService{

    private final Logger log = LoggerFactory.getLogger(ReportIndicatorServiceImpl.class);

    private final ReportIndicatorRepository reportIndicatorRepository;

    public ReportIndicatorServiceImpl(ReportIndicatorRepository reportIndicatorRepository) {
        this.reportIndicatorRepository = reportIndicatorRepository;
    }

    /**
     * Save a reportIndicator.
     *
     * @param reportIndicator the entity to save
     * @return the persisted entity
     */
    @Override
    public ReportIndicator save(ReportIndicator reportIndicator) {
        log.debug("Request to save ReportIndicator : {}", reportIndicator);
        return reportIndicatorRepository.save(reportIndicator);
    }

    /**
     *  Get all the reportIndicators.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<ReportIndicator> findAll(Pageable pageable) {
        log.debug("Request to get all ReportIndicators");
        return reportIndicatorRepository.findAll(pageable);
    }

    /**
     *  Get one reportIndicator by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ReportIndicator findOne(String id) {
        log.debug("Request to get ReportIndicator : {}", id);
        return reportIndicatorRepository.findOne(id);
    }

    /**
     *  Delete the  reportIndicator by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ReportIndicator : {}", id);
        reportIndicatorRepository.delete(id);
    }
}
