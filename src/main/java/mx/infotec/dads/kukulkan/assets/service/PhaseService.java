package mx.infotec.dads.kukulkan.assets.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.assets.service.dto.PhaseDTO;

/**
 * Service Interface for managing Phase.
 */
public interface PhaseService {

    /**
     * Save a phase.
     *
     * @param phaseDTO the entity to save
     * @return the persisted entity
     */
    PhaseDTO save(PhaseDTO phaseDTO);

    /**
     *  Get all the phases.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PhaseDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" phase.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PhaseDTO findOne(String id);

    /**
     *  Delete the "id" phase.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
