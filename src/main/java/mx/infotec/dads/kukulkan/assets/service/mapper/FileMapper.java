package mx.infotec.dads.kukulkan.assets.service.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.assets.domain.File;
import mx.infotec.dads.kukulkan.assets.service.dto.FileDTO;

/**
 * Mapper for the entity File and its DTO FileDTO.
 */
public class FileMapper {

    private FileMapper() {

    }

    public static File fromId(String id) {
        if (id == null) {
            return null;
        }
        File file = new File();
        file.setId(id);
        return file;
    }

    public static File toEntity(FileDTO dto) {
        File to = new File();
        to.setId(dto.getId());
        to.setFile(dto.getFile());
        to.setFileContentType(dto.getFileContentType());
        return to;
    }

    public static FileDTO toDto(File entity) {
        FileDTO to = new FileDTO();
        to.setId(entity.getId());
        to.setFile(entity.getFile());
        to.setFileContentType(entity.getFileContentType());
        return to;
    }

    public static List<File> toEntity(List<FileDTO> dtoList) {
        List<File> toList = new ArrayList<>();
        dtoList.forEach(dto -> toList.add(toEntity(dto)));
        return toList;
    }

    public static List<FileDTO> toDto(List<File> entityList) {
        List<FileDTO> toList = new ArrayList<>();
        entityList.forEach(entity -> toList.add(toDto(entity)));
        return toList;
    }
}
