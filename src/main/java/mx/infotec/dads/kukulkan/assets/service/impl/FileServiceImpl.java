package mx.infotec.dads.kukulkan.assets.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
    
import mx.infotec.dads.kukulkan.assets.domain.File;
import mx.infotec.dads.kukulkan.assets.repository.FileRepository;
import mx.infotec.dads.kukulkan.assets.service.FileService;
import mx.infotec.dads.kukulkan.assets.service.dto.FileDTO;
import mx.infotec.dads.kukulkan.assets.service.mapper.FileMapper;

/**
 * Service Implementation for managing File.
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

    private final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Save a file.
     *
     * @param fileDTO
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public FileDTO save(FileDTO fileDTO) {
        log.debug("Request to save File : {}", fileDTO);
        File file = FileMapper.toEntity(fileDTO);
        file = fileRepository.save(file);
        return FileMapper.toDto(file);
    }

    /**
     * Get all the files.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FileDTO> findAll() {
        log.debug("Request to get all Files");
        return fileRepository.findAll().stream().map(FileMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * get all the files where Asset is null.
     * 
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FileDTO> findAllWhereAssetIsNull() {
        log.debug("Request to get all files where Asset is null");
        return StreamSupport.stream(fileRepository.findAll().spliterator(), false)
                .filter(file -> file.getAsset() == null).map(FileMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one file by id.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FileDTO findOne(String id) {
        log.debug("Request to get File : {}", id);
        File file = fileRepository.findOne(id);
        return FileMapper.toDto(file);
    }

    /**
     * Delete the file by id.
     *
     * @param id
     *            the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete File : {}", id);
        fileRepository.delete(id);
    }
}
