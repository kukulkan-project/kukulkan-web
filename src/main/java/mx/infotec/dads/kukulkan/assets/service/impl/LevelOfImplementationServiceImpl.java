package mx.infotec.dads.kukulkan.assets.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.infotec.dads.kukulkan.assets.domain.LevelOfImplementation;
import mx.infotec.dads.kukulkan.assets.repository.LevelOfImplementationRepository;
import mx.infotec.dads.kukulkan.assets.service.LevelOfImplementationService;
import mx.infotec.dads.kukulkan.assets.service.dto.LevelOfImplementationDTO;
import mx.infotec.dads.kukulkan.assets.service.mapper.LevelOfImplementationMapper;

import static mx.infotec.dads.kukulkan.assets.service.mapper.LevelOfImplementationMapper.*;
/**
 * Service Implementation for managing LevelOfImplementation.
 */
@Service
@Transactional
public class LevelOfImplementationServiceImpl implements LevelOfImplementationService{

    private final Logger log = LoggerFactory.getLogger(LevelOfImplementationServiceImpl.class);

    private final LevelOfImplementationRepository levelOfImplementationRepository;

    public LevelOfImplementationServiceImpl(LevelOfImplementationRepository levelOfImplementationRepository) {
        this.levelOfImplementationRepository = levelOfImplementationRepository;
    }

    /**
     * Save a levelOfImplementation.
     *
     * @param levelOfImplementationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LevelOfImplementationDTO save(LevelOfImplementationDTO levelOfImplementationDTO) {
        log.debug("Request to save LevelOfImplementation : {}", levelOfImplementationDTO);
        LevelOfImplementation levelOfImplementation = toEntity(levelOfImplementationDTO);
        levelOfImplementation = levelOfImplementationRepository.save(levelOfImplementation);
        return toDto(levelOfImplementation);
    }

    /**
     *  Get all the levelOfImplementations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LevelOfImplementationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelOfImplementations");
        return levelOfImplementationRepository.findAll(pageable)
            .map(LevelOfImplementationMapper::toDto);
    }

    /**
     *  Get one levelOfImplementation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public LevelOfImplementationDTO findOne(String id) {
        log.debug("Request to get LevelOfImplementation : {}", id);
        LevelOfImplementation levelOfImplementation = levelOfImplementationRepository.findOne(id);
        return toDto(levelOfImplementation);
    }

    /**
     *  Delete the  levelOfImplementation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete LevelOfImplementation : {}", id);
        levelOfImplementationRepository.delete(id);
    }
}
