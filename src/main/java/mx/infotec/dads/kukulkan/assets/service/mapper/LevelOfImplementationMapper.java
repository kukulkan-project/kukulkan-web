package mx.infotec.dads.kukulkan.assets.service.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.assets.domain.LevelOfImplementation;
import mx.infotec.dads.kukulkan.assets.service.dto.LevelOfImplementationDTO;

/**
 * Mapper for the entity LevelOfImplementation and its DTO
 * LevelOfImplementationDTO.
 */
public class LevelOfImplementationMapper {
    
    private LevelOfImplementationMapper(){
        
    }

    public static LevelOfImplementation fromId(String id) {
        if (id == null) {
            return null;
        }
        LevelOfImplementation levelOfImplementation = new LevelOfImplementation();
        levelOfImplementation.setId(id);
        return levelOfImplementation;
    }

    public static LevelOfImplementation toEntity(LevelOfImplementationDTO dto) {
        LevelOfImplementation to = new LevelOfImplementation();
        to.setBriefDescription(dto.getBriefDescription());
        to.setDescription(dto.getDescription());
        to.setName(dto.getName());
        to.setOrder(dto.getOrder());
        return to;
    }

    public static LevelOfImplementationDTO toDto(LevelOfImplementation entity) {
        LevelOfImplementationDTO to = new LevelOfImplementationDTO();
        to.setId(entity.getId());
        to.setBriefDescription(entity.getBriefDescription());
        to.setDescription(entity.getDescription());
        to.setName(entity.getName());
        to.setOrder(entity.getOrder());
        return to;
    }

    public static List<LevelOfImplementation> toEntity(List<LevelOfImplementationDTO> dtoList) {
        List<LevelOfImplementation> toList = new ArrayList<>();
        dtoList.forEach(dto -> toList.add(toEntity(dto)));
        return toList;
    }

    public static List<LevelOfImplementationDTO> toDto(List<LevelOfImplementation> entityList) {
        List<LevelOfImplementationDTO> toList = new ArrayList<>();
        entityList.forEach(entity -> toList.add(toDto(entity)));
        return toList;
    }
}
