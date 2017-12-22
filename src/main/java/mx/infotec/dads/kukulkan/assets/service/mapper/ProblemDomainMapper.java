package mx.infotec.dads.kukulkan.assets.service.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.assets.domain.ProblemDomain;
import mx.infotec.dads.kukulkan.assets.service.dto.ProblemDomainDTO;

/**
 * Mapper for the entity ProblemDomain and its DTO ProblemDomainDTO.
 */
public class ProblemDomainMapper {

    private ProblemDomainMapper() {
    }

    public static ProblemDomain fromId(String id) {
        if (id == null) {
            return null;
        }
        ProblemDomain problemDomain = new ProblemDomain();
        problemDomain.setId(id);
        return problemDomain;
    }

    public static ProblemDomain toEntity(ProblemDomainDTO dto) {
        ProblemDomain to = new ProblemDomain();
        to.setBriefDescription(dto.getBriefDescription());
        to.setDescription(dto.getDescription());
        to.setName(dto.getName());
        to.setOrder(dto.getOrder());
        return to;
    }

    public static ProblemDomainDTO toDto(ProblemDomain entity) {
        ProblemDomainDTO to = new ProblemDomainDTO();
        to.setId(entity.getId());
        to.setBriefDescription(entity.getBriefDescription());
        to.setDescription(entity.getDescription());
        to.setName(entity.getName());
        to.setOrder(entity.getOrder());
        return to;
    }

    public static List<ProblemDomain> toEntity(List<ProblemDomainDTO> dtoList) {
        List<ProblemDomain> toList = new ArrayList<>();
        dtoList.forEach(dto -> toList.add(toEntity(dto)));
        return toList;
    }

    public static List<ProblemDomainDTO> toDto(List<ProblemDomain> entityList) {
        List<ProblemDomainDTO> toList = new ArrayList<>();
        entityList.forEach(entity -> toList.add(toDto(entity)));
        return toList;
    }
}
