package mx.infotec.dads.kukulkan.assets.service.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.assets.domain.Granularity;
import mx.infotec.dads.kukulkan.assets.service.dto.GranularityDTO;

/**
 * Mapper for the entity Granularity and its DTO GranularityDTO.
 */
public class GranularityMapper {

    private GranularityMapper(){    
    }
    
    public static Granularity fromId(String id) {
        if (id == null) {
            return null;
        }
        Granularity granularity = new Granularity();
        granularity.setId(id);
        return granularity;
    }

    public static Granularity toEntity(GranularityDTO dto) {
        Granularity to = new Granularity();
        to.setBriefDescription(dto.getBriefDescription());
        to.setDescription(dto.getDescription());
        to.setName(dto.getName());
        to.setOrder(dto.getOrder());
        return to;
    }

    public static GranularityDTO toDto(Granularity entity) {
        GranularityDTO to = new GranularityDTO();
        to.setId(entity.getId());
        to.setBriefDescription(entity.getBriefDescription());
        to.setDescription(entity.getDescription());
        to.setName(entity.getName());
        to.setOrder(entity.getOrder());
        return to;
    }

    public static List<Granularity> toEntity(List<GranularityDTO> dtoList) {
        List<Granularity> toList = new ArrayList<>();
        dtoList.forEach(dto -> toList.add(toEntity(dto)));
        return toList;
    }

    public static List<GranularityDTO> toDto(List<Granularity> entityList) {
        List<GranularityDTO> toList = new ArrayList<>();
        entityList.forEach(entity -> toList.add(toDto(entity)));
        return toList;
    }
}
