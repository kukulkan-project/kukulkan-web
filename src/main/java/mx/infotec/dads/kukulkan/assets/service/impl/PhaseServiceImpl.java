package mx.infotec.dads.kukulkan.assets.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.infotec.dads.kukulkan.assets.domain.Phase;
import mx.infotec.dads.kukulkan.assets.repository.PhaseRepository;
import mx.infotec.dads.kukulkan.assets.service.PhaseService;
import mx.infotec.dads.kukulkan.assets.service.dto.PhaseDTO;
import mx.infotec.dads.kukulkan.assets.service.mapper.PhaseMapper;

import static mx.infotec.dads.kukulkan.assets.service.mapper.PhaseMapper.*;

/**
 * Service Implementation for managing Phase.
 */
@Service
@Transactional
public class PhaseServiceImpl implements PhaseService{

    private final Logger log = LoggerFactory.getLogger(PhaseServiceImpl.class);

    private final PhaseRepository phaseRepository;

    public PhaseServiceImpl(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

    /**
     * Save a phase.
     *
     * @param phaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PhaseDTO save(PhaseDTO phaseDTO) {
        log.debug("Request to save Phase : {}", phaseDTO);
        Phase phase = toEntity(phaseDTO);
        phase = phaseRepository.save(phase);
        return toDto(phase);
    }

    /**
     *  Get all the phases.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PhaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Phases");
        return phaseRepository.findAll(pageable)
            .map(PhaseMapper::toDto);
    }

    /**
     *  Get one phase by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PhaseDTO findOne(String id) {
        log.debug("Request to get Phase : {}", id);
        Phase phase = phaseRepository.findOne(id);
        return toDto(phase);
    }

    /**
     *  Delete the  phase by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Phase : {}", id);
        phaseRepository.delete(id);
    }
}
