package mx.infotec.dads.kukulkan.assets.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.infotec.dads.kukulkan.assets.domain.Granularity;
import mx.infotec.dads.kukulkan.assets.repository.GranularityRepository;
import mx.infotec.dads.kukulkan.assets.service.GranularityService;
import mx.infotec.dads.kukulkan.assets.service.dto.GranularityDTO;
import mx.infotec.dads.kukulkan.assets.service.mapper.GranularityMapper;

import static mx.infotec.dads.kukulkan.assets.service.mapper.GranularityMapper.*;

/**
 * Service Implementation for managing Granularity.
 */
@Service
@Transactional
public class GranularityServiceImpl implements GranularityService {

    private final Logger log = LoggerFactory.getLogger(GranularityServiceImpl.class);

    private final GranularityRepository granularityRepository;

    public GranularityServiceImpl(GranularityRepository granularityRepository) {
        this.granularityRepository = granularityRepository;
    }

    /**
     * Save a granularity.
     *
     * @param granularityDTO
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public GranularityDTO save(GranularityDTO granularityDTO) {
        log.debug("Request to save Granularity : {}", granularityDTO);
        Granularity granularity = toEntity(granularityDTO);
        granularity = granularityRepository.save(granularity);
        return toDto(granularity);
    }

    /**
     * Get all the granularities.
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GranularityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Granularities");
        return granularityRepository.findAll(pageable).map(GranularityMapper::toDto);
    }

    /**
     * Get one granularity by id.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GranularityDTO findOne(String id) {
        log.debug("Request to get Granularity : {}", id);
        Granularity granularity = granularityRepository.findOne(id);
        return toDto(granularity);
    }

    /**
     * Delete the granularity by id.
     *
     * @param id
     *            the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Granularity : {}", id);
        granularityRepository.delete(id);
    }
}
