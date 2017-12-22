package mx.infotec.dads.kukulkan.assets.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.assets.service.dto.GranularityDTO;

/**
 * Service Interface for managing Granularity.
 */
public interface GranularityService {

    /**
     * Save a granularity.
     *
     * @param granularityDTO the entity to save
     * @return the persisted entity
     */
    GranularityDTO save(GranularityDTO granularityDTO);

    /**
     *  Get all the granularities.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<GranularityDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" granularity.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    GranularityDTO findOne(String id);

    /**
     *  Delete the "id" granularity.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
