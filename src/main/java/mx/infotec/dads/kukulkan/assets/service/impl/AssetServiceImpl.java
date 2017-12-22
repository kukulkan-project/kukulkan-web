package mx.infotec.dads.kukulkan.assets.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.infotec.dads.kukulkan.assets.domain.Asset;
import mx.infotec.dads.kukulkan.assets.repository.AssetRepository;
import mx.infotec.dads.kukulkan.assets.repository.FileRepository;
import mx.infotec.dads.kukulkan.assets.service.AssetService;
import mx.infotec.dads.kukulkan.assets.service.dto.AssetDTO;
import mx.infotec.dads.kukulkan.assets.service.mapper.AssetMapper;

import static mx.infotec.dads.kukulkan.assets.service.mapper.AssetMapper.*;

/**
 * Service Implementation for managing Asset.
 */
@Service
@Transactional
public class AssetServiceImpl implements AssetService {

    private final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);

    private final AssetRepository assetRepository;
    
    @Autowired
    private FileRepository fileRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    /**
     * Save a asset.
     *
     * @param assetDTO
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public AssetDTO save(AssetDTO assetDTO) {
        log.debug("Request to save Asset : {}", assetDTO);
        Asset asset = toEntity(assetDTO);
        fileRepository.save(asset.getFile());
        asset = assetRepository.save(asset);
        return toDto(asset);
    }

    /**
     * Get all the assets.
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AssetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Assets");
        return assetRepository.findAll(pageable).map(AssetMapper::toDto);
    }

    /**
     * Get one asset by id.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AssetDTO findOne(String id) {
        log.debug("Request to get Asset : {}", id);
        Asset asset = assetRepository.findOne(id);
        return toDto(asset);
    }

    /**
     * Delete the asset by id.
     *
     * @param id
     *            the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Asset : {}", id);
        assetRepository.delete(id);
    }
}
