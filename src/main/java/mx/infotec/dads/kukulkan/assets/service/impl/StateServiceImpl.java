package mx.infotec.dads.kukulkan.assets.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.infotec.dads.kukulkan.assets.domain.State;
import mx.infotec.dads.kukulkan.assets.repository.StateRepository;
import mx.infotec.dads.kukulkan.assets.service.StateService;
import mx.infotec.dads.kukulkan.assets.service.dto.StateDTO;
import mx.infotec.dads.kukulkan.assets.service.mapper.StateMapper;
import static mx.infotec.dads.kukulkan.assets.service.mapper.StateMapper.*;

/**
 * Service Implementation for managing State.
 */
@Service
@Transactional
public class StateServiceImpl implements StateService {

    private final Logger log = LoggerFactory.getLogger(StateServiceImpl.class);

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    /**
     * Save a state.
     *
     * @param stateDTO
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public StateDTO save(StateDTO stateDTO) {
        log.debug("Request to save State : {}", stateDTO);
        State state = toEntity(stateDTO);
        state = stateRepository.save(state);
        return toDto(state);
    }

    /**
     * Get all the states.
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all States");
        return stateRepository.findAll(pageable).map(StateMapper::toDto);
    }

    /**
     * Get one state by id.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StateDTO findOne(String id) {
        log.debug("Request to get State : {}", id);
        State state = stateRepository.findOne(id);
        return toDto(state);
    }

    /**
     * Delete the state by id.
     *
     * @param id
     *            the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete State : {}", id);
        stateRepository.delete(id);
    }
}
