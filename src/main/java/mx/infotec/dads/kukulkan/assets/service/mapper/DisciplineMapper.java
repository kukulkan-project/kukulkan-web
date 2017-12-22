package mx.infotec.dads.kukulkan.assets.service.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.assets.domain.Discipline;
import mx.infotec.dads.kukulkan.assets.service.dto.DisciplineDTO;

/**
 * Mapper for the entity Discipline and its DTO DisciplineDTO.
 */
public class DisciplineMapper {

    private DisciplineMapper() {
    }
    
    public static Discipline fromId(String id) {
        if (id == null) {
            return null;
        }
        Discipline discipline = new Discipline();
        discipline.setId(id);
        return discipline;
    }

    public static Discipline toEntity(DisciplineDTO dto) {
        Discipline to = new Discipline();
        to.setBriefDescription(dto.getBriefDescription());
        to.setDescription(dto.getDescription());
        to.setName(dto.getName());
        to.setOrder(dto.getOrder());
        return to;
    }

    public static DisciplineDTO toDto(Discipline entity) {
        DisciplineDTO to = new DisciplineDTO();
        to.setId(entity.getId());
        to.setBriefDescription(entity.getBriefDescription());
        to.setDescription(entity.getDescription());
        to.setName(entity.getName());
        to.setOrder(entity.getOrder());
        return to;
    }

    public static List<Discipline> toEntity(List<DisciplineDTO> dtoList) {
        List<Discipline> toList = new ArrayList<>();
        dtoList.forEach(dto -> toList.add(toEntity(dto)));
        return toList;
    }

    public static List<DisciplineDTO> toDto(List<Discipline> entityList) {
        List<DisciplineDTO> toList = new ArrayList<>();
        entityList.forEach(entity -> toList.add(toDto(entity)));
        return toList;
    }
}
