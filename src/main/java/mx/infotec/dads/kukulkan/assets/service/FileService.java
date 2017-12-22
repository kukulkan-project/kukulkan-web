package mx.infotec.dads.kukulkan.assets.service;

import java.util.List;

import mx.infotec.dads.kukulkan.assets.service.dto.FileDTO;

/**
 * Service Interface for managing File.
 */
public interface FileService {

    /**
     * Save a file.
     *
     * @param fileDTO
     *            the entity to save
     * @return the persisted entity
     */
    FileDTO save(FileDTO fileDTO);

    /**
     * Get all the files.
     *
     * @return the list of entities
     */
    List<FileDTO> findAll();

    /**
     * Get all the FileDTO where Asset is null.
     *
     * @return the list of entities
     */
    List<FileDTO> findAllWhereAssetIsNull();

    /**
     * Get the "id" file.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    FileDTO findOne(String id);

    /**
     * Delete the "id" file.
     *
     * @param id
     *            the id of the entity
     */
    void delete(String id);
}
