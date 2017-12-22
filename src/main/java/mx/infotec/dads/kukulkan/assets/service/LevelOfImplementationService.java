package mx.infotec.dads.kukulkan.assets.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.assets.service.dto.LevelOfImplementationDTO;

/**
 * Service Interface for managing LevelOfImplementation.
 */
public interface LevelOfImplementationService {

    /**
     * Save a levelOfImplementation.
     *
     * @param levelOfImplementationDTO the entity to save
     * @return the persisted entity
     */
    LevelOfImplementationDTO save(LevelOfImplementationDTO levelOfImplementationDTO);

    /**
     *  Get all the levelOfImplementations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<LevelOfImplementationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" levelOfImplementation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    LevelOfImplementationDTO findOne(String id);

    /**
     *  Delete the "id" levelOfImplementation.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
