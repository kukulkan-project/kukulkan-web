package mx.infotec.dads.kukulkan.assets.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.assets.service.dto.ProblemDomainDTO;

/**
 * Service Interface for managing ProblemDomain.
 */
public interface ProblemDomainService {

    /**
     * Save a problemDomain.
     *
     * @param problemDomainDTO the entity to save
     * @return the persisted entity
     */
    ProblemDomainDTO save(ProblemDomainDTO problemDomainDTO);

    /**
     *  Get all the problemDomains.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ProblemDomainDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" problemDomain.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProblemDomainDTO findOne(String id);

    /**
     *  Delete the "id" problemDomain.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
