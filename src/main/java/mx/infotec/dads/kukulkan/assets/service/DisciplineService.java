package mx.infotec.dads.kukulkan.assets.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.kukulkan.assets.service.dto.DisciplineDTO;

/**
 * Service Interface for managing Discipline.
 */
public interface DisciplineService {

    /**
     * Save a discipline.
     *
     * @param disciplineDTO the entity to save
     * @return the persisted entity
     */
    DisciplineDTO save(DisciplineDTO disciplineDTO);

    /**
     *  Get all the disciplines.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DisciplineDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" discipline.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DisciplineDTO findOne(String id);

    /**
     *  Delete the "id" discipline.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
