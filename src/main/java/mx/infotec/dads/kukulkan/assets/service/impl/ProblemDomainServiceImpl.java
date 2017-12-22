package mx.infotec.dads.kukulkan.assets.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.infotec.dads.kukulkan.assets.domain.ProblemDomain;
import mx.infotec.dads.kukulkan.assets.repository.ProblemDomainRepository;
import mx.infotec.dads.kukulkan.assets.service.ProblemDomainService;
import mx.infotec.dads.kukulkan.assets.service.dto.ProblemDomainDTO;
import mx.infotec.dads.kukulkan.assets.service.mapper.ProblemDomainMapper;

import static mx.infotec.dads.kukulkan.assets.service.mapper.ProblemDomainMapper.*;

/**
 * Service Implementation for managing ProblemDomain.
 */
@Service
@Transactional
public class ProblemDomainServiceImpl implements ProblemDomainService{

    private final Logger log = LoggerFactory.getLogger(ProblemDomainServiceImpl.class);

    private final ProblemDomainRepository problemDomainRepository;

    public ProblemDomainServiceImpl(ProblemDomainRepository problemDomainRepository) {
        this.problemDomainRepository = problemDomainRepository;
    }

    /**
     * Save a problemDomain.
     *
     * @param problemDomainDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProblemDomainDTO save(ProblemDomainDTO problemDomainDTO) {
        log.debug("Request to save ProblemDomain : {}", problemDomainDTO);
        ProblemDomain problemDomain = toEntity(problemDomainDTO);
        problemDomain = problemDomainRepository.save(problemDomain);
        return toDto(problemDomain);
    }

    /**
     *  Get all the problemDomains.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProblemDomainDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProblemDomains");
        return problemDomainRepository.findAll(pageable)
            .map(ProblemDomainMapper::toDto);
    }

    /**
     *  Get one problemDomain by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProblemDomainDTO findOne(String id) {
        log.debug("Request to get ProblemDomain : {}", id);
        ProblemDomain problemDomain = problemDomainRepository.findOne(id);
        return toDto(problemDomain);
    }

    /**
     *  Delete the  problemDomain by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ProblemDomain : {}", id);
        problemDomainRepository.delete(id);
    }
}
