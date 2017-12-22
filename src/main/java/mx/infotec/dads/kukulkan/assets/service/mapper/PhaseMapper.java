package mx.infotec.dads.kukulkan.assets.service.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.assets.domain.Phase;
import mx.infotec.dads.kukulkan.assets.service.dto.PhaseDTO;

/**
 * Mapper for the entity Phase and its DTO PhaseDTO.
 */
public class PhaseMapper {

    private PhaseMapper(){
        
    }
    
    public static Phase fromId(String id) {
        if (id == null) {
            return null;
        }
        Phase phase = new Phase();
        phase.setId(id);
        return phase;
    }

    public static Phase toEntity(PhaseDTO dto) {
        Phase to = new Phase();
        to.setBriefDescription(dto.getBriefDescription());
        to.setDescription(dto.getDescription());
        to.setName(dto.getName());
        to.setOrder(dto.getOrder());
        return to;
    }

    public static PhaseDTO toDto(Phase entity) {
        PhaseDTO to = new PhaseDTO();
        to.setId(entity.getId());
        to.setBriefDescription(entity.getBriefDescription());
        to.setDescription(entity.getDescription());
        to.setName(entity.getName());
        to.setOrder(entity.getOrder());
        return to;
    }

    public static List<Phase> toEntity(List<PhaseDTO> dtoList) {
        List<Phase> toList = new ArrayList<>();
        dtoList.forEach(dto -> toList.add(toEntity(dto)));
        return toList;
    }

    public static List<PhaseDTO> toDto(List<Phase> entityList) {
        List<PhaseDTO> toList = new ArrayList<>();
        entityList.forEach(entity -> toList.add(toDto(entity)));
        return toList;
    }
}
