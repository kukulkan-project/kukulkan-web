package mx.infotec.dads.kukulkan.assets.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.infotec.dads.kukulkan.assets.domain.Discipline;
import mx.infotec.dads.kukulkan.assets.repository.DisciplineRepository;
import mx.infotec.dads.kukulkan.assets.service.DisciplineService;
import mx.infotec.dads.kukulkan.assets.service.dto.DisciplineDTO;
import mx.infotec.dads.kukulkan.assets.service.mapper.DisciplineMapper;

import static mx.infotec.dads.kukulkan.assets.service.mapper.DisciplineMapper.*;
/**
 * Service Implementation for managing Discipline.
 */
@Service
@Transactional
public class DisciplineServiceImpl implements DisciplineService{

    private final Logger log = LoggerFactory.getLogger(DisciplineServiceImpl.class);

    private final DisciplineRepository disciplineRepository;

    public DisciplineServiceImpl(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    /**
     * Save a discipline.
     *
     * @param disciplineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DisciplineDTO save(DisciplineDTO disciplineDTO) {
        log.debug("Request to save Discipline : {}", disciplineDTO);
        Discipline discipline = toEntity(disciplineDTO);
        discipline = disciplineRepository.save(discipline);
        return toDto(discipline);
    }

    /**
     *  Get all the disciplines.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DisciplineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Disciplines");
        return disciplineRepository.findAll(pageable)
            .map(DisciplineMapper::toDto);
    }

    /**
     *  Get one discipline by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DisciplineDTO findOne(String id) {
        log.debug("Request to get Discipline : {}", id);
        Discipline discipline = disciplineRepository.findOne(id);
        return toDto(discipline);
    }

    /**
     *  Delete the  discipline by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Discipline : {}", id);
        disciplineRepository.delete(id);
    }
}
